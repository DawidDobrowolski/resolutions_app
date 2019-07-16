package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.resolutions.entity.Resolution;

public interface ResolutionRepository extends JpaRepository<Resolution,Long> {


}
