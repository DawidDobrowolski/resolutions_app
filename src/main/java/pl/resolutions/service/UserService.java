package pl.resolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.User;
import pl.resolutions.repository.UserRepository;

import java.util.List;


@Service
@Transactional
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    public User getUserByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


}
