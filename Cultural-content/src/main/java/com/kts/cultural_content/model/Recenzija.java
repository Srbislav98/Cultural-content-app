package com.kts.cultural_content.model;

import javax.persistence.*;
import java.io.File;

@Entity
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer ocena;

    @Column
    private String komentar;

    @Column
    private Integer regId;

    @Column
    private Integer kulId;

    @Column
    private String foto;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    @OneToOne(mappedBy = "recenzija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Fotografija fotogrfija ;

    public Recenzija(Integer id, Integer ocena, String komentar, Integer regId, Integer kulId, String foto) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.regId = regId;
        this.kulId = kulId;
        this.foto = foto;
    }

    public Recenzija() {
    }

    @Override
    public boolean equals(Object obj) {
        Recenzija o = (Recenzija) obj;
        if(this.getId()==null || o.getId()==null){
            if(this.getRegId().equals(o.getRegId()))
                if(this.getKulId().equals(o.getKulId()))
                    return true;
                else
                    return false;
            else
                return false;
        }else
            return this.getId().equals(o.getId());
    }

    public void setFoto(String fotoLokacija) {
        this.foto = fotoLokacija;
    }

    public String getFoto() {
        return foto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setFotogrfija(Fotografija fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public Integer getRegId() {
        return regId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public RegistrovaniKorisnik getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public Fotografija getFotogrfija() {
        return fotogrfija;
    }
}
