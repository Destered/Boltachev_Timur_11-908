package ru.destered.semestr3sem.services.interfaces;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.services.impletentations
 * Date:  26.02.2021
 * Time:  22:24
 */
public interface SenderService {
    void sendMessage(String subject, String email, String html);
}
