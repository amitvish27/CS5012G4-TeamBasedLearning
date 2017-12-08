-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2017 at 07:20 PM
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
-- Database: `cs5012projdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(5) NOT NULL COMMENT 'internal record id',
  `code` varchar(10) NOT NULL COMMENT 'course code (e.g.CS5012)',
  `title` varchar(30) NOT NULL COMMENT 'course title ',
  `year` int(4) DEFAULT NULL COMMENT 'course year provided',
  `semester` varchar(10) DEFAULT NULL COMMENT 'course semester provided',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'dateTime when record created',
  `instructor` varchar(10) DEFAULT NULL COMMENT 'sso id of the instructor who created the course',
  `deleted` int(1) DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `code`, `title`, `year`, `semester`, `created`, `instructor`, `deleted`) VALUES
(1, 'NU3886', 'Pharmacology & Nursing Care', 2016, 'Winter', '2017-11-04 22:38:22', 'jh6q3t', 0),
(2, 'MA3153', 'Introduction To Partial Differ', 2017, 'Summer', '2017-11-04 22:38:22', 'jppdle', 0),
(3, 'CS1198', 'Computer Science Principles', 2016, 'Winter', '2017-11-04 22:38:22', 'fr5rae', 0),
(4, 'IS3624', 'Concepts Of Information System', 2016, 'Summer', '2017-11-04 22:38:22', 'instructor', 0),
(5, 'NU2357', 'Psychiatric Mental Health Nurs', 2017, 'Winter', '2017-11-04 22:38:23', 'instructor', 0),
(6, 'BS1337', 'Financial Reporting And Contro', 2016, 'Spring', '2017-11-04 22:38:23', 'jppdle', 0),
(7, 'AR1638', 'Introduction To Visual Thinkin', 2016, 'Winter', '2017-11-04 22:38:23', 'jh6q3t', 0),
(8, 'CS1759', 'Computer Programming I', 2015, 'Summer', '2017-11-04 22:38:23', 'fr5rae', 0),
(9, 'AR1359', 'The Language Of Drawing', 2015, 'Fall', '2017-11-04 22:38:23', 'yt1234', 0),
(10, 'MA2448', 'Probability', 2017, 'Winter', '2017-11-04 22:38:23', 'fr5rae', 0),
(11, 'IS2108', 'Technology Consulting In The G', 2016, 'Fall', '2017-11-04 22:38:23', 'jh6q3t', 0),
(12, 'MA1923', 'Intermediate Analysis', 2016, 'Fall', '2017-11-04 22:38:23', 'jppdle', 0),
(13, 'NU3704', 'Pathophysiology', 2017, 'Fall', '2017-11-04 22:38:23', 'jh6q3t', 0),
(14, 'FI3098', 'Monetary Economics And The Glo', 2015, 'Summer', '2017-11-04 22:38:23', 'fr5rae', 0),
(15, 'BS1254', 'Leadership And Organizational ', 2017, 'Winter', '2017-11-04 22:38:23', 'jh6q3t', 0),
(16, 'AR2169', 'Approaches To Printmaking', 2015, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(17, 'FI3734', 'Advanced Corporate Finance', 2016, 'Winter', '2017-11-04 22:38:24', 'instructor', 0),
(18, 'AR2013', 'Beginning Digital Photo', 2016, 'Spring', '2017-11-04 22:38:24', 'jppdle', 0),
(19, 'AR1414', 'Moving Image Media', 2017, 'Summer', '2017-11-04 22:38:24', 'instructor', 0),
(20, 'AR2145', 'Collaborative Innovation: Big ', 2017, 'Summer', '2017-11-04 22:38:24', 'jh6q3t', 0),
(21, 'CS1307', 'Web Programming', 2016, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(22, 'AR1113', 'Global Perspectives In Contemp', 2016, 'Winter', '2017-11-04 22:38:24', 'jh6q3t', 0),
(23, 'MA2867', 'Advanced Multivariable Calculu', 2015, 'Summer', '2017-11-04 22:38:24', 'jppdle', 0),
(24, 'FI1938', 'Investment Management', 2017, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(25, 'CS2703', 'Data Programming', 2016, 'Summer', '2017-11-04 22:38:24', 'jh6q3t', 0),
(26, 'FI3233', 'Financial Derivatives', 2016, 'Winter', '2017-11-04 22:38:25', 'jh6q3t', 0),
(27, 'MA1682', 'Precalculus', 2015, 'Summer', '2017-11-04 22:38:25', 'asv123', 0),
(28, 'IS3828', 'Electronic BusinessNew', 2015, 'Fall', '2017-11-04 22:38:25', 'instructor', 0),
(29, 'IS2098', 'Information Systems Freshman W', 2016, 'Winter', '2017-11-04 22:38:25', 'instructor', 0),
(30, 'FI2292', 'International Financial Market', 2015, 'Spring', '2017-11-04 22:38:25', 'asv123', 0),
(31, 'CS5012', 'WebDevelopment', 2017, 'Fall', '2017-11-08 18:45:35', 'instructor', 1),
(32, 'CS5023', 'WebDevelopment', 2017, 'Fall', '2017-11-13 17:17:37', 'instructor', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_inst`
--

