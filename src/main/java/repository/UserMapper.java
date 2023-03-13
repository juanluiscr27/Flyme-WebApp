package repository;

import model.User;
import model.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    /**
     * Map the current row of the given ResultSet to a User.
     * @param resultSet The ResultSet of which the current row is to be mapped to a User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    public static User toUser(ResultSet resultSet) throws SQLException {
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

    /**
     * Map the current row of the given ResultSet to an UserDTO.
     * @param resultSet The ResultSet of which the current row is to be mapped to a User.
     * @return The mapped UserDTO from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    public static UserDTO toUserDTO(ResultSet resultSet) throws SQLException {
        return new UserDTO(
                resultSet.getLong("user_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("nationality"),
                resultSet.getString("gender").charAt(0),
                resultSet.getString("phone"),
                resultSet.getInt("points")
        );
    }

    /**
     * Map a given User to an UserDTO.
     * @param user The ResultSet of which the current row is to be mapped to a User.
     * @return The mapped UserDTO from the given User.
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getNationality(),
                user.getGender(),
                user.getPhone(),
                user.getPoints()
        );
    }
}
