package model;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate dateOfBirth,
        CountryDTO nationality,
        String gender,
        String phone,
        int points
) {
}
