package com.wzt.uml.controller;

import com.wzt.uml.model.ViewSpot;
import com.wzt.uml.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private SightService sightService;

    @PostMapping("/searchSight")
    public Object searchSight(String cityName, Model model) {
        List<ViewSpot> viewSpotList = sightService.getSightByName(cityName);
        System.out.println("viewSpotList:"+viewSpotList);
        List<String> sightList = sightService.getPhoPath(viewSpotList);
        System.out.println("sightList: "+sightList);
        if (sightList.size() != 0 && sightList != null) {
            model.addAttribute("introPath", viewSpotList);
            model.addAttribute("phoPath", sightList);
            model.addAttribute("city",cityName);
        }
        return "sight";
    }

    @GetMapping(produces="text/plain;charset=UTF-8", value = "/sightspot")
    public Object getSightDetail(int id,Model model){
        ViewSpot viewSpot = sightService.getSightInfo(id);
        sightService.getInfo(viewSpot,model);
        return "sight-info";
    }
}
