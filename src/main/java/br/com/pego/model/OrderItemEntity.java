package br.com.pego.model;

import java.math.BigDecimal;

public class OrderItemEntity {
    Integer id;
    Integer  orderId;
    Integer productId;
    BigDecimal unitPrice;
    Integer quantity;
    BigDecimal discount;

    public OrderItemEntity(Integer id, Integer orderId, Integer productId, BigDecimal unitPrice, Integer quantity, BigDecimal discount) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderItemEntity(Integer orderId, Integer productId, BigDecimal unitPrice, Integer quantity, BigDecimal discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
