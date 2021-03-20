package ru.destered.semestr3sem.services.impletentations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.destered.semestr3sem.models.User;
import ru.destered.semestr3sem.repositories.UsersRepository;
import ru.destered.semestr3sem.security.UserDetailsImpl;

import java.util.Optional;

@RequiredArgsConstructor
@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByEmail(email);
        if(user.isPresent())
        return new UserDetailsImpl(user.get());
        else throw new UsernameNotFoundException("Users not found");
    }
}
