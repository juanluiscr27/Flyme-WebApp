package repository;

import model.Airport;
import model.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    Optional<Airport> find(String code);

    public List<Airport> findAll(String search);
    List<CountryDTO> findAllCountries();

    List<CountryDTO> findAllCountries(String startsWith);
}
