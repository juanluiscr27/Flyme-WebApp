package repository;

import model.CountryDTO;
import model.Flight;
import model.FlightSearchDTO;
import util.EntityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    public List<Flight> findAllWithSearchCriteria(FlightSearchDTO flightSearch, LocalDate travelDate) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> allFlights = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "flight_id, flight_number, origin, destination, plane_id, departure, arrival " +
                    "FROM flights " +
                    "WHERE origin = ? AND destination = ? " +
                    "AND departure BETWEEN ? AND ? ");

            statement.setString(1, flightSearch.originAirport());
            statement.setString(2, flightSearch.destinationAirport());
            statement.setTimestamp(3, Timestamp.valueOf(travelDate.atStartOfDay()));
            statement.setTimestamp(4, Timestamp.valueOf(travelDate.atTime(LocalTime.MAX)));

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allFlights.add(EntityMapper.mapFlight(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return allFlights;
    }
    @Override
    public List<Flight> findAllByCountry(CountryDTO countryDTO) {
        return null;
    }
}
