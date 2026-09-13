package br.com.pego.dao;
import br.com.pego.database.ConnectionFactory;
import br.com.pego.dto.UserDTO;
import br.com.pego.model.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<UserEntity> findAllUsers() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            while (rs.next()) {
                rs.getInt("id");
                rs.getString("name");
                rs.getString("email");
                rs.getString("password");
                rs.getDate("date");
            }
            return users;
        }
    }

    public UserEntity getUserByID(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new UserEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getDate( "date_of_birth")
                    );
                }
            }
        }
        return null;
    }

    public void createUser(UserEntity user) throws SQLException {
        String sql = """
                INSERT INTO users(id, name, email, password, date_od_birth) VALUES
                (? ? ? ? ?)
                """;

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setDate(5, (Date) user.getDateOfBirth());
            ps.executeUpdate();
        }
    }

    public void updateUser(UserEntity user) throws SQLException {
        String sql = """
                UPDATE users SET
                name = ?,  email = ?, password = ?, date_of_birth = ? WHERE id = ?
        """;
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, (Date) user.getDateOfBirth());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = """
                DELETE FROM users WHERE id = ?; 
        """;
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
