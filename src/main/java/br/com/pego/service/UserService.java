package br.com.pego.service;

import br.com.pego.dto.UserDTO;
import br.com.pego.model.UserEntity;

public class UserService {

    private UserEntity convertToEntity(UserDTO dto) {
        return new UserEntity(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.password(),
                dto.dateOfBirth()
        );
    }
}
