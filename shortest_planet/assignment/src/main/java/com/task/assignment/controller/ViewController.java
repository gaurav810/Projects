package com.task.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
  
@Controller
public class ViewController {
  
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/distanc-planets")
    public String distancPlanets() {
        return "distancPlanets";
    }
    
    @RequestMapping("/shortest-path")
    public String shortestPath() {
        return "shortestPath";
    }
}
