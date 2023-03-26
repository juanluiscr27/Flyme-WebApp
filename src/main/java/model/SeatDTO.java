package model;

public record SeatDTO(
        int seatId,
        int airPlaneId,
        int row,
        char column,
        SeatClassDTO seatClass
) {
}
