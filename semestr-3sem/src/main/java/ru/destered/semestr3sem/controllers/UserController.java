package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.models.Cookie;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.CookieRepository;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.CookieService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    private final CookieService cookieService;
    private final CookieRepository cookieRepository;
    private final UsersRepository usersRepository;

    @Value("${unauth.user.redirect.url}")
    private String unAuthUserRedirectUrl;

    @GetMapping
    public String loadProfile(RedirectAttributes redirectAttributes, @CookieValue(value = "AuthCookie", required = false) String cookieValue,
                              HttpServletRequest request, Model model) {
        if (!cookieService.checkCookie(cookieValue)) {
            return unAuthUserRedirectUrl;
        }
        Optional<Cookie> userCookie = cookieRepository.findByUuid(cookieValue);
        User user = null;
        if(userCookie.isPresent()){
            user = userCookie.get().getUser();
        }
        if(user != null) {
            model.addAttribute("isLogged", "true");
            model.addAttribute("email", user.getEmail());
            model.addAttribute("username", user.getUsername());
        } else{
            return unAuthUserRedirectUrl;
        }

        return "profile";
    }
}
