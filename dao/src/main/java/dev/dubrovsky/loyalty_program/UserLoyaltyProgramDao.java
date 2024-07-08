package dev.dubrovsky.loyalty_program;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoyaltyProgramDao implements IUserLoyaltyProgramDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(UserLoyaltyProgram entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO user_loyalty_programs (user_id, program_id) VALUES (?, ?)")) {

            pst.setInt(1, entity.getUserLoyaltyProgramId().getUserId());
            pst.setInt(2, entity.getUserLoyaltyProgramId().getProgramId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<UserLoyaltyProgram> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM user_loyalty_programs")) {

            final List<UserLoyaltyProgram> userLoyaltyPrograms = new ArrayList<>();

            while (rs.next()) {
                userLoyaltyPrograms.add(new UserLoyaltyProgram(
                        new UserLoyaltyProgramId(
                                rs.getInt("user_id"),
                                rs.getInt("program_id")
                        ),
                        rs.getTimestamp("received_at")
                ));
            }

            return userLoyaltyPrograms;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId, Integer programId) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM user_loyalty_programs WHERE user_id = ? AND program_id = ?")){

            pst.setInt(1, userId);
            pst.setInt(2, programId);

            int i = pst.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + userId + " или " + programId + " не существует");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
