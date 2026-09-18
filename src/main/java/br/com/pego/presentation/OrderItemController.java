package br.com.pego.presentation;
import br.com.pego.dto.CreateOrderItemDTO;
import br.com.pego.dto.OrderItemDTO;
import br.com.pego.service.OrderItemService;

import java.sql.SQLException;
import java.util.List;

public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController() {
        this.orderItemService = new OrderItemService();
    }

    public void getAllOrderItems() throws SQLException {
        orderItemService.findAll()
                .forEach(oi -> {
                    System.out.println(oi.toString());
                });
    }

    public void getOrderItemById(Integer id) throws SQLException {
        System.out.println(orderItemService.findById(id).toString());
    }

    public void createOrderItem(CreateOrderItemDTO dto) throws SQLException {
        orderItemService.createOrderItem(dto);
    }

    public void updateOrderItem(Integer id, CreateOrderItemDTO dto) throws SQLException {
        orderItemService.updateOrderItem(id, dto);
    }

    public void deleteOrderItem(Integer id) throws SQLException {
        orderItemService.deleteOrderItem(id);
    }
}
