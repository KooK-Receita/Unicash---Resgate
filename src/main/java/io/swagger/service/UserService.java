package io.swagger.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    public Long getUserIdByToken(String jwt) {
        if (Objects.equals(System.getenv("AMBIENTE"), "DEV")) {
            return 1L;
        }

        return null;
    }
}
