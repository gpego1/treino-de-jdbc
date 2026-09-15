package br.com.pego.dao;

import br.com.pego.database.ConnectionFactory;
import br.com.pego.model.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<OrderEntity> getAllOrders() throws SQLException {
        String query = "SELECT * FROM orders";
        List<OrderEntity> orders = new ArrayList<>();

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                OrderEntity order = new OrderEntity(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getObject("order_date", LocalDateTime.class),
                        rs.getBigDecimal(4)

                );
                orders.add(order);
            }
            return orders;
        }
    }

    public OrderEntity getOrderById(Integer id) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setInt(1, id);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()) {
                    return new OrderEntity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getObject("order_date", LocalDateTime.class),
                            rs.getBigDecimal(4)
                    );
                }
                return null;
            }
        }
    }

    public void createOrder(OrderEntity order) throws SQLException {
        String query = "INSERT INTO orders (id, user_id, order_date, valor_total) VALUES (?, ?, ?, ?)";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ) {

            ps.setInt(1, order.getId());
            ps.setInt(2, order.getUserId());
            ps.setObject(3, order.getOrderDate());
            ps.setBigDecimal(4, order.getTotal());
            ps.executeUpdate();
        }
    }

    public void updateOrder(OrderEntity order) throws SQLException {
        String sql = "UPDATE orders SET order_date = ?, valor_total = ? WHERE id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setObject(1, order.getOrderDate());
            ps.setBigDecimal(2, order.getTotal());
            ps.setInt(3, order.getId());
            ps.executeUpdate();
        }
    }

    public void deleteOrder(OrderEntity order) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, order.getId());
        }
    }
}
