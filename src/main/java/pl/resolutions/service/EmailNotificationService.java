package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmailNotificationService {


    private EmailService emailService;
    private UserRepository userRepository;
    private UserResolutionRepository userResolutionRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public EmailNotificationService(EmailService emailService, UserRepository userRepository, UserResolutionRepository userResolutionRepository, ActivityRepository activityRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userResolutionRepository = userResolutionRepository;
        this.activityRepository = activityRepository;
    }


    @Scheduled(cron = "0 00 18 * * SUN")
    public void autoSend() {
        StringBuilder sb = new StringBuilder();


        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            sb.setLength(0);
            sb.append("Report about resolution fulfillment during last week: ").append("\n").append("\n");
            List<UserResolution> userResolutionList = userResolutionRepository.getAllByUserAndEmailReminderIsTrue(user);

            if (userResolutionList.size() == 0) {
                continue;
            }
            for (UserResolution userResolution : userResolutionList) {
                sb.append("Resolution: " +
                        userResolution.getName() +
                        " has plan for 7 days: " +
                        userResolution.getWeeklyPlan() + " " + userResolution.getResolution().getUnit() +
                        " you managed to make: " +
                        getLastActivitiesUnits(userResolution) + " " + userResolution.getResolution().getUnit() +
                        " you completed plan in " +
                        Math.floor(10000*getLastActivitiesUnits(userResolution) / userResolution.getWeeklyPlan())/100 +
                        " %")
                        .append("\n").append("\n");
            }
            emailService.sendMail("appresolutions@gmail.com", user.getEmail(), "Weekly resolution sumup", sb.toString());

        }


    }


    public int getLastActivitiesUnits(UserResolution userResolution) {
        Date now = new Date();
        Date sevenDaysAgo = new Date(now.getTime() - TimeUnit.DAYS.toMillis(7));
        List<Activity> activities = activityRepository.getActivitiesByUserResolution(userResolution);
        return activities.stream()
                .filter(a -> a.getDate().after(sevenDaysAgo))
                .mapToInt(a -> a.getUnitsOfActivity())
                .sum();
    }
}
