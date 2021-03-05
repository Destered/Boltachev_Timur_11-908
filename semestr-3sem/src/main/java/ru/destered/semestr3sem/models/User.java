package ru.destered.semestr3sem.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.destered.semestr3sem.dto.forms.SignUpForm;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Cookie> cookieList;

    // TODO: 27.02.2021 - State
    private boolean proved;

    private String currentConfirmationCode;

    public User(Long id) {
        this.id = id;
    }

    public static User fromSignUpForm(SignUpForm form) {
        return User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .build();
    }
}
