package model;

import java.time.LocalDate;

public record FlightPassenger(
        long id,
        SeatDTO seat,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        int bags
) {
}
