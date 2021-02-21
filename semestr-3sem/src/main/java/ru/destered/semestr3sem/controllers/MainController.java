package ru.destered.semestr3sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.destered.semestr3sem.service.user_data.UserDataService;

@Controller
public class MainController {
    @Autowired
    private UserDataService userService;

    @GetMapping("/")
    public String getMainPage(Model model){
        return "profile";
    }

}
