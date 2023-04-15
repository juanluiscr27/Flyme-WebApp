package model;

import org.junit.Before;
import org.junit.Test;
import repository.FlightDAO;
import repository.FlightRepository;
import service.FlightService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ReceiptTest {
    Receipt receipt;
    Flight flight;
    PassengerDTO[] passengers;
    SeatDTO[] flightSeats;
    @Before
    public void setUpReceiptTest() {
        FlightRepository flightRepo = new FlightDAO();
        FlightService flightService = new FlightService(flightRepo);

        BagFareDTO[] bagFares = {
                new BagFareDTO(1, BigDecimal.valueOf(50.00)),
                new BagFareDTO(2, BigDecimal.valueOf(80.00)),
                new BagFareDTO(3, BigDecimal.valueOf(120.00)),
                new BagFareDTO(4, BigDecimal.valueOf(175.00))
        };

        DistanceFareDTO[] distanceFares = {
                new DistanceFareDTO(
                        BigDecimal.valueOf(0),
                        BigDecimal.valueOf(500),
                        BigDecimal.valueOf(1.00)
                ),
                new DistanceFareDTO(
                        BigDecimal.valueOf(501),
                        BigDecimal.valueOf(1000),
                        BigDecimal.valueOf(1.10)
                ),
                new DistanceFareDTO(
                        BigDecimal.valueOf(1001),
                        BigDecimal.valueOf(2501),
                        BigDecimal.valueOf(1.20)
                ),
                new DistanceFareDTO(
                        BigDecimal.valueOf(2501),
                        BigDecimal.valueOf(5000),
                        BigDecimal.valueOf(1.30)
                ),
                new DistanceFareDTO(
                        BigDecimal.valueOf(5001),
                        BigDecimal.valueOf(10000),
                        BigDecimal.valueOf(1.40)
                ),
                new DistanceFareDTO(
                        BigDecimal.valueOf(10001),
                        BigDecimal.valueOf(20000),
                        BigDecimal.valueOf(1.50)
                )
        };


        receipt = new Receipt(bagFares, distanceFares);

        flight = new Flight(
                1,
                "FM1112",
                new AirportDTO(
                        "YYZ",
                        "Toronto Pearson International Airport",
                        "Toronto",
                        new CountryDTO("CA", "Canada"),
                        new Coordinate(BigDecimal.valueOf(43.6777176),
                                BigDecimal.valueOf(-79.6248196))
                ), new AirportDTO(
                "SDQ",
                "Las Americas International Airport",
                "Santo Domingo",
                new CountryDTO("DO", "Dominican Republic"),
                new Coordinate(
                        BigDecimal.valueOf(18.4302189),
                        BigDecimal.valueOf(-69.6771733))),
                new AirPlaneDTO(
                        1, "C-FAAA","Boeing", "747", BigDecimal.valueOf(250)),
                LocalDateTime.parse("2023-03-30T10:30:00"),
                LocalDateTime.parse("2023-03-30T14:50:00")
        );

        SeatClassDTO seatClass = new SeatClassDTO(
                1,
                "First",
                2,
                BigDecimal.valueOf(1.50),
                false
        );

        SeatDTO seat = new SeatDTO(
                3,
                1,
                1,
                'H',
                seatClass
        );

        flightSeats = flightService.findAllFlightSeats(flight);

        passengers = new PassengerDTO[] {new PassengerDTO(
                flight,
                seat,
                "John",
                "Doe",
                LocalDate.parse("1995-07-24"),
                "Male",
                3
        )};
    }
    @Test
    public void testGenerateTickets() {
        /* basePrice = flight.airPlane.basePrice()
         * flightClassFare = flightClass.priceMultiplier()
         * distanceFare = flightDistance.priceMultiplier()
         * bagFee = | passenger.bagCount.fee() - flightClass.checkedBags.fee() |
         */
        receipt.generateTickets(passengers);

        // ticketPrice =  basePrice * flightClassFare * distanceFare + bagFee
        BigDecimal expectedPrice = BigDecimal.valueOf(607.50).setScale(2, RoundingMode.HALF_UP);

        BigDecimal actualPrice = receipt.getSubTotal();

        assertEquals(expectedPrice, actualPrice);
    }
}