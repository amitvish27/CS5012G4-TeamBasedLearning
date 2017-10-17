CREATE TABLE `user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `ssoid` int(12) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `role` int(11) NOT NULL,
  `dept` varchar(15) DEFAULT NULL,
  `snumber` int(15) DEFAULT NULL,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1	