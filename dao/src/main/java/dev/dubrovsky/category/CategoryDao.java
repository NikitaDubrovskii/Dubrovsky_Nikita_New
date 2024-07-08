package dev.dubrovsky.category;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Category entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Category> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM categories")) {

            final List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }

            return categories;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Category entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());
            pst.setInt(3, entity.getId());

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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM categories WHERE id = ?")) {

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
