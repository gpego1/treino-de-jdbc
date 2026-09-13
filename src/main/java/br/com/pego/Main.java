package br.com.pego;
import br.com.pego.dto.CreateUserDTO;
import br.com.pego.dto.UserDTO;
import br.com.pego.presentation.UserController;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        LocalDate dateOfBirth = LocalDate.of(2007, 5, 20);

        UserController userController = new UserController();


        List<CreateUserDTO> createUsers = List.of(
                new CreateUserDTO("Rodrigo", "rodrigo@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
                new CreateUserDTO("Pedro", "pedro@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
                new CreateUserDTO("João", "joao@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
                new CreateUserDTO("Gabriel", "gpego@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth)),
                new CreateUserDTO("Maria", "maria@email.com", "hwgswhgusw",  java.sql.Date.valueOf(dateOfBirth))
        );

        createUsers.forEach(user -> {
            try {
                userController.createUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        userController.getAllUsers();

        System.out.println();
        UserDTO dtoGetId = userController.getUserById(4);
        System.out.println(dtoGetId);


        CreateUserDTO userToUpdate  = new CreateUserDTO("Luiza", "luiza@email.com", "nhbdwhushwsw", java.sql.Date.valueOf(dateOfBirth));
        userController.updateUser(4, userToUpdate);
        System.out.println(userController.getUserById(4));

        System.out.println();
        userController.deleteUser(5);
        userController.getAllUsers();


    }
}