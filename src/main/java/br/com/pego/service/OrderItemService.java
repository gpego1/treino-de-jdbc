package br.com.pego.service;
import br.com.pego.dao.OrderItemDAO;
import br.com.pego.dto.CreateOrderItemDTO;
import br.com.pego.dto.OrderDTO;
import br.com.pego.dto.OrderItemDTO;
import br.com.pego.dto.ProductDTO;
import br.com.pego.model.OrderEntity;
import br.com.pego.model.OrderItemEntity;
import br.com.pego.model.ProductEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderItemService {
    private final OrderItemDAO  orderItemDAO;
    private final OrderService orderService;

    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAO();
        this.orderService = new OrderService();
    }

    public List<OrderItemDTO> findAll() throws SQLException {
        List<OrderItemEntity> entities = orderItemDAO.getOrderItems();

        return entities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OrderItemDTO findById(Integer id) throws SQLException {
        OrderItemEntity entity = orderItemDAO.getOrderItem(id);
        if (entity != null) {
            return convertToDTO(entity);
        } else {
            throw new RuntimeException("OrderItem not found with id: " + id);
        }
    }

    public OrderItemDTO findByOrderId(Integer orderId) throws SQLException {
        OrderItemEntity entity = orderItemDAO.findOrderItemByOrderId(orderId);

        if (entity != null) {
            return convertToDTO(entity);
        }

        throw new RuntimeException("OrderItem not found with orderId: " + orderId);
    }

    public void createOrderItem(CreateOrderItemDTO dto) throws SQLException {
        Integer id = orderItemDAO.getOrderItems().stream()
                .map(OrderItemEntity::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;

        List<ProductDTO> products = dto.products();

        for (ProductDTO product : products) {
            OrderItemEntity orderItem = new OrderItemEntity(
                    dto.order().getId(),
                    product.id(),
                    BigDecimal.valueOf(product.price()),
                    dto.quantity(),

                    this.generateDiscount(
                            dto.unitPrice(),
                            dto.quantity()
                    )
            );
            orderItem.setId(id);

            orderItemDAO.createOrderItem(orderItem);
        }
    }

    public void updateOrderItem(Integer id, CreateOrderItemDTO dto) throws SQLException {
        OrderItemEntity entity = orderItemDAO.getOrderItem(id);

        if (entity != null) {
            entity.setUnitPrice(dto.unitPrice());
            entity.setQuantity(dto.quantity());

            entity.setDiscount(
                    this.generateDiscount(
                            dto.unitPrice(),
                            dto.quantity()
                    )
            );
            orderItemDAO.updateOrderItem(entity);
        }
    }

    public void deleteOrderItem(Integer id) throws SQLException {
        if (orderItemDAO.getOrderItem(id) != null) {
            orderItemDAO.deleteOrderItem(id);
        } else  {
            throw new RuntimeException("OrderItem not found with id: " + id);
        }
    }

    public BigDecimal getDiscountByOrderId(Integer orderId) throws SQLException {
        return orderItemDAO.getOrderItemDiscountByOrderId(orderId);
    }


    private BigDecimal generateDiscount(BigDecimal unitPrice, Integer quantity)  {
        BigDecimal total = BigDecimal.valueOf(quantity).multiply(unitPrice);
        BigDecimal discount = BigDecimal.ZERO;

            if (total.compareTo(BigDecimal.valueOf(100.00)) >= 0) {
                discount = BigDecimal.valueOf(0.10);
            } else if (total.compareTo(BigDecimal.valueOf(500.00)) >= 0) {
                discount = BigDecimal.valueOf(0.27);
            } else if (total.compareTo(BigDecimal.valueOf(2000.00)) >= 0) {
                discount = BigDecimal.valueOf(0.32);
            }

            return discount;
    }


    private OrderItemDTO  convertToDTO(OrderItemEntity entity) {
        return new OrderItemDTO(
                entity.getId(),
                entity.getOrderId(),
                entity.getProductId(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getDiscount()
        );
    }


}
