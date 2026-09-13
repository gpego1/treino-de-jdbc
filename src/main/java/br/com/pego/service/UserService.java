package br.com.pego.service;
import br.com.pego.dao.UserDAO;
import br.com.pego.dto.UserDTO;
import br.com.pego.model.UserEntity;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<UserDTO> getUsers() throws SQLException {
        List<UserEntity> entities = userDAO.findAllUsers();

        List<UserDTO> userDTOS = entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return userDTOS;
    }

    public UserDTO getUserById(Integer id) throws SQLException {
        UserEntity userEntity = userDAO.getUserByID(id);
        if (userEntity != null) {
            return this.convertToDTO(userEntity);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void createUser(UserDTO dto) throws SQLException {
        List<UserDTO> dtos = this.getUsers();

        List<UserEntity> users = dtos.stream()
                .map(this::convertToEntity)
                .toList();

        Integer id = users
                .stream()
                .map(UserEntity::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;

        UserEntity userEntity = this.convertToEntity(dto);
        if (userEntity.getId() == null) {
            userEntity.setId(id);
            userDAO.createUser(userEntity);
        }

    }

    public void updateUser(Integer id, UserDTO dto) throws SQLException {
        UserEntity userEntity = userDAO.getUserByID(id);

        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }

        userEntity.setName(dto.name());
        userEntity.setEmail(dto.email());
        userEntity.setPassword(dto.password());
        userEntity.setDateOfBirth(dto.dateOfBirth());

        userDAO.updateUser(userEntity);
    }

    public void deleteUser(Integer id) throws SQLException {
        UserEntity userEntity = userDAO.getUserByID(id);

        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }
        userDAO.deleteUser(userEntity.getId());
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getDateOfBirth()
        );
    }

    private UserEntity convertToEntity(UserDTO dto) {
        return new UserEntity(
                dto.name(),
                dto.email(),
                dto.password(),
                dto.dateOfBirth()
        );
    }
}
