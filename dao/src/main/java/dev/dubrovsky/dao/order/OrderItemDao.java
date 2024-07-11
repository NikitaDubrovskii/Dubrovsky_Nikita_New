package dev.dubrovsky.dao.order;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.order.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao implements IOrderItemDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(OrderItem entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO order_items (quantity, order_id, product_id) VALUES (?, ?, ?)")) {

            pst.setInt(1, entity.getQuantity());
            pst.setInt(2, entity.getOrderId());
            pst.setInt(3, entity.getProductId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<OrderItem> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM order_items")) {

            final List<OrderItem> orderItems = new ArrayList<>();

            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id")
                ));
            }

            return orderItems;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(OrderItem entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE order_items SET quantity = ?, order_id = ?, product_id = ? WHERE id = ?")) {

            pst.setInt(1, entity.getQuantity());
            pst.setInt(2, entity.getOrderId());
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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM order_items WHERE id = ?")){

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
