package br.com.pego.dao;
import br.com.pego.database.ConnectionFactory;
import br.com.pego.dto.ProductDTO;
import br.com.pego.model.ProductEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<ProductDTO> listAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();

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

    public ProductDTO getProductById(int id) throws SQLException {
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
                    return new ProductDTO(
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

    public void createPoduct(ProductDTO product) throws SQLException {
        String sql = """
                INSERT INTO TABLE products(id, name, price, quantity) VALUES 
                (? ? ? ?)
                """;

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ){

            ps.setInt(1, product.id());
            ps.setString(2, product.name());
            ps.setDouble(3, product.price());
            ps.setInt(4, product.quantity());
            ps.executeUpdate();
        }
    }

    public void updatePoduct(ProductDTO product) throws SQLException {
        String sql = """
                UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?;
                """;
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, product.name());
            ps.setDouble(2, product.price());
            ps.setInt(3, product.quantity());
            ps.setInt(4, product.id());
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
