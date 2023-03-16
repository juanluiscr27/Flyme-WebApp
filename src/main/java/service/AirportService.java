package service;

import model.CountryDTO;
import repository.AirportRepository;
import repository.FlightRepository;

import java.util.List;

public class AirportService {
    private final AirportRepository airportRepo;

    public AirportService(AirportRepository airportRepo) {
        this.airportRepo = airportRepo;
    }

    public List<CountryDTO> findAllCountries(String startsWith) {
        if (startsWith == null || startsWith .equals("")) {
            return airportRepo.findAllCountries();
        } else {
            return airportRepo.findAllCountries(startsWith);
        }
    }
}
