package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value={"", "/", "home"})
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String getMainPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                              @RequestParam(value = "error", required = false) String error,
                              Model model){
        return "main";
    }
}
