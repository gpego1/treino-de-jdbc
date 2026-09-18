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
    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
