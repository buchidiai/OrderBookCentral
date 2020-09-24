-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema orderbooktest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `orderbooktest` ;

-- -----------------------------------------------------
-- Schema orderbooktest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `orderbooktest` DEFAULT CHARACTER SET utf8 ;
USE `orderbooktest` ;

-- -----------------------------------------------------
-- Table `orderbooktest`.`stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderbooktest`.`stock` ;

CREATE TABLE IF NOT EXISTS `orderbooktest`.`stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `symbol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderbooktest`.`stock_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderbooktest`.`stock_order` ;

CREATE TABLE IF NOT EXISTS `orderbooktest`.`stock_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `side` ENUM('SELL', 'BUY') NOT NULL,
  `status` ENUM('COMPLETED', 'IN-PROGRESS', 'CANCELLED') NOT NULL,
  `stock_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_stock1_idx` (`stock_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_stock1`
    FOREIGN KEY (`stock_id`)
    REFERENCES `orderbooktest`.`stock` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderbooktest`.`trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderbooktest`.`trade` ;

CREATE TABLE IF NOT EXISTS `orderbooktest`.`trade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datetime` TIMESTAMP NOT NULL,
  `quantity` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `buy_order_id` INT NOT NULL,
  `sell_order_id` INT NOT NULL,
  `stock_id` INT NOT NULL,
  PRIMARY KEY (`id`, `buy_order_id`, `sell_order_id`),
  INDEX `fk_trade_stock_order1_idx` (`buy_order_id` ASC) VISIBLE,
  INDEX `fk_trade_stock_order2_idx` (`sell_order_id` ASC) VISIBLE,
  INDEX `fk_trade_stock1_idx` (`stock_id` ASC) VISIBLE,
  CONSTRAINT `fk_trade_stock_order1`
    FOREIGN KEY (`buy_order_id`)
    REFERENCES `orderbooktest`.`stock_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_stock_order2`
    FOREIGN KEY (`sell_order_id`)
    REFERENCES `orderbooktest`.`stock_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_stock1`
    FOREIGN KEY (`stock_id`)
    REFERENCES `orderbooktest`.`stock` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orderbooktest`.`order_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orderbooktest`.`order_transaction` ;

CREATE TABLE IF NOT EXISTS `orderbooktest`.`order_transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `transactiontype` ENUM('CREATED', 'FULFILLED', 'PARTIALLY-FULFILLED', 'CANCELLED') NOT NULL,
  `stock_order_id` INT NOT NULL,
  PRIMARY KEY (`id`, `stock_order_id`),
  INDEX `fk_order_transaction_stock_order1_idx` (`stock_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_transaction_stock_order1`
    FOREIGN KEY (`stock_order_id`)
    REFERENCES `orderbooktest`.`stock_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use orderbooktest;


INSERT INTO stock (name,symbol) VALUES ('APPLE' , 'AAPL');
INSERT INTO stock (name,symbol) VALUES (  'Amazon','AMAZN');
INSERT INTO stock (name,symbol) VALUES ( 'Netflix','NFLX');
INSERT INTO stock (name,symbol) VALUES ( 'Tesla','TSLA');
INSERT INTO stock (name,symbol) VALUES ( 'Walmart','WMT');
INSERT INTO stock (name,symbol) VALUES ( ' NVIDIA Corporation','NVDA');
INSERT INTO stock (name,symbol) VALUES ( 'JPMorgan Chase & Co.','JPM');
INSERT INTO stock (name,symbol) VALUES ( 'Google','GOOG');
INSERT INTO stock (name,symbol) VALUES ( 'Alibaba Group Holding Limited','BABA');


INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'COMPLETED', '1', '250','2020-09-16 12:03:21.140234','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'COMPLETED', '2', '450','2020-08-15 13:03:41.140245','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'COMPLETED', '2', '360','2020-08-17 16:05:11.140245','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'CANCELLED', '3', '320','2020-08-02 19:06:30.140250','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'CANCELLED', '2', '3550','2020-07-16 22:07:51.140212','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '4', '33450','2020-08-16 07:02:51.140222','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '5', '30','2020-03-16 20:06:41.140211','1.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '6', '50','2020-08-11 05:12:11.140220','98.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '7', '310','2020-08-16 16:14:21.140255','567.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '5', '35','2020-08-16 06:11:51.14034','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '7', '30','2020-08-16 15:05:51.140215','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '2', '380','2020-08-16 17:07:51.140212','3967.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('SELL', 'IN-PROGRESS', '2', '20','2020-08-16 18:08:51.140211','452.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '2', '398','2020-08-16 19:15:51.140210','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '1', '98','2020-08-16 15:17:51.140239','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '4', '120','2020-08-16 11:34:51.140238','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '8', '250','2020-08-16 21:23:51.140237','987.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '9', '300','2020-08-16 22:45:51.140236','2.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '6', '360','2020-08-16 12:02:51.140235','98.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '4', '90','2020-08-16 11:13:51.140234','12.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '3', '55','2020-08-16 15:05:51.140233','900.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '5', '40','2020-08-16 14:09:51.140232','17.00');
INSERT INTO stock_order (side,status,stock_id,quantity,datetime,price) VALUES ('BUY', 'IN-PROGRESS', '7', '69','2020-08-16 19:16:51.140231','15.00');
