package pl.resolutions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.support.UserResolutionReport;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.UserResolutionRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/report")
public class ReportController {


    private UserResolutionRepository userResolutionRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public ReportController(UserResolutionRepository userResolutionRepository, ActivityRepository activityRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.activityRepository = activityRepository;
    }

    @GetMapping("/generate")
    public String generateReport() {
        return "/report/generate";
    }

    @PostMapping("/generate")
    public String generateReportDates(Model model, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        if (!(from.before(to) && to.before(new Date())) || to == null || from == null) {
            model.addAttribute("wrongDate", true);
            return "report/generate";
        }

        model.addAttribute("from", from);
        model.addAttribute("to", to);

        List<UserResolution> userResolutions = userResolutionRepository.getByStartDateBeforeAndEndDateAfterOrEndDateIsNull(to, from);
        List<UserResolutionReport> userResolutionReports = new ArrayList<>();
        double unitsSum = 0;
        double realizationSum = 0;

        for (UserResolution userResolution : userResolutions) {
            UserResolutionReport userResolutionReport = new UserResolutionReport();
            userResolutionReport.setUserResolution(userResolution);
            if (userResolution.isActive()) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (from.before(userResolution.getStartDate())) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(userResolution.getEndDate().getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (from.after(userResolution.getStartDate())) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(userResolution.getEndDate().getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            }
            userResolutionReport.setPlanForSetDays(userResolutionReport.getNumberOfDays() * userResolution.getWeeklyPlan() / 7.0);
            List<Activity> activities = activityRepository.getActivitiesByUserResolution(userResolution);
            for (Activity activity : activities) {
                if ((activity.getDate().before(to) && activity.getDate().after(from)) || activity.getDate().compareTo(from) == 0 || activity.getDate().compareTo(to) == 0) {
                    unitsSum += activity.getUnitsOfActivity();
                }
            }
            userResolutionReport.setUnitsInActions(unitsSum);
            userResolutionReport.setResolutionRealization(userResolutionReport.getUnitsInActions()/userResolutionReport.getPlanForSetDays());
            realizationSum += userResolutionReport.getResolutionRealization();
            userResolutionReports.add(userResolutionReport);
            unitsSum = 0;
        }

        model.addAttribute("userResolutionReports", userResolutionReports);
        model.addAttribute("userResolutionAverageRealization", Math.floor((realizationSum *10000) / userResolutionReports.size()) / 100);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("from",simpleDateFormat.format(from));
        model.addAttribute("to", simpleDateFormat.format(to));
        return "report/report";

    }


}
