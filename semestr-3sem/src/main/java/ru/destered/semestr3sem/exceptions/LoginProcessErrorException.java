package ru.destered.semestr3sem.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.exceptions
 * Date:  26.02.2021
 * Time:  13:23
 */
public class LoginProcessErrorException extends Exception{
    public LoginProcessErrorException(String message) {
        super(message);
    }
}
