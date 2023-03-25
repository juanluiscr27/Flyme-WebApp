package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Coordinate(
        BigDecimal latitude,
        BigDecimal longitude
) {
}
