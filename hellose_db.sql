-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2024 at 10:41 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- DROP DATABASE hellose1234;

USE hellose1234;

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

DROP TABLE `canho`;
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

DROP TABLE `nhankhau`;
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
('Nguyễn Văn AA', '000012345678', 'BM0000', 5, NULL, NULL, 1980, 'Họ hàng', 'Kĩ sư phần mềm', 'Công ty ABC', 'Có mặt', 'M'),
('Lê Minh B', '0000112233', 'BM0001', 6, 30, 12, 1986, 'Chủ hộ', 'Kiến trúc sư', 'Xưởng kiến trúc', 'Có mặt', 'M'),
('Nguyễn Thị C', '0000556677', 'BM0001', 7, 20, 10, 1990, 'Vợ', 'GIáo viên', 'Trường Tiểu học ABC', 'Có mặt', 'F'),
('Lê Hoàng D', '0000111100', 'BM0001', 8, 23, 12, 2004, 'Con', 'Sinh viên', 'Đại học Bách Khoa Hà Nội', 'Có mặt', 'M'),
('Lê Công M', '0000101022', 'BM0001', 9, 23, 9, 2002, 'Con', 'Sinh viên', 'Đại học Bách Khoa Hà Nội', 'Có mặt', 'M'),
('Nguyễn Quang A', '0000223344', 'BM0002', 10, 15, 9, 1980, 'Chủ hộ', 'Bác sĩ', 'Bệnh viện DEF', 'Có mặt', 'M'),
('Nguyễn Thu H', '0000223355', 'BM0002', 11, 23, 9, 1985, 'Vợ', 'Kế toán', 'Công ty EGH', 'Có mặt', 'F');

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau_canho`
--

DROP TABLE `nhankhau_canho`;
CREATE TABLE `nhankhau_canho` (
  `userID` int(11) NOT NULL,
  `maCanHo` varchar(20) NOT NULL,
  `ngayBatDau` date NOT NULL,
  `ngayKetThuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhankhau_canho`
--

INSERT INTO `nhankhau_canho` (`userID`, `maCanHo`, `ngayBatDau`, `ngayKetThuc`) VALUES
(11, 'BM0002', '2023-12-31', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau_tamtru`
--

DROP TABLE `nhankhau_tamtru`;
CREATE TABLE `nhankhau_tamtru` (
  `userID` int(11) NOT NULL,
  `maCanHo` varchar(20) NOT NULL,
  `trangThai` varchar(11) NOT NULL,
  `ngayBatDau` date NOT NULL,
  `ngayKetThuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhankhau_tamtru`
--

INSERT INTO `nhankhau_tamtru` (`userID`, `maCanHo`, `trangThai`, `ngayBatDau`, `ngayKetThuc`) VALUES
(8, 'BM0001', 'Tạm vắng', '2023-05-19', '2023-05-21');

-- --------------------------------------------------------

--
-- Table structure for table `thuphi`
--

DROP TABLE `thuphi`;
CREATE TABLE `thuphi` (
  `maCanHo` varchar(6) NOT NULL,
  `tenKhoanThu` varchar(50) NOT NULL,
  `Thang` int(11) NOT NULL,
  `Nam` int(11) NOT NULL,
  `soTien` int(11) DEFAULT 0,
  `trangThai` varchar(20) NOT NULL,
  `ghiChu` varchar(20) NOT NULL,
  `maKhoanThu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thuphi`
--

INSERT INTO `thuphi` (`maCanHo`, `tenKhoanThu`, `Thang`, `Nam`, `soTien`, `trangThai`, `ghiChu`, `maKhoanThu`) VALUES
('BM0000', 'Tổ chức Giáng Sinh 2023 cho Cư dân', 12, 2023, 0, 'Chưa đóng', 'Tự nguyện', 2),
('BM0000', 'Tiền điện bổ sung - BM0000 - 11/2023', 11, 2023, 100000, 'Chưa đóng', 'Bắt buộc', 3),
('BM0000', 'Phí gửi xe 12/2023', 12, 2023, 140000, 'Chưa đóng', 'Bắt buộc', 4),
('BM0000', 'Phí dịch vụ 11/2023', 11, 2023, 423500, 'Chưa đóng', 'Bắt buộc', 5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE `user`;
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

-- --------------------------------------------------------

--
-- Table structure for table `adminthongbao`
--

DROP TABLE `adminthongbao`;
CREATE TABLE `adminthongbao` (
    `mathongbao` VARCHAR(6) PRIMARY KEY,
    `ngaythongbao` DATE,
    `noidung` TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `adminthongbao`
--

INSERT INTO `adminthongbao` (`mathongbao`, `ngaythongbao`, `noidung`) VALUES ('0001', '2023-01-01', 'chuc mung nam moi');

-- --------------------------------------------------------

--
-- Table structure for table `adminhomthu`
--

DROP TABLE `adminhomthu`;
CREATE TABLE `adminhomthu` (
    `mahomthu` VARCHAR(6) PRIMARY KEY,
    `ngayGopY` DATE,
    `userID` int(11) NOT NULL,
    `noidung` TEXT
    FOREIGN KEY (`userID`) REFERENCES `user`(`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `adminhomthu`
--

INSERT INTO `adminhomthu` (`mahomthu`, `ngayGopY`,`userID`, `noidung`) VALUES ('0001', '2023-01-01',1, 'chuc mung nam moi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`userID`);

--
-- Indexes for table `thuphi`
--
ALTER TABLE `thuphi`
  ADD PRIMARY KEY (`maKhoanThu`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `thuphi`
--
ALTER TABLE `thuphi`
  MODIFY `maKhoanThu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
