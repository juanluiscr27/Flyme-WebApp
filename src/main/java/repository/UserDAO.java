package repository;

import model.User;
import model.UserDTO;
import util.EntityMapper;

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
    public UserDTO add(User registrationRequest) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement insertStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet keys = null;
        ResultSet resultSet = null;
        UserDTO registeredUser = null;
        try {
            insertStatement = connection.prepareStatement("INSERT INTO users " +
                    "(user_id, first_name, last_name, email, password, birth_date, nationality, gender, phone, points) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, registrationRequest.getFirstName());
            insertStatement.setString(2, registrationRequest.getLastName());
            insertStatement.setString(3, registrationRequest.getEmail().toLowerCase());
            insertStatement.setString(4, registrationRequest.getPassword());
            insertStatement.setDate(5, Date.valueOf(registrationRequest.getDateOfBirth()));
            insertStatement.setString(6, registrationRequest.getNationality());
            insertStatement.setString(7, Character.toString(registrationRequest.getGender()));
            insertStatement.setString(8, registrationRequest.getPhone());
            insertStatement.setInt(9, registrationRequest.getPoints());

            insertStatement.executeUpdate();
            keys = insertStatement.getGeneratedKeys();
            keys.next();

            selectStatement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, countries.country_id, countries.country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id " +
                    "WHERE user_id = ? ");
/*
            selectStatement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, country_id, country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id " +
                    "WHERE user_id = ? ");
*/
            selectStatement.setLong(1, keys.getLong("GENERATED_KEY"));

            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                registeredUser = EntityMapper.toUserDTO(resultSet);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(keys);
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(insertStatement);
            DatabaseConnectionPool.close(selectStatement);
            DatabaseConnectionPool.close(connection);
        }
        return registeredUser;
    }

    @Override
    public Optional<UserDTO> find(Long userId) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserDTO user = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, country_id, country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id " +
                    "WHERE user_id = ? ");

            statement.setLong(1, userId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = EntityMapper.toUserDTO(resultSet);
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
    public List<UserDTO> findAll() {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<UserDTO> allUsers = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, country_id, country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id ");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allUsers.add(EntityMapper.toUserDTO(resultSet));
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
    public UserDTO update(User user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement updateStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;
        UserDTO updatedUser = null;
        try {
            updateStatement = connection.prepareStatement("UPDATE users "
                    + "SET password = ?, points = ? WHERE user_id = ? ");

            updateStatement.setString(1, user.getPassword());
            updateStatement.setInt(2, user.getPoints());
            updateStatement.setLong(3, user.getId());

            updateStatement.executeUpdate();

            selectStatement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, country_id, country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id " +
                    "WHERE user_id = ? ");
            selectStatement.setLong(1, user.getId());

            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                updatedUser = EntityMapper.toUserDTO(resultSet);
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
    public void delete(UserDTO user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE user_id = ? ");
            statement.setLong(1, user.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserDTO user = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id, first_name, last_name, email, password, birth_date, countries.country_id, countries.country_name, gender, phone, points " +
                    "FROM users INNER JOIN countries ON nationality = country_id WHERE email = ? ");

            statement.setString(1, email.toLowerCase());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = EntityMapper.toUserDTO(resultSet);
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

    public boolean isEmailPresent(String email) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isPresent = false;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "user_id FROM users WHERE email = ? LIMIT 1 ");

            statement.setString(1, email.toLowerCase());

            resultSet = statement.executeQuery();

            isPresent = resultSet.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return isPresent;
    }
}
