package service;

import model.CountryDTO;
import repository.FlightRepository;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private final FlightRepository flightRepo;

    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public List<CountryDTO> findAllCountries(String startsWith) {
        return flightRepo.findAllCountries(startsWith);
    }
}
