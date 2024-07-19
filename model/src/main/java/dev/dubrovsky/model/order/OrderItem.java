package dev.dubrovsky.model.order;

import java.util.Objects;

public class OrderItem {

    private final Integer id;
    private final Integer quantity;
    private final Integer orderId;
    private final Integer productId;

    public OrderItem(Integer id, Integer quantity, Integer orderId, Integer productId) {
        this.id = id;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(quantity, orderItem.quantity)
                && Objects.equals(orderId, orderItem.orderId) && Objects.equals(productId, orderItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, orderId, productId);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }

}