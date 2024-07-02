CREATE DATABASE IF NOT EXISTS fitness_app;
USE fitness_app;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

DROP TABLE IF EXISTS attribute;
CREATE TABLE attribute
(
	id				int	 not null auto_increment,
	attribute_name	varchar(50)		not null,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS category;
CREATE TABLE category
(
	id				int	 			not null auto_increment,
	category_name	varchar(50)		not null,
    attribute_id	int	 			not null,
    PRIMARY KEY(id),
    FOREIGN KEY(attribute_id) REFERENCES attribute(id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user 
(
	id					int	 			not null auto_increment,
	first_name			varchar(100)	not null,
    city				varchar(50)		not null,
	avatar				varchar(50),
	mail				varchar(50)		not null,
    card_number			varchar(50)		not null,
    username			varchar(50) 	not null,
    user_password		varchar(50) 	not null,
    verified			boolean,
    councelor			boolean,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS fitness_program;
CREATE TABLE fitness_program 
(
	id					int	 			not null auto_increment,
	program_name		varchar(50)		not null,
    program_description		varchar(50)		not null,
    image_path			varchar(100)	not null,
    duration			varchar(20)		not null,
    intensity			int				not null,
    price				int				not null,
	location			varchar(20)		not null,
    category_id			int	,
    instructor_name		varchar(30),
    instructor_contact  varchar(20),
    author_id			int,
    created_at 			TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES category(id) on delete cascade,
	FOREIGN KEY(author_id) REFERENCES user(id) on delete cascade
);

DROP TABLE IF EXISTS message;
CREATE TABLE message 
(
	id					int	 			not null auto_increment,
	sender_id			int				not null,
	reciever_id			int				not null,
    sent_date			datetime		not null,
    content				varchar(500),
    seen			boolean,
    PRIMARY KEY(id),
    FOREIGN KEY(sender_id) REFERENCES user(id) ON DELETE cascade,
    FOREIGN KEY(reciever_id) REFERENCES user(id) ON DELETE cascade
);

DROP TABLE IF EXISTS participation;
CREATE TABLE participation 
(
	id					int	 			not null auto_increment,
	program_id			int				not null,
	participant_id		int				not null,
    payment_method		varchar(20)		not null,
    payment_date		datetime		not null,
    PRIMARY KEY(id),
    FOREIGN KEY(program_id) REFERENCES fitness_program(id) ON DELETE cascade,
    FOREIGN KEY(participant_id) REFERENCES user(id) ON DELETE cascade
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment 
(
	id					int	 			not null auto_increment,
	program_id			int				not null,
	user_id				int				not null,
    comment_date		datetime		not null,
    content				varchar(500),
    PRIMARY KEY(id),
    FOREIGN KEY(program_id) REFERENCES fitness_program(id) ON DELETE cascade,
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE cascade
);

DROP TABLE IF EXISTS diary;
CREATE TABLE diary 
(
	id					int	 			not null auto_increment,
    record_date			date			not null,
	user_id				int				not null,
	program_id			int				not null,
    results				int				not null,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE cascade,
	FOREIGN KEY(program_id) REFERENCES fitness_program(id) ON DELETE cascade
);

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator 
(
	id					int	 			not null auto_increment,
	first_name			varchar(100)	not null,
	last_name			varchar(100)	not null,
    username			varchar(100)    not null,
    admin_password		varchar(100)	not null,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS log;
CREATE TABLE log
(
	id					int	 			not null auto_increment,
	message				varchar(70)    	not null,
    log_date			timestamp		not null,
    logger 				varchar(70)    	not null,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS subscription;
CREATE TABLE subscription 
(
	id					int				not null auto_increment,
	user_id				int				not null,
	category_id			int				not null,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE cascade,
	FOREIGN KEY(category_id) REFERENCES category(id) ON DELETE cascade
);


/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1 */;

INSERT INTO attribute(attribute_name) values ('trčanje'), ('aerobik'), ('benč'), ('mrtvo dizanje'), 
('propadanje'), ('zgibovi'),('traka za trčanje');
INSERT INTO category(category_name, attribute_id) VALUES ('kardio',1),('kardio',2),('snaga',3),('snaga',4),
('snaga',5),('snaga',6), ('kardio',7);
insert into fitness_program(program_name, program_description, image_path, duration, intensity, price, location, category_id,
instructor_name, instructor_contact)
 values ('trčanje u prirodi','trčanje po brdskim stazama','assets/1.jfif','45 minuta',3,20,'park',1,'Stevica Kurčubić','066/152-471'), 
 ('vježbe za grudi','dizanje tegova','assets/2.jfif','60 minuta',4,30,'teretana',3,'Petar Čelik','066/777-214'),
 ('vježbe za donji dio leđa','dizanje tegova','assets/2.jfif','60 minuta',4,30,'teretana',4,'Petar Čelik','066/777-214'),
 ('vježbe za triceps','razboj','assets/2.jfif','60 minuta',4,30,'teretana',5,'Arnold Švarceneger','066/777-214'),
 ('vježbe za leđa','vratilo','assets/2.jfif','60 minuta',4,30,'teretana',6,'Arnold Švarceneger','066/777-214'),
 ('aerobni trening u teretani','trčanje na traci','assets/2.jfif','60 minuta',4,30,'teretana',7,'Arnold Švarceneger','066/777-214');
insert into user(first_name, city, avatar, mail, card_number, username, user_password, verified, councelor) values 
('Marko Marković','Banja Luka','mm_avatar.jpg','mmarkovic@gmail.com','1111','mm','mm',1,1),
('Jovan Jovanović','Banja Luka','jj_avatar.jpg','igorgrubisa@hotmail.com','2222','jj','jj',1,0),
('Mile Milanović','Banja Luka','mml_avatar.jpg','mmilanovic@gmail.com','3333','mim','mm',1,0);
insert into diary(results, record_date, user_id, program_id) values (54,'2024-3-2',2,1);
insert into comment (content, user_id, program_id, comment_date) values 
('trening je bio naporan',2,1,'2024-04-02 22:34'),('trening je bio naporan',2,1,'2024-04-02'),
('trening je bio naporan',2,1,'2024-04-02'),
('trening je bio naporan',2,1,'2024-04-02'),('trening je bio naporan',2,1,'2024-04-02');
insert into message(sender_id, reciever_id, content, sent_date, seen) values 
(1,2,'Ovo je prva poruka','2024-04-02',0),(2,1,'Ovo je prvi odgovor','2024-04-02',0);
insert into participation(participant_id, program_id, payment_method, payment_date) values (2,1,'paypal','2024-02-04');
insert into administrator(first_name, last_name, username, admin_password) values ('admin','admin','admin','admin');
insert into log(message, log_date, logger) values 
('Ne postoji korisnik čiji je id = 500!','2024-05-31', 'class com.ip.fitnessApp.exceptions.RecordNotFoundException'),
('Korisničko ime mm je već zauzeto!','2024-05-31', 'class com.ip.fitnessApp.exceptions.UsernameAlreadyExistsException'),
('Ne postoji fitnes program čiji je id = 500!','2024-06-01', 'class com.ip.fitnessApp.exceptions.RecordNotFoundException');
insert into subscription(user_id, category_id) values (2, 3), (2,5), (2,7);
