package service;

import model.AirportDTO;
import model.CountryDTO;
import repository.AirportRepository;

import java.util.List;

public class AirportService {
    private final AirportRepository airportRepo;

    public AirportService(AirportRepository airportRepo) {
        this.airportRepo = airportRepo;
    }
    public List<AirportDTO> findAll(String search) {
        return airportRepo.findAll(search);
    }
    public List<CountryDTO> findAllCountries(String startsWith) {
        if (startsWith == null || startsWith .equals("")) {
            return airportRepo.findAllCountries();
        } else {
            return airportRepo.findAllCountries(startsWith);
        }
    }
}
