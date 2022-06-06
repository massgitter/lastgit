package com.atlas.skull.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class TemplateController {

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }

    @GetMapping("/swagger")
    public String logout(){

        return "swagger";
    }
}
