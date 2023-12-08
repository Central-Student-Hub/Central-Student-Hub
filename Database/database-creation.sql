drop database if exists `central-student-hub`;
create database `central-student-hub`;
use `central-student-hub`;

CREATE TABLE `user_account`
(
    `userAccountId` bigint      not null auto_increment,
    `ssn`           varchar(20) not null,
    `userType`      enum ('Student', 'Staff', 'Admin'),
    `email`         varchar(50),
    `passwordHash`  varchar(2000),
    `passwordSalt`  varchar(2000),
    `passwordDate`  date,
    primary key (`userAccountId`, `ssn`),
    unique key (`ssn`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE teaching_staff_profile
(
    teacherId         INT NOT NULL AUTO_INCREMENT,
    firstName         VARCHAR(255),
    lastName          VARCHAR(255),
    biography         VARCHAR(255),
    profilePictureURL VARCHAR(255),
    department        VARCHAR(255),
    PRIMARY KEY (teacherId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE office_hour
(
    officeHourId INT NOT NULL AUTO_INCREMENT,
    teacherId    INT NOT NULL,
    fromTime     TIME,
    toTime       TIME,
    weekDay      VARCHAR(20),
    location     VARCHAR(255),
    PRIMARY KEY (officeHourId),
    FOREIGN KEY (teacherId) REFERENCES teaching_staff_profile (teacherId) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE teaching_staff_contact
(
    teacherId INT          NOT NULL,
    label     VARCHAR(255) NOT NULL,
    data      VARCHAR(255),
    PRIMARY KEY (teacherId, label),
    FOREIGN KEY (teacherId) REFERENCES teaching_staff_profile (teacherId) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_profile
(
    studentId         INT NOT NULL AUTO_INCREMENT,
    firstName         VARCHAR(255),
    lastName          VARCHAR(255),
    biography         VARCHAR(255),
    profilePictureURL VARCHAR(255),
    major             VARCHAR(255),
    minor             VARCHAR(255),
    level             INT NOT NULL,
    noOfHours         INT,
    GPA               FLOAT(3, 3),
    PRIMARY KEY (studentId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_contact
(
    studentId INT          NOT NULL,
    label     VARCHAR(255) NOT NULL,
    data      VARCHAR(255),
    PRIMARY KEY (studentId, label),
    FOREIGN KEY (studentId) REFERENCES student_profile (studentId) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE warning
(
    warningId INT NOT NULL AUTO_INCREMENT,
    studentId INT NOT NULL,
    reason    VARCHAR(255),
    date      DATE,
    PRIMARY KEY (warningId),
    FOREIGN KEY (studentId) REFERENCES student_profile (studentId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE course
(
    `courseId`    int          not null auto_increment,
    `code`        varchar(255) not null,
    `creditHours` int,
    `name`        varchar(255),
    `description` varchar(255),
    `semester`    varchar(255),
    primary key (`courseId`),
    unique key (`code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE course_prerequisite
(
    `courseId`     int          not null,
    `prerequisite` varchar(255) not null,
    primary key (`courseId`, `prerequisite`),
    foreign key (`courseId`) references course (`courseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE semester_course
(
    `semCourseId` bigint not null auto_increment,
    `courseId`    int    not null,
    `maxSeats`    int,
    primary key (`semCourseId`),
    foreign key (`courseId`) references course (`courseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_semester_course_grade
(
    `semCourseId`  bigint not null,
    `studentId`    int    not null,
    `studentGrade` float(3, 3),
    primary key (`semCourseId`, `studentId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade,
    foreign key (`studentId`) references student_profile (`studentId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE semester_course_material_path
(
    `semCourseId`  bigint       not null,
    `materialPath` varchar(255) not null,
    primary key (`semCourseId`, `materialPath`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE semester_course_member
(
    `semCourseId` bigint not null,
    `memberId`    int    not null,
    primary key (`semCourseId`, `memberId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE assignment
(
    `assignmentId`   bigint not null auto_increment,
    `assignmentName` varchar(255),
    `description`    varchar(255),
    `dueDate`        date,
    `semCourseId`    bigint not null,
    primary key (`assignmentId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE assignment_material_path
(
    `assignmentId` bigint       not null,
    `materialPath` varchar(255) not null,
    primary key (`assignmentId`, `materialPath`),
    foreign key (`assignmentId`) references assignment (`assignmentId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE assignment_answer
(
    `assignmentAnsId` bigint not null auto_increment,
    `studentId`       int    not null,
    `grade`           float(3, 3),
    `assignmentId`    bigint not null,
    primary key (`assignmentAnsId`),
    foreign key (`studentId`) references student_profile (`studentId`) on delete restrict on update cascade,
    foreign key (`assignmentId`) references assignment (`assignmentId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE answer_path
(
    `assignmentAnsId` bigint       not null,
    `answerPath`      varchar(255) not null,
    primary key (`assignmentAnsId`, `answerPath`),
    foreign key (`assignmentAnsId`) references assignment_answer (`assignmentAnsId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE announcement
(
    `announcementId`   bigint not null auto_increment,
    `announcementName` varchar(255),
    `description`      varchar(255),
    `semCourseId`      bigint not null,
    primary key (`announcementId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE feedback
(
    `feedbackId`  bigint not null auto_increment,
    `content`     varchar(255),
    `semCourseId` bigint not null,
    primary key (`feedbackId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE location
(
    `room`     int not null,
    `building` int not null,
    capacity   int,
    primary key (`room`, `building`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE session
(
    `sessionId`   bigint not null auto_increment,
    `teacher`     varchar(255),
    `period`      int,
    `weekDay`     varchar(255),
    `sessionType` enum ('Lecture', 'Tutorial', 'Lab'),
    `semCourseId` bigint not null,
    `room`        int    not null,
    `building`    int    not null,
    primary key (`sessionId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade,
    foreign key (`room`, `building`) references location (`room`, `building`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE registration
(
    `studentId`       int    not null,
    `semCourseId`     bigint not null,
    `paymentDeadline` date,
    `paymentFees`     float(10, 7),
    primary key (`studentId`, `semCourseId`),
    foreign key (`studentId`) references student_profile (`studentId`) on delete restrict on update cascade,
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete restrict on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;