package model;

import java.time.LocalDate;

public class PassengerRequest {
    private long flightId;
    private int seatId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private int bags;

    public PassengerRequest(long flightId, String firstName, String lastName, LocalDate dateOfBirth, char gender, int bags) {
        this.flightId = flightId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bags = bags;
    }
    public PassengerRequest(long flightId, int seatId, String firstName, String lastName, LocalDate dateOfBirth, char gender, int bags) {
        this.flightId = flightId;
        this.seatId = seatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bags = bags;
    }

    public long getFlightId() {
        return flightId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public int getBags() {
        return bags;
    }

    @Override
    public String toString() {
        return "PassengerRequest{" +
                "flightId=" + flightId +
                ", seatId=" + seatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", bags=" + bags +
                '}';
    }
}
