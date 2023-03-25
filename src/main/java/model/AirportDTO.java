package model;

public record AirportDTO(
        String airportId,
        String name,
        String city,
        CountryDTO country,
        Coordinate coordinates
) {
}
