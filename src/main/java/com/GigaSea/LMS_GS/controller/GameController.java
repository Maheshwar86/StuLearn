package com.GigaSea.LMS_GS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/game")
    public String game() {
        return "game"; // This will look for game.html in the templates directory
    }
}
