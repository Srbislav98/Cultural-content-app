INSERT into uloga(id, ime) values (1, 'ROLE_ADMIN')
INSERT into uloga(id, ime) values (2, 'ROLE_USER')
INSERT into tip_kulturne_ponude(id, naziv) values (100,'obicna')
-- password is 'user' (bcrypt encoded)
INSERT into registrovani_korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (1,'123@gmail.com','Aca','arak','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)
-- password is 'admin' (bcrypt encoded)
INSERT into admin(user_id,email,ime,korisnicko_ime,lozinka, prezime,enabled)values (2,'124@gmail.com','Reka','alak','$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK','Rekic',true)

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1)
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2)

INSERT into kulturna_ponuda(id,adresa,geo_duzina,geo_sirina,naziv,opis,admin_user_id,tip_kulturne_ponude_id)values (100,'test','23','32','kulturnaponuda','ggez',2,100)
INSERT into kulturna_ponuda(id,adresa,geo_duzina,geo_sirina,naziv,opis,admin_user_id,tip_kulturne_ponude_id)values (101,'test','34','43','nekulturnaponuda','og',2,100)
INSERT into kulturna_ponuda(id,adresa,geo_duzina,geo_sirina,naziv,opis,admin_user_id,tip_kulturne_ponude_id)values (102,'test','56','65','ponuda','lol',2,100)
INSERT into ocena(id, vrednost, kulturna_ponuda_id, registrovani_korisnik_user_id) values (100, 5, 100,1)

INSERT into komentar(id,vrednost,kulturna_ponuda_id,registrovani_korisnik_user_id)values (100,'Veoma lepa lultura!',100,1)
INSERT into fotografija(id,lokacija_fajl,komentar_id,kulturna_ponuda_id)values (100,'files//neki_folder',null,100)
INSERT into novost(id, datum, naziv, opis, kulturna_ponuda_id) values (100, '2020-12-12', 'novost', 'opis', 100)


