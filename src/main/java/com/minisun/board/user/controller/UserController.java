package com.minisun.board.user.controller;

import com.minisun.board.global.dto.ApiResponse;
import com.minisun.board.global.jwt.JwtUtil;
import com.minisun.board.user.dto.LoginRequest;
import com.minisun.board.user.dto.SignupRequest;
import com.minisun.board.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // sign up
    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        // Validation exception
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " field : " + fieldError.getDefaultMessage());
            }
            throw new IllegalArgumentException("input form doesn't meet requirements");
        }
        userService.signup(signupRequest);
        // if succeeded
        return new ApiResponse<>(HttpStatus.CREATED.value(), "completed signing up");
    }

    // login
    @PostMapping("/login")
    public ApiResponse<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        userService.login(loginRequest);

        jwtUtil.addJwtToCookie(jwtUtil.createToken(loginRequest.getUsername()), response);

        return new ApiResponse<>(HttpStatus.OK.value(), "login succeeded");
    }

    // login
    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletResponse response) {

        jwtUtil.logout(response);

        return new ApiResponse<>(HttpStatus.OK.value(), "logout succeeded");
    }
}
