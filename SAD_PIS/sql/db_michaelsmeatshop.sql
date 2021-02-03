-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 14, 2019 at 07:18 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_michaelsmeatshop`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `carcass_stock`
-- (See below for the actual view)
--
CREATE TABLE `carcass_stock` (
`ID` int(4)
,`Product` varchar(50)
,`Unit` varchar(15)
,`Meat` varchar(30)
,`Qnty` double(10,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `customerdetails`
-- (See below for the actual view)
--
CREATE TABLE `customerdetails` (
`ID` int(4)
,`Name` varchar(50)
,`Contact` text
,`Address` text
,`Gender` text
,`Status` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `groceryproductlist`
-- (See below for the actual view)
--
CREATE TABLE `groceryproductlist` (
`product_id` int(4)
,`Name` varchar(50)
,`Type` varchar(25)
,`Quantity` varchar(15)
,`Price` double(10,2)
,`SellPrice` double(19,2)
,`Category` varchar(25)
,`Status` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `livestockin`
-- (See below for the actual view)
--
CREATE TABLE `livestockin` (
`ID` int(4)
,`Supplier` varchar(50)
,`Animal` varchar(30)
,`Qnty` bigint(21)
,`Total_Kgs` double(19,2)
,`Price` double(10,2)
,`TotalPrice` double(19,2)
,`Employee` varchar(50)
,`Date` text
,`Status` varchar(35)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `livestockin_carcass`
-- (See below for the actual view)
--
CREATE TABLE `livestockin_carcass` (
`details_id` int(4)
,`livestockin_id` int(4)
,`Product` varchar(50)
,`Quantity` double(10,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `meatproductlist`
-- (See below for the actual view)
--
CREATE TABLE `meatproductlist` (
`product_id` int(4)
,`Name` varchar(50)
,`Type` varchar(25)
,`Quantity` varchar(15)
,`Price` double(10,2)
,`Meat` varchar(30)
,`Status` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `si_details`
-- (See below for the actual view)
--
CREATE TABLE `si_details` (
`details_id` int(4)
,`reference_id` int(4)
,`Product` varchar(50)
,`Quantity` double(10,2)
,`Unit` varchar(15)
,`Price` double(10,2)
,`TotalPrice` double(19,2)
,`Supplier` varchar(50)
,`Status` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `stocks`
-- (See below for the actual view)
--
CREATE TABLE `stocks` (
`ID` int(4)
,`Product` varchar(50)
,`Unit` varchar(15)
,`Type` varchar(25)
,`Category` varchar(25)
,`Qnty` double(10,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `stock_in_details`
-- (See below for the actual view)
--
CREATE TABLE `stock_in_details` (
`ID` int(4)
,`Employee` varchar(50)
,`Date` text
,`Products` bigint(21)
,`Quantity` double(19,2)
,`status_id` int(2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `supplier_details`
-- (See below for the actual view)
--
CREATE TABLE `supplier_details` (
`ID` int(2)
,`Name` varchar(50)
,`Contact` text
,`Email` text
,`Address` text
,`Status` varchar(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_accounts`
--

