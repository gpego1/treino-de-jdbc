package br.com.pego.dao;
import br.com.pego.database.ConnectionFactory;
import br.com.pego.model.ProductEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<ProductEntity> listAllProducts() throws SQLException {
        List<ProductEntity> products = new ArrayList<>();

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement("select * from product");
                ResultSet rs = ps.executeQuery();
                ) {
            while (rs.next()) {
                rs.getInt("id");
                rs.getString("name");
                rs.getDouble("price");
                rs.getInt("quantity");
            }
            return products;
        }
    }

    public ProductEntity getProductById(int id) throws SQLException {
        String query = "select * from product where id = ?";

        try(
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ) {
            ps.setInt(1, id);
            try(
                    ResultSet rs = ps.executeQuery();
                    ) {
                if (rs.next()) {
                    return new ProductEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    );
                }
            }
        }
        return null;
    }

    public void createPoduct(ProductEntity product) throws SQLException {
        String sql = """
                INSERT INTO TABLE products(id, name, price, quantity) VALUES 
                (? ? ? ?)
                """;

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ){

            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.executeUpdate();
        }
    }

    public void updatePoduct(ProductEntity product) throws SQLException {
        String sql = """
                UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?;
                """;
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        }
    }

    public void deletePoduct(int id) throws SQLException {
        String sql = """
                DELETE FROM products WHERE id = ?;
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
