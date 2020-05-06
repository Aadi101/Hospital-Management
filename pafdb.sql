-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 08:51 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pafdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hosid` int(11) NOT NULL,
  `hosname` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `prov` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `pc` int(11) DEFAULT NULL,
  `phn` int(11) DEFAULT NULL,
  `er` tinyint(1) DEFAULT NULL,
  `surg` tinyint(1) DEFAULT NULL,
  `xray` tinyint(1) DEFAULT NULL,
  `lab` tinyint(1) DEFAULT NULL,
  `gov` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hosid`, `hosname`, `email`, `prov`, `city`, `pc`, `phn`, `er`, `surg`, `xray`, `lab`, `gov`) VALUES
(1, 'Asiri Hospital', 'asiri51@gmail.com', 'Southern', 'Matara', 81000, 412234675, 0, 1, 0, 1, 0),
(2, 'Matara General', 'glgnrtl@gmail.com', 'Southern', 'Matara', 81000, 41367839, 1, 1, 1, 1, 1),
(3, 'Matara General', 'glgnrtl@gmail.com', 'Southern', 'Malabe', 81000, 41367839, 1, 1, 1, 1, 1),
(5, 'Jaffna Genaral', 'jafgnrl@gmail.com', 'Eastern', 'Jaffna', 10020, 3156790, 1, 1, 0, 0, 1),
(6, 'Gampaha Genaral', 'gamgnrl@gmail.com', 'Western', 'Gampaha', 53000, 1156790, 1, 1, 0, 0, 1),
(7, 'Hell Genaral', 'hellgnrl@gmail.com', 'South Hell', 'Sin City', 121212, 66666666, 1, 1, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hosid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
