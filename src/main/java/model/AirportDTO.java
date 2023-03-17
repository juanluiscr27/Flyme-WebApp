package model;

import java.math.BigDecimal;

public record AirportDTO(
        String airport_id,
        String name,
        String city,
        CountryDTO country,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
