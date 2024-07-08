package dev.dubrovsky.order;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Order entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO orders (total_price, address, payment_method_id, user_id) VALUES (?, ?, ?, ?)")) {

            pst.setInt(1, entity.getTotalPrice());
            pst.setString(2, entity.getAddress());
            pst.setInt(3, entity.getPaymentMethodId());
            pst.setInt(4, entity.getUserId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM orders")) {

            final List<Order> orders = new ArrayList<>();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("total_price"),
                        rs.getTimestamp("created_at"),
                        rs.getString("address"),
                        rs.getInt("payment_method_id"),
                        rs.getInt("user_id")
                ));
            }

            return orders;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Order entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE orders SET total_price = ?, address = ?, payment_method_id = ?, user_id = ? WHERE id = ?")) {

            pst.setInt(1, entity.getTotalPrice());
            pst.setString(2, entity.getAddress());
            pst.setInt(3, entity.getPaymentMethodId());
            pst.setInt(4, entity.getUserId());
            pst.setInt(5, entity.getId());

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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM orders WHERE id = ?")){

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
