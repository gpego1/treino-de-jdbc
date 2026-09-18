package br.com.pego;
import br.com.pego.dto.*;
import br.com.pego.model.ProductEntity;
import br.com.pego.presentation.OrderController;
import br.com.pego.presentation.OrderItemController;
import br.com.pego.presentation.ProductController;
import br.com.pego.presentation.UserController;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException {
        OrderController orderController = new OrderController();
        UserController userController = new UserController();
        ProductController productController = new ProductController();
        OrderItemController orderItemController = new OrderItemController();

//        CreateUserDTO newUser = new CreateUserDTO("teste", "wshujw", "bhwgshw", Date.valueOf(LocalDate.now()));
//
//        userController.createUser(newUser);
//
//        CreateOrderDTO newOrder = new CreateOrderDTO(12, BigDecimal.valueOf(177.80));
//
//        orderController.createOrder(newOrder);
//        orderController.getOrders();

//        System.out.println(orderController.getOrderById(1));
//
//        orderController.updateOrder(1, new CreateOrderDTO(6, BigDecimal.valueOf(263.50)));
//        System.out.println(orderController.getOrderById(1));
//
//        orderController.deleteOrder(1);
//        orderController.getOrders();


        List<ProductDTO> products = productController.getProducts();

        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO(
                1,
                products,
                BigDecimal.valueOf(products.stream().map(ProductDTO::price).findFirst().orElseThrow(() -> new RuntimeException("Invalid operation"))),
                12
        );

        orderItemController.createOrderItem(orderItemDTO);

        orderItemController.getAllOrderItems();
    }
}