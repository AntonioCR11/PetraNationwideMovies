/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.21-MariaDB : Database - pnmovies
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pnmovies` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `pnmovies`;

/*Table structure for table `bookings` */

DROP TABLE IF EXISTS `bookings`;

CREATE TABLE `bookings` (
<<<<<<< Updated upstream
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `payment_method_id` int(11) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `booked_seat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookings_movies_id_fk` (`movie_id`),
  KEY `bookings_payment_methods_id_fk` (`payment_method_id`),
  KEY `bookings_users_id_fk` (`user_id`),
  CONSTRAINT `bookings_movies_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `bookings_payment_methods_id_fk` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`),
  CONSTRAINT `bookings_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bookings` */
=======
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) NOT NULL,
                            `movie_id` int(11) NOT NULL,
                            `payment_method_id` int(11) NOT NULL,
                            `total_price` decimal(10,2) NOT NULL,
                            `booked_seat` int(11) NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `bookings_movies_id_fk` (`movie_id`),
                            KEY `bookings_payment_methods_id_fk` (`payment_method_id`),
                            KEY `bookings_users_id_fk` (`user_id`),
                            CONSTRAINT `bookings_movies_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
                            CONSTRAINT `bookings_payment_methods_id_fk` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`),
                            CONSTRAINT `bookings_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

>>>>>>> Stashed changes

/*Table structure for table `locations` */

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
<<<<<<< Updated upstream
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building` varchar(255) NOT NULL,
  `room` varchar(255) NOT NULL,
  `total_seat` int(11) NOT NULL,
  PRIMARY KEY (`id`)
=======
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `building` varchar(255) NOT NULL,
                             `room` varchar(255) NOT NULL,
                             `total_seat` int(11) NOT NULL,
                             PRIMARY KEY (`id`)
>>>>>>> Stashed changes
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `locations` */

<<<<<<< Updated upstream
insert  into `locations`(`id`,`building`,`room`,`total_seat`) values 
(1,'Q','Amphitheater',100),
(2,'T','AVT.502',100),
(3,'P','P.707',100);
=======
insert  into `locations`(`id`,`building`,`room`,`total_seat`) values
                                                                  (1,'Q','Amphitheater',100),
                                                                  (2,'T','AVT.502',100),
                                                                  (3,'P','P.707',100);
>>>>>>> Stashed changes

/*Table structure for table `movies` */

DROP TABLE IF EXISTS `movies`;

CREATE TABLE `movies` (
<<<<<<< Updated upstream
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `playing_time` time NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `movies_locations_id_fk` (`location_id`),
  CONSTRAINT `movies_locations_id_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `movies` */

insert  into `movies`(`id`,`name`,`image`,`start_date`,`end_date`,`playing_time`,`price`,`location_id`) values 
(1,'The Avengers','https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-20 00:00:00','2023-06-30 00:00:00','02:30:00',42186.00,1),
(2,'Inception','https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-21 00:00:00','2023-07-01 00:00:00','02:28:00',68258.00,2),
(3,'The Shawshank Redemption','https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-22 00:00:00','2023-07-02 00:00:00','02:22:00',39733.00,3),
(4,'Pulp Fiction','https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-23 00:00:00','2023-07-03 00:00:00','02:34:00',68892.00,1),
(5,'The Godfather','https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-24 00:00:00','2023-07-04 00:00:00','02:55:00',50258.00,2),
(6,'Titanic','https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-25 00:00:00','2023-07-05 00:00:00','03:14:00',69618.00,3),
(7,'Jurassic Park','https://m.media-amazon.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-26 00:00:00','2023-07-06 00:00:00','02:07:00',72312.00,1),
(8,'The Lion King','https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-27 00:00:00','2023-07-07 00:00:00','01:58:00',27706.00,2),
(9,'Interstellar','https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-28 00:00:00','2023-07-08 00:00:00','02:49:00',46597.00,3),
(10,'The Dark Knight','https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg','2023-06-29 00:00:00','2023-07-09 00:00:00','02:49:00',74866.00,1);

/*Table structure for table `payment_methods` */

DROP TABLE IF EXISTS `payment_methods`;

CREATE TABLE `payment_methods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
=======
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) NOT NULL,
                          `image` varchar(255) DEFAULT NULL,
                          `start_date` datetime NOT NULL,
                          `end_date` datetime NOT NULL,
                          `playing_time` time NOT NULL,
                          `price` decimal(10,2) NOT NULL,
                          `location_id` int(11) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `movies_locations_id_fk` (`location_id`),
                          CONSTRAINT `movies_locations_id_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


/*Table structure for table `payment_methods` */

DROP TABLE IF EXISTS `payment_methods`;

CREATE TABLE `payment_methods` (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `name` varchar(255) NOT NULL,
                                   PRIMARY KEY (`id`)
>>>>>>> Stashed changes
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `payment_methods` */

<<<<<<< Updated upstream
insert  into `payment_methods`(`id`,`name`) values 
(1,'ShopeePay'),
(2,'OVO'),
(3,'GoPay');
=======
insert  into `payment_methods`(`id`,`name`) values
                                                (1,'ShopeePay'),
                                                (2,'OVO'),
                                                (3,'GoPay');
>>>>>>> Stashed changes

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
<<<<<<< Updated upstream
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nrp` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_pk` (`nrp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */
=======
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `nrp` varchar(15) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `users_pk` (`nrp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

>>>>>>> Stashed changes

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
<<<<<<< Updated upstream
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
=======
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
>>>>>>> Stashed changes
