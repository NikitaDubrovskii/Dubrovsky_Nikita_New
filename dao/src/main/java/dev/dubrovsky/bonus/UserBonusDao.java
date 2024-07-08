package dev.dubrovsky.bonus;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBonusDao implements IUserBonusDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(UserBonus entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO user_bonuses (user_id, bonus_id) VALUES (?, ?)")) {

            pst.setInt(1, entity.getUserBonusId().getUserId());
            pst.setInt(2, entity.getUserBonusId().getBonusId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<UserBonus> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM user_bonuses")) {

            final List<UserBonus> userBonuses = new ArrayList<>();

            while (rs.next()) {
                userBonuses.add(new UserBonus(
                        new UserBonusId(
                                rs.getInt("user_id"),
                                rs.getInt("bonus_id")
                        ),
                        rs.getTimestamp("received_at")
                ));
            }

            return userBonuses;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId, Integer bonusId) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM user_bonuses WHERE user_id = ? AND bonus_id = ?")){

            pst.setInt(1, userId);
            pst.setInt(2, bonusId);

            int i = pst.executeUpdate();

            if (i == 0) {
                throw new DbException("Id " + userId + " или " + bonusId + " не существует");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
