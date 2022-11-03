create table if not exists users
(id int auto_increment primary key,
name varchar(100),
lastName varchar(100),
role varchar(50));

create table if not exists patient
(id int auto_increment primary key,
name varchar(100),
lastName varchar (100),
role varchar(50),
nationalId varchar(50),
registrationDate Date);

create table if not exists dentist
(id int auto_increment primary key,
name varchar(100),
lastName varchar(100),
role varchar(50),
licenseNumber varchar(50));


create table if not exists appointment
(id int auto_increment primary key,
dentistID_FK int not null,
patientID_FK int not null,
foreign key (dentistID_FK) references dentist (id),
foreign key (patientID_FK) references patient (id),
dateA date,
timeA timestamp);