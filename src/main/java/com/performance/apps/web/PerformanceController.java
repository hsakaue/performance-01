package com.performance.apps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.performance.domain.service.GoogleApiService;
import com.performance.domain.service.PerformanceService;

@Controller
public class PerformanceController {
    
    PerformanceService service;
    GoogleApiService googleService;
    
    public PerformanceController(PerformanceService service, GoogleApiService googleService) {
        this.service = service;
        this.googleService = googleService;
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/execute")
    public String confirm(Model model) {

        service.truncateTable();
        
        Long start = System.currentTimeMillis();
        service.saveStartTime(start);
        service.execute();
        Long end = System.currentTimeMillis();
        service.saveEndTime(end);
        try {
            googleService.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // model.addAttribute("executeTime", end - start);
        model.addAttribute("executeTime", service.getExecTime());
        return "result";
    }
}
