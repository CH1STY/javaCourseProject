-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2019 at 09:48 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `did` int(10) NOT NULL,
  `salary` int(20) DEFAULT NULL,
  `doctorName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`did`, `salary`, `doctorName`) VALUES
(404, NULL, 'NO DOCTOR');

-- --------------------------------------------------------

--
-- Table structure for table `filetable`
--

CREATE TABLE `filetable` (
  `fileId` int(11) NOT NULL,
  `fileType` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `mid` int(20) NOT NULL,
  `medicineName` varchar(30) DEFAULT NULL,
  `medicinePrice` int(5) DEFAULT NULL,
  `stock` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`mid`, `medicineName`, `medicinePrice`, `stock`) VALUES
(1, 'Oxygen Cylinder', 2000, 15),
(3, 'NAPA', 10, 300),
(4, 'ORSALINE', 5, 120),
(5, 'Syringe', 100, 75),
(6, 'NORMAL SALINE', 85, 175),
(7, 'HIGH DOSED SALINE', 500, 25),
(8, 'Gimax 500', 220, 150),
(9, 'Actrapid (Insulin-100ML)', 1400, 15),
(10, 'Medical Mask', 5, 200),
(11, 'Diabetic Milk', 850, 25),
(12, 'Catheter', 195, 85),
(13, 'BED Sheet', 85, 150),
(14, 'Diaper', 110, 55);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `pid` int(20) NOT NULL,
  `patientName` varchar(20) DEFAULT NULL,
  `roomNo` int(4) DEFAULT NULL,
  `patientPhone` varchar(25) DEFAULT NULL,
  `patientAddress` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`pid`, `patientName`, `roomNo`, `patientPhone`, `patientAddress`) VALUES
(2000, 'ExPatient', 6525, '0175845655', 'Kakrail');

-- --------------------------------------------------------

--
-- Table structure for table `puser`
--

CREATE TABLE `puser` (
  `uid` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(2000) DEFAULT NULL,
  `mobile_number` varchar(11) DEFAULT NULL,
  `usertype` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `puser`
--

INSERT INTO `puser` (`uid`, `username`, `password`, `address`, `mobile_number`, `usertype`) VALUES
(1111, 'Admin', 'admin', 'KHILGAON', '01911111111', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`did`);

--
-- Indexes for table `filetable`
--
ALTER TABLE `filetable`
  ADD PRIMARY KEY (`fileId`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`mid`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `puser`
--
ALTER TABLE `puser`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `uid` (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `did` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=405;

--
-- AUTO_INCREMENT for table `filetable`
--
ALTER TABLE `filetable`
  MODIFY `fileId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `mid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `pid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2001;

--
-- AUTO_INCREMENT for table `puser`
--
ALTER TABLE `puser`
  MODIFY `uid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1112;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
