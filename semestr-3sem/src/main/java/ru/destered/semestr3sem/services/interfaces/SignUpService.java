package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.services.interfaces
 * Date:  26.02.2021
 * Time:  10:37
 */
public interface SignUpService {
    UserDto signUp(SignUpForm form);
}
