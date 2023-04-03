package model;

public class Ticket {
    private PassengerRequest passenger;
    private SeatDTO seat;
    private BagFareDTO[] bagFares;
    private int chargeableBags;

    public Ticket(PassengerRequest passenger, SeatDTO[] seats, BagFareDTO[] bagFares) {
        this.passenger = passenger;
        this.seat = getPassengerSeat(seats);
        this.bagFares = bagFares;
        this.chargeableBags = getChargeableBags();
    }

    private SeatDTO getPassengerSeat(SeatDTO[] seats){
        SeatDTO passengerSeat = null;
        for (SeatDTO seat : seats) {
            if (passenger.getSeatId() == seat.seatId()) {
                passengerSeat = seat;
            }
        }
        return passengerSeat;
    }

    private int getChargeableBags() {
        int bags = 0;
        if (passenger.getBags() > seat.seatClass().checkedBags()) {
            bags = passenger.getBags();
        }
        return bags;
    }

    private BagFareDTO getPassengerBagFare() {
        BagFareDTO PassengerBagFare = bagFares[bagFares.length - 1];
        for (BagFareDTO bagFare : bagFares) {
            if (passenger.getBags() <= bagFare.bagCount()) {
                PassengerBagFare = bagFare;
            }
        }
        return PassengerBagFare;
    }
}
