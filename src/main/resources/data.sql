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
create table `authorities`
        (
        `id` INT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(50) NOT NULL,
        `authority` VARCHAR(50) NOT NULL,
        PRIMARY KEY(`id`)
        );
create table `customer`
                (
                `id` INT NOT NULL AUTO_INCREMENT,
                `email` VARCHAR(50) NOT NULL,
                `username` VARCHAR(50) NOT NULL,
                `pwd` VARCHAR(70) NOT NULL,
                `role` VARCHAR(50) NOT NULL,
                PRIMARY KEY(`id`)
                );

INSERT IGNORE INTO `users` VALUES (NULL,'priyo','priyo123',1);
INSERT IGNORE INTO `authorities` VALUES (NULL,'priyo','write');
