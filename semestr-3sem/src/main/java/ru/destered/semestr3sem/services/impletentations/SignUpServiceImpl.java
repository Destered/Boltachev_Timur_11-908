package ru.destered.semestr3sem.services.impletentations;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.destered.semestr3sem.dto.UserDto;
import ru.destered.semestr3sem.dto.forms.SignUpForm;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.services.interfaces.SignUpService;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User:  SimonOnBoard
 * Project:  spring-basic-course
 * Package:  com.itis.kpfu.education.simononboard.spring.basics.services.impletentations
 * Date:  26.02.2021
 * Time:  10:51
 */
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final BCryptPasswordEncoder encoder;
    private final UsersRepository usersRepository;

    @Override
    public UserDto signUp(SignUpForm form) {
        if (usersRepository.existsByEmail(form.getEmail())) return null;
        // TODO: 26.02.2021 mappers либу прикрутить вместо builder и фабричного метода
        User user = User.fromSignUpForm(form);
        user.setCurrentConfirmationCode(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(form.getPassword()));
        usersRepository.save(user);
        return UserDto.fromUser(user);
    }
}
