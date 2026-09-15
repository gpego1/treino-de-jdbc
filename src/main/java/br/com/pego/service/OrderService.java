package br.com.pego.service;
import br.com.pego.dao.OrderDAO;
import br.com.pego.dto.CreateOrderDTO;
import br.com.pego.dto.OrderDTO;
import br.com.pego.dto.UserDTO;
import br.com.pego.model.OrderEntity;
import br.com.pego.model.ProductEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;
    private final UserService userService;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.userService = new UserService();
    }

    public List<OrderDTO> findAll() throws SQLException {
        List<OrderEntity> orders = orderDAO.getAllOrders();

        return orders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OrderDTO findOrderById(Integer id) throws SQLException {
        OrderEntity order = orderDAO.getOrderById(id);
        if (order != null) {
            return convertToDTO(order);
        } else {
            throw new RuntimeException("Order Not Found");
        }
    }

    public void createOrder(CreateOrderDTO createOrderDTO) throws SQLException {
        List<OrderEntity> orders = orderDAO.getAllOrders();

        Integer id = orders.stream()
                .map(OrderEntity::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;

        if (orderDAO.getOrderById(id) != null) {
            throw new RuntimeException("Order Already Exists");
        }

        boolean isAgeValid = this.isAgeUserAgeValid(createOrderDTO.userId());

        if  (isAgeValid) {
            OrderEntity order = new OrderEntity(
                    id,
                    createOrderDTO.userId(),
                    LocalDateTime.now(),
                    createOrderDTO.total()
            );
            orderDAO.createOrder(order);
        } else {
            throw new RuntimeException("The user has to be at least 18 years old");
        }
    }

    public void updateOrder(Integer id, CreateOrderDTO createOrderDTO) throws SQLException {
        OrderEntity order = orderDAO.getOrderById(id);

        if (order != null) {
            order.setTotal(createOrderDTO.total());
            orderDAO.updateOrder(order);
        } else {
            throw new RuntimeException("Order Not Found");
        }
    }

    public void deleteOrder(Integer id) throws SQLException {
        OrderEntity order = orderDAO.getOrderById(id);
        if (order != null) {
            orderDAO.deleteOrder(order);
        } else {
            throw new RuntimeException("Order Not Found");
        }
    }


    private boolean isAgeUserAgeValid(Integer userId) throws SQLException {
        UserDTO userDTO = userService.getUserById(userId);

        if (userDTO != null) {
            int age = (LocalDate.now().minusYears(userDTO.dateOfBirth().toLocalDate().getYear())).getYear();
            return age >= 18;
        } else {
            throw new RuntimeException("User Not Found");
        }
    }

    private OrderDTO convertToDTO(OrderEntity orderEntity) {
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getOrderDate(),
                orderEntity.getTotal()
        );
    }

    private OrderEntity convertToEntity(CreateOrderDTO dto) {
        return new OrderEntity(
                dto.userId(),
                dto.total()
        );
    }
}
