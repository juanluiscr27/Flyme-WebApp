package model;

public class Reservation {
    private User user;
    private final Flight flight;
    private SeatDTO[] flightSeats;
    private PassengerRequest[] passengers;
    private Receipt receipt;

    public Reservation(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
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

    public Receipt getReceipt() {
        receipt.generateTickets(flight, passengers, flightSeats);
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
