package model;

import java.io.Serializable;
import java.math.BigDecimal;

public record BagFareDTO(
        int bagCount,
        BigDecimal fee
) implements Serializable {
}
