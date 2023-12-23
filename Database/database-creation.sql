drop database if exists `central-student-hub`;
create database `central-student-hub`;
use `central-student-hub`;

CREATE TABLE `user_account`
(
    `userAccountId` bigint      not null auto_increment,
    `ssn`           varchar(20) not null,
    `userType`      enum ('Student', 'Staff', 'Admin') default null,
    `email`         varchar(50)                        default null,
    `passwordHash`  varchar(2000)                      default null,
    `passwordSalt`  varchar(2000)                      default null,
    `passwordDate`  date,
    primary key (`userAccountId`, `ssn`),
    unique key (`ssn`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE teaching_staff_profile
(
    teacherId         INT not null,
    firstName         VARCHAR(255) DEFAULT NULL,
    lastName          VARCHAR(255) DEFAULT NULL,
    biography         VARCHAR(255) DEFAULT NULL,
    profilePictureURL VARCHAR(255) DEFAULT NULL,
    department        VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (teacherId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE office_hour
(
    officeHourId INT not null AUTO_INCREMENT,
    teacherId    INT,
    fromTime     TIME,
    toTime       TIME,
    weekDay      VARCHAR(20),
    location     VARCHAR(255),
    PRIMARY KEY (officeHourId),
    FOREIGN KEY (teacherId) REFERENCES teaching_staff_profile (teacherId) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE teaching_staff_contact
(
    teacherId INT          not null,
    label     VARCHAR(255) not null,
    data      VARCHAR(255),
    PRIMARY KEY (teacherId, label),
    FOREIGN KEY (teacherId) REFERENCES teaching_staff_profile (teacherId) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_profile
(
    studentId         INT not null,
    firstName         VARCHAR(255) DEFAULT NULL,
    lastName          VARCHAR(255) DEFAULT NULL,
    biography         VARCHAR(255) DEFAULT NULL,
    profilePictureURL VARCHAR(255) DEFAULT NULL,
    major             VARCHAR(255) DEFAULT NULL,
    minor             VARCHAR(255) DEFAULT NULL,
    level             INT          DEFAULT NULL,
    noOfHours         INT          DEFAULT NULL,
    GPA               double       DEFAULT NULL,
    PRIMARY KEY (studentId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_contact
(
    studentId INT          not null,
    label     VARCHAR(255) not null,
    data      VARCHAR(255),
    PRIMARY KEY (studentId, label),
    FOREIGN KEY (studentId) REFERENCES student_profile (studentId) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE warning
(
    warningId INT not null AUTO_INCREMENT,
    studentId INT,
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
    `courseId`    int not null auto_increment,
    `code`        varchar(255),
    `name`        varchar(255),
    `description` varchar(255),
    `creditHours` int,
    primary key (`courseId`),
    unique key (`code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE course_prerequisite
(
    `courseId`       int not null,
    `prerequisiteId` int not null,
    primary key (`courseId`, `prerequisiteId`),
    foreign key (`courseId`) references course (`courseId`) on delete cascade on update cascade,
    foreign key (`prerequisiteId`) references course (`courseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE semester_course
(
    `semCourseId` bigint not null auto_increment,
    `courseId`    int,
    `semester`    enum ('FALL', 'SPRING', 'SUMMER'),
    `maxSeats`    int,
    primary key (`semCourseId`),
    foreign key (`courseId`) references course (`courseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_course_grade
(
    `courseId`     int not null,
    `studentId`    int not null,
    `studentGrade` double,
    primary key (`courseId`, `studentId`),
    foreign key (`courseId`) references course (`courseId`) on delete cascade on update cascade,
    foreign key (`studentId`) references student_profile (`studentId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE course_material_path
(
    `semCourseId`  bigint       not null,
    `materialPath` varchar(255) not null,
    primary key (`semCourseId`, `materialPath`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE assignment
(
    `assignmentId`   bigint not null auto_increment,
    `semCourseId`    bigint,
    `assignmentName` varchar(255),
    `description`    varchar(255),
    `dueDate`        date,
    primary key (`assignmentId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE assignment_material_path
(
    `assignmentId` bigint       not null,
    `materialPath` varchar(255) not null,
    primary key (`assignmentId`, `materialPath`),
    foreign key (`assignmentId`) references assignment (`assignmentId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE student_assignment_answer
(
    `studentId`    int    not null,
    `assignmentId` bigint not null,
    `answerPath`   varchar(255),
    `grade`        double,
    primary key (`studentId`, `assignmentId`),
    foreign key (`studentId`) references student_profile (`studentId`) on delete cascade on update cascade,
    foreign key (`assignmentId`) references assignment (`assignmentId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE announcement
(
    `announcementId`   bigint not null auto_increment,
    `semCourseId`      bigint,
    `announcementName` varchar(255),
    `description`      varchar(255),
    primary key (`announcementId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE feedback
(
    `feedbackId`  bigint not null auto_increment,
    `semCourseId` bigint,
    `content`     varchar(255),
    primary key (`feedbackId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE location
(
    `room`     int not null,
    `building` int not null,
    `capacity` int,
    primary key (`room`, `building`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE session
(
    `sessionId`   bigint not null auto_increment,
    `semCourseId` bigint,
    `teacherId`   int,
    `room`        int,
    `building`    int,
    `period`      int,
    `weekDay`     varchar(255),
    `sessionType` enum ('LECTURE', 'TUTORIAL', 'LAB'),
    primary key (`sessionId`),
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade,
    foreign key (`teacherId`) references teaching_staff_profile (`teacherId`) on delete cascade on update cascade,
    foreign key (`room`, `building`) references location (`room`, `building`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE registration
(
    `studentId`       int    not null,
    `semCourseId`     bigint not null,
    `paymentDeadline` date,
    `paymentFees`     double,
    primary key (`studentId`, `semCourseId`),
    foreign key (`studentId`) references student_profile (`studentId`) on delete cascade on update cascade,
    foreign key (`semCourseId`) references semester_course (`semCourseId`) on delete cascade on update cascade
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;