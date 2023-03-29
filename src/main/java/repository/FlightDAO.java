package repository;

import model.CountryDTO;
import model.Flight;
import model.FlightClassDTO;
import model.FlightSearchDTO;
import model.SeatDTO;
import util.EntityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class FlightDAO implements FlightRepository {
    @Override
    public Optional<Flight> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Flight> findAll() {
        return null;
    }

    @Override
    public Map<String, Flight> findAllWithSearchCriteria(FlightSearchDTO flightSearch, LocalDate travelDate) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, Flight> flightResult = new HashMap<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "f.flight_id, f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.price_multiplier, " +
                    "f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, oa.longitude, " +
                    "f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, da.latitude, da.longitude, " +
                    "c.class_id, c.name, COUNT( s.seat_id ) AS seat , ( " +
                    "    SELECT COUNT(sqp.passenger_id) " +
                    "    FROM passengers AS sqp " +
                    "    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id " +
                    "    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id " +
                    "    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id " +
                    "  ) AS passenger " +
                    "FROM flights AS f " +
                    "INNER JOIN seats AS s ON s.plane_id = f.plane_id " +
                    "INNER JOIN classes AS c ON c.class_id = s.class_id " +
                    "INNER JOIN planes AS p ON f.plane_id = p.plane_id " +
                    "INNER JOIN airports AS oa ON f.origin = oa.airport_id " +
                    "INNER JOIN countries AS oc ON oa.country = oc.country_id " +
                    "INNER JOIN airports AS da ON f.destination = da.airport_id " +
                    "INNER JOIN countries AS dc ON da.country = dc.country_id " +
                    "WHERE origin = ? AND destination = ? " +
                    "AND departure BETWEEN ? AND ? " +
                    "GROUP BY f.flight_id, f.flight_number, f.plane_id, c.class_id, c.name, passenger " +
                    "HAVING seat - passenger >= ? ");

            statement.setString(1, flightSearch.originAirport());
            statement.setString(2, flightSearch.destinationAirport());
            statement.setTimestamp(3, Timestamp.valueOf(travelDate.atStartOfDay()));
            statement.setTimestamp(4, Timestamp.valueOf(travelDate.atTime(LocalTime.MAX)));
            statement.setInt(5, flightSearch.passengers());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String flightNumber = resultSet.getString("f.flight_number");
                if (flightResult.containsKey(flightNumber)){
                    flightResult.get(flightNumber).addFlightClass(
                            new FlightClassDTO(
                                    resultSet.getInt("c.class_id"),
                                    resultSet.getString("c.name"),
                                    resultSet.getInt("seat"),
                                    resultSet.getInt("passenger")
                            )
                    );
                } else {
                    Flight newFlight = EntityMapper.mapFlight(resultSet);
                    newFlight.addFlightClass(
                            new FlightClassDTO(
                                    resultSet.getInt("c.class_id"),
                                    resultSet.getString("c.name"),
                                    resultSet.getInt("seat"),
                                    resultSet.getInt("passenger")
                            )
                    );
                    flightResult.put(flightNumber, newFlight);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return flightResult;
    }
    @Override
    public List<Flight> findAllByCountry(CountryDTO countryDTO) {
        return null;
    }

    @Override
    public List<SeatDTO> findAllSeats(Flight flight) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SeatDTO> seats = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier, " +
                    "  (SELECT COUNT(p.passenger_id) " +
                    "  FROM passengers AS p " +
                    "  WHERE p.seat_id = s.seat_id " +
                    "  AND p.flight_id = ?) AS is_reserved " +
                    "FROM seats AS s " +
                    "INNER JOIN flights AS f ON s.plane_id = f.plane_id "  +
                    "INNER JOIN classes AS c ON s.class_id = c.class_id " +
                    "WHERE f.flight_id = ? ");

            statement.setLong(1, flight.getId());
            statement.setLong(2, flight.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                seats.add(EntityMapper.mapSeatDTO(resultSet) );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return seats;
    }
}
