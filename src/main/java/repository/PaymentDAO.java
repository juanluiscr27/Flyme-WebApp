package repository;

import model.Payment;
import model.User;
import model.UserDTO;
import util.EntityMapper;

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
            statement.setString(2, newPayment.getNameOnCard().toUpperCase());
            statement.setDate(3, Date.valueOf(newPayment.getExpiryDate()));
            statement.setString(4, newPayment.getSecurityCode());
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
                payment = EntityMapper.mapPayment(resultSet);
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
    public Optional<Payment> findByUser(UserDTO user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Payment payment = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "payment_id, card_number, name, expiry_date, security_code, user_id " +
                    "FROM payments WHERE user_id = ? ");

            statement.setLong(1, user.id());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                payment = EntityMapper.mapPayment(resultSet);
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
        try {
            updateStatement = connection.prepareStatement("UPDATE payments "
                    + "SET card_number = ?, name = ?, expiry_date = ?, security_code = ? WHERE payment_id = ? ");

            updateStatement.setString(1, payment.getCardNumber());
            updateStatement.setString(2, payment.getNameOnCard().toUpperCase());
            updateStatement.setDate(3, Date.valueOf(payment.getExpiryDate()));
            updateStatement.setString(4, payment.getSecurityCode());
            updateStatement.setLong(5, payment.getId());

            updateStatement.executeUpdate();

            selectStatement = connection.prepareStatement("SELECT " +
                            "payment_id, card_number, name, expiry_date, security_code, user_id " +
                            "FROM payments WHERE payment_id = ? ");
            selectStatement.setLong(1, payment.getId());

            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                updatedPayment = EntityMapper.mapPayment(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(updateStatement);
            DatabaseConnectionPool.close(selectStatement);
            DatabaseConnectionPool.close(connection);
        }
        return updatedPayment;
    }

    @Override
    public void delete(Payment payment) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM payments WHERE payment_id = ? ");
            statement.setLong(1, payment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
    }
}
