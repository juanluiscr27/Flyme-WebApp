package repository;

import model.BagFareDTO;
import model.CountryDTO;
import model.DistanceFareDTO;
import model.Flight;
import model.FlightSearchDTO;
import model.SeatDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FlightRepository {

    Optional<Flight> find(Long id);

    List<Flight> findAll();

    Map<Long, Flight> findAllWithSearchCriteria(FlightSearchDTO flightSearch, LocalDate travelDate);

    List<Flight> findAllByCountry(CountryDTO countryDTO);

    List<SeatDTO> findAllSeats(Flight flight);

    List<BagFareDTO> findAllBagFares();
    List<DistanceFareDTO> findAllDistanceFares();
}
