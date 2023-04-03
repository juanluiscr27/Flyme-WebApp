package model;

import java.math.BigDecimal;

public class Receipt {
    private BagFareDTO[] bagFares;
    private AirPlaneDTO airPlane;
    private PassengerRequest[] passengers;
    private SeatDTO[] seats;
    private Ticket[] tickets;
    private BigDecimal price;
    public Receipt(BagFareDTO[] bagFares) {
        this.bagFares = bagFares;
    }
    public void generateTickets(AirPlaneDTO airPlane, PassengerRequest[] passengers, SeatDTO[] seats) {
        this.airPlane = airPlane;
        this.passengers = passengers;
        this.seats = seats;
    }

    private void getPassengerSeat() {
        for (PassengerRequest passenger : passengers) {
            Ticket passengerTicket = new Ticket(passenger, seats, bagFares);
        }
    }
}
