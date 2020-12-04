package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String vrednost;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    @OneToMany(mappedBy = "komentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Fotografija> fotogrfija = new HashSet<>();

    public Komentar( String vrednost) {

        this.vrednost = vrednost;

    }

    public Komentar() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public Integer getId() {
        return id;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public RegistrovaniKorisnik getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public void setFotogrfija(Set<Fotografija> fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public Set<Fotografija> getFotogrfija() {
        return fotogrfija;
    }
}
