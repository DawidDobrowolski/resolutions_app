package pl.resolutions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserResolutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;

    @Autowired
    public RankingController(UserResolutionRepository userResolutionRepository, ResolutionRepository resolutionRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
    }

    @ModelAttribute("resolutions")
    public List<Resolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return resolutionRepository.customDistinctUserResolutionsByUserEmail((String)session.getAttribute("email"));
    }

    @GetMapping("/choose")
    public String chooseRanking() {
        return "/ranking/choose";
    }
}
