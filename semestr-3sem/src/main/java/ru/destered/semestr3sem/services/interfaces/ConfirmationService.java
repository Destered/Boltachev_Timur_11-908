package ru.destered.semestr3sem.services.interfaces;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.controllers
 * Date:  27.02.2021
 * Time:  0:01
 */
public interface ConfirmationService {
    boolean confirmByCode(String code);
}
