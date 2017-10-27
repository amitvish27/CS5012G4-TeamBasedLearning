-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2017 at 03:05 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(12) NOT NULL,
  `ssoid` varchar(12) NOT NULL,
  `pwd` varchar(20) DEFAULT 'umsl',
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `dept` varchar(15) DEFAULT NULL,
  `snumber` int(15) DEFAULT NULL,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `ssoid`, `pwd`, `fname`, `lname`, `role`, `email`, `dept`, `snumber`, `cdate`, `active`) VALUES
(1, '10288799', '', 'Yuneus', 'Tanudyaya', 1, 'ykt5g4@umsl.edu', 'CS', NULL, '2017-10-18 16:45:59', 1),
(3, '01276465', '', 'Jonathan', 'Bishop', 0, 'jb551w2@umsl.edu', 'Math', NULL, '2017-10-18 16:46:59', 0),
(13, '90203912', 'umsl', 'Linda', 'Vasquez', 0, NULL, NULL, NULL, '2017-10-22 22:32:49', 1),
(14, '65713400', 'umsl', 'Roberto', 'Carlos', 0, NULL, NULL, NULL, '2017-10-22 22:34:48', 0),
(17, '09999221', 'umsl', 'Claire', 'Underwood', 0, NULL, NULL, NULL, '2017-10-24 20:53:33', 1),
(18, '00230456', 'umsl', 'Frank', 'Underwood', 0, NULL, NULL, NULL, '2017-10-25 21:58:44', 1),
(19, '90923456', 'umsl', 'Doug', 'Stamper', 0, NULL, NULL, NULL, '2017-10-25 23:11:27', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
