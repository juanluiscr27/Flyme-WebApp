package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record Confirmation(
        long orderId,
        String confirmationNumber,
        long userId,
        LocalDate orderDate,
        BigDecimal price,
        int passengers
) implements Serializable {
}
