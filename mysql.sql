

show databases ;
create database provider;


use provider;
show tables;

CREATE TABLE `payment` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `serial` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


show table status ;

select * from payment;