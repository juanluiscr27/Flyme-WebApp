package repository;

import model.AirportDTO;
import model.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    Optional<AirportDTO> find(String code);

    public List<AirportDTO> findAll(String search);
    List<CountryDTO> findAllCountries();

    List<CountryDTO> findAllCountries(String startsWith);
}
