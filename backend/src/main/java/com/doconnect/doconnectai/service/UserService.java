package com.doconnect.doconnectai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectai.dto.LoginRequest;
import com.doconnect.doconnectai.dto.RegisterRequest;
import com.doconnect.doconnectai.entity.Role;
import com.doconnect.doconnectai.entity.User;
import com.doconnect.doconnectai.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        Optional<User> user =
                userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            return "Invalid Email or Password";
        }

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.get().getPassword()
                );

        if (!matches) {
            return "Invalid Email or Password";
        }

        return "Login Successful";
    }
}