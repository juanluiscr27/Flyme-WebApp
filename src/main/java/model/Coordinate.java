package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Coordinate(
        BigDecimal latitude,
        BigDecimal longitude
) {

    public BigDecimal getLatitudeRad() {
        return degreeToRad(latitude);
    }
    public BigDecimal getLongitudeRad() {
        return degreeToRad(longitude);
    }
    private BigDecimal degreeToRad(BigDecimal degree) {
        BigDecimal pi = new BigDecimal(Double.toString(Math.PI));
        BigDecimal oneHundredEighty = new BigDecimal("180");
        // Degree * Pi / 180
        return degree.multiply(pi.divide(oneHundredEighty, RoundingMode.HALF_EVEN));
    }
}
