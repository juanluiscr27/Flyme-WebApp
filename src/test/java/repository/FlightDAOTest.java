package repository;

import model.Flight;
import model.FlightSearchDTO;
import org.junit.Test;
import service.FlightService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightDAOTest {

    @Test
    public void findAllWithSearchCriteria() {

        FlightRepository flightRepo = new FlightDAO();

        FlightSearchDTO flightSearch = new FlightSearchDTO(
                "YYZ",
                "SDQ",
                LocalDate.parse("2023-03-30"),
                LocalDate.parse("2023-04-03"),
                1
        );

        var allFlights = new ArrayList<Flight>(
                flightRepo.findAllWithSearchCriteria(
                        flightSearch, flightSearch.departureDate()
                ).values()
        );
        String expectedDestination = flightSearch.destinationAirport();
        String actualDestination = allFlights.get(0).getDestination().airportId();

        assertEquals(expectedDestination, actualDestination);
    }
}