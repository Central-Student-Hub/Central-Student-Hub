drop database if exists `central-student-hub`;
create database `central-student-hub`;
use `central-student-hub`;

create table `userAccount`
(
    `userAccountId` int         not null auto_increment,
    `ssn`           varchar(20) not null,
    `userType`      varchar(20)   default null,
    `email`         varchar(50)   default null,
    `passwordHash`  varchar(2000) default null,
    `passwordSalt`  varchar(2000) default null,
    `passwordDate`  date          default null,
    primary key (`userAccountId`, `ssn`),
    unique key (`ssn`)
);

CREATE TABLE officeHours
(
    id       INT PRIMARY KEY,
    fromTime INT,
    toTime   INT,
    weekDay  VARCHAR(20),
    location VARCHAR(255)
);

CREATE TABLE TeachingStaffProfileInfo
(
    teacherId         INT PRIMARY KEY,
    firstName         VARCHAR(255),
    lastName          VARCHAR(255),
    biography         VARCHAR(255),
    profilePictureURL VARCHAR(255),
    department        VARCHAR(255)
);

CREATE TABLE StudentProfileInfo
(
    studentId         INT PRIMARY KEY,
    firstName         VARCHAR(255),
    lastName          VARCHAR(255),
    biography         TEXT,
    profilePictureURL VARCHAR(255),
    major             VARCHAR(255),
    minor             VARCHAR(255),
    level             INT NOT NULL,
    noOfHours         INT DEFAULT 0,
    GPA               FLOAT
);

CREATE TABLE Warning
(
    warningId INT PRIMARY KEY,
    studentId INT,
    reason    VARCHAR(255),
    date      DATE,
    FOREIGN KEY (studentId) REFERENCES StudentProfileInfo (studentId)
);

CREATE TABLE course
(
    `courseId`    int          not null auto_increment,
    `code`        varchar(255) not null,
    `creditHours` int          default null,
    `name`        varchar(255) default null,
    `description` varchar(255) default null,
    `semester`    varchar(255) default null,
    primary key (`courseId`),
    unique key (`code`)
);

CREATE TABLE course_prerequisites
(
    `courseId`     int not null,
    `prerequisite` varchar(255) not null,
    primary key (`courseId`, `prerequisite`),
    key `fk_courseId1` (`courseId`),
    constraint `fk_courseId1` foreign key (`courseId`) references course (`courseId`) on delete restrict on update cascade
);

CREATE TABLE semesterCourse
(
    `semCourseId` bigint not null auto_increment,
    `courseId`    int    not null,
    `maxSeats`    int default null,
    primary key (`semCourseId`),
    key `fk_courseId2` (`courseId`),
    constraint `fk_courseId2` foreign key (`courseId`) references course (`courseId`) on delete restrict
        on update cascade
);

CREATE TABLE semCourse_grades
(
    `semCourseId`  bigint not null,
    `studentId`    int    not null,
    `studentGrade` float(3, 3),
    primary key (`semCourseId`, `studentId`),
    key `fk_semCourseId1` (`semCourseId`),
    key `fk_studentId1` (`studentId`),
    constraint `fk_semCourseId1` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade,
    constraint `fk_studentId1` foreign key (`studentId`) references StudentProfileInfo (`studentId`) on delete restrict
        on update cascade
);

CREATE TABLE semCourse_materialPaths
(
    `semCourseId`  bigint not null,
    `materialPath` varchar(255) not null,
    primary key (`semCourseId`, `materialPath`),
    key `fk_semCourseId2` (`semCourseId`),
    constraint `fk_semCourseId2` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade
);

CREATE TABLE semCourse_members
(
    `semCourseId` bigint not null,
    `member`      int not null,
    primary key (`semCourseId`, `member`),
    key `fk_semCourseId3` (`semCourseId`),
    constraint `fk_semCourseId3` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade
);

CREATE TABLE assignment
(
    `assignmentId`   bigint not null auto_increment,
    `assignmentName` varchar(255) default null,
    `description`    varchar(255) default null,
    `dueDate`        date         default null,
    primary key (`assignmentId`)
);

CREATE TABLE assignment_materialPaths
(
    `assignmentId` bigint not null,
    `materialPath` varchar(255) not null,
    primary key (`assignmentId`, `materialPath`),
    key `fk_assignmentId1` (`assignmentId`),
    constraint `fk_assignmentId1` foreign key (`assignmentId`) references assignment (`assignmentId`) on delete restrict
        on update cascade
);

CREATE TABLE assignmentAns
(
    `assignmentAnsId` bigint not null auto_increment,
    `studentId`       int    not null,
    `grade`           float(3, 3) default null,
    primary key (`assignmentAnsId`),
    key `fk_studentId2` (`studentId`),
    constraint `fk_studentId2` foreign key (`studentId`) references StudentProfileInfo (`studentId`) on delete restrict
        on update cascade
);

