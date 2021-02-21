package ru.destered.semestr3sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.service.user_data.UserDataService;

@Controller
public class RegistrationController {
    @Autowired
    private UserDataService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User userForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        if(userForm.getPassword().isEmpty() || userForm.getUsername().isEmpty() || userForm.getEmail().isEmpty()){
            model.addAttribute("usernameError", "Заполните все поля");
            return "registration";
        }
        if (!userService.save(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }
}
