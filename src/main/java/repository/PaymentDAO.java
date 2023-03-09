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
    public Payment add(Payment newPayment, User user) {
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
            statement.setLong(5, user.getId());

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
    public Optional<Payment> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUser(User user) {
        return Optional.empty();
    }

    @Override
    public Payment update(Payment payment) {
        return null;
    }

    @Override
    public void delete(Payment payment) {

    }
}
