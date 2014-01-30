-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 27, 2013 at 05:56 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `automehanicar`
--

-- --------------------------------------------------------

--
-- Table structure for table `automobil`
--

CREATE TABLE IF NOT EXISTS `automobil` (
  `SifraAutomobila` int(11) NOT NULL,
  `BrojRegistracije` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Model` int(11) NOT NULL,
  `SifraVlasnika` int(11) NOT NULL,
  PRIMARY KEY (`SifraAutomobila`),
  KEY `Model` (`Model`),
  KEY `SifraVlasnika` (`SifraVlasnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `automobil`
--

INSERT INTO `automobil` (`SifraAutomobila`, `BrojRegistracije`, `Model`, `SifraVlasnika`) VALUES
(1, 'pi012ku', 11, 1),
(2, 'pi001az', 2, 2),
(3, 'pi001aa', 10, 5),
(5, 'pi001ac', 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `dugovanje`
--

CREATE TABLE IF NOT EXISTS `dugovanje` (
  `SifraDugovanja` int(11) NOT NULL,
  `VrednostDugovanja` double NOT NULL,
  `SifraVlasnika` int(11) NOT NULL,
  PRIMARY KEY (`SifraDugovanja`),
  KEY `SifraVlasnika` (`SifraVlasnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dugovanje`
--

INSERT INTO `dugovanje` (`SifraDugovanja`, `VrednostDugovanja`, `SifraVlasnika`) VALUES
(1, 14000, 1),
(2, 15000, 2),
(3, 7000, 3),
(4, 29600, 6);

-- --------------------------------------------------------

--
-- Table structure for table `intervencija`
--

CREATE TABLE IF NOT EXISTS `intervencija` (
  `SifraIntervencije` int(11) NOT NULL,
  `SifraZaposlenog` int(11) NOT NULL,
  `SifraAutomobila` int(11) NOT NULL,
  `DatumIntervencije` datetime NOT NULL,
  PRIMARY KEY (`SifraIntervencije`),
  KEY `SifraZaposlenog` (`SifraZaposlenog`,`SifraAutomobila`),
  KEY `SifraAutomobila` (`SifraAutomobila`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `markaautomobila`
--

CREATE TABLE IF NOT EXISTS `markaautomobila` (
  `SifraMarke` int(11) NOT NULL,
  `NazivMarke` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraMarke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `markaautomobila`
--

INSERT INTO `markaautomobila` (`SifraMarke`, `NazivMarke`) VALUES
(1, 'Alfa Romeo'),
(2, 'Audi'),
(3, 'BMW'),
(4, 'Fiat'),
(5, 'Mercedes'),
(6, 'Opel'),
(7, 'Peugeot'),
(8, 'Renault'),
(9, 'Volkswagen'),
(10, 'Yugo');

-- --------------------------------------------------------

--
-- Table structure for table `modelautomobila`
--

CREATE TABLE IF NOT EXISTS `modelautomobila` (
  `SifraModela` int(11) NOT NULL,
  `SifraMarke` int(11) NOT NULL,
  `NazivModela` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraModela`),
  KEY `SifraMarke` (`SifraMarke`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `modelautomobila`
--

INSERT INTO `modelautomobila` (`SifraModela`, `SifraMarke`, `NazivModela`) VALUES
(1, 1, '146'),
(2, 1, '155'),
(3, 2, 'A4'),
(4, 2, 'A8'),
(5, 3, '118'),
(6, 3, '320'),
(7, 4, 'Punto'),
(8, 4, 'Bravo'),
(9, 5, 'E 220'),
(10, 5, 'GLK 320'),
(11, 6, 'Ascona'),
(12, 6, 'Insignia'),
(13, 7, '206'),
(14, 7, '308'),
(15, 8, 'Clio'),
(16, 8, 'Laguna'),
(17, 9, 'Passat'),
(18, 9, 'Golf'),
(19, 10, '45'),
(20, 10, '65');

-- --------------------------------------------------------

--
-- Table structure for table `opisintervencije`
--

CREATE TABLE IF NOT EXISTS `opisintervencije` (
  `SifraOpisa` int(11) NOT NULL,
  `Opis` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraOpisa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `uradjeneintervencije`
--

CREATE TABLE IF NOT EXISTS `uradjeneintervencije` (
  `SifraIntervencije` int(11) NOT NULL,
  `RB` int(11) NOT NULL,
  `SifraOpisa` int(11) NOT NULL,
  PRIMARY KEY (`SifraIntervencije`,`RB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vlasnikautomobila`
--

CREATE TABLE IF NOT EXISTS `vlasnikautomobila` (
  `SifraVlasnika` int(11) NOT NULL,
  `JMBG` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `Ime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraVlasnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vlasnikautomobila`
--

INSERT INTO `vlasnikautomobila` (`SifraVlasnika`, `JMBG`, `Ime`, `Prezime`) VALUES
(1, '1506948732530', 'Mitko', 'Rusimov'),
(2, '2710977732530', 'Darko', 'Todorovic'),
(3, '0410984732530', 'Srdjan', 'Djordjevic'),
(4, '1111111111111', 'Boban', 'Bajkovic'),
(5, '2222222222222', 'Novak', 'Djokovic'),
(6, '3333333333333', 'Vlade', 'Divac');

-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE IF NOT EXISTS `zaposleni` (
  `SifraZaposlenog` int(11) NOT NULL,
  `JMBG` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `Ime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Username` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DatumRodjenja` date NOT NULL,
  `Adresa` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Telefon` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Pol` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`SifraZaposlenog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zaposleni`
--

INSERT INTO `zaposleni` (`SifraZaposlenog`, `JMBG`, `Ime`, `Prezime`, `Username`, `Password`, `DatumRodjenja`, `Adresa`, `Telefon`, `Email`, `Pol`) VALUES
(1, '1008989732530', 'Milan', 'Rusimov', 'rus', 'rus', '1989-08-10', 'Obiliceva 61', '064/2718765', 'rusmil89@gmail.com', 'muski'),
(2, '2606372732530', 'Igor', 'Gogic', 'igac', 'igac', '1972-06-26', 'Stevana Sremca 54/23', '060/0260672', 'igac26@gmail.com', 'muski'),
(3, '2131564313156', 'Milan', 'Ciric', 'cira', 'cira', '1989-01-02', 'Sopot bb', '067/9875465', 'cira@gmail.com', 'muski'),
(4, '6515674897987', 'Dusan', 'Manic', 'ducan', 'ducan', '1988-06-20', 'Save Kovacevica 88', '064/5468494', 'ducan@hotmail.com', 'muski'),
(5, '1508953732530', 'Vida', 'Rusimov', 'vida53', 'vida53', '1953-08-15', 'Obiliceva 61', '064/0744182', 'vida@gmail.com', 'zenski');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `automobil`
--
ALTER TABLE `automobil`
  ADD CONSTRAINT `automobil_ibfk_1` FOREIGN KEY (`Model`) REFERENCES `modelautomobila` (`SifraModela`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `automobil_ibfk_2` FOREIGN KEY (`SifraVlasnika`) REFERENCES `vlasnikautomobila` (`SifraVlasnika`) ON UPDATE CASCADE;

--
-- Constraints for table `dugovanje`
--
ALTER TABLE `dugovanje`
  ADD CONSTRAINT `dugovanje_ibfk_1` FOREIGN KEY (`SifraVlasnika`) REFERENCES `vlasnikautomobila` (`SifraVlasnika`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `intervencija`
--
ALTER TABLE `intervencija`
  ADD CONSTRAINT `intervencija_ibfk_1` FOREIGN KEY (`SifraZaposlenog`) REFERENCES `zaposleni` (`SifraZaposlenog`) ON UPDATE CASCADE,
  ADD CONSTRAINT `intervencija_ibfk_2` FOREIGN KEY (`SifraAutomobila`) REFERENCES `automobil` (`SifraAutomobila`) ON UPDATE CASCADE;

--
-- Constraints for table `modelautomobila`
--
ALTER TABLE `modelautomobila`
  ADD CONSTRAINT `modelautomobila_ibfk_1` FOREIGN KEY (`SifraMarke`) REFERENCES `markaautomobila` (`SifraMarke`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `uradjeneintervencije`
--
ALTER TABLE `uradjeneintervencije`
  ADD CONSTRAINT `uradjeneintervencije_ibfk_1` FOREIGN KEY (`SifraIntervencije`) REFERENCES `intervencija` (`SifraIntervencije`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
