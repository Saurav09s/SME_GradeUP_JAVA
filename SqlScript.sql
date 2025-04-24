create database driver;
use driver;
CREATE TABLE driver (
  ID varchar(10) NOT NULL,
  Name varchar(50) DEFAULT NULL,
  Location varchar(50) DEFAULT NULL,
  Team varchar(50) DEFAULT NULL,
  Score decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (ID)
);
 CREATE TABLE pos (
  ID varchar(10) DEFAULT NULL primary key references driver(ID),
  First int DEFAULT NULL,
  Second int DEFAULT NULL,
  Third int DEFAULT NULL,
  Fourth int DEFAULT NULL,
  Fifth int DEFAULT NULL,
  Sixth int DEFAULT NULL,
  Seventh int DEFAULT NULL,
  Eighth int DEFAULT NULL,
  Ninth int DEFAULT NULL,
  Tenth int DEFAULT NULL
);

 CREATE TABLE car (
  ID varchar(10) DEFAULT NULL primary KEY references driver(ID),
  Name varchar(50) DEFAULT NULL
);

CREATE TABLE drace (
  ID varchar(10) DEFAULT NULL primary KEY references driver(ID),
  date varchar(15) DEFAULT NULL
);