package model;

public record EmailDTO(
        String email,
        boolean isAvailable
) {
    // Factory method as no-arg constructor
    public EmailDTO() {
        this("", false);
    }
}
