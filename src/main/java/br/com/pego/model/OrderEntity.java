package br.com.pego.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderEntity {

    private Integer id;
    private Integer userId;
    private LocalDateTime orderDate;
    private BigDecimal total;


    public OrderEntity() {}

    public OrderEntity(Integer id, Integer userId, LocalDateTime orderDate, BigDecimal total) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.total = total;
    }

    public OrderEntity(Integer userId, BigDecimal total) {
        this.userId = userId;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
