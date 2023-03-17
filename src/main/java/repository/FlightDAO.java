package repository;

import model.CountryDTO;
import model.Flight;

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
}
