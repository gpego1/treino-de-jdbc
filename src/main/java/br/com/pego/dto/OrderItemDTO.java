package br.com.pego.dto;
import java.math.BigDecimal;

public record OrderItemDTO(
        Integer id,
        Integer orderId,
        Integer productId,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discount
)  {
}
