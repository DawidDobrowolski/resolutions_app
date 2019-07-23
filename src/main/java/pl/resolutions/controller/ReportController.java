package pl.resolutions.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.resolutions.service.ReportService;
import pl.resolutions.support.UserResolutionReport;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate")
    public String generateReport() {
        return "/report/generate";
    }

    @PostMapping("/generate")
    public String generateReportDates(Model model, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, HttpServletRequest request) {
        if (to == null || from == null) {
            model.addAttribute("wrongDate", true);
            return "report/generate";
        }
        if (!(from.before(to) && to.before(new Date())) || to == null || from == null) {
            model.addAttribute("wrongDate", true);
            return "report/generate";
        }

        List<UserResolutionReport> userResolutionReports = reportService.getUserResolutionReportData(from,to,request);
        double average = reportService.getAverage(userResolutionReports);

        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(userResolutionReports));
        model.addAttribute("average", average);
        model.addAttribute("userResolutionReports", userResolutionReports);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("from", simpleDateFormat.format(from));
        model.addAttribute("to", simpleDateFormat.format(to));
        return "report/report";

    }


}
