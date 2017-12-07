use insurance
CREATE TABLE insuranceuser (
	id INTEGER UNSIGNED NOT NULL,
	username VARCHAR(100),
	userpwd VARCHAR(100),
	phonenumb varchar(30),
	email varchar(100),
	nickname varchar (100),
	PRIMARY KEY (id)
) ENGINE=InnoDB
create table insurancerole
(
	id INTEGER UNSIGNED NOT NULL,
	rolename VARCHAR(30),
	roledisplayname varchar(30),
	PRIMARY KEY (id)
)
