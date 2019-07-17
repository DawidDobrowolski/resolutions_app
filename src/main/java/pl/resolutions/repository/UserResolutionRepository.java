package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.resolutions.entity.UserResolution;

import java.util.Date;
import java.util.List;

public interface UserResolutionRepository extends JpaRepository<UserResolution,Long> {

    List<UserResolution> getByUserEmailAndActiveIsTrue(String email);


    List<UserResolution> getByUserEmail(String email);

    List<UserResolution> getByStartDateBeforeAndEndDateAfterOrEndDateIsNull(Date to,Date from);

    @Query("SELECT DISTINCT ur.resolution FROM UserResolution ur WHERE ur.user.email=:email")
    List<UserResolution> customDistinctUserResolutionsByUserEmail(@Param("email") String email);
}