CREATE TABLE `tbl_accounts` (
  `Employee_ID` int(2) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_accounts`
--

INSERT INTO `tbl_accounts` (`Employee_ID`, `Username`, `Password`) VALUES
(1, 'administrator', 'password123');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_adjuststock`
--

CREATE TABLE `tbl_adjuststock` (
  `id` int(8) NOT NULL,
  `employee_id` int(2) DEFAULT NULL,
  `date` text,
  `adjusttype_id` int(2) DEFAULT NULL,
  `product_id` int(4) DEFAULT NULL,
  `Quantity` double(10,2) DEFAULT NULL,
  `Remarks` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_adjuststock`
--

INSERT INTO `tbl_adjuststock` (`id`, `employee_id`, `date`, `adjusttype_id`, `product_id`, `Quantity`, `Remarks`) VALUES
(1, 1, '2019-09-09', 2, 10, 2.00, 'giluto namo paniudto');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_adjusttype`
--

CREATE TABLE `tbl_adjusttype` (
  `adjusttype_id` int(2) NOT NULL,
  `title` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_adjusttype`
--

INSERT INTO `tbl_adjusttype` (`adjusttype_id`, `title`) VALUES
(1, 'Add'),
(2, 'Remove');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admins`
--

CREATE TABLE `tbl_admins` (
  `Employee_ID` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_admins`
--

INSERT INTO `tbl_admins` (`Employee_ID`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_animal`
--

CREATE TABLE `tbl_animal` (
  `animal_ID` int(2) NOT NULL,
  `title` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_animal`
--

INSERT INTO `tbl_animal` (`animal_ID`, `title`) VALUES
(1, 'Pork'),
(2, 'Chicken'),
(3, 'Beef');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_carcassclassification`
--

CREATE TABLE `tbl_carcassclassification` (
  `product_ID` int(4) DEFAULT NULL,
  `AnimalType_ID` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_carcassclassification`
--

INSERT INTO `tbl_carcassclassification` (`product_ID`, `AnimalType_ID`) VALUES
(1, 1),
(2, 1),
(3, 3),
(4, 2),
(8, 3),
(9, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 1),
(20, 1),
(21, 1),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(26, 1),
(27, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cart`
--

CREATE TABLE `tbl_cart` (
  `cart_id` int(3) NOT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `date` text,
  `total_Payment` double(10,2) DEFAULT NULL,
  `totalProduct` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_cartdetails`
--

CREATE TABLE `tbl_cartdetails` (
  `cart_id` int(3) DEFAULT NULL,
  `product_id` int(4) DEFAULT NULL,
  `Quantity` float(10,2) DEFAULT NULL,
  `Price` float(10,2) DEFAULT NULL,
  `TotalPrice` float(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `customer_id` int(4) NOT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `contact` text,
  `address` text,
  `gender_id` int(2) DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_customer`
--

INSERT INTO `tbl_customer` (`customer_id`, `customerName`, `contact`, `address`, `gender_id`, `status_id`) VALUES
(1, 'SJ Moraga', '0922-222-222', 'Davao City', 1, 1),
(2, 'Seth Moraga', '12346', 'Davao', 1, 1),
(3, 'Izza Mae', '091222555', 'Davao City, Sandawa', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_employee`
--

CREATE TABLE `tbl_employee` (
  `Employee_ID` int(4) NOT NULL,
  `employeeName` varchar(50) DEFAULT NULL,
  `contact` text,
  `Gender_ID` int(2) DEFAULT NULL,
  `address` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_employee`
--

INSERT INTO `tbl_employee` (`Employee_ID`, `employeeName`, `contact`, `Gender_ID`, `address`) VALUES
(1, 'Seth Joshua', '0912345678', 1, 'Davao City');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_gender`
--

CREATE TABLE `tbl_gender` (
  `Gender_ID` int(2) NOT NULL,
  `Title` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_gender`
--

INSERT INTO `tbl_gender` (`Gender_ID`, `Title`) VALUES
(1, 'Male'),
(2, 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_inventorylist`
--

CREATE TABLE `tbl_inventorylist` (
  `inventory_id` int(4) NOT NULL,
  `product_id` int(4) DEFAULT NULL,
  `quantity` double(10,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_inventorylist`
--

INSERT INTO `tbl_inventorylist` (`inventory_id`, `product_id`, `quantity`) VALUES
(1, 5, 0.00),
(2, 6, 0.00),
(3, 7, 0.00),
(4, 1, 0.00),
(5, 2, 2.50),
(6, 3, 0.00),
(7, 4, 0.00),
(8, 8, 0.00),
(9, 9, 0.00),
(10, 11, 0.00),
(11, 12, 10.00),
(12, 13, 5.00),
(13, 14, 0.00),
(14, 10, 8.00),
(15, 15, 0.00),
(16, 16, 0.00),
(17, 17, 0.00),
(18, 18, 0.00),
(19, 19, 10.50),
(20, 20, 2.50),
(21, 21, 1.70),
(22, 22, 20.50),
(23, 23, 5.60),
(24, 24, 2.00),
(25, 25, 15.60),
(26, 26, 15.70),
(27, 27, 10.50),
(28, 28, 3.50),
(29, 29, 20.50),
(30, 30, 20.70),
(31, 31, 5.70),
(32, 32, 3.50);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_livestockin`
--

CREATE TABLE `tbl_livestockin` (
  `livestockin_id` int(4) NOT NULL,
  `supplier_id` int(2) DEFAULT NULL,
  `animal_id` int(4) DEFAULT NULL,
  `purchasePrice` double(10,2) DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL,
  `date` text,
  `employee_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_livestockin`
--

INSERT INTO `tbl_livestockin` (`livestockin_id`, `supplier_id`, `animal_id`, `purchasePrice`, `status_id`, `date`, `employee_id`) VALUES
(3, 4, 1, 100.00, 2, '2019-09-04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_livestockin_carcass`
--

CREATE TABLE `tbl_livestockin_carcass` (
  `details_id` int(4) NOT NULL,
  `livestockin_id` int(4) DEFAULT NULL,
  `product_id` int(4) DEFAULT NULL,
  `quantity` double(10,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_livestockin_carcass`
--

INSERT INTO `tbl_livestockin_carcass` (`details_id`, `livestockin_id`, `product_id`, `quantity`) VALUES
(1, 3, 2, 2.50),
(2, 3, 19, 10.50),
(3, 3, 20, 2.50),
(4, 3, 21, 1.70),
(5, 3, 22, 20.50),
(6, 3, 23, 5.60),
(7, 3, 24, 2.00),
(8, 3, 25, 15.60),
(9, 3, 26, 15.70),
(10, 3, 27, 10.50),
(11, 3, 28, 3.50),
(12, 3, 29, 20.50),
(13, 3, 30, 20.70),
(14, 3, 31, 5.70),
(15, 3, 32, 3.50);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_livestockin_particulars`
--

CREATE TABLE `tbl_livestockin_particulars` (
  `particular_id` int(4) NOT NULL,
  `livestockin_id` int(4) DEFAULT NULL,
  `quantity` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_livestockin_particulars`
--

INSERT INTO `tbl_livestockin_particulars` (`particular_id`, `livestockin_id`, `quantity`) VALUES
(9, 3, 95.00),
(11, 3, 50.00);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product`
--

CREATE TABLE `tbl_product` (
  `product_id` int(4) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `productType_id` int(2) DEFAULT NULL,
  `QntyType_id` int(2) DEFAULT NULL,
  `Price` double(10,2) DEFAULT NULL,
  `productCategory_ID` int(2) DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_product`
--

INSERT INTO `tbl_product` (`product_id`, `productName`, `productType_id`, `QntyType_id`, `Price`, `productCategory_ID`, `status_id`) VALUES
(1, 'Tinae sa Baboy', 2, 2, 110.00, NULL, 1),
(2, 'Mascara', 2, 2, 85.00, NULL, 1),
(3, 'Tiil sa Baka', 2, 2, 125.00, NULL, 1),
(4, 'Chicken Wings', 2, 2, 85.00, NULL, 1),
(5, 'Virginia Hotdog 1kg [pack]', 1, 1, 25.00, 2, 2),
(6, 'Virginia Hotdog [kinilo]', 1, 2, 20.00, 2, 2),
(7, 'Stardog [kinilo]', 1, 2, 23.00, 2, 2),
(8, 'Tinae sa Baka', 2, 2, 110.00, NULL, 1),
(9, 'Beef Ribs', 2, 2, 150.00, NULL, 1),
(10, 'Nagoya Halang', 1, 1, 8.00, 1, 1),
(11, 'Pampangga Best 1kg [pack]', 1, 1, 25.00, 9, 2),
(12, 'Century Tuna Spicy', 1, 1, 10.00, 1, 2),
(13, 'CDO 8pcs [pack]', 1, 1, 25.00, 10, 2),
(14, 'CheeseWiz [small]', 1, 1, 15.00, 11, 2),
(15, 'Ikog sa Baka', 2, 2, 110.00, NULL, 1),
(16, 'ulo sa baka', 2, 2, 110.00, NULL, 1),
(17, 'TI-down sa baka', 2, 2, 110.00, NULL, 1),
(18, 'Taba sa Baka', 2, 2, 100.00, NULL, 1),
(19, 'tinae/ikog/ulo sa baboy', 2, 2, 110.00, NULL, 1),
(20, 'tongol/bulaklak', 2, 2, 60.00, NULL, 1),
(21, 'ilogon sa baboy', 2, 2, 40.00, NULL, 1),
(22, 'sari-sari', 2, 2, 100.00, NULL, 1),
(23, 'atay sa baboy', 2, 2, 140.00, NULL, 1),
(24, 'Mk sa baboy', 2, 2, 145.00, NULL, 1),
(25, 'TI-up Baboy', 2, 2, 145.00, NULL, 1),
(26, 'TI-down baboy', 2, 2, 110.00, NULL, 1),
(27, 'TL-Front', 2, 2, 150.00, NULL, 1),
(28, 'Bukog Baboy', 2, 2, 145.00, NULL, 1),
(29, 'Pork ribs (spl cut)', 2, 2, 150.00, NULL, 1),
(30, 'Pork', 2, 2, 185.00, NULL, 1),
(31, 'Taba baboy', 2, 2, 30.00, NULL, 1),
(32, 'panit', 2, 2, 64.00, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_productcategory`
--

CREATE TABLE `tbl_productcategory` (
  `productCategory_id` int(2) NOT NULL,
  `Title` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_productcategory`
--

INSERT INTO `tbl_productcategory` (`productCategory_id`, `Title`) VALUES
(1, 'Can Goods'),
(2, 'Hotdogs'),
(3, 'Vegetables'),
(8, 'Condiments'),
(9, 'Tocino'),
(10, 'Ham'),
(11, 'Cheese'),
(12, 'Longganisa');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_producttype`
--

CREATE TABLE `tbl_producttype` (
  `productType_ID` int(2) NOT NULL,
  `title` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_producttype`
--

INSERT INTO `tbl_producttype` (`productType_ID`, `title`) VALUES
(1, 'Grocery'),
(2, 'Meat');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_quantitytype`
--

CREATE TABLE `tbl_quantitytype` (
  `QntyType_ID` int(2) NOT NULL,
  `title` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_quantitytype`
--

INSERT INTO `tbl_quantitytype` (`QntyType_ID`, `title`) VALUES
(1, 'per pieces'),
(2, 'per kilo');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_status`
--

CREATE TABLE `tbl_status` (
  `status_id` int(2) NOT NULL,
  `title` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_status`
--

INSERT INTO `tbl_status` (`status_id`, `title`) VALUES
(1, 'Actived'),
(2, 'Deactivated');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_stockin`
--

CREATE TABLE `tbl_stockin` (
  `stockIN_id` int(4) NOT NULL,
  `employee_id` int(4) DEFAULT NULL,
  `Date` text,
  `status_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_stockin`
--

INSERT INTO `tbl_stockin` (`stockIN_id`, `employee_id`, `Date`, `status_id`) VALUES
(1, 1, '2019-08-31', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_stockindetails`
--

CREATE TABLE `tbl_stockindetails` (
  `details_ID` int(4) NOT NULL,
  `stockIN_ID` int(4) DEFAULT NULL,
  `Product_id` int(4) DEFAULT NULL,
  `Quantity` double(10,2) DEFAULT NULL,
  `purchasePrice` double(10,2) DEFAULT NULL,
  `supplier_ID` int(2) DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_stockindetails`
--

INSERT INTO `tbl_stockindetails` (`details_ID`, `stockIN_ID`, `Product_id`, `Quantity`, `purchasePrice`, `supplier_ID`, `status_id`) VALUES
(1, 1, 10, 10.00, 18.00, 2, 2),
(3, 1, 12, 10.00, 20.00, 2, 2),
(4, 1, 13, 5.00, 85.00, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_stockinlogs`
--

CREATE TABLE `tbl_stockinlogs` (
  `stocklogs_id` int(8) NOT NULL,
  `stockIN_id` int(4) DEFAULT NULL,
  `employee_id` int(3) DEFAULT NULL,
  `Date` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_stockstatus`
--

CREATE TABLE `tbl_stockstatus` (
  `status_id` int(2) NOT NULL,
  `title` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_stockstatus`
--

INSERT INTO `tbl_stockstatus` (`status_id`, `title`) VALUES
(1, 'Pending'),
(2, 'Done'),
(3, 'Canceled');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_supplier`
--

CREATE TABLE `tbl_supplier` (
  `Supplier_ID` int(2) NOT NULL,
  `SupplierName` varchar(50) DEFAULT NULL,
  `Contact` text,
  `Email` text,
  `address` text,
  `status_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_supplier`
--

INSERT INTO `tbl_supplier` (`Supplier_ID`, `SupplierName`, `Contact`, `Email`, `address`, `status_id`) VALUES
(1, 'SJ Farm', '092-123-222', 'SJ@gmail.com', 'Davao City', 1),
(2, 'Star Enterprise', '092-123-222', 'jf@gmail.com', 'Davao City', 1),
(3, 'Vegie Corp.', '122-123-222', 'vegie@gmail.com', 'Davao City', 1),
(4, 'Sample Piggery & Farm', 'N/A', 'piggery@gmail.com', 'Davao City', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_supplying`
--

CREATE TABLE `tbl_supplying` (
  `supplying_id` int(3) NOT NULL,
  `customer_id` int(3) DEFAULT NULL,
  `date_of_transaction` text,
  `total_balance` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_supplyingdetails`
--

CREATE TABLE `tbl_supplyingdetails` (
  `supplying_id` int(3) DEFAULT NULL,
  `product_id` int(4) DEFAULT NULL,
  `quantity` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transactions`
--

CREATE TABLE `tbl_transactions` (
  `transaction_id` int(4) NOT NULL,
  `supplying_id` int(4) DEFAULT NULL,
  `cart_id` int(4) DEFAULT NULL,
  `amount_paid` double(10,2) DEFAULT NULL,
  `date_of_transaction` text,
  `transactiontype_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transactiontype`
--

CREATE TABLE `tbl_transactiontype` (
  `transactiontype_id` int(2) NOT NULL,
  `title` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transactiontype`
--

INSERT INTO `tbl_transactiontype` (`transactiontype_id`, `title`) VALUES
(1, 'Daily Transaction'),
(2, 'WholeSaler Transaction');

-- --------------------------------------------------------

--
-- Structure for view `carcass_stock`
--
DROP TABLE IF EXISTS `carcass_stock`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `carcass_stock`  AS  select `i`.`inventory_id` AS `ID`,`p`.`productName` AS `Product`,`q`.`title` AS `Unit`,`a`.`title` AS `Meat`,`i`.`quantity` AS `Qnty` from ((((`tbl_inventorylist` `i` join `tbl_product` `p` on((`i`.`product_id` = `p`.`product_id`))) left join `tbl_quantitytype` `q` on((`p`.`QntyType_id` = `q`.`QntyType_ID`))) left join `tbl_carcassclassification` `m` on((`i`.`product_id` = `m`.`product_ID`))) left join `tbl_animal` `a` on((`m`.`AnimalType_ID` = `a`.`animal_ID`))) where (`a`.`title` is not null) ;

-- --------------------------------------------------------

--
-- Structure for view `customerdetails`
--
DROP TABLE IF EXISTS `customerdetails`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `customerdetails`  AS  select `c`.`customer_id` AS `ID`,`c`.`customerName` AS `Name`,`c`.`contact` AS `Contact`,`c`.`address` AS `Address`,`g`.`Title` AS `Gender`,`s`.`title` AS `Status` from ((`tbl_customer` `c` join `tbl_gender` `g` on((`c`.`gender_id` = `g`.`Gender_ID`))) join `tbl_status` `s` on((`c`.`status_id` = `s`.`status_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `groceryproductlist`
--
DROP TABLE IF EXISTS `groceryproductlist`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `groceryproductlist`  AS  select `p`.`product_id` AS `product_id`,`p`.`productName` AS `Name`,`t`.`title` AS `Type`,`q`.`title` AS `Quantity`,`p`.`Price` AS `Price`,((select `tbl_stockindetails`.`purchasePrice` from `tbl_stockindetails` where (`tbl_stockindetails`.`Product_id` = `p`.`product_id`) order by `tbl_stockindetails`.`details_ID` desc limit 1) + `p`.`Price`) AS `SellPrice`,`c`.`Title` AS `Category`,`s`.`title` AS `Status` from ((((`tbl_product` `p` join `tbl_producttype` `t` on((`p`.`productType_id` = `t`.`productType_ID`))) join `tbl_quantitytype` `q` on((`p`.`QntyType_id` = `q`.`QntyType_ID`))) join `tbl_productcategory` `c` on((`p`.`productCategory_ID` = `c`.`productCategory_id`))) join `tbl_status` `s` on((`p`.`status_id` = `s`.`status_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `livestockin`
--
DROP TABLE IF EXISTS `livestockin`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `livestockin`  AS  select `l`.`livestockin_id` AS `ID`,`s`.`SupplierName` AS `Supplier`,`a`.`title` AS `Animal`,(select count(`tbl_livestockin_particulars`.`particular_id`) from `tbl_livestockin_particulars` where (`tbl_livestockin_particulars`.`livestockin_id` = `l`.`livestockin_id`)) AS `Qnty`,(select sum(`tbl_livestockin_particulars`.`quantity`) from `tbl_livestockin_particulars` where (`tbl_livestockin_particulars`.`livestockin_id` = `l`.`livestockin_id`)) AS `Total_Kgs`,`l`.`purchasePrice` AS `Price`,(`l`.`purchasePrice` * (select sum(`tbl_livestockin_particulars`.`quantity`) from `tbl_livestockin_particulars` where (`tbl_livestockin_particulars`.`livestockin_id` = `l`.`livestockin_id`))) AS `TotalPrice`,`e`.`employeeName` AS `Employee`,`l`.`date` AS `Date`,`m`.`title` AS `Status` from ((((`tbl_livestockin` `l` join `tbl_supplier` `s` on((`l`.`supplier_id` = `s`.`Supplier_ID`))) join `tbl_animal` `a` on((`l`.`animal_id` = `a`.`animal_ID`))) join `tbl_stockstatus` `m` on((`l`.`status_id` = `m`.`status_id`))) join `tbl_employee` `e` on((`l`.`employee_id` = `e`.`Employee_ID`))) ;

-- --------------------------------------------------------

--
-- Structure for view `livestockin_carcass`
--
DROP TABLE IF EXISTS `livestockin_carcass`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `livestockin_carcass`  AS  select `c`.`details_id` AS `details_id`,`c`.`livestockin_id` AS `livestockin_id`,`p`.`productName` AS `Product`,`c`.`quantity` AS `Quantity` from (`tbl_livestockin_carcass` `c` join `tbl_product` `p` on((`c`.`product_id` = `p`.`product_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `meatproductlist`
--
DROP TABLE IF EXISTS `meatproductlist`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `meatproductlist`  AS  select `p`.`product_id` AS `product_id`,`p`.`productName` AS `Name`,`t`.`title` AS `Type`,`q`.`title` AS `Quantity`,`p`.`Price` AS `Price`,`a`.`title` AS `Meat`,`s`.`title` AS `Status` from (((((`tbl_product` `p` join `tbl_producttype` `t` on((`p`.`productType_id` = `t`.`productType_ID`))) join `tbl_quantitytype` `q` on((`p`.`QntyType_id` = `q`.`QntyType_ID`))) left join `tbl_carcassclassification` `m` on((`p`.`product_id` = `m`.`product_ID`))) left join `tbl_animal` `a` on((`m`.`AnimalType_ID` = `a`.`animal_ID`))) join `tbl_status` `s` on((`p`.`status_id` = `s`.`status_id`))) where (`p`.`productType_id` = 2) ;

-- --------------------------------------------------------

--
-- Structure for view `si_details`
--
DROP TABLE IF EXISTS `si_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `si_details`  AS  select `d`.`details_ID` AS `details_id`,`d`.`stockIN_ID` AS `reference_id`,`p`.`productName` AS `Product`,`d`.`Quantity` AS `Quantity`,`z`.`title` AS `Unit`,`d`.`purchasePrice` AS `Price`,(`d`.`Quantity` * `d`.`purchasePrice`) AS `TotalPrice`,`s`.`SupplierName` AS `Supplier`,`st`.`title` AS `Status` from ((((`tbl_stockindetails` `d` join `tbl_product` `p` on((`d`.`Product_id` = `p`.`product_id`))) join `tbl_supplier` `s` on((`d`.`supplier_ID` = `s`.`Supplier_ID`))) join `tbl_status` `st` on((`d`.`status_id` = `st`.`status_id`))) left join `tbl_quantitytype` `z` on((`p`.`QntyType_id` = `z`.`QntyType_ID`))) ;

-- --------------------------------------------------------

--
-- Structure for view `stocks`
--
DROP TABLE IF EXISTS `stocks`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stocks`  AS  select `i`.`inventory_id` AS `ID`,`p`.`productName` AS `Product`,`q`.`title` AS `Unit`,`t`.`title` AS `Type`,`c`.`Title` AS `Category`,`i`.`quantity` AS `Qnty` from ((((`tbl_inventorylist` `i` join `tbl_product` `p` on((`i`.`product_id` = `p`.`product_id`))) left join `tbl_quantitytype` `q` on((`p`.`QntyType_id` = `q`.`QntyType_ID`))) left join `tbl_producttype` `t` on((`p`.`productType_id` = `t`.`productType_ID`))) left join `tbl_productcategory` `c` on((`p`.`productCategory_ID` = `c`.`productCategory_id`))) ;

-- --------------------------------------------------------

--
-- Structure for view `stock_in_details`
--
DROP TABLE IF EXISTS `stock_in_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stock_in_details`  AS  select `s`.`stockIN_id` AS `ID`,`e`.`employeeName` AS `Employee`,`s`.`Date` AS `Date`,(select count(`tbl_stockindetails`.`Product_id`) from `tbl_stockindetails` where (`tbl_stockindetails`.`stockIN_ID` = `s`.`stockIN_id`) group by `tbl_stockindetails`.`stockIN_ID`) AS `Products`,(select sum(`tbl_stockindetails`.`Quantity`) from `tbl_stockindetails` where (`tbl_stockindetails`.`stockIN_ID` = `s`.`stockIN_id`) group by `tbl_stockindetails`.`stockIN_ID`) AS `Quantity`,`s`.`status_id` AS `status_id` from (`tbl_stockin` `s` join `tbl_employee` `e` on((`s`.`employee_id` = `e`.`Employee_ID`))) ;

-- --------------------------------------------------------

--
-- Structure for view `supplier_details`
--
DROP TABLE IF EXISTS `supplier_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `supplier_details`  AS  select `s`.`Supplier_ID` AS `ID`,`s`.`SupplierName` AS `Name`,`s`.`Contact` AS `Contact`,`s`.`Email` AS `Email`,`s`.`address` AS `Address`,`a`.`title` AS `Status` from (`tbl_supplier` `s` join `tbl_status` `a` on((`s`.`status_id` = `a`.`status_id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD KEY `Employee_ID` (`Employee_ID`);

--
-- Indexes for table `tbl_adjuststock`
--
ALTER TABLE `tbl_adjuststock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_adjusttype`
--
ALTER TABLE `tbl_adjusttype`
  ADD PRIMARY KEY (`adjusttype_id`);

--
-- Indexes for table `tbl_admins`
--
ALTER TABLE `tbl_admins`
  ADD KEY `Employee_ID` (`Employee_ID`);

--
-- Indexes for table `tbl_animal`
--
ALTER TABLE `tbl_animal`
  ADD PRIMARY KEY (`animal_ID`);

--
-- Indexes for table `tbl_carcassclassification`
--
ALTER TABLE `tbl_carcassclassification`
  ADD KEY `product_ID` (`product_ID`),
  ADD KEY `AnimalType_ID` (`AnimalType_ID`);

--
-- Indexes for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  ADD PRIMARY KEY (`cart_id`);

--
-- Indexes for table `tbl_cartdetails`
--
ALTER TABLE `tbl_cartdetails`
  ADD KEY `cart_id` (`cart_id`);

--
-- Indexes for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `gender_id` (`gender_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD PRIMARY KEY (`Employee_ID`),
  ADD KEY `Gender_ID` (`Gender_ID`);

--
-- Indexes for table `tbl_gender`
--
ALTER TABLE `tbl_gender`
  ADD PRIMARY KEY (`Gender_ID`);

--
-- Indexes for table `tbl_inventorylist`
--
ALTER TABLE `tbl_inventorylist`
  ADD PRIMARY KEY (`inventory_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_livestockin`
--
ALTER TABLE `tbl_livestockin`
  ADD PRIMARY KEY (`livestockin_id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `animal_type` (`animal_id`),
  ADD KEY `status_id` (`status_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `tbl_livestockin_carcass`
--
ALTER TABLE `tbl_livestockin_carcass`
  ADD PRIMARY KEY (`details_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `livestockin_id` (`livestockin_id`);

--
-- Indexes for table `tbl_livestockin_particulars`
--
ALTER TABLE `tbl_livestockin_particulars`
  ADD PRIMARY KEY (`particular_id`),
  ADD KEY `livestockin_id` (`livestockin_id`);

--
-- Indexes for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `productName` (`productName`),
  ADD KEY `productCategory_ID` (`productCategory_ID`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `tbl_productcategory`
--
ALTER TABLE `tbl_productcategory`
  ADD PRIMARY KEY (`productCategory_id`);

--
-- Indexes for table `tbl_producttype`
--
ALTER TABLE `tbl_producttype`
  ADD PRIMARY KEY (`productType_ID`);

--
-- Indexes for table `tbl_quantitytype`
--
ALTER TABLE `tbl_quantitytype`
  ADD PRIMARY KEY (`QntyType_ID`);

--
-- Indexes for table `tbl_status`
--
ALTER TABLE `tbl_status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `tbl_stockin`
--
ALTER TABLE `tbl_stockin`
  ADD PRIMARY KEY (`stockIN_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `tbl_stockindetails`
--
ALTER TABLE `tbl_stockindetails`
  ADD PRIMARY KEY (`details_ID`),
  ADD KEY `supplier_ID` (`supplier_ID`),
  ADD KEY `stockIN_ID` (`stockIN_ID`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `tbl_stockinlogs`
--
ALTER TABLE `tbl_stockinlogs`
  ADD PRIMARY KEY (`stocklogs_id`),
  ADD KEY `stockIN_id` (`stockIN_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `tbl_stockstatus`
--
ALTER TABLE `tbl_stockstatus`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `tbl_supplier`
--
ALTER TABLE `tbl_supplier`
  ADD PRIMARY KEY (`Supplier_ID`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `tbl_supplying`
--
ALTER TABLE `tbl_supplying`
  ADD PRIMARY KEY (`supplying_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `tbl_supplyingdetails`
--
ALTER TABLE `tbl_supplyingdetails`
  ADD KEY `supplying_id` (`supplying_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `tbl_transactions`
--
ALTER TABLE `tbl_transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `supplying_id` (`supplying_id`),
  ADD KEY `cart_id` (`cart_id`);

--
-- Indexes for table `tbl_transactiontype`
--
ALTER TABLE `tbl_transactiontype`
  ADD PRIMARY KEY (`transactiontype_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_adjuststock`
--
ALTER TABLE `tbl_adjuststock`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_adjusttype`
--
ALTER TABLE `tbl_adjusttype`
  MODIFY `adjusttype_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_animal`
--
ALTER TABLE `tbl_animal`
  MODIFY `animal_ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_cart`
--
ALTER TABLE `tbl_cart`
  MODIFY `cart_id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `customer_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  MODIFY `Employee_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_gender`
--
ALTER TABLE `tbl_gender`
  MODIFY `Gender_ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_inventorylist`
--
ALTER TABLE `tbl_inventorylist`
  MODIFY `inventory_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `tbl_livestockin`
--
ALTER TABLE `tbl_livestockin`
  MODIFY `livestockin_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_livestockin_carcass`
--
ALTER TABLE `tbl_livestockin_carcass`
  MODIFY `details_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tbl_livestockin_particulars`
--
ALTER TABLE `tbl_livestockin_particulars`
  MODIFY `particular_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `product_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `tbl_productcategory`
--
ALTER TABLE `tbl_productcategory`
  MODIFY `productCategory_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_producttype`
--
ALTER TABLE `tbl_producttype`
  MODIFY `productType_ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_quantitytype`
--
ALTER TABLE `tbl_quantitytype`
  MODIFY `QntyType_ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_status`
--
ALTER TABLE `tbl_status`
  MODIFY `status_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_stockin`
--
ALTER TABLE `tbl_stockin`
  MODIFY `stockIN_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_stockindetails`
--
ALTER TABLE `tbl_stockindetails`
  MODIFY `details_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_stockinlogs`
--
ALTER TABLE `tbl_stockinlogs`
  MODIFY `stocklogs_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_stockstatus`
--
ALTER TABLE `tbl_stockstatus`
  MODIFY `status_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_supplier`
--
ALTER TABLE `tbl_supplier`
  MODIFY `Supplier_ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_supplying`
--
ALTER TABLE `tbl_supplying`
  MODIFY `supplying_id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_transactions`
--
ALTER TABLE `tbl_transactions`
  MODIFY `transaction_id` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_transactiontype`
--
ALTER TABLE `tbl_transactiontype`
  MODIFY `transactiontype_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD CONSTRAINT `tbl_accounts_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `tbl_employee` (`Employee_ID`);

--
-- Constraints for table `tbl_adjuststock`
--
ALTER TABLE `tbl_adjuststock`
  ADD CONSTRAINT `tbl_adjuststock_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`Employee_ID`),
  ADD CONSTRAINT `tbl_adjuststock_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`product_id`);

--
-- Constraints for table `tbl_admins`
--
ALTER TABLE `tbl_admins`
  ADD CONSTRAINT `tbl_admins_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `tbl_employee` (`Employee_ID`);

--
-- Constraints for table `tbl_carcassclassification`
--
ALTER TABLE `tbl_carcassclassification`
  ADD CONSTRAINT `tbl_carcassclassification_ibfk_1` FOREIGN KEY (`product_ID`) REFERENCES `tbl_product` (`product_id`),
  ADD CONSTRAINT `tbl_carcassclassification_ibfk_2` FOREIGN KEY (`AnimalType_ID`) REFERENCES `tbl_animal` (`animal_ID`);

--
-- Constraints for table `tbl_cartdetails`
--
ALTER TABLE `tbl_cartdetails`
  ADD CONSTRAINT `tbl_cartdetails_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `tbl_cart` (`cart_id`);

--
-- Constraints for table `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD CONSTRAINT `tbl_customer_ibfk_1` FOREIGN KEY (`gender_id`) REFERENCES `tbl_gender` (`Gender_ID`),
  ADD CONSTRAINT `tbl_customer_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`status_id`);

--
-- Constraints for table `tbl_employee`
--
ALTER TABLE `tbl_employee`
  ADD CONSTRAINT `tbl_employee_ibfk_1` FOREIGN KEY (`Gender_ID`) REFERENCES `tbl_gender` (`Gender_ID`);

--
-- Constraints for table `tbl_inventorylist`
--
ALTER TABLE `tbl_inventorylist`
  ADD CONSTRAINT `tbl_inventorylist_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`product_id`);

--
-- Constraints for table `tbl_livestockin`
--
ALTER TABLE `tbl_livestockin`
  ADD CONSTRAINT `tbl_livestockin_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `tbl_supplier` (`Supplier_ID`),
  ADD CONSTRAINT `tbl_livestockin_ibfk_3` FOREIGN KEY (`animal_id`) REFERENCES `tbl_animal` (`animal_ID`),
  ADD CONSTRAINT `tbl_livestockin_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `tbl_stockstatus` (`status_id`),
  ADD CONSTRAINT `tbl_livestockin_ibfk_5` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`Employee_ID`);

--
-- Constraints for table `tbl_livestockin_carcass`
--
ALTER TABLE `tbl_livestockin_carcass`
  ADD CONSTRAINT `tbl_livestockin_carcass_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`product_id`),
  ADD CONSTRAINT `tbl_livestockin_carcass_ibfk_3` FOREIGN KEY (`livestockin_id`) REFERENCES `tbl_livestockin` (`livestockin_id`);

--
-- Constraints for table `tbl_livestockin_particulars`
--
ALTER TABLE `tbl_livestockin_particulars`
  ADD CONSTRAINT `tbl_livestockin_particulars_ibfk_1` FOREIGN KEY (`livestockin_id`) REFERENCES `tbl_livestockin` (`livestockin_id`);

--
-- Constraints for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD CONSTRAINT `tbl_product_ibfk_1` FOREIGN KEY (`productCategory_ID`) REFERENCES `tbl_productcategory` (`productCategory_id`),
  ADD CONSTRAINT `tbl_product_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`status_id`);

--
-- Constraints for table `tbl_stockin`
--
ALTER TABLE `tbl_stockin`
  ADD CONSTRAINT `tbl_stockin_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`Employee_ID`),
  ADD CONSTRAINT `tbl_stockin_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `tbl_stockstatus` (`status_id`);

--
-- Constraints for table `tbl_stockindetails`
--
ALTER TABLE `tbl_stockindetails`
  ADD CONSTRAINT `tbl_stockindetails_ibfk_1` FOREIGN KEY (`supplier_ID`) REFERENCES `tbl_supplier` (`Supplier_ID`),
  ADD CONSTRAINT `tbl_stockindetails_ibfk_2` FOREIGN KEY (`stockIN_ID`) REFERENCES `tbl_stockin` (`stockIN_id`),
  ADD CONSTRAINT `tbl_stockindetails_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`status_id`);

--
-- Constraints for table `tbl_stockinlogs`
--
ALTER TABLE `tbl_stockinlogs`
  ADD CONSTRAINT `tbl_stockinlogs_ibfk_1` FOREIGN KEY (`stockIN_id`) REFERENCES `tbl_stockin` (`stockIN_id`),
  ADD CONSTRAINT `tbl_stockinlogs_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`Employee_ID`);

--
-- Constraints for table `tbl_supplier`
--
ALTER TABLE `tbl_supplier`
  ADD CONSTRAINT `tbl_supplier_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `tbl_status` (`status_id`);

--
-- Constraints for table `tbl_supplying`
--
ALTER TABLE `tbl_supplying`
  ADD CONSTRAINT `tbl_supplying_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`);

--
-- Constraints for table `tbl_supplyingdetails`
--
ALTER TABLE `tbl_supplyingdetails`
  ADD CONSTRAINT `tbl_supplyingdetails_ibfk_1` FOREIGN KEY (`supplying_id`) REFERENCES `tbl_supplying` (`supplying_id`),
  ADD CONSTRAINT `tbl_supplyingdetails_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tbl_product` (`product_id`);

--
-- Constraints for table `tbl_transactions`
--
ALTER TABLE `tbl_transactions`
  ADD CONSTRAINT `tbl_transactions_ibfk_1` FOREIGN KEY (`supplying_id`) REFERENCES `tbl_supplying` (`supplying_id`),
  ADD CONSTRAINT `tbl_transactions_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `tbl_cart` (`cart_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
