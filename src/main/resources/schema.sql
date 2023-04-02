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

CREATE DATABASE flyme;

USE flyme;

--
-- CREATE Database User
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
-- DROP Tables
--

DROP TABLE IF EXISTS `bag_fares`;
DROP TABLE IF EXISTS `distance_fares`;
DROP TABLE IF EXISTS `payments`;
DROP TABLE IF EXISTS `passengers`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `flights`;
DROP TABLE IF EXISTS `flight_status`;
DROP TABLE IF EXISTS `plane_services`;
DROP TABLE IF EXISTS `seats`;
DROP TABLE IF EXISTS `services`;
DROP TABLE IF EXISTS `classes`;
DROP TABLE IF EXISTS `planes`;
DROP TABLE IF EXISTS `airports`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `countries`;

--
-- Table structure for table `bag_fares`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bag_fares` (
  `bag_count` int NOT NULL,
  `fee` decimal(7,2) NOT NULL,
  PRIMARY KEY (`bag_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classes`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `class_id` int NOT NULL,
  `name` varchar(25) NOT NULL,
  `checked_bags` int NOT NULL,
  `price_multiplier` decimal(5,2) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `countries`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `country_id` char(2) NOT NULL,
  `country_name` varchar(50) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `distance_fares`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distance_fares` (
  `min_distance` decimal(7,2) NOT NULL,
  `max_distance` decimal(7,2) NOT NULL,
  `price_multiplier` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `airports`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airports` (
  `airport_id` char(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `city` varchar(25) NOT NULL,
  `country` char(2) NOT NULL,
  `latitude` decimal(10,7) NOT NULL,
  `longitude` decimal(10,7) NOT NULL,
  PRIMARY KEY (`airport_id`),
  CONSTRAINT `airports_country_fk` FOREIGN KEY (`country`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `planes`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planes` (
  `plane_id` int NOT NULL,
  `registration` varchar(6) NOT NULL,
  `manufacturer` varchar(25) NOT NULL,
  `model` varchar(25) NOT NULL,
  `base_price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`plane_id`),
  UNIQUE KEY `registration` (`registration`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flight_status`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_status` (
  `status_id` int NOT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flights`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `flight_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `flight_number` char(6) NOT NULL,
  `origin` char(3) NOT NULL,
  `destination` char(3) NOT NULL,
  `plane_id` int NOT NULL,
  `departure` datetime NOT NULL,
  `arrival` datetime NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `flights_origin_idx` (`origin`),
  KEY `flights_destination_idx` (`destination`),
  CONSTRAINT `flights_destination_fk` FOREIGN KEY (`destination`) REFERENCES `airports` (`airport_id`),
  CONSTRAINT `flights_origin_fk` FOREIGN KEY (`origin`) REFERENCES `airports` (`airport_id`),
  CONSTRAINT `flights_plane_fk` FOREIGN KEY (`plane_id`) REFERENCES `planes` (`plane_id`),
  CONSTRAINT `flights_status_fk` FOREIGN KEY (`status_id`) REFERENCES `flight_status` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(64) NOT NULL,
  `birth_date` date NOT NULL,
  `nationality` char(2) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `phone` varchar(10) NOT NULL,
  `points` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_email_idx` (`email`),
  CONSTRAINT `users_country_fk` FOREIGN KEY (`nationality`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `confirmation_number` char(6) DEFAULT NULL,
  `user_id` bigint unsigned NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_user_idx` (`user_id`),
  CONSTRAINT `orders_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seats`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `plane_id` int NOT NULL,
  `row` int NOT NULL,
  `column` char(1) NOT NULL,
  `class_id` int NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `seats_plane_idx` (`plane_id`),
  KEY `seats_class_idx` (`class_id`),
  CONSTRAINT `seats_class_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`),
  CONSTRAINT `seats_plane_fk` FOREIGN KEY (`plane_id`) REFERENCES `planes` (`plane_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `passengers`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `passenger_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint unsigned NOT NULL,
  `flight_id` bigint unsigned NOT NULL,
  `seat_id` int NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `bags` int NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `passengers_flight_idx` (`flight_id`),
  KEY `passengers_order_idx` (`order_id`),
  KEY `passengers_seat_idx` (`seat_id`),
  CONSTRAINT `passengers_flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`flight_id`),
  CONSTRAINT `passengers_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `passengers_seat_fk` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payments`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `card_number` char(16) NOT NULL,
  `name` varchar(25) NOT NULL,
  `expiry_date` date NOT NULL,
  `security_code` varchar(3) NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `card_number` (`card_number`),
  KEY `payments_user_idx` (`user_id`),
  CONSTRAINT `payments_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `services`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL,
  `description` varchar(25) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plane_services`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane_services` (
  `plane_id` int NOT NULL,
  `class_id` int NOT NULL,
  `service_id` int NOT NULL,
  PRIMARY KEY (`plane_id`,`service_id`, `class_id`),
  KEY `plane_services_service_idx` (`service_id`),
  KEY `plane_services_class_idx` (`class_id`),
  CONSTRAINT `plane_services_class_fk` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`),
  CONSTRAINT `plane_services_plane_fk` FOREIGN KEY (`plane_id`) REFERENCES `planes` (`plane_id`),
  CONSTRAINT `plane_services_service_fk` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
