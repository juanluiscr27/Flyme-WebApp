package model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Receipt  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private BagFareDTO[] bagFares;
    private DistanceFareDTO[] distanceFares;
    private List<Ticket> tickets;
    private BigDecimal subTotal;
    private final BigDecimal TAX_RATE = BigDecimal.valueOf(13L,2);
    private BigDecimal taxes;
    private BigDecimal totalPrice;

    private BigDecimal bagFees;

    public Receipt(BagFareDTO[] bagFares, DistanceFareDTO[] distanceFares) {
        this.bagFares = bagFares;
        this.distanceFares = distanceFares;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public BigDecimal getTAX_RATE() {
        return TAX_RATE;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getBagFees() {
        return bagFees;
    }

    public List<Ticket> getTickets() {
        if (tickets == null)
            tickets = new ArrayList<>();
        return tickets;
    }

    public void generateTickets(PassengerDTO[] passengers) {
        tickets = new ArrayList<>();
        subTotal = BigDecimal.valueOf(0L);
        bagFees = BigDecimal.valueOf(0L);

        for (PassengerDTO passenger : passengers) {
            Ticket passengerTicket = new Ticket(passenger, bagFares, distanceFares);
            tickets.add(passengerTicket);
            subTotal = subTotal.add(passengerTicket.getPrice());
            bagFees = bagFees.add(passengerTicket.getBagFee());
        }
        taxes = subTotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
        totalPrice = subTotal.add(taxes).setScale(2, RoundingMode.HALF_UP);
    }
}
