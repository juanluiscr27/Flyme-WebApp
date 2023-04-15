package model;

import java.io.Serializable;

public record SeatDTO(
        int seatId,
        int airPlaneId,
        int row,
        char column,
        SeatClassDTO seatClass
)  implements Serializable {
}
