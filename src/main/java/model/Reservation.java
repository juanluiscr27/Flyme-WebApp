package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reservation {
    private User user;
    private final Flight flight;
    private SeatDTO[] flightSeats;
    private PassengerDTO[] passengers;
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

    public PassengerDTO[] getFlightPassengers() {
        return passengers;
    }

    public void setFlightPassengers(PassengerRequest[] passengers) {

        List<PassengerDTO> passengerList = new ArrayList<>();

        for (PassengerRequest passenger : passengers) {
            for (SeatDTO seat : flightSeats) {
                if (passenger.getSeatId() == seat.seatId()) {
                    PassengerDTO newPassenger = new PassengerDTO(
                            flight,
                            seat,
                            passenger.getFirstName(),
                            passenger.getLastName(),
                            passenger.getDateOfBirth(),
                            switch (passenger.getGender()) {
                                case 'F' -> "Female";
                                case 'M' -> "Male";
                                default -> "Other";
                                },
                            passenger.getBags()
                    );
                    passengerList.add(newPassenger);
                    break;
                }
            }
        }

        this.passengers = passengerList.toArray(new PassengerDTO[0]);
    }

    public Receipt getReceipt() {
        receipt.generateTickets(passengers);
        user.setPoints(user.getPoints() + receipt.getTotalPrice().intValue()/100);
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String getConfirmationNumber() {
        UUID uuid = UUID.randomUUID();
        return  uuid.toString();
    }
}
