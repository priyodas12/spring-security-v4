

use spring_security;
drop table if exists users;
drop table if exists authorities;
drop table if exists customer;

create table `users`
        (
        `id` INT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(50) NOT NULL,
        `password` VARCHAR(500) NOT NULL,
        `enabled` INT NOT NULL,
        PRIMARY KEY(`id`)
        );

create table `customer`
                (
                `customer_id` INT NOT NULL AUTO_INCREMENT,
                `username` VARCHAR(50) NOT NULL,
                `email` VARCHAR(50) NOT NULL,
                `pwd` VARCHAR(70) NOT NULL,
                `mobile_number` VARCHAR(12) NOT NULL,
                `create_date` DATE,
                PRIMARY KEY(`customer_id`)
                );

INSERT IGNORE INTO `customer` VALUES (1,'priyo','priyo@test.com','$2a$12$OvTNDXqQFOBlIesCMmIiTulGkh.6HqUg6yjZeSGFP2Uy2VvgXA8Zm','2314524627','2022-11-10');
INSERT IGNORE INTO `customer` VALUES (2,'test1','test1@test.com','$2a$12$/gy.iPGs.L0KzUmsgIjhx.PV88BP/lr3bCqZ.wmPKxxu5juuxM/O.','2314524600','2022-09-10');
INSERT IGNORE INTO `customer` VALUES (3,'test2','test2@test.com','$2a$12$SsOhbmNZc9.ZjWjVvflz7O8oPc1G7fpz7yoFn5ozi9OWmiHIAnUdi','2314524601','2021-09-10');


create table `authorities`
        (
        `id` INT NOT NULL AUTO_INCREMENT,
        `customer_id` INT NOT NULL,
        `name` VARCHAR(250) NOT NULL,
        PRIMARY KEY(`id`),
        KEY `customer_id`(`customer_id`),
        CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
        );

INSERT INTO `authorities` (`customer_id`,`name`) VALUES (1,'VIEW_ACCOUNT');
INSERT INTO `authorities` (`customer_id`,`name`) VALUES (1,'VIEW_BALANCE');
INSERT INTO `authorities` (`customer_id`,`name`) VALUES (2,'VIEW_ACCOUNT');
INSERT INTO `authorities` (`customer_id`,`name`) VALUES (3,'VIEW_CARD');
INSERT INTO `authorities` (`customer_id`,`name`) VALUES (3,'VIEW_LOAN');

