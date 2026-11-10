
package com.eventkampus.service;

import com.eventkampus.dto.LoginRequest;
import com.eventkampus.dto.LoginResponse;
import com.eventkampus.entity.User;
import com.eventkampus.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Email atau password salah"
                ));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Email atau password salah"
            );
        }

        if (!"Admin".equalsIgnoreCase(user.getRole())
                && !"Panitia".equalsIgnoreCase(user.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Role tidak memiliki akses"
            );
        }

        return new LoginResponse(
                user.getId(),
                user.getNama(),
                user.getEmail(),
                user.getRole()
        );
    }
}
