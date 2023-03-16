package repository;

import model.Airport;
import model.CountryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportDAO implements AirportRepository {
    @Override
    public Optional<Airport> find(String code) {
        return Optional.empty();
    }

    @Override
    public List<Airport> findAll(String search) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT " +
                    "airports.airport_id, airports.name, airports.city, airports.country, " +
                    "countries.country_name, airports.latitude, airports.longitude " +
                    "FROM airports " +
                    "INNER JOIN countries ON airports.country = countries.country_id " +
                    "WHERE airports.airport_id LIKE ? OR airports.name LIKE ? " +
                    "OR airports.city LIKE ? OR airports.country LIKE ? OR countries.country_name LIKE ? ");

            statement.setString(1, search.toLowerCase() + "%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                airports.add(new Airport());
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
                    "ORDER BY country_id ASC");

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
                    "ORDER BY country_id ASC");

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
