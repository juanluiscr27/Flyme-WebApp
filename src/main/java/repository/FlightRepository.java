package repository;

import model.CountryDTO;
import model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    Optional<Flight> find(Long id);

    List<Flight> findAll();

    List<Flight> findAllByCountry(CountryDTO countryDTO);

    List<CountryDTO> findAllCountries();

    List<CountryDTO> findAllCountries(String startsWith);
}
