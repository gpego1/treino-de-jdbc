package br.com.pego.dto;
import br.com.pego.model.OrderEntity;
import br.com.pego.model.ProductEntity;
import java.math.BigDecimal;
import java.util.List;

public record CreateOrderItemDTO(
        OrderEntity order,
        List<ProductDTO> products,
        BigDecimal unitPrice,
        Integer quantity
) {
}
