package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import util.Geodesy;

public class Ticket  implements Serializable {
    private Flight flight;
    private PassengerDTO passenger;
    private SeatDTO seat;
    private BagFareDTO[] bagFares;
    private DistanceFareDTO[] distanceFares;
    private int chargeableBags;
    private BigDecimal bagFee;
    private BigDecimal price;

    public Ticket(PassengerDTO passenger, BagFareDTO[] bagFares, DistanceFareDTO[] distanceFares) {
        this.passenger = passenger;
        this.flight = passenger.flight();
        this.seat = passenger.seat();
        this.bagFares = bagFares;
        this.distanceFares = distanceFares;
        this.chargeableBags = getChargeableBags();
        calculatePrice();
    }

    private int getChargeableBags() {
        int bags = 0;
        if (passenger.bags() > seat.seatClass().checkedBags()) {
            bags = passenger.bags();
        }
        return bags;
    }

    private BigDecimal calculateBagFee() {
        BigDecimal fee = BigDecimal.valueOf(0);
        BigDecimal discount = BigDecimal.valueOf(0);
        if (chargeableBags == 0) {
            return fee;
        }
        for (int i = 0; i < chargeableBags; i++) {
            fee = fee.add(bagFares[i].fee());
        }
        for (int i = 0; i < seat.seatClass().checkedBags(); i++) {
            discount = discount.add(bagFares[i].fee());
        }
        return fee.subtract(discount);
    }

    private  BigDecimal calculateDistanceFare() {
        BigDecimal flightDistanceFare = BigDecimal.valueOf(1);
        BigDecimal flightDistance = Geodesy.GreatCircleDistance(
                flight.getOrigin().coordinates(),
                flight.getDestination().coordinates()
        );

        for (DistanceFareDTO distanceFare : distanceFares) {
            if (flightDistance.compareTo(distanceFare.max())<= 0) {
                flightDistanceFare = distanceFare.priceMultiplier();
                break;
            }
        }

        return flightDistanceFare;
    }

    private void calculatePrice() {
        BigDecimal basePrice = flight.getAirPlane().basePrice();
        BigDecimal classMultiplier = seat.seatClass().priceMultiplier();
        price = basePrice.multiply(classMultiplier);

        BigDecimal distanceMultiplier = calculateDistanceFare();
        price = price.multiply(distanceMultiplier);

        bagFee = calculateBagFee().setScale(2, RoundingMode.HALF_UP);
        price = price.add(bagFee).setScale(2, RoundingMode.HALF_UP);
    }

    public Flight getFlight() {
        return flight;
    }

    public PassengerDTO getPassenger() {
        return passenger;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public BigDecimal getBagFee() {
        return bagFee;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