CREATE TABLE assignmentAns_ansPaths
(
    `assignmentAnsId` bigint not null,
    `answerPath`      varchar(255) not null,
    primary key (`assignmentAnsId`, `answerPath`),
    key `fk_assignmentAnsId1` (`assignmentAnsId`),
    constraint `fk_assignmentAnsId1` foreign key (`assignmentAnsId`) references assignmentAns (`assignmentAnsId`)
        on delete restrict on update cascade
);

CREATE TABLE announcement
(
    `announcementId`   bigint not null auto_increment,
    `announcementName` varchar(255) default null,
    `description`      varchar(255) default null,
    primary key (`announcementId`)
);

CREATE TABLE feedback
(
    `feedbackId` bigint not null auto_increment,
    `content`    varchar(255) default null,
    primary key (`feedbackId`)
);

CREATE TABLE session
(
    `sessionId`   bigint not null auto_increment,
    `teacher`     varchar(255)                        default null,
    `period`      int                                 default null,
    `weekDay`     varchar(255)                        default null,
    `sessionType` enum ('lecture', 'tutorial', 'lab') default null,
    primary key (`sessionId`)
);

CREATE TABLE location
(
    `room`     int not null,
    `building` int not null,
    capacity   int default null,
    primary key (`room`, `building`)
);

# HAS relationship relations

CREATE TABLE semCourse_announcements
(
    `semCourseId`    bigint not null,
    `announcementId` bigint not null,
    primary key (`semCourseId`, `announcementId`),
    key `fk_semCourseId4` (`semCourseId`),
    key `fk_announcementId1` (`announcementId`),
    constraint `fk_semCourseId4` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade,
    constraint `fk_announcementId1` foreign key (`announcementId`) references announcement (`announcementId`)
        on delete restrict on update cascade
);

CREATE TABLE semCourse_feedbacks
(
    `semCourseId` bigint not null,
    `feedbackId`  bigint not null,
    primary key (`semCourseId`, `feedbackId`),
    key `fk_semCourseId5` (`semCourseId`),
    key `fk_feedbackId1` (`feedbackId`),
    constraint `fk_semCourseId5` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade,
    constraint `fk_feedbackId1` foreign key (`feedbackId`) references feedback (`feedbackId`) on delete restrict
        on update cascade
);

CREATE TABLE semCourse_assignments
(
    `semCourseId`  bigint not null,
    `assignmentId` bigint not null,
    primary key (`semCourseId`, `assignmentId`),
    key `fk_semCourseId6` (`semCourseId`),
    key `fk_assignmentId2` (`assignmentId`),
    constraint `fk_semCourseId6` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade,
    constraint `fk_assignmentId2` foreign key (`assignmentId`) references assignment (`assignmentId`) on delete restrict
        on update cascade
);

CREATE TABLE semCourse_sessions
(
    `semCourseId` bigint not null,
    `sessionId`   bigint not null,
    primary key (`semCourseId`, `sessionId`),
    key `fk_semCourseId7` (`semCourseId`),
    key `fk_sessionId1` (`sessionId`),
    constraint `fk_semCourseId7` foreign key (`semCourseId`) references semesterCourse (`semCourseId`) on delete restrict
        on update cascade,
    constraint `fk_sessionId1` foreign key (`sessionId`) references session (`sessionId`) on delete restrict
        on update cascade
);

CREATE TABLE assignmentAns_assignment
(
    `assignmentAnsId` bigint not null,
    `assignmentId`    bigint not null,
    primary key (`assignmentAnsId`, `assignmentId`),
    key `fk_assignmentAnsId2` (`assignmentAnsId`),
    key `fk_assignmentId3` (`assignmentId`),
    constraint `fk_assignmentAnsId2` foreign key (`assignmentAnsId`) references assignmentAns (`assignmentAnsId`)
        on delete restrict on update cascade,
    constraint `fk_assignmentId3` foreign key (`assignmentId`) references assignment (`assignmentId`) on delete restrict
        on update cascade
);

# IN relationship relation

CREATE TABLE session_location
(
    `sessionId` bigint not null,
    `room`      int not null,
    `building`  int not null,
    primary key (`sessionId`, `room`, `building`),
    key `fk_sessionId2` (`sessionId`),
    key `fk_loc1` (`room`, `building`),
    constraint `fk_sessionId2` foreign key (`sessionId`) references session (`sessionId`) on delete restrict
        on update cascade,
    constraint `fk_loc1` foreign key (`room`, `building`) references location (`room`, `building`) on delete restrict
        on update cascade
);
