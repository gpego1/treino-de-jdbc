package br.com.pego.dto;


import java.sql.Date;

public record UserDTO(
        Integer id,
        String name,
        String email,
        String password,
        Date dateOfBirth
) {
}
