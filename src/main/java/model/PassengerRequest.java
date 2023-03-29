package model;

import java.time.LocalDate;

public record PassengerRequest(
        long flightId,
        int seatId,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        char gender,
        int bags
) {
}
