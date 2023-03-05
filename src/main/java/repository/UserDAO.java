package repository;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class UserDAO implements UserRepository {
    @Override
    public User add(User registrationRequest) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet keys = null;
        User registeredUser = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users " +
                    "(user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, registrationRequest.getFirstName());
            statement.setString(2, registrationRequest.getLastName());
            statement.setString(3, registrationRequest.getEmail().toLowerCase());
            statement.setString(4, registrationRequest.getPassword());
            statement.setDate(5, Date.valueOf(registrationRequest.getDateOfBirth()));
            statement.setString(6, registrationRequest.getNationality());
            statement.setString(7, Character.toString(registrationRequest.getGender()));
            statement.setString(8, registrationRequest.getPhone());
            statement.setInt(9, registrationRequest.getPoints());

            statement.executeUpdate();
            keys = statement.getGeneratedKeys();
            keys.next();
            registeredUser = new User(registrationRequest);
            registeredUser.setId(keys.getLong("GENERATED_KEY"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(keys);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return registeredUser;
    }

    @Override
    public Optional<User> find(Long userId) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points " +
                    "FROM users WHERE user_id = ? ");

            statement.setLong(1, userId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> allUsers = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points " +
                    "FROM users ");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allUsers.add(map(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return allUsers;
    }

    @Override
    public User update(User user) {
        // TODO: Register new user
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement updateStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;
        User updatedUser = null;
        try {
            updateStatement = connection.prepareStatement("UPDATE users "
                    + "SET password = ?, phone = ?, points = ? WHERE user_id = ? ");

            updateStatement.setString(1, user.getPassword());
            updateStatement.setString(2, user.getPhone());
            updateStatement.setInt(3, user.getPoints());
            updateStatement.setLong(9, user.getId());

            updateStatement.executeUpdate();

            selectStatement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points " +
                    " FROM users WHERE user_id = ? ");
            selectStatement.setLong(1, user.getId());

            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                updatedUser = map(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(updateStatement);
            DatabaseConnectionPool.close(selectStatement);
            DatabaseConnectionPool.close(connection);
        }
        return updatedUser;
    }

    @Override
    public void delete(User user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE user_id = ? ");
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points " +
                    "FROM users WHERE email = ? ");

            statement.setString(1, email.toLowerCase());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<String> findAllEmails(String startsWith) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> allEmails = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "email " +
                    "FROM users WHERE email LIKE ? ");

            statement.setString(1, startsWith.toLowerCase() + "%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allEmails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return allEmails;
    }
    /**
     * Map the current row of the given ResultSet to a User.
     * @param resultSet The ResultSet of which the current row is to be mapped to a User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static User map(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("nationality"),
                resultSet.getString("gender").charAt(0),
                resultSet.getString("phone"),
                resultSet.getInt("points")
        );
    }
}
