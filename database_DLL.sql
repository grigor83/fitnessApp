CREATE DATABASE IF NOT EXISTS fitness_app;
USE fitness_app;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

DROP TABLE IF EXISTS atribut;
CREATE TABLE atribut
(
	atribut_id		int	 not null auto_increment,
	naziv_atributa	varchar(50)		not null,
    PRIMARY KEY(atribut_id)
);

DROP TABLE IF EXISTS kategorija;
CREATE TABLE kategorija 
(
	kategorija_id		int	 			not null auto_increment,
	naziv_kategorije	varchar(50)		not null,
    atribut_id			int	 			not null,
    PRIMARY KEY(kategorija_id),
    FOREIGN KEY(atribut_id) REFERENCES atribut(atribut_id)
);

DROP TABLE IF EXISTS korisnik;
CREATE TABLE korisnik 
(
	korisnik_id			int	 			not null auto_increment,
	ime					varchar(100)	not null,
    grad				varchar(50)		not null,
	avatar				varchar(50),
	mejl				varchar(50)		not null,
    broj_kartice		varchar(50)		not null,
    korisnicko_ime		varchar(50) 	not null,
    lozinka				varchar(50) 	not null,
    verifikovan			boolean,
    savjetnik			boolean,
    pretplata			int,
    PRIMARY KEY(korisnik_id),
    FOREIGN KEY(pretplata) REFERENCES kategorija(kategorija_id)
);

DROP TABLE IF EXISTS fitness_program;
CREATE TABLE fitness_program 
(
	program_id			int	 			not null auto_increment,
	naziv_programa		varchar(50)		not null,
    opis				varchar(50)		not null,
    naziv_slike			varchar(100)	not null,
    trajanje_treninga	varchar(20)		not null,
    nivo_tezine			int				not null,
    cijena				int				not null,
	lokacija			varchar(20)		not null,
    kategorija_id		int				,
    instruktor_ime		varchar(30)		,
    instruktor_kontakt  varchar(20)     ,
    autor_id			int,
    PRIMARY KEY(program_id),
    FOREIGN KEY(kategorija_id) REFERENCES kategorija(kategorija_id) on delete cascade,
	FOREIGN KEY(autor_id) REFERENCES korisnik(korisnik_id)
);

DROP TABLE IF EXISTS poruka;
CREATE TABLE poruka 
(
	poruka_id			int	 			not null auto_increment,
	posiljalac_id		int				not null,
	primalac_id			int				not null,
    datum_slanja		datetime		not null,
    tekst				varchar(500),
    procitana			boolean,
    PRIMARY KEY(poruka_id),
    FOREIGN KEY(posiljalac_id) REFERENCES korisnik(korisnik_id) ON DELETE cascade,
    FOREIGN KEY(primalac_id) REFERENCES korisnik(korisnik_id) ON DELETE cascade
);

DROP TABLE IF EXISTS ucestvuje;
CREATE TABLE ucestvuje 
(
	ucestvuje_id		int	 			not null auto_increment,
	program_id			int				not null,
	korisnik_id			int				not null,
    nacin_placanja		varchar(20)		not null,
    datum				datetime		not null,
    PRIMARY KEY(ucestvuje_id),
    FOREIGN KEY(program_id) REFERENCES fitness_program(program_id) ON DELETE cascade,
    FOREIGN KEY(korisnik_id) REFERENCES korisnik(korisnik_id) ON DELETE cascade
);

DROP TABLE IF EXISTS komentar;
CREATE TABLE komentar 
(
	komentar_id			int	 			not null auto_increment,
	program_id			int				not null,
	korisnik_id			int				not null,
    datum				datetime		not null,
    tekst				varchar(500),
    PRIMARY KEY(komentar_id),
    FOREIGN KEY(program_id) REFERENCES fitness_program(program_id) ON DELETE cascade,
    FOREIGN KEY(korisnik_id) REFERENCES korisnik(korisnik_id) ON DELETE cascade
);

