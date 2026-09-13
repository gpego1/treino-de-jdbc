package br.com.pego.presentation;
import br.com.pego.dto.UserDTO;
import br.com.pego.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public static void main(String[] args) {


    }

    private void getAllUsers() throws SQLException {
        List<UserDTO> userDTOS = userService.getUsers();

     userDTOS
            .forEach(u -> {
                System.out.println(u.id());
                System.out.println(u.email());
                System.out.println(u.name());
            });
    }

    private void getUserById(int id) throws SQLException {
        UserDTO userDTO = userService.getUserById(id);
        System.out.println(userDTO.id());
        System.out.println(userDTO.email());
        System.out.println(userDTO.name());
    }

    private void createUser(UserDTO dto) throws SQLException {
         userService.createUser(dto);
    }

    private void updateUser(Integer id, UserDTO dto) throws SQLException {
        userService.updateUser(id, dto);

    }

    private void deleteUser(Integer id) throws SQLException {
        userService.deleteUser(id);
    }


}
