CREATE DATABASE car_rental;

CREATE TABLE cars
(
    id            INT IDENTITY (1,1) PRIMARY KEY,
    maker         NVARCHAR(100)  NOT NULL,
    model         NVARCHAR(100)  NOT NULL,
    year          INT            NOT NULL,
    color         NVARCHAR(50)   NOT NULL,
    license_plate NVARCHAR(50)   NOT NULL,
    daily_price   DECIMAL(10, 2) NOT NULL,
    is_rented     BIT            NOT NULL DEFAULT 0
);

INSERT INTO Cars (maker, model, year, color, license_plate, daily_price, is_rented)
VALUES ('VOLKSWAGEN', 'GOLF 5', 2008, 'SILVER', 'KH1234AB', 100.00, 0);

INSERT INTO Cars (maker, model, year, color, license_plate, daily_price, is_rented)
VALUES ('BMW', 'X5', 2022, 'BLACK', 'CB5678CK', 90.00, 0);

INSERT INTO Cars (maker, model, year, color, license_plate, daily_price, is_rented)
VALUES ('FORD', 'FOCUS', 2019, 'BLUE', 'CA9012EP', 40.00, 1);