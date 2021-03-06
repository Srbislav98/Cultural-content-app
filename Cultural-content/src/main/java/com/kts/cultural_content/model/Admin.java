package com.kts.cultural_content.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name= "ADMINI")
public class Admin extends Korisnik {

    @OneToMany( mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<KulturnaPonuda> kulturnePonude = new HashSet<>();

    public Admin() {
    }
    public Admin(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Set<KulturnaPonuda> kulturnaPonude) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
        this.kulturnePonude=kulturnaPonude;
    }

    public Admin(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
    }

    public Set<KulturnaPonuda> getKulturnePonude() {
        return kulturnePonude;
    }

    public void setKulturnaPonude(Set<KulturnaPonuda> kulturnePonude) {
        this.kulturnePonude = kulturnePonude;
    }

    @Override
    public boolean equals(Object obj) {
        Admin o = (Admin) obj;
        return this.getId().equals(o.getId()) && this.getEmail().equals((o.getEmail()))
                && this.getUsername().equals((o.getUsername()));
    }
}
