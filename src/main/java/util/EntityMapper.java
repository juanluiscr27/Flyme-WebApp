package util;

import model.AirPlaneDTO;
import model.AirportDTO;
import model.Coordinate;
import model.CountryDTO;
import model.Flight;
import model.Payment;
import model.SeatClassDTO;
import model.SeatDTO;
import model.User;
import model.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
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
                resultSet.getString("password"),
                resultSet.getDate("birth_date").toLocalDate(),
                new CountryDTO(
                        resultSet.getString("country_id"),
                        resultSet.getString("country_name")
                ),
                switch (resultSet.getString("gender").charAt(0)) {
                    case 'F' -> "Female";
                    case 'M' -> "Male";
                    default -> "Other";
                },
                resultSet.getString("phone"),
                resultSet.getInt("points")
        );
    }

    public static Payment mapPayment(ResultSet resultSet) throws SQLException {
        return new Payment(
                resultSet.getLong("payment_id"),
                resultSet.getString("card_number"),
                resultSet.getString("name"),
                resultSet.getDate("expiry_date").toLocalDate(),
                resultSet.getString("security_code"),
                resultSet.getLong("user_id")
        );
    }

    public static Flight mapFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getLong("f.flight_id"),
                resultSet.getString("f.flight_number"),
                new AirportDTO(
                        resultSet.getString("f.origin"),
                        resultSet.getString("oa.name"),
                        resultSet.getString("oa.city"),
                        new CountryDTO(
                                resultSet.getString("oa.country"),
                                resultSet.getString("oc.country_name")
                        ),
                        new Coordinate(
                                resultSet.getBigDecimal("oa.latitude"),
                                resultSet.getBigDecimal("oa.longitude")
                        )
                ),
                new AirportDTO(
                        resultSet.getString("f.destination"),
                        resultSet.getString("da.name"),
                        resultSet.getString("da.city"),
                        new CountryDTO(
                                resultSet.getString("da.country"),
                                resultSet.getString("dc.country_name")
                        ),
                        new Coordinate(
                                resultSet.getBigDecimal("da.latitude"),
                                resultSet.getBigDecimal("da.longitude")
                        )
                ),
                new AirPlaneDTO(
                        resultSet.getInt("f.plane_id"),
                        resultSet.getString("p.registration"),
                        resultSet.getString("p.manufacturer"),
                        resultSet.getString("p.model"),
                        resultSet.getBigDecimal("p.base_price")
                ),
                resultSet.getTimestamp("f.departure").toLocalDateTime(),
                resultSet.getTimestamp("f.arrival").toLocalDateTime()
        );
    }

    public static AirportDTO mapAirportDTO(ResultSet resultSet) throws SQLException {
        return new AirportDTO(
                resultSet.getString("a.airport_id"),
                resultSet.getString("a.name"),
                resultSet.getString("a.city"),
                new CountryDTO(
                        resultSet.getString("a.country"),
                        resultSet.getString("c.country_name")
                ),
                new Coordinate(
                        resultSet.getBigDecimal("a.latitude"),
                        resultSet.getBigDecimal("a.longitude")
                )
        );
    }

    public static SeatDTO mapSeatDTO(ResultSet resultSet) throws SQLException {
        return new SeatDTO(
                resultSet.getInt("s.seat_id"),
                resultSet.getInt("s.plane_id"),
                resultSet.getInt("s.row"),
                resultSet.getString("s.column").charAt(0),
                new SeatClassDTO(
                        resultSet.getInt("s.class_id"),
                        resultSet.getString("c.name"),
                        resultSet.getInt("c.checked_bags"),
                        resultSet.getBigDecimal("c.price_multiplier"),
                        resultSet.getInt("is_reserved") != 0
                )
        );
    }
}
