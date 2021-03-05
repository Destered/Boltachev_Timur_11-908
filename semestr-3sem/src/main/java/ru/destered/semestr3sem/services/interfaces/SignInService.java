package ru.destered.semestr3sem.services.interfaces;


import ru.destered.semestr3sem.dto.forms.UserAuthForm;
import ru.destered.semestr3sem.exceptions.LoginProcessErrorException;
import ru.destered.semestr3sem.models.User;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.services.interfaces
 * Date:  26.02.2021
 * Time:  10:03
 */
public interface SignInService {
    User signIn(UserAuthForm signInForm) throws LoginProcessErrorException;
}
