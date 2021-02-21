package ru.destered.semestr3sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.service.user_data.UserDataService;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserDataService userService;

    @GetMapping("/users/{userId}")
    public String getUsersPage(Model model, @PathVariable long userId){
        Optional<User> user = userService.findById(userId);
        user.ifPresent(value -> model.addAttribute("username", value));

        return "users_page";
    }
}
