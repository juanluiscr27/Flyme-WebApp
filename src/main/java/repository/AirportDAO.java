package repository;

import model.AirportDTO;
import model.CountryDTO;
import util.EntityMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportDAO implements AirportRepository {
    @Override
    public Optional<AirportDTO> find(String code) {
        return Optional.empty();
    }

    @Override
    public List<AirportDTO> findAll(String search) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<AirportDTO> airports = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "a.airport_id, a.name, a.city, a.country, " +
                    "c.country_name, a.latitude, a.longitude " +
                    "FROM airports AS a " +
                    "INNER JOIN countries AS c ON a.country = c.country_id " +
                    "ORDER BY a.city ASC ");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                airports.add(EntityMapper.mapAirportDTO(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return airports;
    }

    @Override
    public List<CountryDTO> findAllCountries() {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CountryDTO> countriesDTO = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "country_id, country_name " +
                    "FROM countries " +
                    "ORDER BY country_name ASC ");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countriesDTO.add(new CountryDTO(
                        resultSet.getString("country_id"),
                        resultSet.getString("country_name"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return countriesDTO;
    }

    @Override
    public List<CountryDTO> findAllCountries(String startsWith) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CountryDTO> countriesDTO = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "country_id, country_name " +
                    "FROM countries WHERE country_id LIKE ? OR lower(country_name) LIKE ? " +
                    "ORDER BY country_name ASC ");

            statement.setString(1, startsWith.toUpperCase().substring(0,2) + "%");
            statement.setString(2, startsWith.toLowerCase() + "%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countriesDTO.add(new CountryDTO(
                        resultSet.getString("country_id"),
                        resultSet.getString("country_name"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return countriesDTO;
    }
}
