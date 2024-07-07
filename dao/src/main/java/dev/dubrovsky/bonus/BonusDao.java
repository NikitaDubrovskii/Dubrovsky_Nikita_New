package dev.dubrovsky.bonus;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonusDao implements IBonusDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(Bonus entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO bonuses (name, description, points, program_id) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());
            pst.setInt(3, entity.getPoints());
            pst.setInt(4, entity.getProgramId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Bonus> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM bonuses")) {

            final List<Bonus> bonuses = new ArrayList<>();

            while (rs.next()) {
                bonuses.add(new Bonus(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("points"),
                        rs.getInt("program_id")
                ));
            }

            return bonuses;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Bonus entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE bonuses SET name = ?, description = ?, points = ?, program_id = ? WHERE id = ?")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());
            pst.setDouble(3, entity.getPoints());
            pst.setInt(4, entity.getProgramId());
            pst.setInt(5, entity.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM bonuses WHERE id = ?")){

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
