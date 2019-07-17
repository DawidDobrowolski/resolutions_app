package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.resolutions.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmail(String email);


    @Query("SELECT DISTINCT ur.user FROM UserResolution ur WHERE ur.resolution.id=:id")
    List<User> customDistinctUserForResolution(@Param("id") Long id);

}