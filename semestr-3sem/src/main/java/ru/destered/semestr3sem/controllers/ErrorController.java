package ru.destered.semestr3sem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@RequiredArgsConstructor
public class ErrorController {

    @GetMapping
    public String getErrorPage(){
        System.out.println("check error");
        return "error";
    }
}
