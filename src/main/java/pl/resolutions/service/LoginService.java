package pl.resolutions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.resolutions.entity.User;
import pl.resolutions.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class LoginService {


    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public User getUserBySession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return userRepository.getByEmail((String)session.getAttribute("email"));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

}
