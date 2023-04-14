package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight  implements Serializable {
    long id;
    String flightNumber;
    AirportDTO origin;
    AirportDTO destination;
    AirPlaneDTO airPlane;
    LocalDateTime departure;
    LocalDateTime arrival;
    List<FlightClassDTO> classes;

    public Flight(long id,
                  String flightNumber,
                  AirportDTO origin,
                  AirportDTO destination,
                  AirPlaneDTO airPlane,
                  LocalDateTime departure,
                  LocalDateTime arrival) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.airPlane = airPlane;
        this.departure = departure;
        this.arrival = arrival;
        this.classes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public AirportDTO getOrigin() {
        return origin;
    }

    public void setOrigin(AirportDTO origin) {
        this.origin = origin;
    }

    public AirportDTO getDestination() {
        return destination;
    }

    public void setDestination(AirportDTO destination) {
        this.destination = destination;
    }

    public AirPlaneDTO getAirPlane() {
        return airPlane;
    }

    public void setAirPlane(AirPlaneDTO airPlane) {
        this.airPlane = airPlane;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public List<FlightClassDTO> getClasses() {
        return classes;
    }

    public void addFlightClass(FlightClassDTO flightClass) {
        this.classes.add(flightClass);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flight_number='" + flightNumber + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", airPlane=" + airPlane +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", classes=" + classes +
                '}';
    }
}
