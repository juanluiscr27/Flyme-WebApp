package model;

import java.util.List;

public class Reservation {
    private Flight flight;
    private SeatDTO[] flightSeats;
    private PassengerRequest[] passengers;

    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public SeatDTO[] getFlightSeats() {
        return flightSeats;
    }
    public void setFlightSeats(SeatDTO[] flightSeats) {
        this.flightSeats = flightSeats;
    }

    public PassengerRequest[] getFlightPassengers() {
        return passengers;
    }
    public void setFlightPassengers(PassengerRequest[] passengers) {
        this.passengers = passengers;
    }
}
