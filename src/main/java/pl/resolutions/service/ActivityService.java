package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.ActivityDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityService {


    private ActivityRepository activityRepository;
    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;


    @Autowired
    public ActivityService(ActivityRepository activityRepository, UserResolutionRepository userResolutionRepository,ResolutionRepository resolutionRepository,UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
    }

    public List<UserResolution> getAllActiveUserResolutionsByUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.getByUserEmailAndActiveIsTrue((String)session.getAttribute("email"));
    }


    public List<Activity> getAllActivities(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserResolution> userResolutions = userResolutionRepository.getByUserEmail((String)session.getAttribute("email"));
        List<Activity> activities = new ArrayList<>();
        for (UserResolution userResolution:userResolutions){
            activities.addAll(activityRepository.getActivitiesByUserResolution(userResolution));
        }
        activities = activities.stream()
                .sorted((a1, a2) -> a2.getDate().compareTo(a1.getDate()))
                .collect(Collectors.toList());
        return activities;
    }


    public List<ActivityDashboardChart> getActivityDashboardChart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userRepository.getByEmail((String)session.getAttribute("email"));
        List<Resolution> resolutionList = resolutionRepository.customDistinctUserResolutionsByUserEmail(user.getEmail());
        List<ActivityDashboardChart> activityDashboardChartArrayList = new ArrayList<>();

        for(Resolution resolution : resolutionList){
            ActivityDashboardChart activityDashboardChart = new ActivityDashboardChart();
            activityDashboardChart.setResolution(resolution.getName());
            activityDashboardChart.setNumber(activityRepository.countActivitiesByUserResolutionResolutionAndUserResolutionUser(resolution,user));
            activityDashboardChartArrayList.add(activityDashboardChart);
        }

        return activityDashboardChartArrayList;
    }


    public List<UnitsName> getUnitsNames(HttpServletRequest request) {
        List<UserResolution> userResolutionList = getAllActiveUserResolutionsByUser(request);
        List<UnitsName> unitsNames = new ArrayList<>();
        for(UserResolution userResolution:userResolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(userResolution.getId());
            unitsName.setName(userResolution.getResolution().getUnit());
            unitsNames.add(unitsName);
        }
        return unitsNames;
    }

    public UserResolution getUserResolutionById(Long id) {
        return userResolutionRepository.findOne(id);
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findOne(id);
    }

    public void deleteActivity(Long id) {
        activityRepository.delete(id);
    }



}
