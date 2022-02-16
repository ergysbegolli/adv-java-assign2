CREATE DATABASE coviddb;
USE coviddb;

CREATE TABLE `countries` (
  `iso_code` varchar(5) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `continent` varchar(255) DEFAULT NULL,
  `median_age` double DEFAULT NULL,
  `stringency_index` double DEFAULT NULL,
  `reproduction_rate` double DEFAULT NULL,
  `population` double DEFAULT NULL,
  PRIMARY KEY (`iso_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `coviddata` (
  `iso_code` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `new_cases` double default NULL,
  `new_cases_smoothed` double default NULL,
  `total_cases` double default NULL,
  `new_deaths` double default NULL,
  `new_deaths_smoothed` double default NULL,
  `total_deaths` double default NULL,
  `new_tests` double default NULL,
  `total_tests` double default NULL,
  PRIMARY KEY (`iso_code`, `date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

