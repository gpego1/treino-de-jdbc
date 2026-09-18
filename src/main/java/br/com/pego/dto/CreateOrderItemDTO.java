package br.com.pego.dto;
import java.math.BigDecimal;
import java.util.List;

public record CreateOrderItemDTO(
        Integer orderId,
        List<ProductDTO> products,
        BigDecimal unitPrice,
        Integer quantity
) {
}
