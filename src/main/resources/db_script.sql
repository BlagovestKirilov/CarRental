CREATE DATABASE CarRental;

CREATE TABLE Cars
(
    Id           INT IDENTITY (1,1) PRIMARY KEY,
    Maker        NVARCHAR(100)  NOT NULL,
    Model        NVARCHAR(100)  NOT NULL,
    Year         INT            NOT NULL,
    Color        NVARCHAR(50)   NOT NULL,
    LicensePlate NVARCHAR(50)   NOT NULL,
    DailyPrice   DECIMAL(10, 2) NOT NULL,
    IsRented     BIT            NOT NULL DEFAULT 0
);

INSERT INTO Cars (Maker, Model, Year, Color, LicensePlate, DailyPrice, IsRented)
VALUES ('Volkswagen', 'Golf 5', 2008, 'Silver', 'KH1234AB', 100.00, 0);

INSERT INTO Cars (Maker, Model, Year, Color, LicensePlate, DailyPrice, IsRented)
VALUES ('BMW', 'X5', 2022, 'Black', 'CB5678CK', 90.00, 0);

INSERT INTO Cars (Maker, Model, Year, Color, LicensePlate, DailyPrice, IsRented)
VALUES ('Ford', 'Focus', 2019, 'Blue', 'CA9012EP', 40.00, 1);