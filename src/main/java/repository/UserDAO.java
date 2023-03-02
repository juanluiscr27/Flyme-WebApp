package repository;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
public class UserDAO implements UserRepository {
    @Override
    public User add(User registrationRequest) {
        // TODO: Register new user
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
//        ResultSet keys = null;
        User registeredUser = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users "
                    + "(email, password, first_name, last_name, birth_date, nationality, genre, phone, points) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");

            statement.setString(1, registrationRequest.getEmail());
            statement.setString(2, registrationRequest.getPassword());
            statement.setString(3, registrationRequest.getFirstName());
            statement.setString(4, registrationRequest.getLastName());
            statement.setDate(5, Date.valueOf(registrationRequest.getDateOfBirth()));
            statement.setString(6, registrationRequest.getNationality());
            statement.setString(7, Character.toString(registrationRequest.getGender()));
            statement.setString(8, registrationRequest.getPhone());
            statement.setInt(9, registrationRequest.getPoints());

            statement.executeUpdate();
//            keys = statement.getGeneratedKeys();
//            keys.next();
            // registeredUser = new User(keys.getLong(1), registrationRequest);
            registeredUser = new User(registrationRequest);
            registeredUser.setId(0L);

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            // DatabaseConnectionPool.close(keys);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return registeredUser;
    }

    @Override
    public Optional<User> find(Long userID) {
        User user = null;
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users" +
                    " WHERE email = ?");
            statement.setString(1, "test@emaill.com");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        // TODO: Find all users
        return null;
    }

    @Override
    public User update(User user) {
        // TODO: Update user account (password or phone number)
        return null;
    }

    @Override
    public void delete(User user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE email = ? ");
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        } finally {
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO: Find a user by email
        return Optional.empty();
    }

    @Override
    public List<String> findAllEmails() {
        // TODO: Find all users emails
        return null;
    }
    /**
     * Map the current row of the given ResultSet to a User.
     * @param resultSet The ResultSet of which the current row is to be mapped to a User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static User map(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("nationality"),
                resultSet.getString("genre").charAt(0),
                resultSet.getString("phone"),
                resultSet.getInt("points")
        );
    }
}
