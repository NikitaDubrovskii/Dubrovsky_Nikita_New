package dev.dubrovsky.dao.loyalty_program;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.loyalty_program.LoyaltyProgram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyProgramDao implements ILoyaltyProgramDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(LoyaltyProgram entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO loyalty_programs (name, description) VALUES (?, ?)")) {

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getDescription());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<LoyaltyProgram> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM loyalty_programs")) {

            final List<LoyaltyProgram> loyaltyPrograms = new ArrayList<>();

            while (rs.next()) {
                loyaltyPrograms.add(new LoyaltyProgram(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at")
                ));
            }

            return loyaltyPrograms;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(LoyaltyProgram entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE loyalty_programs SET name = ?, description = ? WHERE id = ?")) {

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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM loyalty_programs WHERE id = ?")) {

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
