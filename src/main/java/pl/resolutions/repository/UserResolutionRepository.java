package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.resolutions.entity.UserResolution;

import java.util.List;

public interface UserResolutionRepository extends JpaRepository<UserResolution,Long> {

    List<UserResolution> getByUserEmail(String email);




}
