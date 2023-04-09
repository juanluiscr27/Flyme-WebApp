package model;

import java.math.BigDecimal;

public record DistanceFareDTO(
        BigDecimal min,
        BigDecimal max,
        BigDecimal priceMultiplier
) {
}
