package service;

import model.Flight;
import model.FlightSearchDTO;
import repository.FlightRepository;

import java.util.ArrayList;
import java.util.List;

public class FlightService {

    FlightRepository flightRepo;
    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public List<Flight> findAllOneWay(FlightSearchDTO flightSearch) {
        return new ArrayList<>(flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.departureDate()).values());
    }
    public List<Flight> findAllRoundTrip(FlightSearchDTO flightSearch) {
        return new ArrayList<>(flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.returnDate()).values());
    }
}
