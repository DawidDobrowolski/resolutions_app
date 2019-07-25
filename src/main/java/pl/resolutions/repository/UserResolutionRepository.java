package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;

import java.util.Date;
import java.util.List;

public interface UserResolutionRepository extends JpaRepository<UserResolution,Long> {

    List<UserResolution> getByUserEmailAndActiveIsTrue(String email);


    List<UserResolution> getByUserEmail(String email);

    @Query("SELECT ur FROM UserResolution ur WHERE ur.user.email=:email and ur.startDate <:to and (ur.endDate >:from1 or ur.endDate is null )")
    List<UserResolution> customUsetFromTo(@Param("email") String email,@Param("to") Date to,@Param("from1") Date from);

    List<UserResolution> getAllByUserAndResolutionId(User user, Long id);

    List<UserResolution> getAllByUserAndEmailReminderIsTrue(User user);


}
