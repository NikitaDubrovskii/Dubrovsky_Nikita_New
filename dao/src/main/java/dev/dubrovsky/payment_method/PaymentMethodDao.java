package dev.dubrovsky.payment_method;

import dev.dubrovsky.ConnectionDataBase;
import dev.dubrovsky.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDao implements IPaymentMethodDao {

    private final Connection connection = ConnectionDataBase.getConnection();

    @Override
    public void create(PaymentMethod entity) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO payment_method (method) VALUES (?)")) {

            pst.setString(1, entity.getMethod());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<PaymentMethod> getAll() {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM payment_method")) {

            final List<PaymentMethod> paymentMethods = new ArrayList<>();

            while (rs.next()) {
                paymentMethods.add(new PaymentMethod(
                        rs.getInt("id"),
                        rs.getString("method")
                ));
            }

            return paymentMethods;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(PaymentMethod entity) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE payment_method SET method = ? WHERE id = ?")) {

            pst.setString(1, entity.getMethod());
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
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM payment_method WHERE id = ?")){

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
