package repository;

import model.Confirmation;
import model.Flight;
import model.FlightClassDTO;
import model.Order;
import model.PassengerDTO;
import model.Reservation;
import model.SeatDTO;
import model.UserDTO;
import util.EntityMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static util.EntityMapper.mapPassenger;

public class OrderDAO implements OrderRepository {

    @Override
    public Confirmation add(Reservation reservation) {
        Connection transaction = DatabaseConnectionPool.getConnection();
        PreparedStatement insertStatement = null;
        PreparedStatement passengerStatement = null;
        PreparedStatement userStatement = null;
        PreparedStatement selectStatement = null;
        ResultSet keys = null;
        ResultSet resultSet = null;
        Confirmation orderConfirmation = null;
        try {
            transaction.setAutoCommit(false);
            // INSERT ORDER
            insertStatement = transaction.prepareStatement("INSERT INTO orders " +
                    "(order_id, confirmation_number, user_id, order_date, price) " +
                    "VALUES (NULL, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, reservation.getConfirmationNumber());
            insertStatement.setLong(2, reservation.getUser().getId());
            insertStatement.setDate(3, Date.valueOf(LocalDate.now()));
            insertStatement.setBigDecimal(4, reservation.getReceipt().getTotalPrice());

            insertStatement.executeUpdate();
            keys = insertStatement.getGeneratedKeys();
            keys.next();

            long orderId = keys.getLong("GENERATED_KEY");

            // INSERT PASSENGERS
            passengerStatement = transaction.prepareStatement("INSERT INTO passengers " +
                    "(passenger_id, order_id, flight_id, seat_id, first_name, last_name, birth_date, gender, bags) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?) ");
            for (PassengerDTO passenger: reservation.getFlightPassengers()) {
                passengerStatement.setLong(1, orderId);
                passengerStatement.setLong(2, reservation.getFlight().getId());
                passengerStatement.setInt(3, passenger.seat().seatId());
                passengerStatement.setString(4, passenger.firstName());
                passengerStatement.setString(5, passenger.lastName());
                passengerStatement.setDate(6, Date.valueOf(passenger.dateOfBirth()));
                passengerStatement.setString(7, Character.toString(switch (passenger.gender()) {
                    case "Female"-> 'F';
                    case  "Male" -> 'M';
                    default -> 'X';
                }));
                passengerStatement.setInt(8, passenger.bags());

                passengerStatement.executeUpdate();
            }

            // UPDATE USER
            userStatement = transaction.prepareStatement("UPDATE users "
                    + "SET points = ? WHERE user_id = ? ");

            userStatement.setInt(1, reservation.getUser().getPoints());
            userStatement.setLong(2, reservation.getUser().getId());

            userStatement.executeUpdate();

            // SELECT ORDER CONFIRMATION
            selectStatement = transaction.prepareStatement("SELECT " +
                    "o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, COUNT(p.passenger_id) AS passengers_count " +
                    "FROM orders AS o INNER JOIN passengers AS p ON o.order_id = p.order_id " +
                    "WHERE o.order_id = ? " +
                    "GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price ");

            selectStatement.setLong(1, orderId);

            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                orderConfirmation = new Confirmation(
                        resultSet.getLong("order_id"),
                        resultSet.getString("confirmation_number"),
                        resultSet.getLong("user_id"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getBigDecimal("price"),
                        resultSet.getInt("passenger_count")
                );
            }
            // COMMIT
            transaction.commit();
            transaction.setAutoCommit(true);
        } catch (SQLException e) {
            DatabaseConnectionPool.rollback(transaction);
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(keys);
            DatabaseConnectionPool.close(selectStatement);
            DatabaseConnectionPool.close(userStatement);
            DatabaseConnectionPool.close(passengerStatement);
            DatabaseConnectionPool.close(insertStatement);
            DatabaseConnectionPool.close(transaction);
        }
        return orderConfirmation;
    }

    @Override
    public Optional<Order> find(Long orderId) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Order order = null;
        try {
            statement = connection.prepareStatement("SELECT " +
                    "o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, f.flight_id, " +
                    "f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.base_price, " +
                    "f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, " +
                    "oa.longitude, f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, " +
                    "da.latitude, da.longitude, c.class_id, c.name, COUNT( s.seat_id ) AS seat, ( " +
                    "    SELECT COUNT(sqp.passenger_id) " +
                    "    FROM passengers AS sqp " +
                    "    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id " +
                    "    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id " +
                    "    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id " +
                    "  ) AS passenger, " +
                    "ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier, " +
                    "ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, " +
                    "  (SELECT COUNT(p.passenger_id) " +
                    "  FROM passengers AS p " +
                    "  WHERE p.seat_id = s.seat_id " +
                    "  AND p.flight_id = f.flight_id) AS is_reserved " +
                    "FROM orders AS o INNER JOIN passengers AS ps ON o.order_id = ps.order_id " +
                    "INNER JOIN flights AS f ON ps.flight_id = f.flight_id " +
                    "INNER JOIN seats AS s ON s.plane_id = f.plane_id " +
                    "INNER JOIN classes AS c ON c.class_id = s.class_id " +
                    "INNER JOIN planes AS p ON f.plane_id = p.plane_id " +
                    "INNER JOIN airports AS oa ON f.origin = oa.airport_id " +
                    "INNER JOIN countries AS oc ON oa.country = oc.country_id " +
                    "INNER JOIN airports AS da ON f.destination = da.airport_id " +
                    "INNER JOIN countries AS dc ON da.country = dc.country_id " +
                    "INNER JOIN seats AS se ON ps.seat_id = se.seat_id " +
                    "WHERE c.class_id = s.class_id AND ps.seat_id = s.seat_id " +
                    "AND o.order_id = ? AND f.flight_id = ( " +
                    "  SELECT flight_id " +
                    "  FROM passengers " +
                    "  WHERE order_id = ? " +
                    "  LIMIT 1 " +
                    ") " +
                    "GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, f.flight_id, " +
                    "f.flight_number, f.plane_id, c.class_id, c.name, passenger, ps.passenger_id, s.seat_id, " +
                    "s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier, " +
                    "ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, is_reserved " +
                    "HAVING passenger > 0 ");

            statement.setLong(1, orderId);
            statement.setLong(2, orderId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (order == null) {
                    order = EntityMapper.mapOrder(resultSet);
                    order.passengers().add(mapPassenger(resultSet));
                } else {
                    order.passengers().add(mapPassenger(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public Map<Long, Order> findAllByUser(UserDTO user) {
        Connection connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<Long, Order> orders = new HashMap<>();
        try {
            statement = connection.prepareStatement("SELECT o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, " +
                    "f.flight_id, f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.base_price, " +
                    "f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, oa.longitude, " +
                    "f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, da.latitude, da.longitude, " +
                    "c.class_id, c.name, COUNT( s.seat_id ) AS seat , ( " +
                    "    SELECT COUNT(sqp.passenger_id) " +
                    "    FROM passengers AS sqp " +
                    "    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id " +
                    "    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id " +
                    "    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id " +
                    "  ) AS passenger, " +
                    "ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier, " +
                    "ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, " +
                    "  (SELECT COUNT(p.passenger_id) " +
                    "  FROM passengers AS p " +
                    "  WHERE p.seat_id = s.seat_id " +
                    "  AND p.flight_id = f.flight_id) AS is_reserved  " +
                    "FROM orders AS o INNER JOIN passengers AS ps ON o.order_id = ps.order_id " +
                    "INNER JOIN flights AS f ON ps.flight_id = f.flight_id " +
                    "INNER JOIN seats AS s ON s.plane_id = f.plane_id " +
                    "INNER JOIN classes AS c ON c.class_id = s.class_id " +
                    "INNER JOIN planes AS p ON f.plane_id = p.plane_id " +
                    "INNER JOIN airports AS oa ON f.origin = oa.airport_id " +
                    "INNER JOIN countries AS oc ON oa.country = oc.country_id " +
                    "INNER JOIN airports AS da ON f.destination = da.airport_id " +
                    "INNER JOIN countries AS dc ON da.country = dc.country_id " +
                    "INNER JOIN seats AS se ON ps.seat_id = se.seat_id " +
                    "WHERE c.class_id = s.class_id AND ps.seat_id = s.seat_id AND o.user_id = ? " +
                    "GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, f.flight_id, f.flight_number, f.plane_id, c.class_id, c.name, passenger, ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,\n" +
                    "ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, is_reserved " +
                    "HAVING passenger > 0 ");

            statement.setLong(1, user.id());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long orderId = resultSet.getLong("o.order_id");
                if (orders.containsKey(orderId)){
                    orders.get(orderId).passengers().add(mapPassenger(resultSet));
                } else {
                    Order newOrder = EntityMapper.mapOrder(resultSet);
                    orders.put(orderId, newOrder);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(statement);
            DatabaseConnectionPool.close(connection);
        }
        return orders;
    }

    @Override
    public void delete(Order order) {
        Connection transaction = DatabaseConnectionPool.getConnection();
        PreparedStatement userStatement = null;
        PreparedStatement updateStatement = null;
        PreparedStatement passengerStatement = null;
        PreparedStatement orderStatement = null;
        ResultSet resultSet = null;
        try {

            transaction.setAutoCommit(false);
            // UPDATE USER
            userStatement = transaction.prepareStatement("SELECT " +
                    "points " +
                    "FROM users WHERE user_id = ? ");

            userStatement.setLong(1, order.userId());

            resultSet = userStatement.executeQuery();

            updateStatement = transaction.prepareStatement("UPDATE users "
                    + "SET points = ? WHERE user_id = ? ");

            while (resultSet.next()) {
                int points = resultSet.getInt("points");
                int previousPoints = points - order.price().intValue()/100;

                updateStatement.setInt(1, previousPoints);
                updateStatement.setLong(2, order.userId());
                userStatement.executeUpdate();
            }

            // DELETE PASSENGERS
            passengerStatement = transaction.prepareStatement("DELETE FROM passengers WHERE order_id = ? ");
            passengerStatement.setLong(1, order.Id());
            passengerStatement.executeUpdate();

            // DELETE ORDER
            orderStatement = transaction.prepareStatement("DELETE FROM orders WHERE order_id = ? ");

            orderStatement.setLong(1, order.Id());
            orderStatement.executeUpdate();

            transaction.commit();
            transaction.setAutoCommit(true);
        } catch (SQLException e) {
            DatabaseConnectionPool.rollback(transaction);
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnectionPool.close(resultSet);
            DatabaseConnectionPool.close(userStatement);
            DatabaseConnectionPool.close(updateStatement);
            DatabaseConnectionPool.close(passengerStatement);
            DatabaseConnectionPool.close(orderStatement);
            DatabaseConnectionPool.close(transaction);
        }
    }
}
