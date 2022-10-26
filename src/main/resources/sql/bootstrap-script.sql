create database spring_security;

use spring_security;

create table `users`
        (
        `id` INT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(50) NOT NULL,
        `password` VARCHAR(50) NOT NULL,
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

INSERT IGNORE INTO `users` VALUES (NULL,'priyo','priyo123',1);
INSERT IGNORE INTO `authorities` VALUES (NULL,'priyo','write');
