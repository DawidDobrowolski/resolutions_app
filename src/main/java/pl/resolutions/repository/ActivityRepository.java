package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.resolutions.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