CREATE TABLE `course_inst` (
  `relnid` int(5) NOT NULL COMMENT 'internal record id',
  `courseid` int(5) NOT NULL COMMENT 'fk to course internal id',
  `instid` varchar(10) NOT NULL COMMENT 'fk to user internal id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(6) NOT NULL COMMENT 'internal record id',
  `content` text NOT NULL COMMENT 'content of the question',
  `opt_a` text NOT NULL COMMENT 'option a',
  `opt_b` text NOT NULL COMMENT 'option b',
  `opt_c` text NOT NULL COMMENT 'option c',
  `opt_d` text NOT NULL COMMENT 'option d',
  `answer` int(1) NOT NULL COMMENT '1-opt_a,2-opt_b,3-opt_c,4-opt_d',
  `kywd` text COMMENT 'Keywords for Question',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time when record created',
  `instructorid` varchar(10) DEFAULT NULL COMMENT 'sso id instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `content`, `opt_a`, `opt_b`, `opt_c`, `opt_d`, `answer`, `kywd`, `created`, `instructorid`, `deleted`) VALUES
(1, 'What is 2+2?', '5', '4', '2', '1', 2, 'math', '2017-11-15 12:25:24', 'instructor', 0),
(2, 'What is 3*2?', '4', '5', '6', '7', 3, 'math', '2017-11-15 12:25:24', 'instructor', 0),
(3, 'Grand Central Terminal, Park Avenue, New York is the world\'s', 'largest railway station', 'highest railway station', 'longest railway station', 'None of the above', 1, 'general', '2017-11-15 12:38:19', 'instructor', 0),
(4, 'Entomology is the science that studies', 'Behavior of human beings', 'Insects', 'The origin and history of technical and scientific terms', 'The formation of rocks', 2, 'science', '2017-11-15 12:50:45', 'instructor', 1);

-- --------------------------------------------------------

--
-- Table structure for table `question_course`
--

CREATE TABLE `question_course` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `questionid` int(5) NOT NULL COMMENT 'fk to question id',
  `courseid` int(5) NOT NULL COMMENT 'fk to course id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `quest_quiz`
--

CREATE TABLE `quest_quiz` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `questid` int(6) NOT NULL COMMENT 'fk to question id',
  `quizid` int(6) NOT NULL COMMENT 'fk to quiz id',
  `questnumber` int(6) NOT NULL COMMENT 'fk to question number',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `id` int(6) NOT NULL COMMENT 'internal record id',
  `courseid` int(5) NOT NULL COMMENT 'fk to course',
  `number` int(5) NOT NULL COMMENT 'number of the quiz',
  `time_limit` int(5) NOT NULL COMMENT 'time limit of the quiz in mins',
  `start_time` datetime NOT NULL COMMENT 'start time when quiz started',
  `end_time` datetime NOT NULL COMMENT 'time when quiz ends',
  `created` int(11) NOT NULL COMMENT 'created timestamp',
  `instructorid` int(5) NOT NULL COMMENT 'sso id of instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted?0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sgroup`
--

CREATE TABLE `sgroup` (
  `groupid` int(6) NOT NULL COMMENT 'internal record id',
  `courseid` int(6) NOT NULL COMMENT 'fk to course',
  `groupnumber` int(6) NOT NULL COMMENT 'group number',
  `instructorid` varchar(10) NOT NULL COMMENT 'instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sgroup`
--

INSERT INTO `sgroup` (`groupid`, `courseid`, `groupnumber`, `instructorid`, `deleted`) VALUES
(1, 30, 1, 'instructor', 0),
(2, 30, 2, 'instructor', 0),
(3, 30, 3, 'instructor', 0),
(4, 30, 4, 'instructor', 0),
(5, 30, 5, 'instructor', 0),
(6, 30, 6, 'instructor', 0),
(7, 29, 1, 'instructor', 0),
(8, 29, 2, 'instructor', 0),
(9, 30, 7, 'instructor', 0),
(10, 30, 8, 'instructor', 0),
(11, 30, 9, 'instructor', 1),
(12, 30, 9, 'instructor', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sgroup_quiz`
--

CREATE TABLE `sgroup_quiz` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `groupid` int(6) NOT NULL COMMENT 'fk to group id',
  `quizid` int(6) NOT NULL COMMENT 'fk to quiz id',
  `token` varchar(6) NOT NULL COMMENT 'unique token alphanumeric random generated',
  `deleted` int(1) NOT NULL COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_course`
--

CREATE TABLE `student_course` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `studentid` varchar(10) NOT NULL COMMENT 'fk to student in user table',
  `courseid` int(5) NOT NULL COMMENT 'fk to course id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_course`
--

INSERT INTO `student_course` (`relnid`, `studentid`, `courseid`, `deleted`) VALUES
(1, 'student', 30, 0),
(2, 'ar2y89', 30, 0),
(3, 'arksk0', 30, 0),
(4, 'ar2y89', 29, 0),
(5, 'cjqxc2', 29, 0),
(6, 'dcjlpi', 29, 0),
(7, 'dcrs4p', 29, 1),
(8, 'dt4pyv', 29, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_sgroup`
--

CREATE TABLE `student_sgroup` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `studentid` varchar(10) NOT NULL COMMENT 'fk to student in user table',
  `groupid` int(6) NOT NULL COMMENT 'fk to group id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted?0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_sgroup`
--

INSERT INTO `student_sgroup` (`relnid`, `studentid`, `groupid`, `deleted`) VALUES
(1, 'student', 1, 0),
(2, 'ar2y89', 9, 0),
(3, 'arksk0', 9, 0),
(4, 'eawdq0', 9, 0),
(5, 'ewxf91', 9, 0),
(6, 'grxy71', 9, 0),
(7, 'jmgyjb', 9, 0),
(8, 'dcjlpi', 1, 0),
(9, 'dt4pyv', 1, 0),
(10, 'eajdgi', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(5) NOT NULL COMMENT 'internal record id',
  `title` varchar(60) NOT NULL COMMENT 'topic title',
  `courseid` int(5) NOT NULL COMMENT 'fk to course',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time when created',
  `instructorid` varchar(10) DEFAULT NULL COMMENT 'sso id of the instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `title`, `courseid`, `created`, `instructorid`, `deleted`) VALUES
(1, 'Fundamentals of Arts & Culture Writing', 7, '2017-11-05 01:11:47', 'offn92', 0),
(2, 'Popular Music Criticism', 19, '2017-11-05 01:11:47', 'offn92', 0),
(3, 'Writing About Television', 22, '2017-11-05 01:11:47', 'instructor', 0),
(4, 'How to Put Stories Together', 18, '2017-11-05 01:11:47', 'tfyvww', 0),
(5, 'Film Criticism', 20, '2017-11-05 01:11:48', 'lbwl17', 0),
(6, 'Non-Popular Music Forms/Theater', 9, '2017-11-05 01:11:48', 'offn92', 0),
(7, 'Visual Arts ', 18, '2017-11-05 01:11:48', 'instructor', 0),
(8, 'Othello ', 9, '2017-11-05 01:11:48', 'fr5rae', 0),
(9, 'Architecture', 19, '2017-11-05 01:11:48', 'offn92', 0),
(10, 'Trend & Enterprise Pieces', 19, '2017-11-05 01:11:48', 'tfyvww', 0),
(11, 'Freelance Pitches & Writing Gigs', 9, '2017-11-05 01:11:48', 'jppdle', 0),
(12, 'Rationale for Business Studies ', 6, '2017-11-05 01:11:48', 'fr5rae', 0),
(13, 'Nature of business', 15, '2017-11-05 01:11:48', 'or7hui', 0),
(14, 'Business management', 6, '2017-11-05 01:11:48', 'or7hui', 0),
(15, 'Business planning', 15, '2017-11-05 01:11:48', 'or7hui', 0),
(16, 'Operations', 15, '2017-11-05 01:11:48', 'fr5rae', 0),
(17, 'Marketing', 6, '2017-11-05 01:11:48', 'lbwl17', 0),
(18, 'Understanding business activity', 15, '2017-11-05 01:11:48', 'fr5rae', 0),
(19, 'People in business', 6, '2017-11-05 01:11:48', 'lbwl17', 0),
(20, 'Financial information and decisions', 15, '2017-11-05 01:11:48', 'fr5rae', 0),
(21, 'External influences on business activity ', 15, '2017-11-05 01:11:48', 'jh6q3t', 0),
(22, 'Branching And Iteration', 25, '2017-11-05 01:11:48', 'instructor', 0),
(23, 'String Manipulation', 25, '2017-11-05 01:11:48', 'instructor', 0),
(24, 'Approximations', 8, '2017-11-05 01:11:48', 'instructor', 0),
(25, 'Decomposition', 21, '2017-11-05 01:11:48', 'instructor', 0),
(26, 'Abstractions', 8, '2017-11-05 01:11:48', 'jppdle', 0),
(27, 'Functions', 8, '2017-11-05 01:11:49', 'jppdle', 0),
(28, 'Tuples', 21, '2017-11-05 01:11:49', 'nt669p', 0),
(29, 'Recursion', 3, '2017-11-05 01:11:49', 'nt669p', 0),
(30, 'Dictionaries', 3, '2017-11-05 01:11:49', 'jh6q3t', 0),
(31, 'Testing', 21, '2017-11-05 01:11:49', 'nt669p', 0),
(32, 'Debugging', 21, '2017-11-05 01:11:49', 'fr5rae', 0),
(33, 'Exceptions', 25, '2017-11-05 01:11:49', 'jh6q3t', 0),
(34, 'Assertions', 25, '2017-11-05 01:11:49', 'fr5rae', 0),
(35, 'Object Oriented Programming', 21, '2017-11-05 01:11:49', 'jh6q3t', 0),
(36, 'Python Classes And Inheritance', 25, '2017-11-05 01:11:49', 'nt669p', 0),
(37, 'Understanding Program Efficiency', 8, '2017-11-05 01:11:49', 'lbwl17', 0),
(38, 'Understanding Program Efficiency', 8, '2017-11-05 01:11:49', 'fr5rae', 0),
(39, 'Searching And Sorting', 21, '2017-11-05 01:11:49', 'instructor', 0),
(40, 'Computer Architecture And Organisation', 25, '2017-11-05 01:11:49', 'offn92', 0),
(41, 'Problem-Solving With Computers', 21, '2017-11-05 01:11:49', 'jppdle', 0),
(42, 'Software Engineering', 8, '2017-11-05 01:11:49', 'jh6q3t', 0),
(43, 'Introduction To Financial Management', 30, '2017-11-05 01:11:49', 'nt669p', 0),
(44, 'Time Value Of Money', 26, '2017-11-05 01:11:49', 'jh6q3t', 0),
(45, 'Discounted Cash Flow Valuation', 17, '2017-11-05 01:11:49', 'instructor', 0),
(46, 'Bond Valuation', 14, '2017-11-05 01:11:49', 'lbwl17', 0),
(47, 'Stock Valuation', 24, '2017-11-05 01:11:49', 'instructor', 0),
(48, 'Investment Criteria', 24, '2017-11-05 01:11:49', 'tfyvww', 0),
(49, 'Making Capital Decisions', 17, '2017-11-05 01:11:49', 'instructor', 0),
(50, 'Capital Market History', 30, '2017-11-05 01:11:50', 'or7hui', 0),
(51, 'Risk & Return', 17, '2017-11-05 01:11:50', 'nt669p', 0),
(52, 'Cost Of Capital', 30, '2017-11-05 01:11:50', 'nt669p', 0),
(53, 'Capital Structure', 14, '2017-11-05 01:11:50', 'jppdle', 0),
(54, 'Financial Statements Basics', 24, '2017-11-05 01:11:50', 'lbwl17', 0),
(55, 'Analysis Of Financial Statements', 30, '2017-11-05 01:11:50', 'lbwl17', 0),
(56, 'Dividends And Dividends Policy', 14, '2017-11-05 01:11:50', 'or7hui', 0),
(57, 'Raising Capital', 30, '2017-11-05 01:11:50', 'lbwl17', 0),
(58, 'Intro To Information Systems', 11, '2017-11-05 01:11:50', 'lbwl17', 0),
(59, 'Types Of Information Systems', 28, '2017-11-05 01:11:50', 'lbwl17', 0),
(60, 'It Infrastructure', 11, '2017-11-05 01:11:50', 'fr5rae', 0),
(61, 'Ethical And Social Issues In Information Systems', 28, '2017-11-05 01:11:50', 'jppdle', 0),
(62, 'Business Intelligence', 11, '2017-11-05 01:11:50', 'fr5rae', 0),
(63, 'Social Tools & Web 2.0', 29, '2017-11-05 01:11:50', 'jppdle', 0),
(64, 'Managing Knowledge', 29, '2017-11-05 01:11:50', 'jppdle', 0),
(65, 'Building Systems', 4, '2017-11-05 01:11:50', 'jh6q3t', 0),
(66, 'It Outsourcing Cloud Computing & Saas', 29, '2017-11-05 01:11:50', 'or7hui', 0),
(67, 'Open Source', 4, '2017-11-05 01:11:50', 'lbwl17', 0),
(68, 'Computation', 12, '2017-11-05 01:11:50', 'jppdle', 0),
(69, 'Number Theory', 10, '2017-11-05 01:11:50', 'nt669p', 0),
(70, 'Consumer Arithmetic', 27, '2017-11-05 01:11:50', 'instructor', 0),
(71, 'Sets', 12, '2017-11-05 01:11:50', 'jppdle', 0),
(72, 'Measurement', 10, '2017-11-05 01:11:51', 'nt669p', 0),
(73, 'Statistics', 2, '2017-11-05 01:11:51', 'fr5rae', 0),
(74, 'Algebra', 27, '2017-11-05 01:11:51', 'jh6q3t', 0),
(75, 'Relations', 2, '2017-11-05 01:11:51', 'instructor', 0),
(76, 'Geometry And Trigonometry', 2, '2017-11-05 01:11:51', 'instructor', 0),
(77, 'Vectors And Matrices', 10, '2017-11-05 01:11:51', 'jh6q3t', 0),
(78, 'Basic Arithmetic And Algebra', 23, '2017-11-05 01:11:51', 'tfyvww', 0),
(79, 'Plane Geometry', 10, '2017-11-05 01:11:51', 'instructor', 0),
(80, 'Probability', 12, '2017-11-05 01:11:51', 'tfyvww', 0),
(81, 'Trigonometric Ratios', 2, '2017-11-05 01:11:51', 'instructor', 0),
(82, 'Linear Functions And Lines', 27, '2017-11-05 01:11:51', 'lbwl17', 0),
(83, 'Series And Applications', 27, '2017-11-05 01:11:51', 'nt669p', 0),
(84, 'Geometrical Applications Of Differentiation', 12, '2017-11-05 01:11:51', 'fr5rae', 0),
(85, 'Integration', 10, '2017-11-05 01:11:51', 'instructor', 0),
(86, 'Logarithmic And Exponential Functions', 12, '2017-11-05 01:11:51', 'jppdle', 0),
(87, 'Applications Of Calculus To The Physical World', 2, '2017-11-05 01:11:51', 'jppdle', 0),
(88, 'Polynomials', 12, '2017-11-05 01:11:51', 'jh6q3t', 0),
(89, 'Binomial Theorem', 27, '2017-11-05 01:11:51', 'jppdle', 0),
(90, 'Permutations', 10, '2017-11-05 01:11:51', 'jh6q3t', 0),
(91, 'Psychosocial Functional Patterns And Communication Skills', 5, '2017-11-05 01:11:51', 'lbwl17', 0),
(92, 'Health Management Pattern—Assessment', 5, '2017-11-05 01:11:51', 'jppdle', 0),
(93, 'Cognitve-Perceptual Pattern And The Aging Adult', 13, '2017-11-05 01:11:51', 'tfyvww', 0),
(94, 'Introduction To Pharmacology And Medication Management', 5, '2017-11-05 01:11:51', 'instructor', 0),
(95, 'Health Management Pattern—Asepsis And Safety', 5, '2017-11-05 01:11:51', 'nt669p', 0),
(96, 'Activity-Exercise', 5, '2017-11-05 01:11:52', 'instructor', 0),
(97, 'Nutritional-Metabolic Pattern', 1, '2017-11-05 01:11:52', 'nt669p', 0),
(98, 'Activity-Exercise Pattern&Oxygenation', 13, '2017-11-05 01:11:52', 'instructor', 0),
(99, 'Ajax&Jquery', 31, '2017-11-10 01:04:14', 'instructor', 0),
(100, 'ABC', 30, '2017-12-04 18:32:51', 'yt1234', 0);

-- --------------------------------------------------------

--
-- Table structure for table `topic_inst`
--

CREATE TABLE `topic_inst` (
  `relnid` int(5) NOT NULL COMMENT 'internal record id',
  `topicid` int(5) NOT NULL COMMENT 'fk to topic',
  `instructorid` varchar(10) NOT NULL COMMENT 'fk to instructor',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL COMMENT 'Internal table id, AutoIncrement',
  `ssoid` varchar(10) NOT NULL COMMENT 'sso id for login and session details',
  `pswd` varchar(50) DEFAULT NULL COMMENT 'password',
  `fname` varchar(60) NOT NULL COMMENT 'first name of the user',
  `lname` varchar(60) DEFAULT NULL COMMENT 'last name of the user',
  `email` varchar(50) NOT NULL COMMENT 'email of the user',
  `dept` varchar(50) DEFAULT NULL COMMENT 'department name',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'time when the record was created',
  `createdby` varchar(10) DEFAULT NULL COMMENT 'sso id of the user who created the record',
  `modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'modified timestamp',
  `modifiedby` varchar(10) DEFAULT NULL COMMENT 'sso id of the user who modified',
  `deleted` int(1) DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes',
  `role` int(1) DEFAULT NULL COMMENT 'role 0-student, 1-instructor, 2-admin',
  `active` int(1) DEFAULT '0' COMMENT 'active? 0-Inactive, 1-active, 2-firstTimeLogin',
  `snumber` int(11) DEFAULT NULL COMMENT 'Student Number'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `created`, `createdby`, `modified`, `modifiedby`, `deleted`, `role`, `active`, `snumber`) VALUES
(1, 'student', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Ada', 'Hill', 'ada.hill@mail.com', 'Finance', '2017-11-04 21:24:58', 'asv123', '2017-11-04 21:24:58', 'asv123', 0, 0, 1, 15787575),
(4, 'ar2y89', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Addie', 'Reyes', 'addie.reyes@mail.com', 'Business', '2017-11-04 21:41:33', 'yh1234', '2017-11-04 21:41:33', 'yh1234', 0, 0, 1, 91893072),
(5, 'arksk0', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Agnes', 'Rogers', 'agnes.rogers@mail.com', 'Arts', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1, 48840382),
(6, 'cdgsyj', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Claude', 'Dixon', 'claude.dixon@mail.com', 'Mathematics', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1, 20609289),
(7, 'instructor', 'iRh7dcqt6ynMBP3DJVQ2iQ==:Q8A6igGsj19jDu7jBpSgEQ==', 'Clyde', 'Patel', 'amitvish27@gmail.com', 'Nursing', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'instructor', 0, 1, 1, 29878310),
(8, 'cjqxc2', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Cora', 'Jackson', 'cora.jackson@mail.com', 'Computer Science', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'yt1234', 0, 0, 1, 83011409),
(9, 'dt4pyv', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Daisy', 'Turner', 'daisy.turner@mail.com', 'Mathematics', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'yt1234', 0, 0, 1, 80964404),
(10, 'dcjlpi', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Dan', 'Cunningham', 'dan.cunningham@mail.com', 'Information Systems', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1, 91510632),
(11, 'dmsb34', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Daniel', 'Marshall', 'daniel.marshall@mail.com', 'Finance', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1, 14587116),
(12, 'dcxvbb', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'David', 'Chavez', 'david.chavez@mail.com												', 'Mathematics', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 0, 1, 50327199),
(13, 'dcrs4p', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Della', 'Cook', 'della.cook@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1, 98902944),
(14, 'dcqqma', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Dora', 'Carter', 'dora.carter@mail.com', 'Finance', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1, 18019053),
(15, 'ewxf91', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Earl', 'Wagner', 'earl.wagner@mail.com', 'Arts', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1, 61682089),
(16, 'ehr4rz', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Ed', 'Hicks', 'ed.hicks@mail.com', 'Mathematics', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1, 15386407),
(17, 'eh3gwr', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Edgar', 'Holmes', 'edgar.holmes@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1, 78055387),
(18, 'er2z7u', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Edith', 'Robinson', 'edith.robinson@mail.com', 'Arts', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1, 94379705),
(19, 'eajdgi', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Edna', 'Adams', 'edna.adams@mail.com', 'Computer Science', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1, 45092069),
(20, 'eawdq0', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Edward', 'Alexander', 'edward.alexander@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 0, 1, 47856084),
(21, 'ehtewx', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Edwin', 'Hunt', 'edwin.hunt@mail.com', 'Arts', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1, 31649040),
(22, 'fr5rae', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Frank', 'Reynolds', 'frank.reynolds@mail.com', 'Mathematics', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 1, 1, 58412412),
(23, 'fwmzfm', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Fred', 'West', 'fred.west@mail.com', 'Business', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1, 89475618),
(24, 'fwniq8', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Frederick', 'Woods', 'frederick.woods@mail.com', 'Finance', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1, 60666236),
(25, 'gjpys5', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'George', 'Jordan', 'george.jordan@mail.com', 'Computer Science', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1, 68982635),
(26, 'grrtcu', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Georgia', 'Ross', 'georgia.ross@mail.com', 'Nursing', '2017-11-04 21:41:35', 'yh1234', '2017-11-04 21:41:35', 'yh1234', 0, 0, 1, 46612095),
(27, 'ghlc4a', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Gertrude', 'Harris', 'gertrude.harris@mail.com', 'Computer Science', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1, 31630758),
(28, 'ghh9fw', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Grace', 'Hernandez', 'grace.hernandez@mail.com', 'Arts', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 0, 1, 98947322),
(29, 'grxy71', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Guy', 'Rose', 'guy.rose@mail.com', 'Business', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1, 27251370),
(30, 'hmxzjy', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Harriet', 'Myers', 'harriet.myers@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1, 72357446),
(31, 'jkxeed', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Jessie', 'King', 'jessie.king@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1, 83044210),
(32, 'jppdle', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Jessie', 'Peters', 'jessie.peters@mail.com', 'Finance', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 1, 1, 29606632),
(33, 'jmgyjb', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Jim', 'Mason', 'jim.mason@mail.com', 'Arts', '2017-11-04 21:41:35', 'yh1234', '2017-11-04 21:41:35', 'yh1234', 0, 0, 1, 86241530),
(34, 'jbioiy', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Joe', 'Bryant', 'joe.bryant@mail.com', 'Information Systems', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1, 70081336),
(35, 'jh6q3t', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'John', 'Henderson', 'john.henderson@mail.com', 'Computer Science', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 1, 1, 56489774),
(36, 'jh8nww', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Joseph', 'Hamilton', 'joseph.hamilton@mail.com', 'Nursing', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1, 77428044),
(37, 'jeebw5', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Josephine', 'Evans', 'josephine.evans@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1, 41183189),
(38, 'jht0e6', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Julia', 'Hall', 'julia.hall@mail.com', 'Nursing', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 0, 1, 46116764),
(39, 'jc460i', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Julius', 'Castro', 'julius.castro@mail.com', 'Computer Science', '2017-11-04 21:41:36', 'asv123', '2017-11-04 21:41:36', 'asv123', 0, 0, 1, 64773235),
(40, 'kl5aug', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Kate', 'Long', 'kate.long@mail.com', 'Mathematics', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1, 75459299),
(41, 'kcijns', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Katherine', 'Collins', 'katherine.collins@mail.com', 'Nursing', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1, 71917263),
(42, 'kb2cwk', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Kathryn', 'Butler', 'kathryn.butler@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1, 75814241),
(43, 'khfh27', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Katie', 'Hughes', 'katie.hughes@mail.com', 'Mathematics', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1, 33634703),
(44, 'ltkke1', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Laura', 'Thompson', 'laura.thompson@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1, 11515494),
(45, 'lwce7f', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Lulu', 'Watson', 'lulu.watson@mail.com', 'Finance', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1, 99986624),
(46, 'lbfthp', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Luther', 'Boyd', 'luther.boyd@mail.com', 'Computer Science', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1, 53124427),
(47, 'lbwl17', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Lydia', 'Brooks', 'lydia.brooks@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 1, 1, 15820669),
(48, 'mg1f78', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Mabel', 'Gonzalez', 'mabel.gonzalez@mail.com', 'Business', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1, 89142911),
(49, 'mwazy6', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Mae', 'Wood', 'mae.wood@mail.com', 'Arts', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1, 52967001),
(50, 'mpi7sb', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Maggie', 'Parker', 'maggie.parker@mail.com', 'Finance', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1, 49220813),
(51, 'mbwu14', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Mamie', 'Bailey', 'mamie.bailey@mail.com', 'Nursing', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1, 55276118),
(52, 'mjb98p', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Margaret', 'Jones', 'margaret.jones@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1, 86228944),
(53, 'nrpoqt', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Nancy', 'Rivera', 'nancy.rivera@mail.com', 'Nursing', '2017-11-04 21:41:36', 'asv123', '2017-11-04 21:41:36', 'asv123', 0, 0, 1, 88084085),
(55, 'ns7sfr', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Nannie', 'Sanders', 'nannie.sanders@mail.com', 'Arts', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1, 15912972),
(56, 'nt669p', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Nellie', 'Thomas', 'nellie.thomas@mail.com', 'Mathematics', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1, 15830275),
(57, 'nmp4iv', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Nettie', 'Morgan', 'nettie.morgan@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'yt1234', 0, 0, 1, 60660035),
(58, 'nfiyol', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Nora', 'Flores', 'nora.flores@mail.com', 'Arts', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'yt1234', 0, 0, 1, 73530353),
(59, 'offn92', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Olive', 'Fisher', 'olive.fisher@mail.com', 'Mathematics', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1, 79493783),
(60, 'orsg1b', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Oliver', 'Rice', 'oliver.rice@mail.com', 'Information Systems', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 0, 1, 87337004),
(61, 'or7hui', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Oscar', 'Ruiz', 'oscar.ruiz@mail.com', 'Nursing', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1, 10011160),
(62, 'ome7r3', 'i/oz662we/EjaczkmP0fCA==:BjjC1j8awKykKo+awkXtOQ==', 'Otto', 'Meyer', 'otto.meyer@mail.com', 'Finance', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'ome7r3', 0, 0, 1, 68848417),
(63, 'rykv5t', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Rose', 'Young', 'rose.young@mail.com', 'Business', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1, 43411663),
(64, 'rwrg4c', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Roy', 'Webb', 'roy.webb@mail.com', 'Arts', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1, 88450069),
(65, 'tgc0oh', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Thomas', 'Graham', 'thomas.graham@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1, 32863142),
(66, 'tfyvww', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Tom', 'Freeman', 'tom.freeman@mail.com', 'Arts', '2017-11-04 21:46:36', 'fz1234', '2017-11-04 21:46:36', 'fz1234', 0, 1, 1, 51642760),
(67, 'vsv0p0', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Viola', 'Sullivan', 'viola.sullivan@mail.com', 'Arts', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 0, 1, 72861845),
(68, 'wwcel0', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Walter', 'Wallace', 'walter.wallace@mail.com', 'Information Systems', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1, 51169244),
(69, 'asv123', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Ameet', 'Vishwakarma', 'ameet.vishwakarma@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 2, 1, 43526252),
(70, 'yh1234', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Yang', 'Huang', 'yang.huang@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1, NULL),
(71, 'yt1234', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Yuneus', 'Tanudyaya', 'yuneus.tanudyaya@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1, NULL),
(72, 'fz1234', 'z8wu3FgaSQXplSyAWd+UDA==:rqMGlGj7foeYP7NvdWELvA==', 'Frank', 'Zhao', 'frank.zhao@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`,`year`,`semester`) USING BTREE;

--
-- Indexes for table `course_inst`
--
ALTER TABLE `course_inst`
  ADD PRIMARY KEY (`relnid`),
  ADD KEY `fk_course` (`courseid`),
  ADD KEY `fk_course_inst_instid` (`instid`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question_course`
--
ALTER TABLE `question_course`
  ADD PRIMARY KEY (`relnid`);

--
-- Indexes for table `quest_quiz`
--
ALTER TABLE `quest_quiz`
  ADD PRIMARY KEY (`relnid`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sgroup`
--
ALTER TABLE `sgroup`
  ADD PRIMARY KEY (`groupid`);

--
-- Indexes for table `sgroup_quiz`
--
ALTER TABLE `sgroup_quiz`
  ADD PRIMARY KEY (`relnid`);

--
-- Indexes for table `student_course`
--
ALTER TABLE `student_course`
  ADD PRIMARY KEY (`relnid`);

--
-- Indexes for table `student_sgroup`
--
ALTER TABLE `student_sgroup`
  ADD PRIMARY KEY (`relnid`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_topic_course_id` (`courseid`);

--
-- Indexes for table `topic_inst`
--
ALTER TABLE `topic_inst`
  ADD PRIMARY KEY (`relnid`),
  ADD KEY `fk_topic_inst_topicid` (`topicid`),
  ADD KEY `fk_topic_inst_instid` (`instructorid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `SSOID` (`ssoid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `course_inst`
--
ALTER TABLE `course_inst`
  MODIFY `relnid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `question_course`
--
ALTER TABLE `question_course`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `quest_quiz`
--
ALTER TABLE `quest_quiz`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `sgroup`
--
ALTER TABLE `sgroup`
  MODIFY `groupid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `sgroup_quiz`
--
ALTER TABLE `sgroup_quiz`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `student_sgroup`
--
ALTER TABLE `student_sgroup`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT for table `topic_inst`
--
ALTER TABLE `topic_inst`
  MODIFY `relnid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Internal table id, AutoIncrement', AUTO_INCREMENT=73;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `course_inst`
--
ALTER TABLE `course_inst`
  ADD CONSTRAINT `fk_course` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `fk_course_inst_instid` FOREIGN KEY (`instid`) REFERENCES `user` (`ssoid`);

--
-- Constraints for table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `fk_topic_course_id` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`);

--
-- Constraints for table `topic_inst`
--
ALTER TABLE `topic_inst`
  ADD CONSTRAINT `fk_topic_inst_instid` FOREIGN KEY (`instructorid`) REFERENCES `user` (`ssoid`),
  ADD CONSTRAINT `fk_topic_inst_topicid` FOREIGN KEY (`topicid`) REFERENCES `topic` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
