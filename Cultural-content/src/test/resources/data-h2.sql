INSERT into uloga(id, ime) values (0, 'NISTA');
-- password is 'user' (bcrypt encoded)
INSERT into korisnik(user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values (3,'3@gmail.com','Aca','acika','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true);

-- password is 'user' (bcrypt encoded)
INSERT into registrovani_korisnik(reset_password_token,user_id,email,ime,korisnicko_ime,lozinka,prezime,enabled) values ('abc',4,'5@gmail.com','Marko','mark','$2a$04$Amda.Gm4Q.ZbXz9wcohDHOhOBaNQAkSS1QO26Eh8Hovu3uzEpQvcq','Acic',true);
INSERT into verification_token(id,token,user_id) values(2,'abb',4);

INSERT INTO ocena(id, vrednost) VALUES (101,'4')
