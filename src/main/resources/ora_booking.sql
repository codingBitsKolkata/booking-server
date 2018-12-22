-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2018 at 08:17 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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

CREATE TABLE `booking_info` (
  `booking_info_id` bigint(20) NOT NULL,
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
  `booking_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_info`
--

INSERT INTO `booking_info` (`booking_info_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `address`, `checkin_pref_time`, `checkout_pref_time`, `company_name`, `email`, `gstin`, `id_file_url`, `identity_id`, `mobile`, `name`, `booking_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '9874364445', 'abc', 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8776332145', 'df', 2),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8445652145', 'afdbsfc', 3),
(4, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8767652145', 'fabfsc', 4),
(5, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '2323435645', 'fdfasfbc', 5),
(6, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '6756452145', 'sdfasdfbc', 6),
(7, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '5676562145', 'sdfabc', 7),
(8, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '9877332145', 'sfabc', 8),
(9, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '3676461458', 'sfdabc', 9),
(10, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '3566452145', 'asfdbc', 10),
(11, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '9874364445', 'abc', 11),
(12, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8776332145', 'df', 12),
(13, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8445652145', 'afdbsfc', 13),
(14, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '8767652145', 'fabfsc', 14),
(15, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '2323435645', 'fdfasfbc', 15),
(16, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '6756452145', 'sdfasdfbc', 16),
(17, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Kolkata', '10:00:00', '08:00:00', 'Orastays', 'sadasd@dsd.dsd', '147845215478954', 'www.google.com', '1', '5676562145', 'sdfabc', 17);

-- --------------------------------------------------------

--
-- Table structure for table `booking_price`
--

CREATE TABLE `booking_price` (
  `booking_price_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `room_vs_price_id` bigint(20) DEFAULT NULL,
  `booking_vs_room_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_price`
--

INSERT INTO `booking_price` (`booking_price_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `room_vs_price_id`, `booking_vs_room_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 1),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 2),
(4, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 2),
(5, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 3),
(6, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 4),
(7, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 4),
(8, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 3, 4),
(9, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 5),
(10, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 5),
(11, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 6),
(12, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 6),
(13, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 7),
(14, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 7),
(15, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 8),
(16, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 8),
(17, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 9),
(18, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 9),
(19, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 10),
(20, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 10),
(21, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 11),
(22, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 11),
(23, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 12),
(24, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 12),
(25, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 13),
(26, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 13),
(27, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 14),
(28, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 15),
(29, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 16),
(30, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 3, 17),
(31, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 1, 18),
(32, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 2, 18),
(33, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 3, 18);

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_payment`
--

CREATE TABLE `booking_vs_payment` (
  `booking_vs_payment_id` bigint(20) NOT NULL,
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
  `gateway_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_room`
--

CREATE TABLE `booking_vs_room` (
  `booking_vs_room_id` bigint(20) NOT NULL,
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
  `sac_code_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_vs_room`
--

INSERT INTO `booking_vs_room` (`booking_vs_room_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `cgst`, `igst`, `num_of_adult`, `num_of_cot`, `num_of_shared_bed`, `num_of_shared_cot`, `property_pricedrop_id`, `rhd_id`, `rod_id`, `room_actual_price`, `room_gst_slab_price`, `room_id`, `rop_id`, `sgst`, `booking_id`, `gst_slab_id`, `sac_code_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '2', '1', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '1', 1, '120', 1, 1, 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '2', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '2', 1, '120', 2, 1, 1),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '3', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '1', 1, '120', 3, 1, 1),
(4, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '2', '1', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '2', 1, '120', 4, 1, 1),
(5, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '1', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '3', 1, '120', 4, 1, 1),
(6, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '2', '1', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '3', 1, '120', 5, 1, 1),
(7, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '3', '1', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '1', 1, '120', 6, 1, 1),
(8, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '1', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '1', 1, '120', 7, 1, 1),
(9, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '2', '1', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '3', 1, '120', 8, 1, 1),
(10, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '1', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '2', 1, '120', 9, 1, 1),
(11, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '1', '0', NULL, NULL, NULL, NULL, NULL, '1000', '1000', '4', 1, '120', 10, 1, 1),
(12, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '2', '0', NULL, NULL, NULL, '1000', '1000', '5', 1, '120', 11, 1, 1),
(13, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '2', '2', NULL, NULL, NULL, '1000', '1000', '6', 1, '120', 12, 1, 1),
(14, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '', '2', NULL, NULL, NULL, '1000', '1000', '7', 1, '120', 13, 1, 1),
(15, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '1', '1', NULL, NULL, NULL, '1000', '1000', '7', 1, '120', 14, 1, 1),
(16, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '3', '0', NULL, NULL, NULL, '1000', '1000', '8', 1, '120', 15, 1, 1),
(17, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '3', '1', NULL, NULL, NULL, '1000', '1000', '10', 1, '120', 16, 1, 1),
(18, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '120', '', '', '', '1', '0', NULL, NULL, NULL, '1000', '1000', '10', 1, '120', 17, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_room_ora_discount`
--

CREATE TABLE `booking_vs_room_ora_discount` (
  `brod_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `rod_id` varchar(255) DEFAULT NULL,
  `booking_vs_room_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_booking`
--

CREATE TABLE `master_booking` (
  `booking_id` bigint(20) NOT NULL,
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
  `convenience_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_booking`
--

INSERT INTO `master_booking` (`booking_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `booking_approval`, `checkin_date`, `checkout_date`, `convenience_amt_wgst`, `grand_total`, `num_of_days`, `orabooking_id`, `property_id`, `total_payble_with_gst`, `total_payble_without_gst`, `user_id`, `convenience_id`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-25', '2019-01-26', '236', '2476', '2', 'ORAB00000000001', 1, '2240', '2000', 1, 1),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-05', '236', '2476', '2', 'ORAB00000000002', 1, '2240', '2000', 1, 1),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-04', '236', '2476', '2', 'ORAB00000000003', 1, '2240', '2000', 1, 1),
(4, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-10', '2019-01-22', '236', '2476', '2', 'ORAB00000000004', 1, '2240', '2000', 1, 1),
(5, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-06', '236', '2476', '2', 'ORAB00000000005', 1, '2240', '2000', 1, 1),
(6, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-07', '2019-01-10', '236', '2476', '2', 'ORAB00000000006', 1, '2240', '2000', 1, 1),
(7, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-21', '2019-01-25', '236', '2476', '2', 'ORAB00000000007', 1, '2240', '2000', 1, 1),
(8, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-23', '2019-01-25', '236', '2476', '2', 'ORAB00000000008', 1, '2240', '2000', 1, 1),
(9, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-24', '2019-01-25', '236', '2476', '2', 'ORAB00000000009', 1, '2240', '2000', 1, 1),
(10, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-22', '2019-01-26', '236', '2476', '2', 'ORAB00000000010', 1, '2240', '2000', 1, 1),
(11, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-30', '236', '2476', '2', 'ORAB00000000011', 2, '2240', '2000', 1, 1),
(12, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-10', '236', '2476', '2', 'ORAB00000000012', 2, '2240', '2000', 1, 1),
(13, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-09', '2019-01-18', '236', '2476', '2', 'ORAB00000000013', 2, '2240', '2000', 1, 1),
(14, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-01', '2019-01-31', '236', '2476', '2', 'ORAB00000000014', 2, '2240', '2000', 1, 1),
(15, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-02', '2019-01-30', '236', '2476', '2', 'ORAB00000000015', 2, '2240', '2000', 1, 1),
(16, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-07', '2019-01-25', '236', '2476', '2', 'ORAB00000000016', 2, '2240', '2000', 1, 1),
(17, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Y', '2019-01-21', '2019-01-25', '236', '2476', '2', 'ORAB00000000017', 2, '2240', '2000', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `master_convenience`
--

CREATE TABLE `master_convenience` (
  `convenience_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `gst_percentage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_convenience`
--

INSERT INTO `master_convenience` (`convenience_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `amount`, `gst_percentage`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, '200', '18');

-- --------------------------------------------------------

--
-- Table structure for table `master_gateway`
--

CREATE TABLE `master_gateway` (
  `gateway_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `gateway_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_gateway`
--

INSERT INTO `master_gateway` (`gateway_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `gateway_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'CASHFREE');

-- --------------------------------------------------------

--
-- Table structure for table `master_gst_slab`
--

CREATE TABLE `master_gst_slab` (
  `gst_slab_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `from_amount` varchar(255) DEFAULT NULL,
  `percentage` varchar(255) DEFAULT NULL,
  `to_amount` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `master_sac_code` (
  `sac_code_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sac_name` varchar(255) DEFAULT NULL,
  `sac_code_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_sac_code`
--

INSERT INTO `master_sac_code` (`sac_code_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `sac_name`, `sac_code_number`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Service', '996311');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_info`
--
ALTER TABLE `booking_info`
  ADD PRIMARY KEY (`booking_info_id`),
  ADD KEY `FKo1b17kgui5bkhpme9y5n1hnnd` (`booking_id`);

--
-- Indexes for table `booking_price`
--
ALTER TABLE `booking_price`
  ADD PRIMARY KEY (`booking_price_id`),
  ADD KEY `FKixjogkpnvlab9fi6j0palwvtx` (`booking_vs_room_id`);

--
-- Indexes for table `booking_vs_payment`
--
ALTER TABLE `booking_vs_payment`
  ADD PRIMARY KEY (`booking_vs_payment_id`),
  ADD KEY `FK7o4e18jl8hkm5t6t1xs00gtr8` (`booking_id`),
  ADD KEY `FKs3qn4233lkx83a4rimjvfs563` (`gateway_id`);

--
-- Indexes for table `booking_vs_room`
--
ALTER TABLE `booking_vs_room`
  ADD PRIMARY KEY (`booking_vs_room_id`),
  ADD KEY `FK5ufallrpmt0lxf4fgp4u0vudm` (`booking_id`),
  ADD KEY `FKkvrct3l7pfffk88kjk7ffx4jk` (`gst_slab_id`),
  ADD KEY `FKdeiy7awthvtj76bw7kiescxqp` (`sac_code_id`);

--
-- Indexes for table `booking_vs_room_ora_discount`
--
ALTER TABLE `booking_vs_room_ora_discount`
  ADD PRIMARY KEY (`brod_id`),
  ADD KEY `FKc55jscpp800wbixwi45vabx3p` (`booking_vs_room_id`);

--
-- Indexes for table `master_booking`
--
ALTER TABLE `master_booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `FKggx0d1efi7l9qvxfoxwuq72e7` (`convenience_id`);

--
-- Indexes for table `master_convenience`
--
ALTER TABLE `master_convenience`
  ADD PRIMARY KEY (`convenience_id`);

--
-- Indexes for table `master_gateway`
--
ALTER TABLE `master_gateway`
  ADD PRIMARY KEY (`gateway_id`);

--
-- Indexes for table `master_gst_slab`
--
ALTER TABLE `master_gst_slab`
  ADD PRIMARY KEY (`gst_slab_id`);

--
-- Indexes for table `master_sac_code`
--
ALTER TABLE `master_sac_code`
  ADD PRIMARY KEY (`sac_code_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_info`
--
ALTER TABLE `booking_info`
  MODIFY `booking_info_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `booking_price`
--
ALTER TABLE `booking_price`
  MODIFY `booking_price_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `booking_vs_payment`
--
ALTER TABLE `booking_vs_payment`
  MODIFY `booking_vs_payment_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `booking_vs_room`
--
ALTER TABLE `booking_vs_room`
  MODIFY `booking_vs_room_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `booking_vs_room_ora_discount`
--
ALTER TABLE `booking_vs_room_ora_discount`
  MODIFY `brod_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `master_booking`
--
ALTER TABLE `master_booking`
  MODIFY `booking_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `master_convenience`
--
ALTER TABLE `master_convenience`
  MODIFY `convenience_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `master_gateway`
--
ALTER TABLE `master_gateway`
  MODIFY `gateway_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `master_gst_slab`
--
ALTER TABLE `master_gst_slab`
  MODIFY `gst_slab_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `master_sac_code`
--
ALTER TABLE `master_sac_code`
  MODIFY `sac_code_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
-- Constraints for table `booking_vs_room_ora_discount`
--
ALTER TABLE `booking_vs_room_ora_discount`
  ADD CONSTRAINT `FKc55jscpp800wbixwi45vabx3p` FOREIGN KEY (`booking_vs_room_id`) REFERENCES `booking_vs_room` (`booking_vs_room_id`);

--
-- Constraints for table `master_booking`
--
ALTER TABLE `master_booking`
  ADD CONSTRAINT `FKggx0d1efi7l9qvxfoxwuq72e7` FOREIGN KEY (`convenience_id`) REFERENCES `master_convenience` (`convenience_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
