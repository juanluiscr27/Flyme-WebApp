package model;

import java.io.Serializable;

public record FlightClassDTO(
        int classId,
        String className,
        int capacity,
        int occupancy
) implements Serializable {
}
