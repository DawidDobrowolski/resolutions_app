package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.UserResolutionReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {



    private UserResolutionRepository userResolutionRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public ReportService(UserResolutionRepository userResolutionRepository, ActivityRepository activityRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.activityRepository = activityRepository;
    }

    public List<UserResolutionReport> getUserResolutionReportData(Date from, Date to, HttpServletRequest request) {

        HttpSession session = request.getSession();

        List<UserResolution> userResolutions = userResolutionRepository.customUsetFromTo((String) session.getAttribute("email"), to, from);
        List<UserResolutionReport> userResolutionReports = new ArrayList<>();
        int unitsSum = 0;

        for (UserResolution userResolution : userResolutions) {
            UserResolutionReport userResolutionReport = new UserResolutionReport();
            userResolutionReport.setName(userResolution.getName());
            userResolutionReport.setResolutionType(userResolution.getResolution().getName());
            userResolutionReport.setResolutionUnit(userResolution.getResolution().getUnit());
            if (userResolution.isActive() && (from.after(userResolution.getStartDate()) || from.equals(userResolution.getStartDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (userResolution.isActive() && (from.before(userResolution.getStartDate()) || from.equals(userResolution.getStartDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (!userResolution.isActive() && (from.after(userResolution.getStartDate()) || from.equals(userResolution.getStartDate())) && (to.before(userResolution.getEndDate()) || to.equals(userResolution.getEndDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (!userResolution.isActive() && (from.before(userResolution.getStartDate()) || from.equals(userResolution.getStartDate())) && (to.after(userResolution.getEndDate()) || to.equals(userResolution.getEndDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(userResolution.getEndDate().getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (!userResolution.isActive() && (from.before(userResolution.getStartDate()) || from.equals(userResolution.getStartDate())) && (to.before(userResolution.getEndDate()) || to.equals(userResolution.getEndDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (!userResolution.isActive() && (from.after(userResolution.getStartDate()) || from.equals(userResolution.getStartDate())) && (to.after(userResolution.getEndDate()) || to.equals(userResolution.getEndDate()))) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(userResolution.getEndDate().getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            }
            userResolutionReport.setPlanForSetDays(Math.floor(userResolutionReport.getNumberOfDays() * userResolution.getWeeklyPlan() * 100 / 7.0) / 100);
            List<Activity> activities = activityRepository.getActivitiesByUserResolution(userResolution);
            for (Activity activity : activities) {
                if ((activity.getDate().before(to) && activity.getDate().after(from)) || activity.getDate().compareTo(from) == 0 || activity.getDate().compareTo(to) == 0) {
                    unitsSum += activity.getUnitsOfActivity();
                }
            }
            userResolutionReport.setUnitsInActions(unitsSum);

            if (userResolutionReport.getPlanForSetDays() > unitsSum) {
                userResolutionReport.setToGo(userResolutionReport.getPlanForSetDays() - unitsSum);
            } else {
                userResolutionReport.setToGo(0);
            }
            userResolutionReport.setResolutionRealization(Math.floor(userResolutionReport.getUnitsInActions() * 1000 / userResolutionReport.getPlanForSetDays()) / 10);
            userResolutionReports.add(userResolutionReport);
            unitsSum = 0;
        }

        userResolutionReports = userResolutionReports.stream()
                .sorted(Comparator.comparingDouble(urr -> urr.getResolutionRealization()))
                .collect(Collectors.toList());
        return userResolutionReports;

    }


    public double getAverage(List<UserResolutionReport> userResolutionReports){
        double realizationSum = 0;
        for(UserResolutionReport userResolutionReport : userResolutionReports){
            realizationSum += userResolutionReport.getResolutionRealization();
        }
        double average = Math.floor((realizationSum * 100) / userResolutionReports.size()) / 100;
        if (average > 100) {
            average = 100;
        }
        return average;
    }




}
