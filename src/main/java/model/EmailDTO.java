package model;

import java.io.Serializable;

public record EmailDTO(
        String email,
        boolean isAvailable
) implements Serializable {
    // Factory method as no-arg constructor
    public EmailDTO() {
        this("", false);
    }
}
