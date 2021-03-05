package ru.destered.semestr3sem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.User;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.repositories
 * Date:  26.02.2021
 * Time:  10:01
 */

public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findByCurrentConfirmationCode(String code);
}
