package br.com.pego.presentation;
import br.com.pego.dto.CreateOrderDTO;
import br.com.pego.dto.OrderDTO;
import br.com.pego.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    public void getOrders() throws SQLException {
        List<OrderDTO> orders = orderService.findAll();

        orders.forEach(order -> {
            System.out.println(order.toString());
        });
    }

    public OrderDTO getOrderById(Integer id) throws SQLException {
        return orderService.findOrderById(id);
    }

    public void createOrder(CreateOrderDTO order) throws SQLException {
        orderService.createOrder(order);
    }

    public void updateOrder(Integer id, CreateOrderDTO order) throws SQLException {
        orderService.updateOrder(id, order);
    }

    public void deleteOrder(Integer id) throws SQLException {
        orderService.deleteOrder(id);
    }
}
