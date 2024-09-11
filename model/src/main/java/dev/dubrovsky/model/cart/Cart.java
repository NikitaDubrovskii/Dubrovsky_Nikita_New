package dev.dubrovsky.model.cart;

import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    public Cart() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(createdAt, cart.createdAt) && Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, user);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", timestamp=" + createdAt +
                ", userId=" + user +
                '}';
    }

    public CartResponse mapToResponse() {
        return new CartResponse(id, createdAt, user.mapToResponse());
    }

}
