package service;

import model.Flight;
import model.FlightSearchDTO;
import repository.FlightRepository;

import java.util.List;

public class FlightService {

    FlightRepository flightRepo;
    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public List<Flight> findAllOneWay(FlightSearchDTO flightSearch) {
        return flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.departureDate());
    }
    public List<Flight> findAllRoundTrip(FlightSearchDTO flightSearch) {
        return flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.returnDate());
    }
}
