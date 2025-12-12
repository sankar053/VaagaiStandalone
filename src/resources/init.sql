--Category

CREATE TABLE Category (
    categoryID int NOT NULL,
    categoryName varchar(255) NOT NULL,
   PRIMARY KEY (categoryID)
);

--ProductDetails

CREATE TABLE ProductDetails (
   productID int NOT NULL,
   productName varchar(255) NOT NULL,
   productPackSize varchar(255) NOT NULL,
   productImage varchar(255) NOT NULL,
   productAttribute varchar(255) NOT NULL,
   categoryID int,
   PRIMARY KEY (productID),
   CONSTRAINT FK_Category FOREIGN KEY (categoryID)
   REFERENCES Category(categoryID)  
);

-- ProductPrice
CREATE TABLE ProductPrice (
   id int NOT NULL,
   productActualPrice double NOT NULL,
   productDiscountPrice double,
   productID int,
   PRIMARY KEY (id),
   CONSTRAINT FK_Product FOREIGN KEY (productID)
   REFERENCES ProductDetails(productID)  
);
-----ProductRating
CREATE TABLE ProductRating (
   id int NOT NULL,
   productRating double NOT NULL,
   productID int,
   PRIMARY KEY (id),
   CONSTRAINT FK_Product_Rating FOREIGN KEY (productID)
   REFERENCES ProductDetails(productID)  
);