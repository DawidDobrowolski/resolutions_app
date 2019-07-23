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
import pl.resolutions.support.UserResolutionRanking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class RankingService {

    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public RankingService(UserResolutionRepository userResolutionRepository, ResolutionRepository resolutionRepository, UserRepository userRepository, ActivityRepository activityRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    public List<Resolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return resolutionRepository.customDistinctUserResolutionsByUserEmail((String) session.getAttribute("email"));
    }


    public List<UserResolutionRanking> getUserResolutionRanking(Long id) {
        int sumUnits = 0;
        List<User> users = userRepository.customDistinctUserForResolution(id);
        List<UserResolutionRanking> userResolutionRankings = new ArrayList<>();
        for (User user : users) {
            UserResolutionRanking userResolutionRanking = new UserResolutionRanking();
            List<UserResolution> userResolutions = userResolutionRepository.getAllByUserAndResolutionId(user, id);
            for (UserResolution userResolution : userResolutions) {
                sumUnits += getLastActivitiesUnits(userResolution, 30);
            }
            userResolutionRanking.setUser(user.getName());
            userResolutionRanking.setSumUnits(sumUnits);
            userResolutionRanking.setUnitName(resolutionRepository.findOne(id).getUnit());
            userResolutionRankings.add(userResolutionRanking);
            sumUnits = 0;
        }

        userResolutionRankings = userResolutionRankings.stream().sorted((u1, u2) -> u2.getSumUnits().compareTo(u1.getSumUnits())).collect(Collectors.toList());
        return userResolutionRankings;
    }


    public int getLastActivitiesUnits(UserResolution userResolution, int days) {
        Date now = new Date();
        Date sevenDaysAgo = new Date(now.getTime() - TimeUnit.DAYS.toMillis(days));
        List<Activity> activities = activityRepository.getActivitiesByUserResolution(userResolution);
        return activities.stream()
                .filter(a -> a.getDate().after(sevenDaysAgo))
                .mapToInt(a -> a.getUnitsOfActivity())
                .sum();
    }

    public Resolution getResolutionById(Long id) {
        return resolutionRepository.findOne(id);
    }


}
