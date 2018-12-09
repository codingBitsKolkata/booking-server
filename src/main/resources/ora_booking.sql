-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 07:49 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ora_booking`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_info`
--

DROP TABLE IF EXISTS `booking_info`;
CREATE TABLE IF NOT EXISTS `booking_info` (
  `booking_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `checkin_pref_time` varchar(255) DEFAULT NULL,
  `checkout_pref_time` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gstin` varchar(255) DEFAULT NULL,
  `id_file_url` varchar(255) DEFAULT NULL,
  `identity_id` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_info_id`),
  KEY `FKo1b17kgui5bkhpme9y5n1hnnd` (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_price`
--

DROP TABLE IF EXISTS `booking_price`;
CREATE TABLE IF NOT EXISTS `booking_price` (
  `booking_price_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `room_vs_price_id` bigint(20) DEFAULT NULL,
  `booking_vs_room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_price_id`),
  KEY `FKixjogkpnvlab9fi6j0palwvtx` (`booking_vs_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_homestay`
--

DROP TABLE IF EXISTS `booking_vs_homestay`;
CREATE TABLE IF NOT EXISTS `booking_vs_homestay` (
  `booking_vs_homestay_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `booking_approval` bigint(20) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_vs_homestay_id`),
  KEY `FKeofyb3h3kcjuiwpyygamijv3n` (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_payment`
--

DROP TABLE IF EXISTS `booking_vs_payment`;
CREATE TABLE IF NOT EXISTS `booking_vs_payment` (
  `booking_vs_payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `amount_paid` varchar(255) DEFAULT NULL,
  `orderAmount` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `other_info` varchar(255) DEFAULT NULL,
  `paymentMode` varchar(255) DEFAULT NULL,
  `referenceId` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `txMsg` varchar(255) DEFAULT NULL,
  `txStatus` varchar(255) DEFAULT NULL,
  `txTime` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  `gateway_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_vs_payment_id`),
  KEY `FK7o4e18jl8hkm5t6t1xs00gtr8` (`booking_id`),
  KEY `FKs3qn4233lkx83a4rimjvfs563` (`gateway_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_room`
--

DROP TABLE IF EXISTS `booking_vs_room`;
CREATE TABLE IF NOT EXISTS `booking_vs_room` (
  `booking_vs_room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cgst` varchar(255) DEFAULT NULL,
  `igst` varchar(255) DEFAULT NULL,
  `num_of_adult` varchar(255) DEFAULT NULL,
  `num_of_cot` varchar(255) DEFAULT NULL,
  `property_pricedrop_id` bigint(20) DEFAULT NULL,
  `rhd_id` bigint(20) DEFAULT NULL,
  `rod_id` bigint(20) DEFAULT NULL,
  `room_actual_price` varchar(255) DEFAULT NULL,
  `room_gst_slab_price` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `rop_id` bigint(20) DEFAULT NULL,
  `sgst` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  `gst_slab_id` bigint(20) NOT NULL,
  `sac_code_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_vs_room_id`),
  KEY `FK5ufallrpmt0lxf4fgp4u0vudm` (`booking_id`),
  KEY `FKkvrct3l7pfffk88kjk7ffx4jk` (`gst_slab_id`),
  KEY `FKdeiy7awthvtj76bw7kiescxqp` (`sac_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_booking`
--

DROP TABLE IF EXISTS `master_booking`;
CREATE TABLE IF NOT EXISTS `master_booking` (
  `booking_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `checkin_date` varchar(255) DEFAULT NULL,
  `checkout_date` varchar(255) DEFAULT NULL,
  `grand_total` varchar(255) DEFAULT NULL,
  `num_of_days` varchar(255) DEFAULT NULL,
  `orabooking_id` varchar(255) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `total_payble_with_gst` varchar(255) DEFAULT NULL,
  `total_payble_without_gst` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `convenience_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKggx0d1efi7l9qvxfoxwuq72e7` (`convenience_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_convenience`
--

DROP TABLE IF EXISTS `master_convenience`;
CREATE TABLE IF NOT EXISTS `master_convenience` (
  `convenience_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`convenience_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_convenience`
--

INSERT INTO `master_convenience` (`convenience_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `amount`) VALUES
(1, 1, '2018-08-10 23:28:42', NULL, NULL, 1, '200');

-- --------------------------------------------------------

--
-- Table structure for table `master_gateway`
--

DROP TABLE IF EXISTS `master_gateway`;
CREATE TABLE IF NOT EXISTS `master_gateway` (
  `gateway_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gateway_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gateway_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_gateway`
--

INSERT INTO `master_gateway` (`gateway_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `gateway_name`) VALUES
(1, 1, '2018-08-10 23:28:42', NULL, NULL, 1, 'Cashfree');

-- --------------------------------------------------------

--
-- Table structure for table `master_gst_slab`
--

DROP TABLE IF EXISTS `master_gst_slab`;
CREATE TABLE IF NOT EXISTS `master_gst_slab` (
  `gst_slab_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `from_amount` varchar(255) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `to_amount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gst_slab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_gst_slab`
--

INSERT INTO `master_gst_slab` (`gst_slab_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `from_amount`, `percentage`, `to_amount`) VALUES
(1, 1, '2018-08-10 23:28:42', NULL, NULL, 1, '0', '0', '999'),
(2, 1, '2018-08-10 23:28:42', NULL, NULL, 1, '1000', '12', '2499'),
(3, 1, '2018-08-10 23:28:42', NULL, NULL, 1, '1500', '18', '7499');

-- --------------------------------------------------------

--
-- Table structure for table `master_sac_code`
--

DROP TABLE IF EXISTS `master_sac_code`;
CREATE TABLE IF NOT EXISTS `master_sac_code` (
  `sac_code_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sac_name` varchar(255) DEFAULT NULL,
  `sac_code_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sac_code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_sac_code`
--

INSERT INTO `master_sac_code` (`sac_code_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `sac_name`, `sac_code_number`) VALUES
(1, 1, '2018-08-10 23:28:42', NULL, NULL, 1, 'Sample 1', '222222');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_info`
--
ALTER TABLE `booking_info`
  ADD CONSTRAINT `FKo1b17kgui5bkhpme9y5n1hnnd` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`);

--
-- Constraints for table `booking_price`
--
ALTER TABLE `booking_price`
  ADD CONSTRAINT `FKixjogkpnvlab9fi6j0palwvtx` FOREIGN KEY (`booking_vs_room_id`) REFERENCES `booking_vs_room` (`booking_vs_room_id`);

--
-- Constraints for table `booking_vs_homestay`
--
ALTER TABLE `booking_vs_homestay`
  ADD CONSTRAINT `FKeofyb3h3kcjuiwpyygamijv3n` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`);

--
-- Constraints for table `booking_vs_payment`
--
ALTER TABLE `booking_vs_payment`
  ADD CONSTRAINT `FK7o4e18jl8hkm5t6t1xs00gtr8` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`),
  ADD CONSTRAINT `FKs3qn4233lkx83a4rimjvfs563` FOREIGN KEY (`gateway_id`) REFERENCES `master_gateway` (`gateway_id`);

--
-- Constraints for table `booking_vs_room`
--
ALTER TABLE `booking_vs_room`
  ADD CONSTRAINT `FK5ufallrpmt0lxf4fgp4u0vudm` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`),
  ADD CONSTRAINT `FKdeiy7awthvtj76bw7kiescxqp` FOREIGN KEY (`sac_code_id`) REFERENCES `master_sac_code` (`sac_code_id`),
  ADD CONSTRAINT `FKkvrct3l7pfffk88kjk7ffx4jk` FOREIGN KEY (`gst_slab_id`) REFERENCES `master_gst_slab` (`gst_slab_id`);

--
-- Constraints for table `master_booking`
--
ALTER TABLE `master_booking`
  ADD CONSTRAINT `FKggx0d1efi7l9qvxfoxwuq72e7` FOREIGN KEY (`convenience_id`) REFERENCES `master_convenience` (`convenience_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
