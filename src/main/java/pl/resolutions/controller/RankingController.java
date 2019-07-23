package pl.resolutions.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.resolutions.entity.Resolution;
import pl.resolutions.service.RankingService;
import pl.resolutions.support.UserResolutionRanking;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {


    private RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @ModelAttribute("resolutions")
    public List<Resolution> getAllResolutions(HttpServletRequest request) {
        return rankingService.getAllResolutions(request);
    }

    @GetMapping("/choose")
    public String chooseRanking() {
        return "/ranking/choose";
    }


    @GetMapping("/ranking/{id}")
    public String showRanking(Model model, @PathVariable Long id) {

        List<UserResolutionRanking> userResolutionRankings = rankingService.getUserResolutionRanking(id);

        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(userResolutionRankings));
        model.addAttribute("userResolutionRankings",userResolutionRankings);
        model.addAttribute("resolution",rankingService.getResolutionById(id));
        return "/ranking/ranking";

    }


}