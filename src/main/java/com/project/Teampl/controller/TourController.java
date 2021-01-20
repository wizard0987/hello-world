package com.project.Teampl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TourController {

    @GetMapping("/tour/new")
    public @ResponseBody String tour() {
        return "여행지 등록폼";
    }
}
