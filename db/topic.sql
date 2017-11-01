-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2017 at 08:47 PM
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
-- Database: `cs5012g4db`
--

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(5) NOT NULL,
  `title` text NOT NULL,
  `course` int(5) NOT NULL,
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isdelete` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `title`, `course`, `creation_time`, `isdelete`) VALUES
(1, 'this is topic1 ', 1, '2017-10-16 21:45:29', 0),
(2, 'this is topic2', 2, '2017-10-16 21:45:29', 1),
(3, 'topics3', 1, '2017-10-23 15:42:53', 0),
(4, 'topics4', 2, '2017-10-23 15:42:53', 0),
(5, 'topics5', 1, '2017-10-23 15:46:57', 0),
(6, 'topics7', 1, '2017-10-23 15:46:57', 0),
(7, 'topics6', 2, '2017-10-23 15:46:57', 0),
(8, 'topics8', 2, '2017-10-23 15:46:57', 0),
(9, 'topics9', 2, '2017-10-23 15:46:57', 0),
(10, 'topics10', 1, '2017-10-23 15:46:57', 0),
(11, 'topics12', 1, '2017-10-23 15:46:57', 0),
(12, 'topics11', 1, '2017-10-23 15:46:57', 0),
(13, 'topics14', 1, '2017-10-23 15:46:58', 0),
(14, 'topics23', 2, '2017-10-23 15:46:58', 0),
(15, 'topics13', 1, '2017-10-23 15:46:58', 0),
(16, 'topics15', 2, '2017-10-23 15:46:58', 0),
(17, 'topics16', 2, '2017-10-23 15:46:58', 0),
(18, 'topics17', 2, '2017-10-23 15:46:58', 0),
(19, 'topics18', 2, '2017-10-23 15:46:58', 0),
(20, 'topics19', 1, '2017-10-23 15:46:58', 0),
(21, 'topics20', 1, '2017-10-23 15:46:58', 1),
(22, 'topics21', 1, '2017-10-23 15:46:58', 0),
(23, 'topics22', 1, '2017-10-23 15:46:58', 0),
(24, 'topics24', 1, '2017-10-23 15:46:58', 0),
(25, 'topics25', 1, '2017-10-23 15:46:58', 0),
(26, 'topics26', 1, '2017-10-23 15:46:58', 0),
(27, 'topics27', 1, '2017-10-23 15:46:58', 0),
(28, 'topics28', 1, '2017-10-23 15:46:58', 0),
(29, 'topics29', 1, '2017-10-23 15:46:59', 0),
(30, 'topics30', 1, '2017-10-23 15:46:59', 0),
(31, 'topics31', 2, '2017-10-23 15:46:59', 0),
(32, 'topics32', 1, '2017-10-23 15:46:59', 0),
(33, 'topics33', 2, '2017-10-23 15:46:59', 0),
(34, 'topics34', 2, '2017-10-23 15:46:59', 0),
(35, 'topics35', 2, '2017-10-23 15:46:59', 0),
(36, 'topics36', 2, '2017-10-23 15:46:59', 0),
(37, 'topics37', 1, '2017-10-23 15:46:59', 0),
(38, 'topics39', 1, '2017-10-23 15:46:59', 0),
(39, 'topics38', 1, '2017-10-23 15:46:59', 0),
(40, 'topic59', 2, '2017-10-24 21:11:35', 0),
(41, 'jhgasdfhgasjdf', 1, '2017-10-25 23:14:38', 1),
(42, 'New Topic for Quiz', 1, '2017-10-26 15:59:31', 1),
(43, 'asdfsdf', 1, '2017-10-31 19:55:46', 1),
(44, 'qwerqwer', 1, '2017-11-01 01:52:30', 1),
(45, 'Topic60', 1, '2017-11-01 02:00:36', 1),
(46, 'asdfwefrf', 1, '2017-11-01 02:11:11', 1),
(47, 'asdasfasdf', 1, '2017-11-01 02:12:32', 1),
(48, 'new title', 1, '2017-11-01 16:42:54', 1),
(49, 'HelloWorld', 6, '2017-11-01 19:22:44', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`),
  ADD KEY `topic_course` (`course`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `topic_course` FOREIGN KEY (`course`) REFERENCES `course` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
