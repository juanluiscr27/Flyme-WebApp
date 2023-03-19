package model;

import java.time.LocalDateTime;

public class Flight {
    long flight_id;
    String flight_number;
    String origin;
    String destination;
    int plane_id;
    LocalDateTime departure;
    LocalDateTime arrival;

    public Flight(long flight_id,
                  String flight_number,
                  String origin,
                  String destination,
                  int plane_id,
                  LocalDateTime departure,
                  LocalDateTime arrival) {
        this.flight_id = flight_id;
        this.flight_number = flight_number;
        this.origin = origin;
        this.destination = destination;
        this.plane_id = plane_id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(long flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
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

    @Override
    public String toString() {
        return "Flight{" +
                "flight_id=" + flight_id +
                ", flight_number='" + flight_number + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", plane_id=" + plane_id +
                ", departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
