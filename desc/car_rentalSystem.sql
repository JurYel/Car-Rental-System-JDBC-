create database car_rentalSystem;
use car_rentalSystem;

CREATE TABLE branch(
	branch_id INT NOT NULL AUTO_INCREMENT,
	branch_name VARCHAR(70),
	branch_mngr_first varchar(60),
	branch_mngr_last varchar(50),
	contact_number varchar(15),
	PRIMARY KEY(branch_id));

CREATE TABLE manager(
	manager_id INT NOT NULL AUTO_INCREMENT,
	branch_id INT NOT NULL,
	mngr_lastName VARCHAR(50),
	mngr_firstName VARCHAR(60),
	contact_number VARCHAR(15),
	PRIMARY KEY(manager_id),
	PRIMARY KEY(branch_id),
	FOREIGN KEY(branch_id) REFERENCES branch(branch_id));

CREATE TABLE agent(
	agent_id INT NOT NULL AUTO_INCREMENT,
	branch_id INT NOT NULL,
	agent_lastName VARCHAR(50),
	agent_firstName VARCHAR(60),
	contact_number VARCHAR(15),
	PRIMARY KEY(agent_id),
	FOREIGN KEY(branch_id) REFERENCES branch(branch_id));

CREATE TABLE carowner(
	owner_id INT NOT NULL AUTO_INCREMENT,
	agent_id INT NOT NULL,
	branch_id INT NOT NULL,
	owner_firstName VARCHAR(60),
	owner_lastName VARCHAR(50),
	cars_owned INT DEFAULT 0,
	contact_number VARCHAR(15),
	PRIMARY KEY(owner_id),
	FOREIGN KEY(agent_id) REFERENCES agent(agent_id),
	FOREIGN KEY(branch_id) REFERENCES branch(branch_id));

CREATE TABLE car(
	car_id INT NOT NULL AUTO_INCREMENT,
	owner_id INT NOT NULL,
	car_name VARCHAR(50),
	brand VARCHAR(30),
	car_type VARCHAR(20),
	plate_number VARCHAR(20) NOT NULL,
	PRIMARY KEY(car_id),
	PRIMARY KEY(plate_number),
	FOREIGN KEY(owner_id) REFERENCES carowner(owner_id));

CREATE TABLE driver(
	driver_id INT NOT NULL AUTO_INCREMENT,
	agent_id INT NOT NULL,
	car_id INT NOT NULL,
	branch_id INT NOT NULL,
	driver_firstName VARCHAR(60),
	driver_lastName VARCHAR(50),
	contact_number VARCHAR(15),
	PRIMARY KEY(driver_id),
	FOREIGN KEY(agent_id) REFERENCES agent(agent_id),
	FOREIGN KEY(car_id) REFERENCES car(car_id),
	FOREIGN KEY(branch_id) REFERENCES branch(branch_id));

CREATE TABLE customer(
	customer_id INT NOT NULL AUTO_INCREMENT,
	last_name VARCHAR(50),
	first_name VARCHAR(60),
	contact_number VARCHAR(15),
	PRIMARY KEY(customer_id));

CREATE TABLE booking(
	booking_id INT NOT NULL AUTO_INCREMENT,
	customer_id INT NOT NULL,
	car_id INT NOT NULL,
	agent_id INT NOT NULL,
	date_booked TIMESTAMP NULL,
	date_rented TIMESTAMP NULL,
	PRIMARY KEY(booking_id),
	FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
	FOREIGN KEY(car_id) REFERENCES car(car_id),
	FOREIGN KEY(agent_id) REFERENCES agent(agent_id)
);

CREATE TABLE accounts(
	account_id VARCHAR(12) NOT NULL,
	first_name VARCHAR(60),
	last_name VARCHAR(50),
	password VARCHAR(100));

CREATE TABLE booking_history(
	history_id INT NOT NULL AUTO_INCREMENT,
	account_id VARCHAR(12) NOT NULL,
	action VARCHAR(50),
	customer VARCHAR(120),
	car VARCHAR(100),
	action_date TIMESTAMP NOT NULL,
	PRIMARY KEY(history_id));