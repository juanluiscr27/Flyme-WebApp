package model;

import java.time.LocalDate;

public record UserDTO(
        Long userId,
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth,
        String nationality,
        char gender,
        String phone,
        int points
) {
}
