drop database if exists `central-student-hub`;
create database `central-student-hub`;
use `central-student-hub`;

create table `userAccount` (
	`userAccountId` int not null auto_increment,
    `ssn` varchar(20) not null,
    `userType` varchar(20) default null,
    `email` varchar(50) default null,
    `passwordHash` varchar(2000) default null,
    `passwordSalt` varchar(2000) default null,
    `passwordDate` date default null,
	primary key (`userAccountId`, `ssn`),
    unique key (`ssn`)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
