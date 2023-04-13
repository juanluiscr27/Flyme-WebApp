
INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `birth_date`, `nationality`, `gender`, `phone`, `points`)
VALUES (NULL, 'John', 'Doe', 'john@email.com', 'Welcome2023', '1995-07-24', 'CA', 'M', '9876543210', 2000);

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `birth_date`, `nationality`, `gender`, `phone`, `points`)
VALUES (NULL, 'User', 'Test', 'user.test@email.com', 'Welcome2023', '1992-08-16', 'CA', 'F', '1234567890', 1000);

INSERT INTO `orders` (`order_id`, `confirmation_number`, `user_id`, `order_date`, `price`)
VALUES (NULL, 'e58ed763-928c-4155-bee9-fdbaaadc15f3', 21, '2023-03-24 10:15:36', 1200.0);

INSERT INTO `orders` (`order_id`, `confirmation_number`, `user_id`, `order_date`, `price`)
VALUES (NULL, 'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454', 21, '2023-04-12 16:25:08', 645.50);

INSERT INTO `passengers` (`passenger_id`,`order_id`,`flight_id`,`seat_id`,`first_name`,`last_name`,`birth_date`,`gender`,`bags`)
VALUES (NULL, 2, 1, 10, 'User4', 'Test', '1995-07-24', 'F', 1);

INSERT INTO `passengers` (`passenger_id`,`order_id`,`flight_id`,`seat_id`,`first_name`,`last_name`,`birth_date`,`gender`,`bags`)
VALUES (NULL, 3, 5, 32, 'User1', 'Test', '1995-07-24', 'M', 1);


-- Orders
SELECT o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price,
f.flight_id, f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.base_price,
f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, oa.longitude,
f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, da.latitude, da.longitude,
c.class_id, c.name, COUNT( s.seat_id ) AS seat , (
    SELECT COUNT(sqp.passenger_id)
    FROM passengers AS sqp
    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id
    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id
    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id
  ) AS passenger,
ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,
ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags,
  (SELECT COUNT(p.passenger_id)
  FROM passengers AS p
  WHERE p.seat_id = s.seat_id
  AND p.flight_id = f.flight_id) AS is_reserved
FROM orders AS o INNER JOIN passengers AS ps ON o.order_id = ps.order_id
INNER JOIN flights AS f ON ps.flight_id = f.flight_id
INNER JOIN seats AS s ON s.plane_id = f.plane_id
INNER JOIN classes AS c ON c.class_id = s.class_id
INNER JOIN planes AS p ON f.plane_id = p.plane_id
INNER JOIN airports AS oa ON f.origin = oa.airport_id
INNER JOIN countries AS oc ON oa.country = oc.country_id
INNER JOIN airports AS da ON f.destination = da.airport_id
INNER JOIN countries AS dc ON da.country = dc.country_id
INNER JOIN seats AS se ON ps.seat_id = se.seat_id
WHERE c.class_id = s.class_id AND ps.seat_id = s.seat_id AND o.user_id = 21
GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, f.flight_id, f.flight_number, f.plane_id, c.class_id, c.name, passenger, ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,
ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, is_reserved
HAVING passenger > 0;

-- Order
SELECT o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price,
f.flight_id, f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.base_price,
f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, oa.longitude,
f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, da.latitude, da.longitude,
c.class_id, c.name, COUNT( s.seat_id ) AS seat , (
    SELECT COUNT(sqp.passenger_id)
    FROM passengers AS sqp
    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id
    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id
    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id
  ) AS passenger,
ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,
ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags,
  (SELECT COUNT(p.passenger_id)
  FROM passengers AS p
  WHERE p.seat_id = s.seat_id
  AND p.flight_id = f.flight_id) AS is_reserved
