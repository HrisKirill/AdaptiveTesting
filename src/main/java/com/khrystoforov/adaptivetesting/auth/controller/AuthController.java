package com.khrystoforov.adaptivetesting.auth.controller;

import com.khrystoforov.adaptivetesting.auth.dto.request.LoginRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.request.RegisterRequestDto;
import com.khrystoforov.adaptivetesting.auth.dto.response.AuthenticationResponseDto;
import com.khrystoforov.adaptivetesting.auth.service.AuthService;
import com.khrystoforov.adaptivetesting.user.model.User;
import com.khrystoforov.adaptivetesting.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Validated
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponseDto refreshToken(@RequestParam UUID refreshToken) {
        return authService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public void revokeToken() {
        User user = userService.getCurrentUser();
        authService.revokeUserRefreshToken(user);
    }
}
