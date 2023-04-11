
INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `birth_date`, `nationality`, `gender`, `phone`, `points`)
VALUES (NULL, 'User', 'Test', 'user.test@email.com', 'Welcome2023', '1995-07-24', 'CA', 'M', '1234567890', 0);

INSERT INTO `orders` (`order_id`, `confirmation_number`, `user_id`, `order_date`, `price`)
VALUES (NULL, 'WXZUTM', 3, '2023-03-24 10:15:36', 1200.0);

INSERT INTO `passengers` (`passenger_id`,`order_id`,`flight_id`,`seat_id`,`first_name`,`last_name`,`birth_date`,`gender`,`bags`)
VALUES (NULL, 1, 1, 8, 'User', 'Test', '1995-07-24', 'M', 1);

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
f.flight_id, f.flight_number, f.plane_id, p.registration, p.manufacturer, p.model, p.price_multiplier,
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
AND f.departure BETWEEN '2023-03-30 00:00:00' AND '2023-03-30 23:59:59'
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
