package dev.dubrovsky.analytics;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsDao implements IAnalyticsDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Analytics entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO analytics (activity, user_id) VALUES (?, ?)")) {

            pst.setString(1, entity.getActivity());
            pst.setInt(2, entity.getUserId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Analytics> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM analytics")) {

            final List<Analytics> analytics = new ArrayList<>();

            while (rs.next()) {
                analytics.add(new Analytics(
                        rs.getInt("id"),
                        rs.getString("activity"),
                        rs.getTimestamp("timestamp"),
                        rs.getInt("user_id")
                ));
            }

            return analytics;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Analytics entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE analytics SET activity = ?, user_id = ? WHERE id = ?")) {

            pst.setString(1, entity.getActivity());
            pst.setInt(2, entity.getUserId());
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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM analytics WHERE id = ?")){

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
