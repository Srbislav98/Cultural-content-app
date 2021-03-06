INSERT into uloga(id, ime) values (1, 'ROLE_ADMIN')
INSERT into uloga(id, ime) values (2, 'ROLE_USER')
INSERT into tip_kulturne_ponude(id, naziv) values (100,'obicna')
INSERT into tip_kulturne_ponude(id, naziv) values (5,'brisanje')
INSERT into lokacija(id, naziv_lokacije, geo_duzina, geo_sirina) values (100, 'Novi Sad', 19.833549, 45.267136)
-- password is 'user' (bcrypt encoded)
INSERT into registrovani_korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (1,'123@gmail.com','Aca','arak','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)
-- password is 'admin' (bcrypt encoded)
INSERT into admin(user_id,email,ime,korisnicko_ime,lozinka, prezime,enabled)values (2,'124@gmail.com','Reka','alak','$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK','Rekic',true)

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1)
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2)

INSERT into kulturna_ponuda(id,adresa,naziv,opis,admin_user_id,tip_kulturne_ponude_id,lokacija_id)values (100,'test','kulturnaponuda','ggez',2,100,100)
INSERT into kulturna_ponuda(id,adresa,naziv,opis,admin_user_id,tip_kulturne_ponude_id,lokacija_id)values (101,'test','nekulturnaponuda','og',2,100,100)
INSERT into kulturna_ponuda(id,adresa,naziv,opis,admin_user_id,tip_kulturne_ponude_id,lokacija_id)values (102,'test','ponuda','lol',2,100,100)
INSERT into kulturna_ponuda(id,adresa,naziv,opis,admin_user_id,tip_kulturne_ponude_id,lokacija_id)values (103,'test','glhf','opis',2,100,100)

INSERT into novost(id, datum, naziv, opis, kulturna_ponuda_id) values (100, '2020-12-12', 'novost', 'opis', 100)
INSERT into novost(id, datum, naziv, opis, kulturna_ponuda_id) values (105, '2020-12-21', 'novost5', 'opis5', 100)

INSERT into recenzija(id,ocena, komentar,kulturna_ponuda_id,registrovani_korisnik_user_id)values (100,4,'Veoma lepa lultura!',100,1)
INSERT into recenzija(id, ocena, komentar) values (101,3,'wdwdwdwd')

INSERT into fotografija(id,lokacija_fajl,kulturna_ponuda_id,recenzija_id)values (100,'files//neki_folder',100,100)
INSERT into fotografija(id, lokacija_fajl)values (101,'wdwdwdwdw')



INSERT into registrovani_korisnik_kulturna_ponuda(kulturna_ponuda_id,registrovani_korisnik_user_id) values (100,1)
INSERT into uloga(id, ime) values (0, 'NISTA')
-- password is 'user' (bcrypt encoded)
INSERT into korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (3,'3@gmail.com','Aca','acika','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)

-- password is 'user' (bcrypt encoded)
INSERT into registrovani_korisnik(reset_password_token,user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values ('abc',4,'5@gmail.com','Marko','mark','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)
INSERT into verification_token(id,token,user_id) values(2,'abb',4)

INSERT into registrovani_korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (210,'210@gmail.com','Marko','mark210','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',false)