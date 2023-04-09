package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private BagFareDTO[] bagFares;
    private DistanceFareDTO[] distanceFares;
    private List<Ticket> tickets;
    private BigDecimal totalPrice;
    public Receipt(BagFareDTO[] bagFares, DistanceFareDTO[] distanceFares) {
        this.bagFares = bagFares;
        this.distanceFares = distanceFares;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<Ticket> getTickets() {
        if (tickets == null)
            tickets = new ArrayList<>();
        return tickets;
    }

    public void generateTickets(Flight flight, PassengerRequest[] passengers, SeatDTO[] seats) {
        tickets = new ArrayList<>();
        totalPrice = BigDecimal.valueOf(0L);

        for (PassengerRequest passenger : passengers) {
            Ticket passengerTicket = new Ticket(passenger, flight, seats, bagFares, distanceFares);
            tickets.add(passengerTicket);
            totalPrice = totalPrice.add(passengerTicket.getPrice());
        }
    }
}
