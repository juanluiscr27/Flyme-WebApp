package service;

import model.BagFareDTO;
import model.Flight;
import model.FlightSearchDTO;
import model.SeatDTO;
import repository.FlightRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FlightService {

    FlightRepository flightRepo;
    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public Flight[] findAllOneWay(FlightSearchDTO flightSearch) {
        Collection<Flight> flights = flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.departureDate()).values();
        return flights.toArray(new Flight[0]);
    }
    public Flight[] findAllRoundTrip(FlightSearchDTO flightSearch) {
        Collection<Flight> flights = flightRepo.findAllWithSearchCriteria(flightSearch, flightSearch.returnDate()).values();
        return flights.toArray(new Flight[0]);
    }
    public SeatDTO[] findAllFlightSeats(Flight flight) {
        return flightRepo.findAllSeats(flight).toArray(new SeatDTO[0]);
    }

    public BagFareDTO[] findAllBagFares() {
        return flightRepo.findAllBagFares().toArray(new BagFareDTO[0]);
    }
}
