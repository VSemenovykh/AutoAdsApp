package ru.ncedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String testGetMapping(Model model){
        return "index";
    }
    @PostMapping
    public String testPostMapping() {
        return "index";
    }
}
