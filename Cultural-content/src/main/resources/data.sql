INSERT into uloga(id, ime) values (1, 'ROLE_ADMIN')
INSERT into uloga(id, ime) values (2, 'ROLE_USER')
INSERT into tip_kulturne_ponude(id, naziv) values (100,'obicna')
-- password is 'user' (bcrypt encoded)
INSERT into registrovani_korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (101,'123@','Aca','arak','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true)
-- password is 'admin' (bcrypt encoded)
INSERT into admin(user_id,email,ime,korisnicko_ime,lozinka, prezime,enabled)values (100,'124@','Reka','alak','$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK','Rekic',true)

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (100, 1)
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (101, 2)

INSERT into kulturna_ponuda(id,adresa,geo_duzina,geo_sirina,naziv,opis,admin_user_id,tip_kulturne_ponude_id)values (100,'test','nesto','asal','Lak','okasd',100,100)
INSERT into ocena(id, vrednost, kulturna_ponuda_id, registrovani_korisnik_user_id) values (100, 5, 100,101)

INSERT into komentar(id,vrednost,kulturna_ponuda_id,registrovani_korisnik_user_id)values (100,'Veoma lepa lultura!',100,101)
INSERT into fotografija(id,lokacija_fajl,komentar_id,kulturna_ponuda_id)values (100,'files//neki_folder',null,100)


