package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Order(
        long Id,
        String confirmationNumber,
        LocalDate orderDate,
        long userId,
        BigDecimal price,
        Flight flight,
        List<FlightPassenger> passengers

) implements Serializable {
}
