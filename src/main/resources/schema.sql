DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Login;
DROP TABLE IF EXISTS Board;

CREATE TABLE User
(
    user_id VARCHAR (300) PRIMARY KEY,
    password varchar (200) NOT NULL,
    user_name varchar (100) NOT NULL,
    email varchar (200) NOT NULL,
    created_date datetime  NOT  NULL,
    modify_date datetime  NOT NULL
);

CREATE TABLE Login
(
    uid long auto_increment  PRIMARY KEY,
    user_id varchar (100) NOT NULL,
    access_token varchar (300) ,
    access_token_expired_date datetime ,
    last_login_date datetime  ,
    last_login_fail_date datetime

);

CREATE TABLE Board
(
    board_id long auto_increment  PRIMARY KEY,
    user_id varchar (100) NOT NULL,
    title varchar (100) NOT NULL,
    context varchar (500)NOT NULL,
    created_date datetime  NOT NULL,
    modify_date datetime  NOT NULL

);


