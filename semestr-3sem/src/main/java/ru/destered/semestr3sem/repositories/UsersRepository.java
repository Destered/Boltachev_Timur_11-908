package ru.destered.semestr3sem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.destered.semestr3sem.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByCurrentConfirmationCode(String code);
}
