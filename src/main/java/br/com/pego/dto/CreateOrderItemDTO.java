package br.com.pego.dto;
import br.com.pego.model.OrderEntity;
import br.com.pego.model.ProductEntity;
import java.math.BigDecimal;
import java.util.List;

public record CreateOrderItemDTO(
        OrderEntity order,
        List<ProductEntity> products,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discount
) {
}
