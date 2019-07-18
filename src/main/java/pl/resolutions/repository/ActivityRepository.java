package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {

    List<Activity> getActivitiesByUserResolution(UserResolution userResolution);

    Integer countActivitiesByUserResolutionResolutionAndUserResolutionUser(Resolution resolution, User user);
}