FROM orders AS o INNER JOIN passengers AS ps ON o.order_id = ps.order_id
INNER JOIN flights AS f ON ps.flight_id = f.flight_id
INNER JOIN seats AS s ON s.plane_id = f.plane_id
INNER JOIN classes AS c ON c.class_id = s.class_id
INNER JOIN planes AS p ON f.plane_id = p.plane_id
INNER JOIN airports AS oa ON f.origin = oa.airport_id
INNER JOIN countries AS oc ON oa.country = oc.country_id
INNER JOIN airports AS da ON f.destination = da.airport_id
INNER JOIN countries AS dc ON da.country = dc.country_id
INNER JOIN seats AS se ON ps.seat_id = se.seat_id
WHERE c.class_id = s.class_id AND ps.seat_id = s.seat_id
AND o.order_id = 2 AND f.flight_id = (
  SELECT flight_id
  FROM passengers
  WHERE order_id = 2
  LIMIT 1
)
GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, f.flight_id, f.flight_number, f.plane_id, c.class_id, c.name, passenger, ps.passenger_id, s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,
ps.first_name, ps.last_name, ps.birth_date, ps.gender, ps.bags, is_reserved
HAVING passenger > 0;

-- Order Confirmation
SELECT o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price, COUNT(p.passenger_id) AS passengers_count
FROM orders AS o INNER JOIN passengers AS p ON o.order_id = p.order_id
WHERE o.order_id = 2
GROUP BY o.order_id, o.confirmation_number, o.user_id, o.order_date, o.price;

-- Seats --
SELECT s.seat_id, s.plane_id, s.row, s.column, s.class_id, c.name, c.checked_bags, c.price_multiplier,
  (SELECT COUNT(p.passenger_id)
  FROM passengers AS p
  WHERE p.seat_id = s.seat_id
  AND p.flight_id = 1) AS is_reserved
FROM seats AS s
INNER JOIN flights AS f ON s.plane_id = f.plane_id
INNER JOIN classes AS c ON s.class_id = c.class_id
WHERE f.flight_id = 1;

-- Passengers --
SELECT p.flight_id, c.name, COUNT(p.passenger_id) AS passengers
FROM passengers AS p
INNER JOIN seats AS s ON s.seat_id = p.seat_id
INNER JOIN classes AS c ON c.class_id = s.class_id
WHERE p.flight_id = 1
GROUP BY p.flight_id, c.name;

-- Flights --
SELECT
f.flight_id, f.flight_number, f.plane_id, f.status_id, p.registration, p.manufacturer, p.model, p.base_price,
f.departure, f.origin, oa.name, oa.city, oa.country, oc.country_name, oa.latitude, oa.longitude,
f.arrival, f.destination, da.name, da.city, da.country, dc.country_name, da.latitude, da.longitude,
c.class_id, c.name, COUNT( s.seat_id ) AS seat , (
    SELECT COUNT(sqp.passenger_id)
    FROM passengers AS sqp
    INNER JOIN seats AS sqs ON sqs.seat_id = sqp.seat_id
    LEFT JOIN classes AS sqc ON sqc.class_id = sqs.class_id
    WHERE sqc.class_id = c.class_id AND sqp.flight_id = f.flight_id
  ) AS passenger
FROM flights AS f
INNER JOIN seats AS s ON s.plane_id = f.plane_id
INNER JOIN classes AS c ON c.class_id = s.class_id
INNER JOIN planes AS p ON f.plane_id = p.plane_id
INNER JOIN airports AS oa ON f.origin = oa.airport_id
INNER JOIN countries AS oc ON oa.country = oc.country_id
INNER JOIN airports AS da ON f.destination = da.airport_id
INNER JOIN countries AS dc ON da.country = dc.country_id
WHERE f.origin = 'YYZ' AND f.destination = 'SDQ'
AND f.departure BETWEEN '2023-04-15 00:00:00' AND '2023-04-15 23:59:59'
AND f.status_id = 1
GROUP BY f.flight_id, f.flight_number, f.plane_id, c.class_id, c.name, passenger
HAVING seat - passenger >= 1;

-- Airports --
SELECT
a.airport_id, a.name, a.city, a.country, c.country_name, a.latitude, a.longitude
FROM airports AS a
INNER JOIN countries AS c ON a.country = c.country_id
WHERE a.airport_id LIKE ? OR LOWER(a.name) LIKE ?
OR LOWER(a.city) LIKE ? OR a.country LIKE ? OR c.country_name LIKE 1;

-- Users
SELECT user_id, first_name, last_name, email, password, birth_date, country_id, country_name, gender, phone, points
FROM users INNER JOIN countries ON nationality = country_id
WHERE user_id = 21;
