package model;

import java.io.Serializable;

public record Service(
        int id,
        String description
) implements Serializable {
}
