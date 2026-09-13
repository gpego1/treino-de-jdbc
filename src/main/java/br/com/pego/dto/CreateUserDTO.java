package br.com.pego.dto;

import java.sql.Date;

public record CreateUserDTO (
        String name,
        String email,
        String password,
        Date dateOfBirth
) {
}
