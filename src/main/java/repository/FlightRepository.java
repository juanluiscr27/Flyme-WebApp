package repository;

import model.CountryDTO;
import model.Flight;
import model.FlightSearchDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    Optional<Flight> find(Long id);

    List<Flight> findAll();

    List<Flight> findAllWithSearchCriteria(FlightSearchDTO flightSearch, LocalDate travelDate);


    List<Flight> findAllByCountry(CountryDTO countryDTO);
}