DROP TABLE IF EXISTS dnevnik;
CREATE TABLE dnevnik 
(
	zapis_id			int	 			not null auto_increment,
    datum				date			not null,
	korisnik_id			int				not null,
	program_id			int				not null,
    rezultati			int				not null,
    PRIMARY KEY(zapis_id),
    FOREIGN KEY(korisnik_id) REFERENCES korisnik(korisnik_id) ON DELETE cascade,
	FOREIGN KEY(program_id) REFERENCES fitness_program(program_id) ON DELETE cascade
);

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator 
(
	administrator_id	int	 			not null auto_increment,
	ime					varchar(100)	not null,
	prezime				varchar(100)	not null,
    korisnicko_ime		varchar(100)    not null,
    lozinka				varchar(100)	not null,
    PRIMARY KEY(administrator_id)
);

DROP TABLE IF EXISTS log;
CREATE TABLE log 
(
	log_id				int	 			not null auto_increment,
	poruka 				varchar(70)    	not null,
    datum				timestamp		not null,
    logger 				varchar(70)    	not null,
    PRIMARY KEY(log_id)
);

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1 */;

INSERT INTO atribut(naziv_atributa) values ('trčanje'), ('aerobik'), ('benč'), ('mrtvo dizanje');
INSERT INTO kategorija(naziv_kategorije, atribut_id) VALUES ('kardio',1),('kardio',2),('snaga',3),('snaga',4);
insert into fitness_program(naziv_programa,opis, naziv_slike, trajanje_treninga,nivo_tezine,cijena,lokacija,kategorija_id,instruktor_ime, instruktor_kontakt)
 values ('trčanje u prirodi','trčanje po brdskim stazama','assets/1.jfif','45 minuta',3,20,'park',1,'Stevica Kurčubić','066/152-471'), 
 ('trening u teretani','dizanje tegova','assets/2.jfif','60 minuta',4,30,'teretana',3,'Petar Čelik','066/777-214');
insert into korisnik(ime,grad,avatar,mejl,broj_kartice,korisnicko_ime,lozinka,verifikovan,savjetnik) values 
('Marko Markovic','Banja Luka','mm_avatar.jpg','mmarkovic@gmail.com','1111','mm','mm',1,1),
('Jovan Jovanovic','Banja Luka','jj_avatar.jpg','igorgrubisa@hotmail.com','2222','jj','jj',0,0),
('Mile Milanovic','Banja Luka','mml_avatar.jpg','mmilanovic@gmail.com','3333','mim','mm',0,0);
insert into dnevnik(rezultati,datum,korisnik_id,program_id) values (54,'2024-3-2',2,1);
insert into komentar(tekst,korisnik_id,program_id, datum) values ('trening je bio naporan',2,1,'2024-04-02 22:34'),('trening je bio naporan',2,1,'2024-04-02'),('trening je bio naporan',2,1,'2024-04-02'),
('trening je bio naporan',2,1,'2024-04-02'),('trening je bio naporan',2,1,'2024-04-02');
insert into poruka(posiljalac_id,primalac_id,tekst,datum_slanja,procitana) values (1,2,'Ovo je prva poruka','2024-04-02',0),(2,1,'Ovo je prvi odgovor','2024-04-02',0);
insert into ucestvuje(korisnik_id,program_id,nacin_placanja,datum) values (2,1,'paypal','2024-02-04');
insert into administrator(ime, prezime, korisnicko_ime, lozinka) values ('admin','admin','admin','admin');
insert into log(poruka, datum, logger) values ('Ne postoji korisnik čiji je id = 500!','2024-05-31', 'class com.ip.fitnessApp.exceptions.RecordNotFoundException'),
('Korisničko ime mm je već zauzeto!','2024-05-31', 'class com.ip.fitnessApp.exceptions.UsernameAlreadyExistsException');


