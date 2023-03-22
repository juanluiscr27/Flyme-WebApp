package model;

public record AirportDTO(
        String airport_id,
        String name,
        String city,
        CountryDTO country,
        Coordinate coordinates
) {
}
