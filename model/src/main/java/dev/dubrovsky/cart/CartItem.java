package dev.dubrovsky.cart;

import java.util.Objects;

public class CartItem {

    private final Integer id;
    private final Integer quantity;
    private final Integer cartId;
    private final Integer productId;

    public CartItem(Integer id, Integer quantity, Integer cartId, Integer productId) {
        this.id = id;
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id) && Objects.equals(quantity, cartItem.quantity)
                && Objects.equals(cartId, cartItem.cartId) && Objects.equals(productId, cartItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, cartId, productId);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }

}