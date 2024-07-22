package dev.dubrovsky.model.cart;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id")
    private Integer userId;

    public Cart(Integer userId) {
        this.createdAt = LocalDateTime.now();
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(createdAt, cart.createdAt) && Objects.equals(userId, cart.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, userId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", timestamp=" + createdAt +
                ", userId=" + userId +
                '}';
    }

}
