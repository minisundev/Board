package com.minisun.board.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupRequest {
    @Size(min = 6,max = 15, message ="username should be longer than 6 and shorter than 15")
    @Pattern(regexp = "^[a-z0-9]*$", message = "only lowercases and numbers are allowed for username")
    private String username;

    @Size(min = 8,max = 15, message ="pw should be longer than 6 and shorter than 15")
    @Pattern(regexp = "^[a-zA-Z_0-9]*$", message = "only alphabets and numbers are allowed for pw")
    private String password;

    private String passwordCheck;

}
