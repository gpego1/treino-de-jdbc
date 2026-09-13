package br.com.pego.service;
import br.com.pego.dao.UserDAO;
import br.com.pego.dto.UserDTO;
import br.com.pego.model.UserEntity;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<UserEntity> getUsers() throws SQLException {
        return userDAO.findAllUsers();
    }







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
