package com.minisun.board.user.service;

import com.minisun.board.user.dto.LoginRequest;
import com.minisun.board.user.entity.User;
import com.minisun.board.user.dto.SignupRequest;
import com.minisun.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String passwordCheck = requestDto.getPasswordCheck();

        //check if password contains username
        if (requestDto.getPassword().contains(username)){
            throw new IllegalArgumentException("password should not contain username");
        }

        // check username duplication
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // check password
        if (!passwordEncoder.matches(passwordCheck, password)) {
            throw new IllegalArgumentException("password check doesn't match with password");
        }

        //register user
        User user = new User(username,password);
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        // find username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("닉네임을 확인해주세요."));
        // check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("패스워드를 확인해주세요.");
        }
    }
}
