 -- CSD 4464 – Programming Java EE
 -- Term Project - Airline Reservation System
 --
 -- Host: 127.0.0.1    Database: Flyme
 -- ------------------------------------------------------
 -- MySQL Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Create Database
--

DROP TABLE IF EXISTS `user`;
CREATE DATABASE flyme;

USE flyme;

--
-- CREATE USER --
--

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'secret';

-- GRANT Global Privileges
GRANT SHOW DATABASES, RELOAD
ON *.*
TO 'admin'@'localhost';

-- GRANT Database Privileges
GRANT SELECT, INSERT, UPDATE, DELETE,
  CREATE, INDEX, ALTER, EXECUTE,
  CREATE VIEW, SHOW VIEW, CREATE ROUTINE,
  ALTER ROUTINE, EVENT, TRIGGER, REFERENCES
ON flyme.*
TO 'admin'@'localhost';

FLUSH PRIVILEGES;

-- SHOW GRANTS FOR 'admin'@'localhost';

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;



-- ----------------------
-- Drop tables if exist -
-- ---------------------- 

DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS distance_fares;
DROP TABLE IF EXISTS bag_fares;
DROP TABLE IF EXISTS passengers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS plane_services;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS planes;
DROP TABLE IF EXISTS airports;
DROP TABLE IF EXISTS users;

-- Table countries
CREATE TABLE countries (
	country_id VARCHAR(2) PRIMARY KEY,
	country_name VARCHAR(25) NOT NULL
);

-- Table payments
CREATE TABLE payments (
	payment_id VARCHAR(16) PRIMARY KEY,
	expiry_date VARCHAR(4) NOT NULL,
	name VARCHAR(25) NOT NULL,
	security_code VARCHAR(3) NOT NULL
);

-- Table users
CREATE TABLE users (
	email VARCHAR(25) PRIMARY KEY,
	password VARCHAR(20) NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
	birth_date DATE NOT NULL,
	nationality VARCHAR(2) NOT NULL,
	genre VARCHAR(15),
	phone VARCHAR(10) NOT NULL,
	payment_id VARCHAR(16),
    points INTEGER,
	CONSTRAINT users_countries_fk FOREIGN KEY (nationality) REFERENCES countries(country_id),
	CONSTRAINT users_payments_fk FOREIGN KEY (payment_id) REFERENCES payments(payment_id)
);

-- Table airports
CREATE TABLE airports (
    airport_id VARCHAR(3) PRIMARY KEY,
    city VARCHAR(25) NOT NULL,
    country VARCHAR(25) NOT NULL,
	latitude DECIMAL NOT NULL,
	longitude DECIMAL NOT NULL
);

-- Table planes
CREATE TABLE planes (
    plane_id VARCHAR(6) PRIMARY KEY,
	manufacturer VARCHAR(20) NOT NULL,
	model VARCHAR(20) NOT NULL,
	base_price DECIMAL NOT NULL
);

-- Table classes
CREATE TABLE classes (
	class_id INTEGER PRIMARY KEY,
	bags INTEGER NOT NULL,
	price_multiplier DECIMAL NOT NULL
);

-- Table services
CREATE TABLE services (
	service_id INTEGER PRIMARY KEY,
	description VARCHAR(25) NOT NULL
);

-- Table seats
CREATE TABLE seats (
	seat_id INTEGER NOT NULL,
	plane_id VARCHAR(6) NOT NULL,
	class_id INTEGER NOT NULL,
	PRIMARY KEY(seat_id, plane_id),
	CONSTRAINT seats_plane_fk FOREIGN KEY (plane_id) REFERENCES planes(plane_id),
	CONSTRAINT seats_class_fk FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

-- Table plane_services
CREATE TABLE plane_services (
	plane_id VARCHAR(6) NOT NULL,
	service_id INTEGER NOT NULL,
	class_id INTEGER NOT NULL,
	PRIMARY KEY(plane_id, service_id),
	CONSTRAINT plane_services_plane_fk FOREIGN KEY (plane_id) REFERENCES planes(plane_id),
	CONSTRAINT plane_services_services_fk FOREIGN KEY (service_id) REFERENCES services(service_id),
	CONSTRAINT plane_services_class_fk FOREIGN KEY (class_id) REFERENCES classes(class_id)
);

-- Table flights
CREATE TABLE flights (
	flight_id INTEGER PRIMARY KEY,
	id_from VARCHAR(3) NOT NULL,
	id_to VARCHAR(3) NOT NULL,
	plane_id VARCHAR(6) NOT NULL,
	flight_date DATE NOT NULL,
	CONSTRAINT flights_airports_from_fk FOREIGN KEY (id_from) REFERENCES airports(airport_id),
	CONSTRAINT flights_airports_to_fk FOREIGN KEY (id_to) REFERENCES airports(airport_id),
	CONSTRAINT flights_planes_fk FOREIGN KEY (plane_id) REFERENCES planes(plane_id)
);

-- Table orders
CREATE TABLE orders (
	order_id INTEGER PRIMARY KEY,
	user_id VARCHAR(25) NOT NULL,
	flight_id INTEGER NOT NULL,
	round_trip BOOLEAN NOT NULL,
	price DECIMAL NOT NULL,
	CONSTRAINT orders_users_fk FOREIGN KEY (user_id) REFERENCES users(email),
	CONSTRAINT orders_flights_fk FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

-- Table passengers
CREATE TABLE passengers (
    passenger_id INTEGER NOT NULL,
    flight_id INTEGER NOT NULL,
    order_id INTEGER NOT NULL,
	seat_id INTEGER NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
	birth_date DATE NOT NULL,
	genre VARCHAR(15),
	bags INTEGER NOT NULL,
	PRIMARY KEY(passenger_id, flight_id),
	CONSTRAINT passengers_flights_fk FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    CONSTRAINT passengers_orders_fk FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT passengers_seats_fk FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

-- Table bag_fares
CREATE TABLE bag_fares (
	bag_count INTEGER PRIMARY KEY,
	price DECIMAL NOT NULL
);

-- Table distance_fares
CREATE TABLE distance_fares (
	min_distance INTEGER NOT NULL,
	max_distance INTEGER NOT NULL,
	price_multiplier DECIMAL NOT NULL
);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;