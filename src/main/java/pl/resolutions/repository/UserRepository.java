package pl.resolutions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.resolutions.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmail(String email);



}
