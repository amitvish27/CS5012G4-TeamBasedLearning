-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2017 at 07:29 AM
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
(4, 'IS3624', 'Concepts Of Information System', 2016, 'Summer', '2017-11-04 22:38:22', 'cpuol7', 0),
(5, 'NU2357', 'Psychiatric Mental Health Nurs', 2017, 'Winter', '2017-11-04 22:38:23', 'cpuol7', 0),
(6, 'BS1337', 'Financial Reporting And Contro', 2016, 'Spring', '2017-11-04 22:38:23', 'jppdle', 0),
(7, 'AR1638', 'Introduction To Visual Thinkin', 2016, 'Winter', '2017-11-04 22:38:23', 'jh6q3t', 0),
(8, 'CS1759', 'Computer Programming I', 2015, 'Summer', '2017-11-04 22:38:23', 'fr5rae', 0),
(9, 'AR1359', 'The Language Of Drawing', 2015, 'Fall', '2017-11-04 22:38:23', 'jppdle', 0),
(10, 'MA2448', 'Probability', 2017, 'Winter', '2017-11-04 22:38:23', 'fr5rae', 0),
(11, 'IS2108', 'Technology Consulting In The G', 2016, 'Fall', '2017-11-04 22:38:23', 'jh6q3t', 0),
(12, 'MA1923', 'Intermediate Analysis', 2016, 'Fall', '2017-11-04 22:38:23', 'jppdle', 0),
(13, 'NU3704', 'Pathophysiology', 2017, 'Fall', '2017-11-04 22:38:23', 'jh6q3t', 0),
(14, 'FI3098', 'Monetary Economics And The Glo', 2015, 'Summer', '2017-11-04 22:38:23', 'fr5rae', 0),
(15, 'BS1254', 'Leadership And Organizational ', 2017, 'Winter', '2017-11-04 22:38:23', 'jh6q3t', 0),
(16, 'AR2169', 'Approaches To Printmaking', 2015, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(17, 'FI3734', 'Advanced Corporate Finance', 2016, 'Winter', '2017-11-04 22:38:24', 'cpuol7', 0),
(18, 'AR2013', 'Beginning Digital Photo', 2016, 'Spring', '2017-11-04 22:38:24', 'jppdle', 0),
(19, 'AR1414', 'Moving Image Media', 2017, 'Summer', '2017-11-04 22:38:24', 'cpuol7', 0),
(20, 'AR2145', 'Collaborative Innovation: Big ', 2017, 'Summer', '2017-11-04 22:38:24', 'jh6q3t', 0),
(21, 'CS1307', 'Web Programming', 2016, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(22, 'AR1113', 'Global Perspectives In Contemp', 2016, 'Winter', '2017-11-04 22:38:24', 'jh6q3t', 0),
(23, 'MA2867', 'Advanced Multivariable Calculu', 2015, 'Summer', '2017-11-04 22:38:24', 'jppdle', 0),
(24, 'FI1938', 'Investment Management', 2017, 'Winter', '2017-11-04 22:38:24', 'fr5rae', 0),
(25, 'CS2703', 'Data Programming', 2016, 'Summer', '2017-11-04 22:38:24', 'jh6q3t', 0),
(26, 'FI3233', 'Financial Derivatives', 2016, 'Winter', '2017-11-04 22:38:25', 'jh6q3t', 0),
(27, 'MA1682', 'Precalculus', 2015, 'Summer', '2017-11-04 22:38:25', 'jppdle', 0),
(28, 'IS3828', 'Electronic Business', 2015, 'Fall', '2017-11-04 22:38:25', 'fr5rae', 0),
(29, 'IS2098', 'Information Systems Freshman W', 2016, 'Winter', '2017-11-04 22:38:25', 'cpuol7', 0),
(30, 'FI2292', 'International Financial Market', 2015, 'Spring', '2017-11-04 22:38:25', 'fr5rae', 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_inst`
--

CREATE TABLE `course_inst` (
  `relnid` int(5) NOT NULL COMMENT 'internal record id',
  `courseid` int(5) NOT NULL COMMENT 'fk to course internal id',
  `instid` int(5) NOT NULL COMMENT 'fk to user internal id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(6) NOT NULL COMMENT 'internal record id',
  `content` text NOT NULL COMMENT 'content of the question',
  `opt_a` varchar(50) NOT NULL COMMENT 'option a',
  `opt_b` varchar(50) NOT NULL COMMENT 'option b',
  `opt_c` varchar(50) NOT NULL COMMENT 'option c',
  `opt_d` varchar(50) NOT NULL COMMENT 'option d',
  `answer` int(1) NOT NULL COMMENT '0-opt_a,1-opt_b,2-opt_c,3-opt_d',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time when record created',
  `instructorid` int(5) DEFAULT NULL COMMENT 'sso id instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `courseid` int(6) NOT NULL COMMENT 'fk to course',
  `groupnumber` int(6) NOT NULL COMMENT 'group number',
  `instructorid` int(5) NOT NULL COMMENT 'instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `studentid` int(5) NOT NULL COMMENT 'fk to student in user table',
  `courseid` int(5) NOT NULL COMMENT 'fk to course id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_sgroup`
--

CREATE TABLE `student_sgroup` (
  `relnid` int(6) NOT NULL COMMENT 'internal record id',
  `studentid` int(6) NOT NULL COMMENT 'fk to student in user table',
  `groupid` int(6) NOT NULL COMMENT 'fk to group id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted?0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(5) NOT NULL COMMENT 'internal record id',
  `title` varchar(60) NOT NULL COMMENT 'topic title',
  `courseid` int(5) NOT NULL COMMENT 'fk to course',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time when created',
  `instructorid` int(5) DEFAULT NULL COMMENT 'sso id of the instructor who created',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `title`, `courseid`, `created`, `instructorid`, `deleted`) VALUES
(1, 'Fundamentals of Arts & Culture Writing', 7, '2017-11-05 01:11:47', 0, 0),
(2, 'Popular Music Criticism', 19, '2017-11-05 01:11:47', 0, 0),
(3, 'Writing About Television', 22, '2017-11-05 01:11:47', 0, 0),
(4, 'How to Put Stories Together', 18, '2017-11-05 01:11:47', 0, 0),
(5, 'Film Criticism', 20, '2017-11-05 01:11:48', 0, 0),
(6, 'Non-Popular Music Forms/Theater', 9, '2017-11-05 01:11:48', 0, 0),
(7, 'Visual Arts ', 18, '2017-11-05 01:11:48', 0, 0),
(8, 'Othello ', 9, '2017-11-05 01:11:48', 0, 0),
(9, 'Architecture', 19, '2017-11-05 01:11:48', 0, 0),
(10, 'Trend & Enterprise Pieces', 19, '2017-11-05 01:11:48', 0, 0),
(11, 'Freelance Pitches & Writing Gigs', 9, '2017-11-05 01:11:48', 0, 0),
(12, 'Rationale for Business Studies ', 6, '2017-11-05 01:11:48', 0, 0),
(13, 'Nature of business', 15, '2017-11-05 01:11:48', 0, 0),
(14, 'Business management', 6, '2017-11-05 01:11:48', 0, 0),
(15, 'Business planning', 15, '2017-11-05 01:11:48', 0, 0),
(16, 'Operations', 15, '2017-11-05 01:11:48', 0, 0),
(17, 'Marketing', 6, '2017-11-05 01:11:48', 0, 0),
(18, 'Understanding business activity', 15, '2017-11-05 01:11:48', 0, 0),
(19, 'People in business', 6, '2017-11-05 01:11:48', 0, 0),
(20, 'Financial information and decisions', 15, '2017-11-05 01:11:48', 0, 0),
(21, 'External influences on business activity ', 15, '2017-11-05 01:11:48', 0, 0),
(22, 'Branching And Iteration', 25, '2017-11-05 01:11:48', 0, 0),
(23, 'String Manipulation', 25, '2017-11-05 01:11:48', 0, 0),
(24, 'Approximations', 8, '2017-11-05 01:11:48', 0, 0),
(25, 'Decomposition', 21, '2017-11-05 01:11:48', 0, 0),
(26, 'Abstractions', 8, '2017-11-05 01:11:48', 0, 0),
(27, 'Functions', 8, '2017-11-05 01:11:49', 0, 0),
(28, 'Tuples', 21, '2017-11-05 01:11:49', 0, 0),
(29, 'Recursion', 3, '2017-11-05 01:11:49', 0, 0),
(30, 'Dictionaries', 3, '2017-11-05 01:11:49', 0, 0),
(31, 'Testing', 21, '2017-11-05 01:11:49', 0, 0),
(32, 'Debugging', 21, '2017-11-05 01:11:49', 0, 0),
(33, 'Exceptions', 25, '2017-11-05 01:11:49', 0, 0),
(34, 'Assertions', 25, '2017-11-05 01:11:49', 0, 0),
(35, 'Object Oriented Programming', 21, '2017-11-05 01:11:49', 0, 0),
(36, 'Python Classes And Inheritance', 25, '2017-11-05 01:11:49', 0, 0),
(37, 'Understanding Program Efficiency', 8, '2017-11-05 01:11:49', 0, 0),
(38, 'Understanding Program Efficiency', 8, '2017-11-05 01:11:49', 0, 0),
(39, 'Searching And Sorting', 21, '2017-11-05 01:11:49', 0, 0),
(40, 'Computer Architecture And Organisation', 25, '2017-11-05 01:11:49', 0, 0),
(41, 'Problem-Solving With Computers', 21, '2017-11-05 01:11:49', 0, 0),
(42, 'Software Engineering', 8, '2017-11-05 01:11:49', 0, 0),
(43, 'Introduction To Financial Management', 30, '2017-11-05 01:11:49', 0, 0),
(44, 'Time Value Of Money', 26, '2017-11-05 01:11:49', 0, 0),
(45, 'Discounted Cash Flow Valuation', 17, '2017-11-05 01:11:49', 0, 0),
(46, 'Bond Valuation', 14, '2017-11-05 01:11:49', 0, 0),
(47, 'Stock Valuation', 24, '2017-11-05 01:11:49', 0, 0),
(48, 'Investment Criteria', 24, '2017-11-05 01:11:49', 0, 0),
(49, 'Making Capital Decisions', 17, '2017-11-05 01:11:49', 0, 0),
(50, 'Capital Market History', 30, '2017-11-05 01:11:50', 0, 0),
(51, 'Risk & Return', 17, '2017-11-05 01:11:50', 0, 0),
(52, 'Cost Of Capital', 30, '2017-11-05 01:11:50', 0, 0),
(53, 'Capital Structure', 14, '2017-11-05 01:11:50', 0, 0),
(54, 'Financial Statements Basics', 24, '2017-11-05 01:11:50', 0, 0),
(55, 'Analysis Of Financial Statements', 30, '2017-11-05 01:11:50', 0, 0),
(56, 'Dividends And Dividends Policy', 14, '2017-11-05 01:11:50', 0, 0),
(57, 'Raising Capital', 30, '2017-11-05 01:11:50', 0, 0),
(58, 'Intro To Information Systems', 11, '2017-11-05 01:11:50', 0, 0),
(59, 'Types Of Information Systems', 28, '2017-11-05 01:11:50', 0, 0),
(60, 'It Infrastructure', 11, '2017-11-05 01:11:50', 0, 0),
(61, 'Ethical And Social Issues In Information Systems', 28, '2017-11-05 01:11:50', 0, 0),
(62, 'Business Intelligence', 11, '2017-11-05 01:11:50', 0, 0),
(63, 'Social Tools & Web 2.0', 29, '2017-11-05 01:11:50', 0, 0),
(64, 'Managing Knowledge', 29, '2017-11-05 01:11:50', 0, 0),
(65, 'Building Systems', 4, '2017-11-05 01:11:50', 0, 0),
(66, 'It Outsourcing Cloud Computing & Saas', 29, '2017-11-05 01:11:50', 0, 0),
(67, 'Open Source', 4, '2017-11-05 01:11:50', 0, 0),
(68, 'Computation', 12, '2017-11-05 01:11:50', 0, 0),
(69, 'Number Theory', 10, '2017-11-05 01:11:50', 0, 0),
(70, 'Consumer Arithmetic', 27, '2017-11-05 01:11:50', 0, 0),
(71, 'Sets', 12, '2017-11-05 01:11:50', 0, 0),
(72, 'Measurement', 10, '2017-11-05 01:11:51', 0, 0),
(73, 'Statistics', 2, '2017-11-05 01:11:51', 0, 0),
(74, 'Algebra', 27, '2017-11-05 01:11:51', 0, 0),
(75, 'Relations', 2, '2017-11-05 01:11:51', 0, 0),
(76, 'Geometry And Trigonometry', 2, '2017-11-05 01:11:51', 0, 0),
(77, 'Vectors And Matrices', 10, '2017-11-05 01:11:51', 0, 0),
(78, 'Basic Arithmetic And Algebra', 23, '2017-11-05 01:11:51', 0, 0),
(79, 'Plane Geometry', 10, '2017-11-05 01:11:51', 0, 0),
(80, 'Probability', 12, '2017-11-05 01:11:51', 0, 0),
(81, 'Trigonometric Ratios', 2, '2017-11-05 01:11:51', 0, 0),
(82, 'Linear Functions And Lines', 27, '2017-11-05 01:11:51', 0, 0),
(83, 'Series And Applications', 27, '2017-11-05 01:11:51', 0, 0),
(84, 'Geometrical Applications Of Differentiation', 12, '2017-11-05 01:11:51', 0, 0),
(85, 'Integration', 10, '2017-11-05 01:11:51', 0, 0),
(86, 'Logarithmic And Exponential Functions', 12, '2017-11-05 01:11:51', 0, 0),
(87, 'Applications Of Calculus To The Physical World', 2, '2017-11-05 01:11:51', 0, 0),
(88, 'Polynomials', 12, '2017-11-05 01:11:51', 0, 0),
(89, 'Binomial Theorem', 27, '2017-11-05 01:11:51', 0, 0),
(90, 'Permutations', 10, '2017-11-05 01:11:51', 0, 0),
(91, 'Psychosocial Functional Patterns And Communication Skills', 5, '2017-11-05 01:11:51', 0, 0),
(92, 'Health Management Pattern—Assessment', 5, '2017-11-05 01:11:51', 0, 0),
(93, 'Cognitve-Perceptual Pattern And The Aging Adult', 13, '2017-11-05 01:11:51', 0, 0),
(94, 'Introduction To Pharmacology And Medication Management', 5, '2017-11-05 01:11:51', 0, 0),
(95, 'Health Management Pattern—Asepsis And Safety', 5, '2017-11-05 01:11:51', 0, 0),
(96, 'Activity-Exercise', 5, '2017-11-05 01:11:52', 0, 0),
(97, 'Nutritional-Metabolic Pattern', 1, '2017-11-05 01:11:52', 0, 0),
(98, 'Activity-Exercise Pattern—Oxygenation', 13, '2017-11-05 01:11:52', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `topic_inst`
--

CREATE TABLE `topic_inst` (
  `relnid` int(5) NOT NULL COMMENT 'internal record id',
  `topicid` int(5) NOT NULL COMMENT 'fk to topic',
  `instructorid` int(5) NOT NULL COMMENT 'fk to instructor',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT 'is deleted? 0-No, 1-Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(5) NOT NULL COMMENT 'Internal table id, AutoIncrement',
  `ssoid` varchar(10) NOT NULL COMMENT 'sso id for login and session details',
  `pswd` varchar(30) DEFAULT NULL COMMENT 'password',
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
  `active` int(1) DEFAULT '0' COMMENT 'active? 0-Inactive, 1-active, 2-firstTimeLogin'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `ssoid`, `pswd`, `fname`, `lname`, `email`, `dept`, `created`, `createdby`, `modified`, `modifiedby`, `deleted`, `role`, `active`) VALUES
(1, 'ahr5ip', 'passwd', 'Ada', 'Hill', 'ada.hill@mail.com', 'Finance', '2017-11-04 21:24:58', 'asv123', '2017-11-04 21:24:58', 'asv123', 0, 0, 1),
(4, 'ar2y89', 'passwd', 'Addie', 'Reyes', 'addie.reyes@mail.com', 'Business', '2017-11-04 21:41:33', 'yh1234', '2017-11-04 21:41:33', 'yh1234', 0, 0, 1),
(5, 'arksk0', 'passwd', 'Agnes', 'Rogers', 'agnes.rogers@mail.com', 'Arts', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1),
(6, 'cdgsyj', 'passwd', 'Claude', 'Dixon', 'claude.dixon@mail.com', 'Mathematics', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1),
(7, 'cpuol7', 'passwd', 'Clyde', 'Patel', 'clyde.patel@mail.com', 'Nursing', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'yt1234', 0, 1, 1),
(8, 'cjqxc2', 'passwd', 'Cora', 'Jackson', 'cora.jackson@mail.com', 'Computer Science', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'yt1234', 0, 0, 1),
(9, 'dt4pyv', 'passwd', 'Daisy', 'Turner', 'daisy.turner@mail.com', 'Mathematics', '2017-11-04 21:41:33', 'yt1234', '2017-11-04 21:41:33', 'yt1234', 0, 0, 1),
(10, 'dcjlpi', 'passwd', 'Dan', 'Cunningham', 'dan.cunningham@mail.com', 'Information Systems', '2017-11-04 21:41:33', 'asv123', '2017-11-04 21:41:33', 'asv123', 0, 0, 1),
(11, 'dmsb34', 'passwd', 'Daniel', 'Marshall', 'daniel.marshall@mail.com', 'Finance', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1),
(12, 'dcxvbb', 'passwd', 'David', 'Chavez', 'david.chavez@mail.com', 'Mathematics', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 0, 1),
(13, 'dcrs4p', 'passwd', 'Della', 'Cook', 'della.cook@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1),
(14, 'dcqqma', 'passwd', 'Dora', 'Carter', 'dora.carter@mail.com', 'Finance', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1),
(15, 'ewxf91', 'passwd', 'Earl', 'Wagner', 'earl.wagner@mail.com', 'Arts', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1),
(16, 'ehr4rz', 'passwd', 'Ed', 'Hicks', 'ed.hicks@mail.com', 'Mathematics', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1),
(17, 'eh3gwr', 'passwd', 'Edgar', 'Holmes', 'edgar.holmes@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1),
(18, 'er2z7u', 'passwd', 'Edith', 'Robinson', 'edith.robinson@mail.com', 'Arts', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1),
(19, 'eajdgi', 'passwd', 'Edna', 'Adams', 'edna.adams@mail.com', 'Computer Science', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1),
(20, 'eawdq0', 'passwd', 'Edward', 'Alexander', 'edward.alexander@mail.com', 'Information Systems', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 0, 1),
(21, 'ehtewx', 'passwd', 'Edwin', 'Hunt', 'edwin.hunt@mail.com', 'Arts', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1),
(22, 'fr5rae', 'passwd', 'Frank', 'Reynolds', 'frank.reynolds@mail.com', 'Mathematics', '2017-11-04 21:41:34', 'asv123', '2017-11-04 21:41:34', 'asv123', 0, 1, 1),
(23, 'fwmzfm', 'passwd', 'Fred', 'West', 'fred.west@mail.com', 'Business', '2017-11-04 21:41:34', 'fz1234', '2017-11-04 21:41:34', 'fz1234', 0, 0, 1),
(24, 'fwniq8', 'passwd', 'Frederick', 'Woods', 'frederick.woods@mail.com', 'Finance', '2017-11-04 21:41:34', 'yh1234', '2017-11-04 21:41:34', 'yh1234', 0, 0, 1),
(25, 'gjpys5', 'passwd', 'George', 'Jordan', 'george.jordan@mail.com', 'Computer Science', '2017-11-04 21:41:34', 'yt1234', '2017-11-04 21:41:34', 'yt1234', 0, 0, 1),
(26, 'grrtcu', 'passwd', 'Georgia', 'Ross', 'georgia.ross@mail.com', 'Nursing', '2017-11-04 21:41:35', 'yh1234', '2017-11-04 21:41:35', 'yh1234', 0, 0, 1),
(27, 'ghlc4a', 'passwd', 'Gertrude', 'Harris', 'gertrude.harris@mail.com', 'Computer Science', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1),
(28, 'ghh9fw', 'passwd', 'Grace', 'Hernandez', 'grace.hernandez@mail.com', 'Arts', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 0, 1),
(29, 'grxy71', 'passwd', 'Guy', 'Rose', 'guy.rose@mail.com', 'Business', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1),
(30, 'hmxzjy', 'passwd', 'Harriet', 'Myers', 'harriet.myers@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1),
(31, 'jkxeed', 'passwd', 'Jessie', 'King', 'jessie.king@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1),
(32, 'jppdle', 'passwd', 'Jessie', 'Peters', 'jessie.peters@mail.com', 'Finance', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 1, 1),
(33, 'jmgyjb', 'passwd', 'Jim', 'Mason', 'jim.mason@mail.com', 'Arts', '2017-11-04 21:41:35', 'yh1234', '2017-11-04 21:41:35', 'yh1234', 0, 0, 1),
(34, 'jbioiy', 'passwd', 'Joe', 'Bryant', 'joe.bryant@mail.com', 'Information Systems', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1),
(35, 'jh6q3t', 'passwd', 'John', 'Henderson', 'john.henderson@mail.com', 'Computer Science', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 1, 1),
(36, 'jh8nww', 'passwd', 'Joseph', 'Hamilton', 'joseph.hamilton@mail.com', 'Nursing', '2017-11-04 21:41:35', 'yt1234', '2017-11-04 21:41:35', 'yt1234', 0, 0, 1),
(37, 'jeebw5', 'passwd', 'Josephine', 'Evans', 'josephine.evans@mail.com', 'Business', '2017-11-04 21:41:35', 'fz1234', '2017-11-04 21:41:35', 'fz1234', 0, 0, 1),
(38, 'jht0e6', 'passwd', 'Julia', 'Hall', 'julia.hall@mail.com', 'Nursing', '2017-11-04 21:41:35', 'asv123', '2017-11-04 21:41:35', 'asv123', 0, 0, 1),
(39, 'jc460i', 'passwd', 'Julius', 'Castro', 'julius.castro@mail.com', 'Computer Science', '2017-11-04 21:41:36', 'asv123', '2017-11-04 21:41:36', 'asv123', 0, 0, 1),
(40, 'kl5aug', 'passwd', 'Kate', 'Long', 'kate.long@mail.com', 'Mathematics', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1),
(41, 'kcijns', 'passwd', 'Katherine', 'Collins', 'katherine.collins@mail.com', 'Nursing', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1),
(42, 'kb2cwk', 'passwd', 'Kathryn', 'Butler', 'kathryn.butler@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1),
(43, 'khfh27', 'passwd', 'Katie', 'Hughes', 'katie.hughes@mail.com', 'Mathematics', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1),
(44, 'ltkke1', 'passwd', 'Laura', 'Thompson', 'laura.thompson@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1),
(45, 'lwce7f', 'passwd', 'Lulu', 'Watson', 'lulu.watson@mail.com', 'Finance', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1),
(46, 'lbfthp', 'passwd', 'Luther', 'Boyd', 'luther.boyd@mail.com', 'Computer Science', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1),
(47, 'lbwl17', 'passwd', 'Lydia', 'Brooks', 'lydia.brooks@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 1, 1),
(48, 'mg1f78', 'passwd', 'Mabel', 'Gonzalez', 'mabel.gonzalez@mail.com', 'Business', '2017-11-04 21:41:36', 'fz1234', '2017-11-04 21:41:36', 'fz1234', 0, 0, 1),
(49, 'mwazy6', 'passwd', 'Mae', 'Wood', 'mae.wood@mail.com', 'Arts', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1),
(50, 'mpi7sb', 'passwd', 'Maggie', 'Parker', 'maggie.parker@mail.com', 'Finance', '2017-11-04 21:41:36', 'yt1234', '2017-11-04 21:41:36', 'yt1234', 0, 0, 1),
(51, 'mbwu14', 'passwd', 'Mamie', 'Bailey', 'mamie.bailey@mail.com', 'Nursing', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1),
(52, 'mjb98p', 'passwd', 'Margaret', 'Jones', 'margaret.jones@mail.com', 'Information Systems', '2017-11-04 21:41:36', 'yh1234', '2017-11-04 21:41:36', 'yh1234', 0, 0, 1),
(53, 'nrpoqt', 'passwd', 'Nancy', 'Rivera', 'nancy.rivera@mail.com', 'Nursing', '2017-11-04 21:41:36', 'asv123', '2017-11-04 21:41:36', 'asv123', 0, 0, 1),
(55, 'ns7sfr', 'passwd', 'Nannie', 'Sanders', 'nannie.sanders@mail.com', 'Arts', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1),
(56, 'nt669p', 'passwd', 'Nellie', 'Thomas', 'nellie.thomas@mail.com', 'Mathematics', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1),
(57, 'nmp4iv', 'passwd', 'Nettie', 'Morgan', 'nettie.morgan@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'yt1234', 0, 0, 1),
(58, 'nfiyol', 'passwd', 'Nora', 'Flores', 'nora.flores@mail.com', 'Arts', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'yt1234', 0, 0, 1),
(59, 'offn92', 'passwd', 'Olive', 'Fisher', 'olive.fisher@mail.com', 'Mathematics', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1),
(60, 'orsg1b', 'passwd', 'Oliver', 'Rice', 'oliver.rice@mail.com', 'Information Systems', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 0, 1),
(61, 'or7hui', 'passwd', 'Oscar', 'Ruiz', 'oscar.ruiz@mail.com', 'Nursing', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 1, 1),
(62, 'ome7r3', 'passwd', 'Otto', 'Meyer', 'otto.meyer@mail.com', 'Finance', '2017-11-04 21:46:36', 'yt1234', '2017-11-04 21:46:36', 'yt1234', 0, 0, 1),
(63, 'rykv5t', 'passwd', 'Rose', 'Young', 'rose.young@mail.com', 'Business', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1),
(64, 'rwrg4c', 'passwd', 'Roy', 'Webb', 'roy.webb@mail.com', 'Arts', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1),
(65, 'tgc0oh', 'passwd', 'Thomas', 'Graham', 'thomas.graham@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1),
(66, 'tfyvww', 'passwd', 'Tom', 'Freeman', 'tom.freeman@mail.com', 'Arts', '2017-11-04 21:46:36', 'fz1234', '2017-11-04 21:46:36', 'fz1234', 0, 1, 1),
(67, 'vsv0p0', 'passwd', 'Viola', 'Sullivan', 'viola.sullivan@mail.com', 'Arts', '2017-11-04 21:46:36', 'yh1234', '2017-11-04 21:46:36', 'yh1234', 0, 0, 1),
(68, 'wwcel0', 'passwd', 'Walter', 'Wallace', 'walter.wallace@mail.com', 'Information Systems', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 0, 1),
(69, 'asv123', 'passwd', 'Ameet', 'Vishwakarma', 'ameet.vishwakarma@mail.com', 'Computer Science', '2017-11-04 21:46:36', 'asv123', '2017-11-04 21:46:36', 'asv123', 0, 2, 1),
(70, 'yh1234', 'passwd', 'Yang', 'Huang', 'yang.huang@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1),
(71, 'yt1234', 'passwd', 'Yuneus', 'Tanudyaya', 'yuneus.tanudyaya@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1),
(72, 'fz1234', 'passwd', 'Frank', 'Zhao', 'frank.zhao@mail.com', 'Computer Science', '2017-11-04 21:46:37', 'asv123', '2017-11-04 21:46:37', 'asv123', 0, 2, 1);

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
  ADD KEY `fk_instructor` (`instid`);

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
  ADD PRIMARY KEY (`relnid`);

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
  ADD KEY `fk_topic_inst_instid` (`instructorid`),
  ADD KEY `fk_topic_inst_topicid` (`topicid`);

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
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `course_inst`
--
ALTER TABLE `course_inst`
  MODIFY `relnid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
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
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `sgroup_quiz`
--
ALTER TABLE `sgroup_quiz`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `student_course`
--
ALTER TABLE `student_course`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `student_sgroup`
--
ALTER TABLE `student_sgroup`
  MODIFY `relnid` int(6) NOT NULL AUTO_INCREMENT COMMENT 'internal record id';
--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'internal record id', AUTO_INCREMENT=99;
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
  ADD CONSTRAINT `fk_instructor` FOREIGN KEY (`instid`) REFERENCES `user` (`id`);

--
-- Constraints for table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `fk_topic_course_id` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`);

--
-- Constraints for table `topic_inst`
--
ALTER TABLE `topic_inst`
  ADD CONSTRAINT `fk_topic_inst_instid` FOREIGN KEY (`instructorid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_topic_inst_topicid` FOREIGN KEY (`topicid`) REFERENCES `topic` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
