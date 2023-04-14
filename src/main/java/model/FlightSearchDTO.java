package model;

import java.io.Serializable;
import java.time.LocalDate;

public record FlightSearchDTO (
        String originAirport,
        String destinationAirport,
        LocalDate departureDate,
        LocalDate returnDate,
        int passengers
) implements Serializable {
}
