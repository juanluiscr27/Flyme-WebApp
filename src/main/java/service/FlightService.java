package service;

import model.Flight;
import model.FlightSearchDTO;
import repository.FlightRepository;

import java.util.Map;

public class FlightService {

    FlightRepository flightRepo;
    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public Map<String,Flight> findAllOneWay(FlightSearchDTO flightSearch) {
        return flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.departureDate());
    }
    public Map<String,Flight> findAllRoundTrip(FlightSearchDTO flightSearch) {
        return flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.returnDate());
    }
}
