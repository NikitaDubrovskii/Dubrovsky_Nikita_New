package dev.dubrovsky.order;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    private final Integer id;
    private final Integer totalPrice;
    private final Timestamp createdAt;
    private final String address;
    private final Integer paymentMethodId;
    private final Integer userId;

    public Order(Integer id, Integer totalPrice, Timestamp createdAt,
                 String address, Integer paymentMethodId, Integer userId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.address = address;
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(totalPrice, order.totalPrice)
                && Objects.equals(createdAt, order.createdAt) && Objects.equals(address, order.address)
                && Objects.equals(paymentMethodId, order.paymentMethodId) && Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, createdAt, address, paymentMethodId, userId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", address='" + address + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", userId=" + userId +
                '}';
    }

}