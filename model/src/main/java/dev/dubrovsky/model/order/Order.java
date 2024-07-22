package dev.dubrovsky.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "address")
    private String address;

    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @Column(name = "user_id")
    private Integer userId;

    public Order(Integer totalPrice, String address,
                 Integer paymentMethodId, Integer userId) {
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
        this.address = address;
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
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