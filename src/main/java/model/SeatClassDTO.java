package model;

import java.math.BigDecimal;

public record SeatClassDTO(
        int classId,
        String className,
        int checkedBags,
        BigDecimal priceMultiplier,
        Boolean isReserved
) {
}
