package repository;

import model.CountryDTO;
import model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Flight> findAllByCountry(CountryDTO countryDTO) {
        return null;
    }

    @Override
    public List<CountryDTO> findAllCountries() {
        return null;
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
                    "ORDER BY country_id DESC");

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
