package dev.dubrovsky.cart;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements ICartItemDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(CartItem entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO cart_items (quantity, cart_id, product_id) VALUES (?, ?, ?)")) {

            pst.setInt(1, entity.getQuantity());
            pst.setInt(2, entity.getCartId());
            pst.setInt(3, entity.getProductId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<CartItem> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM cart_items")) {

            final List<CartItem> cartItems = new ArrayList<>();

            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id")
                ));
            }

            return cartItems;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(CartItem entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE cart_items SET quantity = ?, cart_id = ?, product_id = ? WHERE id = ?")) {

            pst.setInt(1, entity.getQuantity());
            pst.setInt(2, entity.getCartId());
            pst.setInt(3, entity.getProductId());
            pst.setInt(4, entity.getId());

            int i = pst.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + entity.getId() + " не существует");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM cart_items WHERE id = ?")){

            pst.setInt(1, id);

            int i = pst.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + id + " не существует");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
