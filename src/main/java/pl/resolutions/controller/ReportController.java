package pl.resolutions.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.support.UserResolutionReport;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.UserResolutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private UserRepository userRepository;

    @Autowired
    public ReportController(UserResolutionRepository userResolutionRepository, ActivityRepository activityRepository,UserRepository userRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/generate")
    public String generateReport() {
        return "/report/generate";
    }

    @PostMapping("/generate")
    public String generateReportDates(Model model, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, HttpServletRequest request) {
        if (to == null || from == null) {
            model.addAttribute("wrongDate", true);
            return "report/generate";
        }
        if (!(from.before(to) && to.before(new Date())) || to == null || from == null) {
            model.addAttribute("wrongDate", true);
            return "report/generate";
        }

        model.addAttribute("from", from);
        model.addAttribute("to", to);

        HttpSession session = request.getSession();

        List<UserResolution> userResolutions = userResolutionRepository.customUsetFromTo((String)session.getAttribute("email"),to, from);
        List<UserResolutionReport> userResolutionReports = new ArrayList<>();
        int unitsSum = 0;
        double realizationSum = 0;

        for (UserResolution userResolution : userResolutions) {
            UserResolutionReport userResolutionReport = new UserResolutionReport();
            userResolutionReport.setName(userResolution.getName());
            userResolutionReport.setResolutionType(userResolution.getResolution().getName());
            userResolutionReport.setResolutionUnit(userResolution.getResolution().getUnit());
            if (userResolution.isActive() && from.after(userResolution.getStartDate())) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if(userResolution.isActive() && from.before(userResolution.getStartDate())){
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if(!userResolution.isActive() && from.after(userResolution.getStartDate()) && to.before(userResolution.getEndDate()) ) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS) + 1);
            } else if (!userResolution.isActive() && from.before(userResolution.getStartDate()) && to.after(userResolution.getEndDate())) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(userResolution.getEndDate().getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            }else if (!userResolution.isActive() && from.before(userResolution.getStartDate()) && to.before(userResolution.getEndDate())) {
                userResolutionReport.setNumberOfDays(TimeUnit.DAYS.convert(to.getTime() - userResolution.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
            }else if (!userResolution.isActive() && from.after(userResolution.getStartDate()) && to.after(userResolution.getEndDate())) {
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
            realizationSum += userResolutionReport.getResolutionRealization();
            userResolutionReports.add(userResolutionReport);
            unitsSum = 0;
        }
        double average = Math.floor((realizationSum * 100) / userResolutionReports.size()) / 100;
        if(average>100){
            average = 100;
        }
        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(userResolutionReports));
        model.addAttribute("average", average);
        model.addAttribute("userResolutionReports", userResolutionReports);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("from", simpleDateFormat.format(from));
        model.addAttribute("to", simpleDateFormat.format(to));
        return "report/report";

    }


}
