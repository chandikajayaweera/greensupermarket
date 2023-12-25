CREATE TABLE `unit` (
  `UnitName` varchar(255) NOT NULL,
  `UnitAbbreviation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UnitName`),
  UNIQUE KEY `UnitAbbreviation` (`UnitAbbreviation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `category` (
  `CategoryName` varchar(255) NOT NULL,
  `CategoryImageURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CategoryName`),
  UNIQUE KEY `CategoryImageURL` (`CategoryImageURL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subcategory` (
  `CategoryName` varchar(255) DEFAULT NULL,
  `SubCategoryName` varchar(255) NOT NULL,
  PRIMARY KEY (`SubCategoryName`),
  KEY `CategoryName` (`CategoryName`),
  CONSTRAINT `subcategory_ibfk_1` FOREIGN KEY (`CategoryName`) REFERENCES `category` (`CategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(255) DEFAULT NULL,
  `SubCategoryName` varchar(255) DEFAULT NULL,
  `ProductSKU` varchar(255) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `ProductDescription` varchar(255) DEFAULT NULL,
  `ProductUnitPrice` decimal(10,2) NOT NULL,
  `ProductStock` int DEFAULT NULL,
  `ProductImageURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductSKU` (`ProductSKU`),
  UNIQUE KEY `ProductName` (`ProductName`),
  KEY `BrandName` (`BrandName`),
  KEY `SubCategoryName` (`SubCategoryName`),
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`SubCategoryName`) REFERENCES `subcategory` (`SubCategoryName`),
  CONSTRAINT `product_chk_1` CHECK ((`ProductUnitPrice` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `CustomerFname` varchar(255) DEFAULT NULL,
  `CustomerLname` varchar(255) DEFAULT NULL,
  `CustomerEmail` varchar(255) DEFAULT NULL,
  `CustomerPnumber` varchar(255) DEFAULT NULL,
  `CustomerPasswordHash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `CustomerEmail` (`CustomerEmail`),
  UNIQUE KEY `CustomerPnumber` (`CustomerPnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `CustomerID` int NOT NULL,
  `AddressID` int NOT NULL,
  `AddressType` varchar(255) DEFAULT NULL,
  `AddressStreet` varchar(255) DEFAULT NULL,
  `AddressCity` varchar(255) DEFAULT NULL,
  `AddressState` varchar(255) DEFAULT NULL,
  `AddressZipCode` varchar(255) DEFAULT NULL,
  `AddressCountry` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`,`AddressID`),
  KEY `idx_address` (`AddressID`,`CustomerID`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orderitem` (
  `CustomerOrderID` int NOT NULL,
  `ProductID` int NOT NULL,
  `OrderItemQuantity` int DEFAULT NULL,
  `OrderItemUnitPrice` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`CustomerOrderID`,`ProductID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`CustomerOrderID`) REFERENCES `customerorder` (`CustomerOrderID`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customerorder` (
  `CustomerOrderID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `ShippingAddressID` int DEFAULT NULL,
  `BillingAddressID` int DEFAULT NULL,
  `CustomerOrderDate` date DEFAULT NULL,
  `CustomerOrderStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerOrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `ShippingAddressID` (`ShippingAddressID`,`CustomerID`),
  KEY `BillingAddressID` (`BillingAddressID`,`CustomerID`),
  CONSTRAINT `customerorder_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `customerorder_ibfk_2` FOREIGN KEY (`ShippingAddressID`, `CustomerID`) REFERENCES `address` (`AddressID`, `CustomerID`),
  CONSTRAINT `customerorder_ibfk_3` FOREIGN KEY (`BillingAddressID`, `CustomerID`) REFERENCES `address` (`AddressID`, `CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feedback` (
  `CustomerID` int DEFAULT NULL,
  `FeedbackID` int NOT NULL AUTO_INCREMENT,
  `FeedbackRating` int DEFAULT NULL,
  `FeedbackMessage` varchar(255) DEFAULT NULL,
  `FeedbackDate` date DEFAULT NULL,
  PRIMARY KEY (`FeedbackID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `RoleName` varchar(255) DEFAULT NULL,
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `EmployeeFname` varchar(255) DEFAULT NULL,
  `EmployeeLname` varchar(255) DEFAULT NULL,
  `EmployeeEmail` varchar(255) DEFAULT NULL,
  `EmployeePasswordHash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `EmployeeEmail` (`EmployeeEmail`),
  KEY `RoleName` (`RoleName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `employee` VALUES ('admin', 1, 'Demo', 'Account', 'admin@test.com', '$2a$10$.NbMpABkrDd6uSiujKwmZePTSpH3LjKYNEX7TXTszvFWb6aib2F4S');