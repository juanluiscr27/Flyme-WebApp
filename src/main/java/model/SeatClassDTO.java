package model;

import java.io.Serializable;
import java.math.BigDecimal;

public record SeatClassDTO(
        int classId,
        String className,
        int checkedBags,
        BigDecimal priceMultiplier,
        Boolean isReserved
) implements Serializable {
}
