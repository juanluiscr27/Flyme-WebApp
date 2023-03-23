package util;

import model.Coordinate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Geodesy {

    private static final double EARTH_RADIUS = 6371.01;
    /**
     * Given the latitude and longitude of two points compute the great circle distance between them.
     * The haversine formula determines the great-circle distance between two points on a sphere
     * given their longitudes and latitudes. The Great-circle distance or spherical distance is the
     * shortest distance between two points on the surface of a sphere, measured along the surface of the sphere
     */
    public static BigDecimal GreatCircleDistance(Coordinate origin, Coordinate destination) {
        double x1 = Math.toRadians(origin.latitude().doubleValue());
        double y1 = Math.toRadians(origin.longitude().doubleValue());
        double x2 = Math.toRadians(destination.latitude().doubleValue());
        double y2 = Math.toRadians(destination.longitude().doubleValue());

        // a and c are sides from the haversine spherical triangle.
        double a = Math.pow(Math.sin(x2 - x1 / 2), 2) + Math.pow(Math.sin(y2 - y1 / 2), 2) * Math.cos(x1) * Math.cos(x2);
        double c = 2 * Math.asin(Math.sqrt(a));
        // The distance between origin and destination
        BigDecimal distance = BigDecimal.valueOf(EARTH_RADIUS * c);
        return distance.setScale(2, RoundingMode.HALF_UP);
    }
}
