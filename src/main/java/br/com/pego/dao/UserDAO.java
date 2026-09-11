package br.com.pego.dao;
import br.com.pego.database.ConnectionFactory;
import br.com.pego.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<UserDTO> findAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
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

    public UserDTO getUserByID(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try(
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
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

    public void createUser(UserDTO user) throws SQLException {
        String sql = """
                INSERT INTO users(id, name, email, password, date_od_birth) VALUES
                (? ? ? ? ?)
                """;

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, user.id());
            ps.setString(2, user.name());
            ps.setString(3, user.email());
            ps.setString(4, user.password());
            ps.setDate(5, (Date) user.dateOfBirth());
            ps.executeUpdate();
        }
    }

    public void updateUser(UserDTO user) throws SQLException {
        String sql = """
                UPDATE users SET
                name = ?,  email = ?, password = ?, date_of_birth = ? WHERE id = ?
        """;
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, user.name());
            ps.setString(2, user.email());
            ps.setString(3, user.password());
            ps.setDate(4, (Date) user.dateOfBirth());
            ps.setInt(5, user.id());
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
