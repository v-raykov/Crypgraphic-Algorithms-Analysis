package com.dreamteam.algorithm.analysis.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto extends LoginDto {
    @NotBlank
    @Email(message = "Invalid email")
    private String email;
}
