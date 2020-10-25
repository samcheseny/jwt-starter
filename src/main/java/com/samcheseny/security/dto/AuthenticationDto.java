package com.samcheseny.security.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationDto {

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;
}
