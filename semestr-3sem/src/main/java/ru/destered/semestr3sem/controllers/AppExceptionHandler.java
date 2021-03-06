package ru.destered.semestr3sem.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({ LoginProcessErrorException.class })
    public String loginException(RedirectAttributes redirectAttributes, Exception ex){
        redirectAttributes.addAttribute("error", "Login incorrect");
        return "redirect:/signIn";
    }
}
