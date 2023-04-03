package model;

import java.math.BigDecimal;

public record BagFareDTO(
        int bagCount,
        BigDecimal fee
) {
}
