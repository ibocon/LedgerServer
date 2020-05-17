package com.ibocon.ledger.model.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}