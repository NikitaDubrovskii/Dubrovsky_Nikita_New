package dev.dubrovsky.dao.product;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Product entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO products (name, description, price, category_id) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());
            pst.setDouble(3, entity.getPrice());
            pst.setInt(4, entity.getCategoryId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM products")) {

            final List<Product> products = new ArrayList<>();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("category_id")
                ));
            }

            return products;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE products SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());
            pst.setDouble(3, entity.getPrice());
            pst.setInt(4, entity.getCategoryId());
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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM products WHERE id = ?")){

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