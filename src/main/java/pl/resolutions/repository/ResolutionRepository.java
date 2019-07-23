package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.resolutions.entity.Resolution;

import java.util.List;

public interface ResolutionRepository extends JpaRepository<Resolution,Long> {

    @Query("SELECT DISTINCT ur.resolution FROM UserResolution ur WHERE ur.user.email=:email")
    List<Resolution> customDistinctUserResolutionsByUserEmail(@Param("email") String email);
}
