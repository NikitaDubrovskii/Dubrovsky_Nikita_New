package dev.dubrovsky.model.order;

import dev.dubrovsky.model.payment.method.PaymentMethod;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(Integer totalPrice, String address,
                 PaymentMethod paymentMethod, User user) {
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(totalPrice, order.totalPrice)
                && Objects.equals(createdAt, order.createdAt) && Objects.equals(address, order.address)
                && Objects.equals(paymentMethod, order.paymentMethod) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, createdAt, address, paymentMethod, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", address='" + address + '\'' +
                ", paymentMethodId='" + paymentMethod + '\'' +
                ", userId=" + user +
                '}';
    }

}