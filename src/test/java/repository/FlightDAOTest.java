package repository;

import model.AirPlaneDTO;
import model.AirportDTO;
import model.Coordinate;
import model.CountryDTO;
import model.Flight;
import model.FlightSearchDTO;
import model.SeatDTO;
import org.junit.Test;
import service.FlightService;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    /**
     * given a flight, when search all seats, then a list of flight seats is returned
     */
    @Test
    public void findAllSeats() {

        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);

        Flight flight = new Flight(
                1,
                "FM1112",
                new AirportDTO(
                        "YYZ",
                        "Toronto Pearson International Airport",
                        "Toronto",
                        new CountryDTO("CA", "Canada"),
                        new Coordinate(BigDecimal.valueOf(43.6777176),
                                BigDecimal.valueOf(-79.6248196))
                ), new AirportDTO(
                "SDQ",
                "Las Americas International Airport",
                "Santo Domingo",
                new CountryDTO("DO", "Dominican Republic"),
                new Coordinate(
                        BigDecimal.valueOf(18.4302189),
                        BigDecimal.valueOf(-69.6771733))),
                new AirPlaneDTO(
                1, "C-FAAA","Boeing", "747", BigDecimal.valueOf(1.0)),
                LocalDateTime.parse("2023-03-30T10:30:00"),
                LocalDateTime.parse("2023-03-30T14:50:00")
        );

        SeatDTO[] flightSeats = flightService.findAllFlightSeats(flight);

        assertTrue(flightSeats.length > 0);
    }
}