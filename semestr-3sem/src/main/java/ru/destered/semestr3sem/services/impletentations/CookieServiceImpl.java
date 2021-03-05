package ru.destered.semestr3sem.services.impletentations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.CookieRepository;
import ru.destered.semestr3sem.services.interfaces.CookieService;
import javax.servlet.http.Cookie;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {
    private final CookieRepository cookieRepository;

    // TODO: 26.02.2021 Global exception handler - redirect to cookie creation process
    @Override
    public boolean checkCookie(String cookieValue) {
        return cookieValue != null;
    }

    @Override
    public Cookie createCookie(User user) {
        String value = UUID.randomUUID().toString();
       ru.destered.semestr3sem.models.Cookie cookie = ru.destered.semestr3sem.models.Cookie.fromValueAndUser(value, user);
        cookieRepository.save(cookie);
        return new Cookie("AuthCookie", value);
    }
}
