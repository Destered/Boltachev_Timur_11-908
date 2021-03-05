package ru.destered.semestr3sem.controllers;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;

import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.services.interfaces.CookieService;
import ru.destered.semestr3sem.services.interfaces.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {
    private final CookieService cookieService;
    private final SignInService signInService;

    @Value("${auth.user.redirect.url}")
    private String authUserRedirectUrl;

    @GetMapping
    public String getSignInPage(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "info", required = false) String info,
                                Model model) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        model.addAttribute("error", error);
        model.addAttribute("info", info);
        return "sign_in_page";
    }

    @PostMapping
    @SneakyThrows(LoginProcessErrorException.class)
    public String signInUser(@CookieValue(value = "AuthCookie", required = false) String cookieValue,
                             @Valid UserAuthForm signInForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletResponse httpServletResponse) {
        if (cookieService.checkCookie(cookieValue)) {
            return authUserRedirectUrl;
        }
        if (bindingResult.hasErrors()) {
            List<String> errorsList = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectAttributes.addAttribute("error", errorsList.toString());
            return "redirect:/signIn";
        }
        User user = null;
        if ((user = signInService.signIn(signInForm)) != null) {
            Cookie cookie = cookieService.createCookie(user);
            httpServletResponse.addCookie(cookie);
            return authUserRedirectUrl;
        }
        redirectAttributes.addAttribute("error", "Login on password incorrect ( S W W )");
        return "redirect:/signIn";
    }
}
