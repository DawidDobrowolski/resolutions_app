package pl.resolutions.service;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.ResolutionDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResolutionService {

    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;
    private ActivityRepository activityRepository;


    @Autowired
    public ResolutionService(UserResolutionRepository userResolutionRepository, ResolutionRepository resolutionRepository, UserRepository userRepository, ActivityRepository activityRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }


    public List<Resolution> getAllResolutions() {
        return resolutionRepository.findAll();
    }


    public List<UserResolution> getAllUserResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserResolution> userResolutionList = userResolutionRepository.getByUserEmail((String) session.getAttribute("email"));
        for (UserResolution userResolution : userResolutionList) {
            userResolution.setLastActivitiesUnits(getLastActivitiesUnits(userResolution));
        }
        userResolutionList = userResolutionList.stream()
                .sorted((ur1, ur2) -> ur2.getStartDate().compareTo(ur1.getStartDate()))
                .sorted((ur1, ur2) -> Boolean.compare(ur2.isActive(), ur1.isActive()))
                .collect(Collectors.toList());
        return userResolutionList;
    }


    public List<ResolutionDashboardChart> getDashboardChart(HttpServletRequest request) {
        List<UserResolution> userResolutions = getAllUserResolutions(request);
        List<ResolutionDashboardChart> dashboardCharts = new ArrayList<>();

        for (UserResolution userResolution : userResolutions) {
            ResolutionDashboardChart resolutionDashboardChart = new ResolutionDashboardChart();
            resolutionDashboardChart.setName(userResolution.getName());
            resolutionDashboardChart.setDone(getLastActivitiesUnits(userResolution));
            if (userResolution.getWeeklyPlan() > getLastActivitiesUnits(userResolution)) {
                resolutionDashboardChart.setToGo(userResolution.getWeeklyPlan() - getLastActivitiesUnits(userResolution));
            } else {
                resolutionDashboardChart.setToGo(0);
            }
            dashboardCharts.add(resolutionDashboardChart);
        }
        return dashboardCharts;
    }


    public List<UnitsName> getUnits() {
        List<Resolution> resolutionList = getAllResolutions();
        List<UnitsName> unitsNames = new ArrayList<>();
        for (Resolution resolution : resolutionList) {
            UnitsName unitsName = new UnitsName();
            unitsName.setId(resolution.getId());
            unitsName.setName(resolution.getUnit());
            unitsNames.add(unitsName);
        }
        return unitsNames;
    }

    public void addUserResolution(HttpServletRequest request, UserResolution userResolution) {
        HttpSession session = request.getSession();
        userResolution.setUser(userRepository.getByEmail((String) session.getAttribute("email")));
        userResolutionRepository.save(userResolution);
    }

    public UserResolution getOneUserResolution(Long id) {
        UserResolution userResolution = userResolutionRepository.findOne(id);
        Hibernate.initialize(userResolution.getActivities());
        userResolution.setActivities(userResolution.getActivities().stream()
                .sorted((a1, a2) -> a2.getDate().compareTo(a1.getDate()))
                .collect(Collectors.toList()));
        userResolution.setLastActivitiesUnits(getLastActivitiesUnits(userResolution));
        return userResolution;
    }

    public void deleteUserResolution(Long id) {
        userResolutionRepository.delete(id);
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
