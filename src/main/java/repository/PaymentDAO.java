package repository;

import model.Payment;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PaymentDAO implements PaymentRepository {
    @Override
    public Payment add(Payment newPayment) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet keys = null;
        Payment payment = null;
        try {
            statement = connection.prepareStatement("INSERT INTO payments " +
                    "(payment_id, card_number, name, expiry_date, security_code, user_id) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, newPayment.getCardNumber());
            statement.setString(2, newPayment.getNameOnCard());
            statement.setDate(3, Date.valueOf(newPayment.getExpiryDate()));
            statement.setInt(4, newPayment.getSecurityCode());
            statement.setLong(5, newPayment.getUserId());

            statement.executeUpdate();
            keys = statement.getGeneratedKeys();
            keys.next();
            payment = new Payment(newPayment);
            payment.setId(keys.getLong("GENERATED_KEY"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(keys);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return payment;
    }

    @Override
    public Optional<Payment> find(Long paymentId) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "payment_id, card_number, name, expiry_date, security_code, user_id " +
                    "FROM payments WHERE payment_id = ? ");

            statement.setLong(1, paymentId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                payment = mapPayment(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public Optional<Payment> findByUser(User user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "payment_id, card_number, name, expiry_date, security_code, user_id " +
                    "FROM payments WHERE user_id = ? ");

            statement.setLong(1, user.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                payment = mapPayment(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public Payment update(Payment payment) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement updateStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;
        Payment updatedPayment = null;

        return updatedPayment;
    }

    @Override
    public void delete(Payment payment) {

    }
    private static Payment mapPayment(ResultSet resultSet) throws SQLException {
        return new Payment(
                resultSet.getLong("payment_id"),
                resultSet.getString("card_number"),
                resultSet.getString("name"),
                resultSet.getDate("expiry_date").toLocalDate(),
                0,
                resultSet.getLong("user_id")
        );
    }
}
