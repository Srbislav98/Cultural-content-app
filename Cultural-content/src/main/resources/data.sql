INSERT into uloga(id, ime) values (1, 'ROLE_ADMIN')
INSERT into uloga(id, ime) values (2, 'ROLE_USER')
INSERT into tip_kulturne_ponude(id, naziv) values (100,'obicna')

INSERT into registrovani_korisnik(id,email,ime,korisnicko_ime,lozinka,prezime) values (101,'123@','Aca','arak','123','Acic')
INSERT into admin(id,email,ime,korisnicko_ime,lozinka, prezime)values (100,'124@','Reka','alak','124','Rekic')

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (100, 1)
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (101, 2)

INSERT into kulturna_ponuda(id,adresa,geo_duzina,geo_sirina,naziv,opis,admin_id,tip_kulturne_ponude_id)values (100,'test','nesto','asal','Lak','okasd',100,100)
INSERT into ocena(id, vrednost, kulturna_ponuda_id, registrovani_korisnik_id) values (100, 5, 100,101)

INSERT into komentar(id,vrednost,kulturna_ponuda_id,registrovani_korisnik_id)values (100,'Veoma lepa lultura!',100,101)
INSERT into fotografija(id,lokacija_fajl,komentar_id,kulturna_ponuda_id)values (100,'files//neki_folder',null,100)

