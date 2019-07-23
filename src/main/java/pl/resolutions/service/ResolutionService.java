package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.ResolutionDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ResolutionService {

    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;


    @Autowired
    public ResolutionService(UserResolutionRepository userResolutionRepository, ResolutionRepository resolutionRepository, UserRepository userRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
    }


    public List<Resolution> getAllResolutions() {
        return resolutionRepository.findAll();
    }


    public List<UserResolution> getAllUserResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.getByUserEmail((String) session.getAttribute("email"));
    }


    public List<ResolutionDashboardChart> getDashboardChart(HttpServletRequest request){
        List<UserResolution> userResolutions = getAllUserResolutions(request);
        List<ResolutionDashboardChart> dashboardCharts = new ArrayList<>();

        for (UserResolution userResolution : userResolutions) {
            ResolutionDashboardChart resolutionDashboardChart = new ResolutionDashboardChart();
            resolutionDashboardChart.setName(userResolution.getName());
            resolutionDashboardChart.setDone(userResolution.getLastActivitiesUnits());
            if (userResolution.getWeeklyPlan() > userResolution.getLastActivitiesUnits()) {
                resolutionDashboardChart.setToGo(userResolution.getWeeklyPlan() - userResolution.getLastActivitiesUnits());
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
        for(Resolution resolution:resolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(resolution.getId());
            unitsName.setName(resolution.getUnit());
            unitsNames.add(unitsName);
        }
        return unitsNames;
    }

    public void addUserResolution(HttpServletRequest request, UserResolution userResolution){
        HttpSession session = request.getSession();
        userResolution.setUser(userRepository.getByEmail((String) session.getAttribute("email")));
        userResolutionRepository.save(userResolution);
    }

    public UserResolution getOneUserResolution(Long id){
        return userResolutionRepository.findOne(id);
    }

    public void deleteUserResolution(Long id){
        userResolutionRepository.delete(id);
    }

}
