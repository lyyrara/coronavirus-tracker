package com.example.coronavirustracker.controllers;

import com.example.coronavirustracker.CoronavirusTrackerApplication;
import com.example.coronavirustracker.services.CoronavirusDataService;
import models.LocationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStatus> allstats = coronavirusDataService.getAllStats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStatus", allstats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";

    }
}
