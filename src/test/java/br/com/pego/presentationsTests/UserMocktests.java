package br.com.pego.presentationsTests;
import br.com.pego.dto.CreateUserDTO;
import br.com.pego.presentation.UserController;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserMocktests extends TestCase {
    LocalDate dateOfBirth = LocalDate.of(2007, 5, 20);
    UserController userController = new UserController();

    List<CreateUserDTO> createUsers = List.of(
            new CreateUserDTO("Rodrigo", "rodrigo@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
            new CreateUserDTO("Pedro", "pedro@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
            new CreateUserDTO("João", "joao@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
            new CreateUserDTO("Gabriel", "gpego@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
            new CreateUserDTO("Maria", "maria@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth))
    );

    @Test
    public void createManyUsers() throws SQLException {
        createUsers.forEach(user -> {
            try {
                userController.createUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void getAllUsers() throws SQLException {
        userController.getAllUsers();
    }

    @Test
    public void getUserById() throws SQLException {
        userController.getUserById(4);
    }

    @Test
    public void updateUser() throws SQLException {
        CreateUserDTO userToUpdate  = new CreateUserDTO("Luiza", "luiza@email.com", "nhbdwhushwsw", java.sql.Date.valueOf(dateOfBirth));
        userController.updateUser(4, userToUpdate);
        System.out.println(userController.getUserById(4));
    }

    @Test
    public void deleteUser() throws SQLException {
        userController.deleteUser(7);
        userController.getAllUsers();
    }

}
