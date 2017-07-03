DROP DATABASE IF EXISTS JakeAndTomHotel;

CREATE DATABASE JakeAndTomHotel;

USE JakeAndTomHotel;

CREATE TABLE RoomRate(
	RoomRateID INT NOT NULL auto_increment,
    Rate DECIMAL (6,2) NOT NULL,
    SeasonEventTime VARCHAR (30) NULL,
    DateStart DATE NOT NULL,
    DateEnd DATE NULL,
    PRIMARY KEY (RoomRateID)
);

CREATE TABLE Amenity(
	AmenityID INT NOT NULL auto_increment,
    AmenityName VARCHAR (50) NOT NULL,
    AmenityNumber smallint NOT NULL,
    PRIMARY KEY (AmenityID)
);

CREATE TABLE Room(
	RoomID INT NOT NULL auto_increment,
    RoomNumber MEDIUMINT NOT NULL,
    Floor smallint NOT NULL,
    RoomType varchar(30) NOT NULL,
    RoomView varchar(50) null,
    Occupancy smallint not null,
    BathroomType varchar(30),
    RoomRateID INT NOT NULL,
    PRIMARY KEY (RoomID),
    Constraint fk_Room_RoomRate
    FOREIGN KEY (RoomRateID)
    REFERENCES RoomRate(RoomRateID)
);

CREATE TABLE RoomAmenities(
	RoomAmenityID INT NOT NULL auto_increment,
    RoomID INT NOT NULL,
    AmenityID INT NOT NULL,
    PRIMARY KEY (RoomAmenityID),
    constraint fk_RoomAmenities_Room
    FOREIGN KEY (RoomID)
    REFERENCES Room(RoomID),
    constraint fk_RoomAmenities_Amenity
    FOREIGN KEY (AmenityID)
    REFERENCES Amenity(AmenityID)
);

CREATE TABLE EmergencyContact (
	EmergencyContactID INT NOT NULL auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    Phone smallint not null,
    PRIMARY KEY (EmergencyContactID)
);

CREATE TABLE Customer (
	CustomerID INT NOT NULL auto_increment,
    FirstName varchar(30) NOT NULL,
    LastName varchar(30) NOT NULL,
    Phone smallint NOT NULL,
    Email varchar(50) Not Null,
    CustomerAddress varchar(30) null,
    memberstatus varchar(10) null,
    EmergencyContactID INT NUll,
    PRIMARY KEY (CustomerID),
    constraint fk_Customer_EmergencyContact
    FOREIGN KEY (EmergencyContactID)
    REFERENCES EmergencyContact(EmergencyContactID)
);

CREATE TABLE Promotion(
	PromotionID INT NOT NULL auto_increment,
    PromotionName varchar(30) Not Null,
    DateValid DATE not null,
    DateInvalid DATE not null,
    AmountType smallint not null,
    Amount Decimal (5,2) not null,
    EventName varchar (50) null,
    PRIMARY KEY (PromotionID)
);

CREATE TABLE AddOnPrice(
	AddOnPriceID INT not null auto_increment,
    Price Decimal(5,2) not null,
    DateStart Date not null,
    DateEnd Date null,
    Primary KEY (AddOnPriceID)
);

CREATE TABLE AddOn(
	AddOnID INT Not Null auto_increment,
    AddOnName varchar(30) not null,
    AddOnPriceID Int not null,
    PRIMARY KEY (AddOnID),
    constraint fk_AddOn_AddOnPrice
    FOREIGN KEY (AddOnPriceID)
    REFERENCES AddOnPrice(AddOnPriceID)
);

CREATE TABLE Guest (
	GuestID INT not null auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    Age smallint not null,
    Primary Key (GuestID)
);

CREATE TABLE Reservation(
	ReservationID INT not null auto_increment,
    CustomerID INT not null,
    PRIMARY KEY (ReservationID),
    CONSTRAINT fk_Reservation_Customer
    FOREIGN KEY (CustomerID)
    REFERENCES Customer(CustomerID)
);

CREATE TABLE ReservationRoom(
	ReservationRoomID INT not null auto_increment,
    ReservationID INT not null,
    RoomID INT not null,
    DateStart DATE not null,
    DateEnd DATE not null,
    ExtraOccupancy smallint null,
    CheckIN BOOL not null,
    CheckOut BOOL not null,
    PRIMARY KEY (ReservationRoomID)
);

ALTER TABLE ReservationRoom
	ADD CONSTRAINT fk_ReservationRoom_Reservation
    FOREIGN KEY (ReservationID)
    REFERENCES Reservation(ReservationID),
    ADD CONSTRAINT fk_ReservationRoom_Room
    FOREIGN KEY (RoomID)
    REFERENCES Room(RoomID);

CREATE TABLE GuestReservation(
	GuestReservationID INT not null auto_increment,
    ReservationID INT not null,
    GuestID INT not null,
    PRIMARY KEY (GuestReservationID),
    CONSTRAINT fk_GuestReservation_Reservation
    FOREIGN KEY (ReservationID)
    REFERENCES Reservation(ReservationID),
    CONSTRAINT fk_GuestReservation_Guest
    FOREIGN KEY (GuestID)
    REFERENCES Guest(GuestID)
);

CREATE TABLE Billing(
	BillingID INT not null auto_increment,
    ReservationID INT not null,
    PromotionID INT null,
    CreditCardNumber smallint not null,
    PAID BOOL not null,
    PRIMARY KEY (BillingID),
    CONSTRAINT fk_Billing_Reservation
    FOREIGN KEY (ReservationID)
    REFERENCES Reservation(ReservationID),
    CONSTRAINT fk_Billing_Promotion
    FOREIGN KEY (PromotionID)
    REFERENCES Promotion(PromotionID)
);

CREATE TABLE BillingAddOn (
	BillingAddOnID INT not null auto_increment,
    BillingID INT not null,
    AddOnID INT not null,
    PRIMARY KEY (BillingAddOnID),
    CONSTRAINT fk_BillingAddOn_Billing
    FOREIGN KEY (BillingID)
    REFERENCES Billing(BillingID),
    CONSTRAINT fk_BillingAddOn_AddOn
    FOREIGN KEY (AddOnID)
    REFERENCES AddOn(AddOnID)
);
