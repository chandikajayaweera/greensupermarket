CREATE DATABASE greensupermarket;

USE greensupermarket;

-- Create Unit Table
CREATE TABLE Unit (
    UnitName VARCHAR(255) PRIMARY KEY,
    UnitAbbreviation VARCHAR(255) UNIQUE
);

-- Create Brand Table
CREATE TABLE Brand (
    BrandName VARCHAR(255) PRIMARY KEY,
    BrandLogoURL VARCHAR(255) UNIQUE,
    BrandIsActive BOOLEAN
);

-- Create Variation Table
CREATE TABLE Variation (
    VariationName VARCHAR(255) PRIMARY KEY,
    VariationDescription VARCHAR(255)
);

-- Create VariationValue Table
CREATE TABLE VariationValue (
    VariationName VARCHAR(255),
    VariationValueName VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (VariationName) REFERENCES Variation(VariationName)
);

-- Create Category Table
CREATE TABLE Category (
    CategoryName VARCHAR(255) PRIMARY KEY,
    CategoryImageURL VARCHAR(255) UNIQUE
);

-- Create SubCategory Table
CREATE TABLE SubCategory (
    CategoryName VARCHAR(255),
    SubCategoryName VARCHAR(255) PRIMARY KEY,
    SubCategoryImageURL VARCHAR(255) UNIQUE,
    FOREIGN KEY (CategoryName) REFERENCES Category(CategoryName)
);

-- Create Product Table
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    UnitName VARCHAR(255),
    BrandName VARCHAR(255),
    VariationValueName VARCHAR(255),
    SubCategoryName VARCHAR(255),
    ProductSKU VARCHAR(255) UNIQUE,
    ProductName VARCHAR(255) UNIQUE,
    ProductDescription VARCHAR(255),
    ProductUnitPrice DECIMAL,
    ProductIsDiscounted BOOLEAN,
    ProductDiscountedPrice DECIMAL,
    ProductIsActive BOOLEAN,
    FOREIGN KEY (UnitName) REFERENCES Unit(UnitName),
    FOREIGN KEY (BrandName) REFERENCES Brand(BrandName),
    FOREIGN KEY (VariationValueName) REFERENCES VariationValue(VariationValueName),
    FOREIGN KEY (SubCategoryName) REFERENCES SubCategory(SubCategoryName)
);

-- Create ProductImage Table
CREATE TABLE ProductImage (
    ProductID INT,
    ProductImageID INT,
    ProductImageURL VARCHAR(255) UNIQUE,
    PRIMARY KEY (ProductID, ProductImageID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create Trigger
DELIMITER //
CREATE TRIGGER SetProductImageID
BEFORE INSERT ON ProductImage
FOR EACH ROW
BEGIN
    DECLARE maxImageID INT;

    -- Find the maximum ProductImageID for the given ProductID
    SELECT IFNULL(MAX(ProductImageID), 0) INTO maxImageID
    FROM ProductImage
    WHERE ProductID = NEW.ProductID;

    -- Set the new ProductImageID
    SET NEW.ProductImageID = maxImageID + 1;
END;
//
DELIMITER ;

-- Create Stock Table
CREATE TABLE Stock (
    ProductID INT,
    StockID INT AUTO_INCREMENT PRIMARY KEY,
    StockQuantity INT,
    StockDate DATE,
    StockAvailable BOOLEAN,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create Customer Table
CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerFname VARCHAR(255),
    CustomerLname VARCHAR(255),
    CustomerEmail VARCHAR(255) UNIQUE,
    CustomerPnumber VARCHAR(255) UNIQUE,
    CustomerPasswordHash VARCHAR(255)
);

-- Create Address Table
CREATE TABLE Address (
    CustomerID INT,
    AddressID INT,
    AddressType VARCHAR(255),
    AddressStreet VARCHAR(255),
    AddressCity VARCHAR(255),
    AddressState VARCHAR(255),
    AddressZipCode VARCHAR(255),
    AddressCountry VARCHAR(255),
    PRIMARY KEY (CustomerID, AddressID),
    INDEX idx_address (AddressID, CustomerID),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Create Trigger
DELIMITER //
CREATE TRIGGER SetAddressID
BEFORE INSERT ON Address
FOR EACH ROW
BEGIN
    DECLARE maxAddressID INT;

    -- Find the maximum AddressID for the given CustomerID
    SELECT COALESCE(MAX(AddressID), 0) INTO maxAddressID
    FROM Address
    WHERE CustomerID = NEW.CustomerID;

    -- Set the new AddressID
    SET NEW.AddressID = maxAddressID + 1;
END;
//
DELIMITER ;


-- Create CustomerOrder Table
CREATE TABLE CustomerOrder (
    CustomerOrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    ShippingAddressID INT,
    BillingAddressID INT,
    CustomerOrderDate DATE,
    CustomerOrderStatus VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ShippingAddressID, CustomerID) REFERENCES Address(AddressID, CustomerID),
    FOREIGN KEY (BillingAddressID, CustomerID) REFERENCES Address(AddressID, CustomerID)
);


-- Create OrderItem Table
CREATE TABLE OrderItem (
    CustomerOrderID INT,
    ProductID INT,
    OrderItemQuantity INT,
    OrderItemUnitPrice DECIMAL,
    PRIMARY KEY (CustomerOrderID, ProductID),
    FOREIGN KEY (CustomerOrderID) REFERENCES CustomerOrder(CustomerOrderID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create Feedback Table
CREATE TABLE Feedback (
    CustomerID INT,
    FeedbackID INT AUTO_INCREMENT PRIMARY KEY,
    FeedbackRating INT,
    FeedbackMessage VARCHAR(255),
    FeedbackDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Create Support Table
CREATE TABLE Support (
    SupportID INT AUTO_INCREMENT PRIMARY KEY,
    SupportDate DATE,
    SupportName VARCHAR(255),
    SupportEmail VARCHAR(255),
    SupportMessage VARCHAR(255)
);

-- Create Permission Table
CREATE TABLE Permission (
    PermissionName VARCHAR(255) PRIMARY KEY,
    PermissionProperty VARCHAR(255) UNIQUE
);

-- Create Role Table
CREATE TABLE Role (
    PermissionName VARCHAR(255),
    RoleName VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (PermissionName) REFERENCES Permission(PermissionName)
);

-- Create Employee Table
CREATE TABLE Employee (
    RoleName VARCHAR(255),
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeFname VARCHAR(255),
    EmployeeLname VARCHAR(255),
    EmployeeEmail VARCHAR(255) UNIQUE,
    EmployeePasswordHash VARCHAR(255),
    FOREIGN KEY (RoleName) REFERENCES Role(RoleName)
);
