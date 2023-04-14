package model;

import java.io.Serializable;

public record CountryDTO(
        String id,
        String name
) implements Serializable {
}
