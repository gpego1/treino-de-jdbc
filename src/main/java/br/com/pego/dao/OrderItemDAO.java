package br.com.pego.dao;
import br.com.pego.database.ConnectionFactory;
import br.com.pego.model.OrderItemEntity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public List<OrderItemEntity> getOrderItems() throws SQLException {
        String query = "SELECT * FROM order_items ORDER BY id ASC";
        List<OrderItemEntity> orderItems = new ArrayList<>();

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                ) {
            while (rs.next()) {
                OrderItemEntity orderItem = new OrderItemEntity(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getBigDecimal(4),
                rs.getInt(5),
                rs.getBigDecimal(6)
                );
                orderItems.add(orderItem);
            }
            return orderItems;
        }
    }

    public BigDecimal getOrderItemDiscountByOrderId(Integer orderId) throws SQLException {
        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                ) {
            ps.setInt(1, orderId);
            if (rs.next()) {
                return rs.getBigDecimal(6);
            }
        }
        return null;
    }

    public OrderItemEntity getOrderItem(Integer id) throws SQLException {
        String query = "SELECT * FROM order_items WHERE id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                ){
            ps.setInt(1, id);
            if (rs.next()) {
                return new OrderItemEntity(
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getBigDecimal(6)
                );
            }
        }
        return null;
    }

    public void createOrderItem(OrderItemEntity orderItem) throws SQLException {
        String sql = "INSERT INTO order_items (id, order_id, product_id, unit_price, quantity, discount) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, orderItem.getId());
            ps.setInt(2, orderItem.getOrderId());
            ps.setInt(3, orderItem.getProductId());
            ps.setBigDecimal(4, orderItem.getUnitPrice());
            ps.setInt(5, orderItem.getQuantity());
            ps.setBigDecimal(6, orderItem.getDiscount());
            ps.executeUpdate();
        }
    }
    public void updateOrderItem(OrderItemEntity orderItem) throws SQLException {
        String sql = "UPDATE order_items SET unit_price = ?, quantity = ?, discount = ? WHERE id = ?";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setBigDecimal(1, orderItem.getUnitPrice());
            ps.setInt(2, orderItem.getQuantity());
            ps.setBigDecimal(3, orderItem.getDiscount());
            ps.setInt(4, orderItem.getId());
            ps.executeUpdate();
        }
    }

    public void deleteOrderItem(Integer id) throws SQLException {
        String sql = "DELETE FROM ordfer_items WHERE id = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


}
