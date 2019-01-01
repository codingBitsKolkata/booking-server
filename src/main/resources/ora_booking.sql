-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2019 at 03:43 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_info`
--

INSERT INTO `booking_info` (`booking_info_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `address`, `checkin_pref_time`, `checkout_pref_time`, `company_name`, `email`, `gstin`, `id_file_url`, `identity_id`, `mobile`, `name`, `booking_id`) VALUES
(1, 1, '2018-12-30 15:14:43', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
(2, 1, '2018-12-30 15:23:28', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(3, 1, '2018-12-30 15:36:30', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
(4, 1, '2018-12-30 15:38:38', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
(5, 1, '2018-12-30 19:12:07', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
(6, 1, '2018-12-30 19:21:39', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6),
(7, 1, '2018-12-30 19:39:12', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7),
(8, 1, '2018-12-30 19:43:56', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8),
(9, 1, '2018-12-30 19:51:13', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9),
(10, 1, '2018-12-30 19:55:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10),
(11, 1, '2018-12-30 20:13:54', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11),
(12, 1, '2018-12-30 20:32:57', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12),
(13, 1, '2018-12-30 20:46:02', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13),
(14, 1, '2018-12-30 21:18:20', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14);

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
  `percentage` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_vs_payment`
--

INSERT INTO `booking_vs_payment` (`booking_vs_payment_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `amount_paid`, `orderAmount`, `orderId`, `other_info`, `paymentMode`, `percentage`, `referenceId`, `signature`, `txMsg`, `txStatus`, `txTime`, `booking_id`, `gateway_id`) VALUES
(1, 1, '2018-12-30 15:23:41', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546163621504', NULL, NULL, '100', NULL, NULL, NULL, NULL, NULL, 2, 2),
(2, 1, '2018-12-30 15:36:52', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546164412684', NULL, NULL, '100', NULL, NULL, NULL, NULL, NULL, 3, 2),
(3, 1, '2018-12-30 15:38:50', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546164530680', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 4, 1),
(4, 1, '2018-12-30 19:12:12', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546177332301', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 5, 1),
(5, 1, '2018-12-30 19:21:43', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546177903995', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 6, 1),
(6, 1, '2018-12-30 19:39:16', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546178956161', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 7, 1),
(7, 1, '2018-12-30 19:43:58', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546179238967', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 8, 1),
(8, 1, '2018-12-30 19:51:16', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546179676201', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 9, 1),
(9, 1, '2018-12-30 19:55:04', NULL, NULL, 1, '0', '836.00', 'ORA_TRNS1546179904876', NULL, NULL, '100.00', NULL, NULL, NULL, NULL, NULL, 10, 1),
(10, 1, '2018-12-30 20:13:58', 1, '2018-12-30 20:17:16', 1, '0', '836.00', 'ORA_TRNS1546181038977', NULL, 'CREDIT_CARD', '100.00', '56887', NULL, 'OK', 'SUCCESS', '2018-12-30 20:14:52', 11, 1),
(11, 1, '2018-12-30 20:33:01', 1, '2018-12-30 20:36:18', 1, '0', '836.00', 'ORA_TRNS1546182181438', NULL, 'CREDIT_CARD', '100.00', '56889', '+Bo7F1hUBDejTGe+Sgen5o8pKKXGP8e2Nwq5FW/cJDI=', 'OK', 'SUCCESS', '2018-12-30 20:35:33', 12, 1),
(12, 1, '2018-12-30 20:46:05', 1, '2018-12-30 20:48:23', 2, '0', '836.00', 'ORA_TRNS1546182965040', NULL, 'CREDIT_CARD', '100.00', '56891', 'p9uwawv0w6L12S4pZxfa7FRsUix1OseAhWwQwAiC7JU=', 'OK', 'SUCCESS', '2018-12-30 20:47:57', 13, 1),
(13, 1, '2018-12-30 21:18:21', 1, '2018-12-30 21:20:27', 1, '0', '836.00', 'ORA_TRNS1546184901586', NULL, 'CREDIT_CARD', '100.00', '56892', 'NUhQAYiSWohmBVeufzyFc9DgyYrCESk87oSwmdx2AS0=', 'OK', 'SUCCESS', '2018-12-30 21:19:52', 14, 1);

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
  `num_of_shared_bed` varchar(255) DEFAULT NULL,
  `num_of_shared_cot` varchar(255) DEFAULT NULL,
  `ora_room_name` varchar(255) DEFAULT NULL,
  `room_actual_price` varchar(255) DEFAULT NULL,
  `room_gst_slab_price` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `room_vs_offer_id` varchar(255) DEFAULT NULL,
  `sgst` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  `gst_slab_id` bigint(20) NOT NULL,
  `sac_code_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_vs_room_id`),
  KEY `FK5ufallrpmt0lxf4fgp4u0vudm` (`booking_id`),
  KEY `FKkvrct3l7pfffk88kjk7ffx4jk` (`gst_slab_id`),
  KEY `FKdeiy7awthvtj76bw7kiescxqp` (`sac_code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_vs_room`
--

INSERT INTO `booking_vs_room` (`booking_vs_room_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `cgst`, `igst`, `num_of_adult`, `num_of_cot`, `num_of_shared_bed`, `num_of_shared_cot`, `ora_room_name`, `room_actual_price`, `room_gst_slab_price`, `room_id`, `room_vs_offer_id`, `sgst`, `booking_id`, `gst_slab_id`, `sac_code_id`) VALUES
(1, 1, '2018-12-30 15:14:19', NULL, NULL, 2, '0.00', NULL, NULL, NULL, '1', '1', 'Ora1231', '200', '200.00', '1', '1', '0.00', 1, 1, 1),
(2, 1, '2018-12-30 15:23:26', NULL, NULL, 2, '0.00', NULL, NULL, NULL, '1', '1', 'Ora1232', '200', '200.00', '2', '1', '0.00', 2, 1, 1),
(3, 1, '2018-12-30 15:36:28', NULL, NULL, 2, '0.00', NULL, NULL, NULL, '1', '1', 'Ora1233', '200', '200.00', '3', '1', '0.00', 3, 1, 1),
(4, 1, '2018-12-30 15:38:19', NULL, NULL, 2, '0.00', NULL, NULL, NULL, '1', '1', 'Ora1234', '200', '200.00', '4', '1', '0.00', 4, 1, 1),
(5, 1, '2018-12-30 19:12:03', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'Ora1234', '200', '200.00', '4', '1', '0.00', 5, 1, 1),
(6, 1, '2018-12-30 19:21:31', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'Ora1234', '200', '200.00', '4', '1', '0.00', 6, 1, 1),
(7, 1, '2018-12-30 19:39:10', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'ORAR00000000006', '200', '200.00', '6', '1', '0.00', 7, 1, 1),
(8, 1, '2018-12-30 19:43:54', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'ORAR00000000007', '200', '200.00', '7', '1', '0.00', 8, 1, 1),
(9, 1, '2018-12-30 19:51:12', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'ORAR00000000008', '200', '200.00', '8', '1', '0.00', 9, 1, 1),
(10, 1, '2018-12-30 19:55:01', NULL, NULL, 2, '0.00', NULL, NULL, '2', NULL, NULL, 'ORAR00000000006', '200', '200.00', '9', '1', '0.00', 10, 1, 1),
(11, 1, '2018-12-30 20:13:52', 1, '2018-12-30 20:21:03', 2, '0.00', NULL, NULL, '2', '4', NULL, 'ORAR00000000132', '200', '200.00', '132', '1', '0.00', 11, 1, 1),
(12, 1, '2018-12-30 20:32:54', NULL, NULL, 2, '0.00', NULL, NULL, '2', '2', NULL, 'ORAR00000000133', '200', '200.00', '133', '1', '0.00', 12, 1, 1),
(13, 1, '2018-12-30 20:45:59', NULL, NULL, 2, '0.00', NULL, NULL, '2', '2', NULL, 'ORAR00000000134', '200', '200.00', '134', '1', '0.00', 13, 1, 1),
(14, 1, '2018-12-30 21:18:18', NULL, NULL, 2, '0.00', NULL, NULL, '2', '3', NULL, 'ORAR00000000132', '200', '200.00', '135', '1', '0.00', 14, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cancellation_vs_room`
--

DROP TABLE IF EXISTS `cancellation_vs_room`;
CREATE TABLE IF NOT EXISTS `cancellation_vs_room` (
  `cancellation_vs_room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cancellation_slab_id` varchar(255) DEFAULT NULL,
  `booking_vs_room_id` bigint(20) NOT NULL,
  `cancellation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cancellation_vs_room_id`),
  KEY `FK6swa3fl9vk3sx659lb3j0n095` (`booking_vs_room_id`),
  KEY `FKsn71db4exhm6rar3t4xsn95hd` (`cancellation_id`)
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
  `booking_approval` varchar(255) DEFAULT NULL,
  `checkin_date` varchar(255) DEFAULT NULL,
  `checkout_date` varchar(255) DEFAULT NULL,
  `convenience_amt_wgst` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_booking`
--

INSERT INTO `master_booking` (`booking_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `booking_approval`, `checkin_date`, `checkout_date`, `convenience_amt_wgst`, `grand_total`, `num_of_days`, `orabooking_id`, `property_id`, `total_payble_with_gst`, `total_payble_without_gst`, `user_id`, `convenience_id`) VALUES
(1, 1, '2018-12-30 15:14:18', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546163058940', 1, '600.00', '600.00', 1, 1),
(2, 1, '2018-12-30 15:23:26', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546163606100', 1, '600.00', '600.00', 1, 1),
(3, 1, '2018-12-30 15:36:28', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546164388413', 1, '600.00', '600.00', 1, 1),
(4, 1, '2018-12-30 15:38:19', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546164499147', 1, '600.00', '600.00', 1, 1),
(5, 1, '2018-12-30 19:12:02', NULL, NULL, 2, NULL, '2019-04-10', '2019-04-13', '236.00', '836.00', '3', 'ORA1546177322683', 1, '600.00', '600.00', 1, 1),
(6, 1, '2018-12-30 19:21:30', NULL, NULL, 2, NULL, '2019-04-14', '2019-04-17', '236.00', '836.00', '3', 'ORA1546177890325', 1, '600.00', '600.00', 1, 1),
(7, 1, '2018-12-30 19:39:09', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546178949637', 2, '600.00', '600.00', 1, 1),
(8, 1, '2018-12-30 19:43:52', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546179232865', 2, '600.00', '600.00', 1, 1),
(9, 1, '2018-12-30 19:51:10', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546179670986', 2, '600.00', '600.00', 1, 1),
(10, 1, '2018-12-30 19:55:00', NULL, NULL, 2, NULL, '2019-04-06', '2019-04-09', '236.00', '836.00', '3', 'ORA1546179900247', 2, '600.00', '600.00', 1, 1),
(11, 1, '2018-12-30 20:13:46', 1, '2018-12-30 20:21:03', 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546181026547', 18, '600.00', '600.00', 1, 1),
(12, 1, '2018-12-30 20:32:54', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546182174845', 18, '600.00', '600.00', 1, 1),
(13, 1, '2018-12-30 20:45:57', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546182957773', 18, '600.00', '600.00', 1, 1),
(14, 1, '2018-12-30 21:18:18', NULL, NULL, 2, NULL, '2019-04-03', '2019-04-06', '236.00', '836.00', '3', 'ORA1546184898397', 18, '600.00', '600.00', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `master_cancellation`
--

DROP TABLE IF EXISTS `master_cancellation`;
CREATE TABLE IF NOT EXISTS `master_cancellation` (
  `cancellation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `reason_for_cancellation` varchar(255) DEFAULT NULL,
  `total_amount_paid` varchar(255) DEFAULT NULL,
  `total_amount_refunded` varchar(255) DEFAULT NULL,
  `total_payble_without_gst` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cancellation_id`),
  KEY `FKn4ln85ds3dvicd3f41q3jvxt4` (`booking_id`)
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
  `gst_percentage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`convenience_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_convenience`
--

INSERT INTO `master_convenience` (`convenience_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `amount`, `gst_percentage`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '200', '18');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_gateway`
--

INSERT INTO `master_gateway` (`gateway_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `gateway_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'CASHFREE'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'CASH');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_gst_slab`
--

INSERT INTO `master_gst_slab` (`gst_slab_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `from_amount`, `percentage`, `to_amount`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '0', '0', '999'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '1000', '12', '2499'),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '2500', '18', '7499'),
(4, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '7500', '28', '99999999');

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
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Service', '996311');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_info`
--
ALTER TABLE `booking_info`
  ADD CONSTRAINT `FKo1b17kgui5bkhpme9y5n1hnnd` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`);

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
-- Constraints for table `cancellation_vs_room`
--
ALTER TABLE `cancellation_vs_room`
  ADD CONSTRAINT `FK6swa3fl9vk3sx659lb3j0n095` FOREIGN KEY (`booking_vs_room_id`) REFERENCES `booking_vs_room` (`booking_vs_room_id`),
  ADD CONSTRAINT `FKsn71db4exhm6rar3t4xsn95hd` FOREIGN KEY (`cancellation_id`) REFERENCES `master_cancellation` (`cancellation_id`);

--
-- Constraints for table `master_booking`
--
ALTER TABLE `master_booking`
  ADD CONSTRAINT `FKggx0d1efi7l9qvxfoxwuq72e7` FOREIGN KEY (`convenience_id`) REFERENCES `master_convenience` (`convenience_id`);

--
-- Constraints for table `master_cancellation`
--
ALTER TABLE `master_cancellation`
  ADD CONSTRAINT `FKn4ln85ds3dvicd3f41q3jvxt4` FOREIGN KEY (`booking_id`) REFERENCES `master_booking` (`booking_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
