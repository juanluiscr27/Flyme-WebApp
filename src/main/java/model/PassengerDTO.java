package model;

import java.io.Serializable;
import java.time.LocalDate;

public record PassengerDTO (
        Flight flight,
        SeatDTO seat,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        int bags
) implements Serializable {
}
