-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 19, 2023 at 06:15 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pnmovies`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `payment_method_id` int NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `booked_seat` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `playing_time` time NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `location_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `name`, `image`, `start_date`, `end_date`, `playing_time`, `price`, `location_id`) VALUES
(1, 'The Avengers', 'image1.jpg', '2023-06-20 00:00:00', '2023-06-30 00:00:00', '02:30:00', 75000.00, 1),
(2, 'Inception', 'image2.jpg', '2023-06-21 00:00:00', '2023-07-01 00:00:00', '02:28:00', 30000.00, 2),
(3, 'The Shawshank Redemption', 'image3.jpg', '2023-06-22 00:00:00', '2023-07-02 00:00:00', '02:22:00', 25000.00, 3),
(4, 'Pulp Fiction', 'image4.jpg', '2023-06-23 00:00:00', '2023-07-03 00:00:00', '02:34:00', 40000.00, 1),
(5, 'The Godfather', 'image5.jpg', '2023-06-24 00:00:00', '2023-07-04 00:00:00', '02:55:00', 40000.00, 2),
(6, 'Titanic', 'image6.jpg', '2023-06-25 00:00:00', '2023-07-05 00:00:00', '03:14:00', 55000.00, 3),
(7, 'Jurassic Park', 'image7.jpg', '2023-06-26 00:00:00', '2023-07-06 00:00:00', '02:07:00', 60000.00, 1),
(8, 'The Lion King', 'image8.jpg', '2023-06-27 00:00:00', '2023-07-07 00:00:00', '01:58:00', 20000.00, 2),
(9, 'Interstellar', 'image9.jpg', '2023-06-28 00:00:00', '2023-07-08 00:00:00', '02:49:00', 45000.00, 3),
(10, 'The Dark Knight', 'image10.jpg', '2023-06-29 00:00:00', '2023-07-09 00:00:00', '02:32:00', 15000.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment_methods`
--

CREATE TABLE `payment_methods` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment_methods`
--

INSERT INTO `payment_methods` (`id`, `name`) VALUES
(1, 'ShopeePay'),
(2, 'OVO'),
(3, 'GoPay');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `nrp` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nrp`, `password`, `name`) VALUES
(1, '123456789', '456', 'VictorJago'),
(2, '987654321', '321', 'jane.smith'),
(3, '246813579', '234', 'alex.wilson'),
(4, '135792468', '456', 'emily.jackson'),
(6, '220116969', '123', 'Victor'),
(8, '220115678', '123', 'Nicho'),
(9, '220116789', '321', 'JJ_Jago'),
(11, '220116906', '123', 'Antonio Christopher'),
(12, '220115665', '123', 'Nicholas Hartono');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `movies_locations_id_fk` (`location_id`);

--
-- Indexes for table `payment_methods`
--
ALTER TABLE `payment_methods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_pk` (`nrp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `payment_methods`
--
ALTER TABLE `payment_methods`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_locations_id_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
