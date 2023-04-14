package model;

import java.io.Serializable;

public record AirportDTO(
        String airportId,
        String name,
        String city,
        CountryDTO country,
        Coordinate coordinates
) implements Serializable {
}
