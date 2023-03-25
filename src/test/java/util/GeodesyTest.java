package util;

import model.Coordinate;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GeodesyTest {
    /**
     * given the coordinates of two points, when calculates, then the distance is returned
     */
    @Test
    public void testGreatCircleDistance() {
        Coordinate newYork = new Coordinate(
                new BigDecimal("40.714268"),
                new BigDecimal("-74.005974")
        );

        Coordinate london = new Coordinate(
                new BigDecimal("51.500153"),
                new BigDecimal("-0.126236")
        );
        // Distance between Manhattan Community Board 1, New York and City of Westminster, London (kilometers)
        double expectedDistance = 5570.48;

        BigDecimal distance = Geodesy.GreatCircleDistance(newYork, london);
        double actualDistance = distance.doubleValue();
        double delta = 0.01d;

        assertEquals(expectedDistance, actualDistance, delta);
    }
}