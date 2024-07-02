package dev.dubrovsky.cart;

import java.sql.Timestamp;
import java.util.Objects;

public class Cart {

    private final Integer id;
    private final Timestamp timestamp;
    private final Integer userId;

    public Cart(Integer id, Timestamp timestamp, Integer userId) {
        this.id = id;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(timestamp, cart.timestamp) && Objects.equals(userId, cart.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, userId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                '}';
    }

}
