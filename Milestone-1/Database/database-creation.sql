drop database if exists `central-student-hub`;
create database `central-student-hub`;
use `central-student-hub`;

create table `userAccountKeys` (
	`userAccountId` int not null auto_increment,
	`userName` varchar(50) not null,
	`userSSN` varchar(20) not null,
	primary key (`userAccountId`, `userName`, `userSSN`),
    unique key (`userName`),
    unique key (`userSSN`),
    unique key (`userName`, `userSSN`),
    index (`userSSN`)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------------------------------------

create table `userAccountInfo` (
	`ssn` varchar(20) not null,
    `userType` varchar(20) not null,
	`firstName` varchar(50) not null,
	`lastName` varchar(50) not null,
    `email` varchar(50) not null,
    `passwordHash` varchar(2000) not null,
    `passwordSalt` varchar(2000) not null,
    `passwordDate` date not null,
	foreign key (`ssn`) references userAccountKeys(`userSSN`) on update cascade
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
