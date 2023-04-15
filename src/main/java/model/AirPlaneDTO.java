package model;

import java.io.Serializable;
import java.math.BigDecimal;

public record AirPlaneDTO(
        int airPlaneId,
        String registration,
        String manufacturer,
        String model,
        BigDecimal basePrice
) implements Serializable {
}
