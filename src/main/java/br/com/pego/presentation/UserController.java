package br.com.pego.presentation;
import br.com.pego.dto.CreateUserDTO;
import br.com.pego.dto.UserDTO;
import br.com.pego.service.UserService;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private final UserService userService = new UserService();


    public void getAllUsers() throws SQLException {
        List<UserDTO> userDTOS = userService.getUsers();

     userDTOS
            .forEach(u -> {
                System.out.println(u.toString());
            });
    }

    public UserDTO getUserById(Integer id) throws SQLException {
        return userService.getUserById(id);
    }

    public void createUser(CreateUserDTO dto) throws SQLException {
         userService.createUser(dto);
    }

    public void updateUser(Integer id, CreateUserDTO dto) throws SQLException {
        userService.updateUser(id, dto);

    }

    public void deleteUser(Integer id) throws SQLException {
        userService.deleteUser(id);
    }


}
