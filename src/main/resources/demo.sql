SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS demo;

CREATE DATABASE demo;

USE demo;

DROP TABLE IF EXISTS BidList;

CREATE TABLE BidList (
  bid_list_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE,
  ask_quantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (bid_list_id)
)

DROP TABLE IF EXISTS Trade;

CREATE TABLE Trade (
  trade_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (trade_id)
)

DROP TABLE IF EXISTS CurvePoint;

CREATE TABLE CurvePoint (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  CurveId tinyint,
  asOfDate TIMESTAMP,
  term DOUBLE ,
  value DOUBLE ,
  creationDate TIMESTAMP ,

  PRIMARY KEY (Id)
)

DROP TABLE IF EXISTS Rating;

CREATE TABLE Rating (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber tinyint,

  PRIMARY KEY (Id)
)

DROP TABLE IF EXISTS RuleName;

CREATE TABLE RuleName (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
)

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
)

LOCK TABLE Users WRITE;

INSERT INTO Users(fullname, username, password, role) VALUES("Administrator", "admin", "$2a$10$oGWdvIBJ76tH7MtHgvUiyeLmiVtdmMqahwrflWlHjIfMj21qY7mIK", "ADMIN");
INSERT INTO Users(fullname, username, password, role) VALUES("User", "user", "$2a$10$354RauqesQNQ0TCV2PjJte90SBxHwvFdX4MkdnTv8wnXr85ilRAFq", "USER");

UNLOCK TABLE;

COMMIT;