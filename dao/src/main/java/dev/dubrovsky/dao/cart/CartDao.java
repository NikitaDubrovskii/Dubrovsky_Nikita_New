package dev.dubrovsky.dao.cart;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.exception.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements ICartDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Cart entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO carts (user_id) VALUES (?)")) {

            pst.setInt(1, entity.getUserId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Cart> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM carts")) {

            final List<Cart> carts = new ArrayList<>();

            while (rs.next()) {
                carts.add(new Cart(
                        rs.getInt("id"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("user_id")
                ));
            }

            return carts;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Cart entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE carts SET user_id = ? WHERE id = ?")) {

            pst.setInt(1, entity.getUserId());
            pst.setInt(2, entity.getId());

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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM carts WHERE id = ?")){

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
