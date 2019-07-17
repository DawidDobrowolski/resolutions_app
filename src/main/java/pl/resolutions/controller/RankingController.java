package pl.resolutions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.UserResolutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private UserResolutionRepository userResolutionRepository;

    @Autowired
    public RankingController(UserResolutionRepository userResolutionRepository) {
        this.userResolutionRepository = userResolutionRepository;
    }

    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.customDistinctUserResolutionsByUserEmail((String)session.getAttribute("email"));
    }

    @GetMapping("/choose")
    public String chooseRanking() {
        return "/ranking/choose";
    }
}
