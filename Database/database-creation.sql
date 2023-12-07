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

CREATE TABLE officeHours (
    id INT PRIMARY KEY,
    fromTime INT,
    toTime INT,
    weekDay VARCHAR(20),
    location VARCHAR(255)
);

CREATE TABLE TeachingStaffProfileInfo (
    teacherId INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    biography VARCHAR(255),
    profilePictureURL VARCHAR(255),
    department VARCHAR(255)
);

CREATE TABLE StudentProfileInfo (
    studentId INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    biography TEXT,
    profilePictureURL VARCHAR(255),
    major VARCHAR(255),
    minor VARCHAR(255),
    level INT NOT NULL,
    noOfHours INT DEFAULT 0,
    GPA FLOAT
);

CREATE TABLE Warning (
    warningId INT PRIMARY KEY,
    studentId INT,
    reason VARCHAR(255),
    date DATE,
    FOREIGN KEY (studentId) REFERENCES StudentProfileInfo(studentId)
);

