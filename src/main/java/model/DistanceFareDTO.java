package model;

import java.io.Serializable;
import java.math.BigDecimal;

public record DistanceFareDTO(
        BigDecimal min,
        BigDecimal max,
        BigDecimal priceMultiplier
) implements Serializable {
}
