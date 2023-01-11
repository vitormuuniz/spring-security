package com.workshop.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "user@email.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "another.user@email.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserByEmail(String userEmail) {
        return APPLICATION_USERS.stream()
                .filter(user -> user.getUsername().equals(userEmail))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
