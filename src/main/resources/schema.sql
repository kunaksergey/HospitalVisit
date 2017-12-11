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
  ID       INT         NOT NULL AUTO_INCREMENT,
  USERNAME    VARCHAR(255) NOT NULL,
  EMAIL    VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  STATUS   TINYINT(1)  NOT NULL DEFAULT 0,
  UNIQUE (USERNAME),
  UNIQUE (EMAIL),
  PRIMARY KEY (ID)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS role (
  ROLE VARCHAR(100) NOT NULL,
  PRIMARY KEY (ROLE)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS user_roles (
  USER_ID INT         NOT NULL,
  ROLE VARCHAR(100) NOT NULL,
  PRIMARY KEY (USER_ID, ROLE),
  FOREIGN KEY (USER_ID)
  REFERENCES users (ID),
  FOREIGN KEY (ROLE)
  REFERENCES role (ROLE)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS chield (
  ID INT         NOT NULL AUTO_INCREMENT,
  USER_ID INT         NOT NULL,
  FULLNAME VARCHAR(255) NOT NULL,
  BIRTHDAY DATE NOT NULL,
  UNIQUE (USER_ID,FULLNAME,BIRTHDAY),
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES users (ID)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS schedule (
  ID INT         NOT NULL AUTO_INCREMENT,
  USER_ID INT         NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  START DATE NOT NULL,
  ROOM VARCHAR(20) NOT NULL,
  UNIQUE (USER_ID,NAME,START),
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES users (ID)
)ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS day (
  ID INT         NOT NULL AUTO_INCREMENT,
  SHEDULLE_ID INT         NOT NULL,
  IS_EVEN TINYINT(1) NOT NULL,
  WEEKDAY VARCHAR(10) NOT NULL,
  UNIQUE (SHEDULLE_ID,IS_EVEN,WEEKDAY),
  PRIMARY KEY (ID),
  FOREIGN KEY (SHEDULLE_ID) REFERENCES schedule (ID)
)ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS day_details (
  ID INT         NOT NULL AUTO_INCREMENT,
  DAY_ID INT         NOT NULL,
  TIME VARCHAR(10) NOT NULL,
  UNIQUE (DAY_ID,TIME),
  PRIMARY KEY (ID),
  FOREIGN KEY (DAY_ID) REFERENCES day (ID)
)ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS specialization (
  ID INT         NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255) NOT NULL,
  UNIQUE (NAME),
  PRIMARY KEY (ID)
)ENGINE = InnoDB
  CHARACTER SET = UTF8;