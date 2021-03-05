package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.services.interfaces.CookieService;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.controllers
 * Date:  26.02.2021
 * Time:  9:37
 */
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    private final CookieService cookieService;

    @Value("${unauth.user.redirect.url}")
    private String unAuthUserRedirectUrl;

    @GetMapping
    public String loadProfile(RedirectAttributes redirectAttributes,@CookieValue(value = "AuthCookie", required = false) String cookieValue) {
        if (!cookieService.checkCookie(cookieValue)) {
            return unAuthUserRedirectUrl;
        }
        return "profile";
    }
}
