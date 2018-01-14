CREATE DATABASE IF NOT EXISTS hospital
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE hospital;

CREATE TABLE IF NOT EXISTS district (
  ID   INT          NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(150) NOT NULL,
  UNIQUE (NAME),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS hospital (
  ID          INT          NOT NULL AUTO_INCREMENT,
  NAME        VARCHAR(150) NOT NULL,
  ADDRESS     VARCHAR(150) NOT NULL,
  DISTRICT_ID INT          NOT NULL,
  CONSTRAINT FK_DISTRICT_1 FOREIGN KEY (DISTRICT_ID)
  REFERENCES district (id),
  UNIQUE (NAME),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS users (
  ID       INT          NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(255) NOT NULL,
  EMAIL    VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  STATUS   TINYINT(1)   NOT NULL DEFAULT 0,
  UNIQUE (USERNAME),
  UNIQUE (EMAIL),
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS role (
  ROLE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ROLE)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS user_roles (
  USER_ID INT          NOT NULL,
  ROLE    VARCHAR(100) NOT NULL,
  PRIMARY KEY (USER_ID, ROLE),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID),
  FOREIGN KEY (ROLE)
  REFERENCES role (ROLE)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;


CREATE TABLE IF NOT EXISTS specialization (
  ID   INT          NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255) NOT NULL,
  UNIQUE (NAME),
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS specialization_details (
  ID                INT NOT NULL AUTO_INCREMENT,
  DOCTOR_ID         INT NOT NULL,
  SPECIALIZATION_ID INT NOT NULL,
  UNIQUE (DOCTOR_ID, SPECIALIZATION_ID),
  PRIMARY KEY (ID),
  FOREIGN KEY (DOCTOR_ID)
  REFERENCES doctor (ID),
  FOREIGN KEY (SPECIALIZATION_ID)
  REFERENCES specialization (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;


CREATE TABLE IF NOT EXISTS doctor (
  ID          INT NOT NULL AUTO_INCREMENT,
  USER_ID     INT NOT NULL,
  HOSPITAL_ID INT NOT NULL,
  UNIQUE (USER_ID, HOSPITAL_ID),
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID),
  FOREIGN KEY (HOSPITAL_ID)
  REFERENCES hospital (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS patient (
  ID      INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS chield (
  ID         INT          NOT NULL AUTO_INCREMENT,
  PATIENT_ID INT          NOT NULL,
  FULLNAME   VARCHAR(255) NOT NULL,
  BIRTHDAY   DATE         NOT NULL,
  UNIQUE (PATIENT_ID, FULLNAME, BIRTHDAY),
  PRIMARY KEY (ID),
  FOREIGN KEY (PATIENT_ID) REFERENCES patient (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS supervisor (
  ID          INT NOT NULL AUTO_INCREMENT,
  USER_ID     INT NOT NULL,
  HOSPITAL_ID INT NOT NULL,
  UNIQUE (USER_ID, HOSPITAL_ID),
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID),
  FOREIGN KEY (HOSPITAL_ID)
  REFERENCES hospital (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS admin (
  ID      INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS schedule (
  ID        INT         NOT NULL AUTO_INCREMENT,
  DOCTOR_ID INT         NOT NULL,
  START     DATE        NOT NULL,
  ROOM      VARCHAR(20) NOT NULL,
  NOTICE    VARCHAR(255),
  UNIQUE (DOCTOR_ID, START),
  PRIMARY KEY (ID),
  FOREIGN KEY (DOCTOR_ID) REFERENCES doctor (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS schedule_day (
  ID          INT          NOT NULL AUTO_INCREMENT,
  SCHEDULE_ID INT          NOT NULL,
  EVEN_OR_ODD VARCHAR(4)   NOT NULL,
  WEEKDAY     VARCHAR(9)   NOT NULL,
  TIME        VARCHAR(5)   NOT NULL,
  NOTICE      VARCHAR(500),
  UNIQUE (SCHEDULE_ID, EVEN_OR_ODD, WEEKDAY, TIME),
  PRIMARY KEY (ID),
  FOREIGN KEY (SCHEDULE_ID) REFERENCES schedule (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS schedule_time (
  ID              INT        NOT NULL AUTO_INCREMENT,
  SCHEDULE_DAY_ID INT        NOT NULL,
  TIME            VARCHAR(5) NOT NULL,
  UNIQUE (SCHEDULE_DAY_ID, TIME),
  PRIMARY KEY (ID),
  FOREIGN KEY (SCHEDULE_DAY_ID) REFERENCES schedule_day (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;


CREATE TABLE IF NOT EXISTS ticket (
  ID         INT         NOT NULL AUTO_INCREMENT,
  DOCTOR_ID  INT         NOT NULL,
  PATIENT_ID INT         NOT NULL,
  DATE_      DATE        NOT NULL,
  TIME       VARCHAR(20) NOT NULL,
  STATUS     VARCHAR(20) NOT NULL,
  UNIQUE (DOCTOR_ID, DATE_, TIME),
  PRIMARY KEY (ID),
  FOREIGN KEY (DOCTOR_ID) REFERENCES doctor (ID),
  FOREIGN KEY (PATIENT_ID) REFERENCES patient (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;