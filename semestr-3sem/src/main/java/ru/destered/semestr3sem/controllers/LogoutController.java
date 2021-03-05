package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController {

    @GetMapping
    public String logout(){
        return "/signIn";
    }
}
