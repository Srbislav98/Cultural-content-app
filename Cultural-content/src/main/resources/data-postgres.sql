INSERT into uloga(id, ime) values (1, 'ROLE_ADMIN')
INSERT into uloga(id, ime) values (2, 'ROLE_USER')
INSERT into tip_kulturne_ponude(id, naziv) values (100,'obicna')
INSERT into lokacija(id, naziv_lokacije, geo_duzina, geo_sirina) values (100, 'Novi Sad', 123, 321)
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
INSERT into recenzija(id, ocena, komentar, kulturna_ponuda_id, registrovani_korisnik_user_id)values (100,4,'Nesto',100,1)
INSERT into fotografija(id,lokacija_fajl,recenzija_id,kulturna_ponuda_id)values (100,'files//neki_folder',null,100)
INSERT into novost(id, datum, naziv, opis, kulturna_ponuda_id) values (100, '2020-12-12', 'novost', 'opis', 100)

INSERT into registrovani_korisnik_kulturna_ponuda(kulturna_ponuda_id,registrovani_korisnik_user_id) values (100,1)
INSERT into registrovani_korisnik_kulturna_ponuda(kulturna_ponuda_id,registrovani_korisnik_user_id) values (101,1)
INSERT into registrovani_korisnik_kulturna_ponuda(kulturna_ponuda_id,registrovani_korisnik_user_id) values (102,1)
--INSERT into registrovani_korisnik_kulturna_ponuda(kulturna_ponuda_id,registrovani_korisnik_user_id) values (103,1)

INSERT into registrovani_korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (3,'323@gmail.com','Aca','tarak','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)
--