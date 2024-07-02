package dev.dubrovsky.order;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {

    private final Integer id;
    private final Integer totalPrice;
    private final Timestamp createdAt;
    private final String address;
    private final String paymentMethod;
    private final Integer userId;

    public Order(Integer id, Integer totalPrice, Timestamp createdAt,
                 String address, String paymentMethod, Integer userId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.address = address;
        this.paymentMethod = paymentMethod;
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

    public String getPaymentMethod() {
        return paymentMethod;
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
                && Objects.equals(paymentMethod, order.paymentMethod) && Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, createdAt, address, paymentMethod, userId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", address='" + address + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", userId=" + userId +
                '}';
    }

}