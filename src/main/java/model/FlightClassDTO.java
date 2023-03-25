package model;

public record FlightClassDTO(
        int classId,
        String className,
        int capacity,
        int occupancy
) {
}
