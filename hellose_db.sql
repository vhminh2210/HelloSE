-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2023 at 12:48 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hellose_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `canho`
--

CREATE TABLE `canho` (
  `maCanHo` varchar(6) NOT NULL,
  `dienTich` float NOT NULL,
  `soXeMay` int(11) NOT NULL,
  `soXeHoi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `canho`
--

INSERT INTO `canho` (`maCanHo`, `dienTich`, `soXeMay`, `soXeHoi`) VALUES
('BM0000', 60.5, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau`
--

CREATE TABLE `nhankhau` (
  `hoTen` varchar(50) NOT NULL,
  `cccd` varchar(12) NOT NULL,
  `maCanHo` varchar(6) NOT NULL,
  `userID` int(11) NOT NULL,
  `ngaySinh` int(11) DEFAULT NULL,
  `thangSinh` int(11) DEFAULT NULL,
  `namSinh` int(11) DEFAULT NULL,
  `quanHe` varchar(50) NOT NULL,
  `ngheNghiep` varchar(100) NOT NULL,
  `coQuan` varchar(100) NOT NULL,
  `trangThai` varchar(20) NOT NULL,
  `gioiTinh` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhankhau`
--

INSERT INTO `nhankhau` (`hoTen`, `cccd`, `maCanHo`, `userID`, `ngaySinh`, `thangSinh`, `namSinh`, `quanHe`, `ngheNghiep`, `coQuan`, `trangThai`, `gioiTinh`) VALUES
('Vương Hoàng Minh', '000020210590', 'BM0000', 1, 22, 10, 2003, 'ADMIN', 'Sinh viên', 'Trường Công nghệ Thông tin và Truyền thông, Đại học Bách Khoa Hà Nội', 'Có mặt', 'M'),
('Nguyễn Văn A', '000012345678', 'BM0000', 5, NULL, NULL, 1980, 'Họ hàng', 'Kĩ sư phần mềm', 'Công ty A', 'Có mặt', 'M');

-- --------------------------------------------------------

--
-- Table structure for table `thuphi`
--

CREATE TABLE `thuphi` (
  `maCanHo` varchar(6) NOT NULL,
  `tenKhoanThu` varchar(50) NOT NULL,
  `Thang` int(11) NOT NULL,
  `Nam` int(11) NOT NULL,
  `soTien` int(11) DEFAULT NULL,
  `trangThai` varchar(20) NOT NULL,
  `ghiChu` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thuphi`
--

INSERT INTO `thuphi` (`maCanHo`, `tenKhoanThu`, `Thang`, `Nam`, `soTien`, `trangThai`, `ghiChu`) VALUES
('BM0000', 'Tổ chức Giáng Sinh 2023 cho Cư dân', 12, 2023, NULL, 'Chưa đóng', 'Tự nguyện'),
('BM0000', 'Trung thu 2023', 9, 2023, 100000, 'Đã đóng', 'Tự nguyện'),
('BM0000', 'Phí dịch vụ 11/2023', 11, 2023, 423500, 'Chưa đóng', 'Bắt buộc');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `passWd` varchar(50) NOT NULL DEFAULT '12345678',
  `quyenHan` varchar(20) NOT NULL DEFAULT 'Cư dân'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `sdt`, `passWd`, `quyenHan`) VALUES
(1, 'MinhVH_0590', '0961472210', '20210590', 'ADMIN'),
(2, 'BienNC_0101', '', '20210101', 'ADMIN'),
(3, 'NhatTH_0655', '', '20210655', 'ADMIN'),
(4, 'DucNM_5196', '', '20215196', 'ADMIN'),
(5, 'resident_1', '', '12345678', 'Cư dân'),
(6, 'resident_2', '', '12345678', 'Cư dân');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
