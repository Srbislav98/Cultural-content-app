package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class KulturnaPonuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Column(nullable=false, unique = true)//
    private Integer id;

    @Column(nullable=false, unique = true)
    private String naziv;

    @Column(nullable=false)
    private String geoSirina;

    @Column(nullable=false)
    private String geoDuzina;

    @Column(nullable=false)
    private String adresa;

    @Column(nullable=false)
    private String opis;

    @OneToMany( mappedBy = "kulturnaPonuda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Fotografija> fotogrfija = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( nullable = false)
    private Admin admin;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( nullable = false)
    private TipKulturnePonude tipKulturnePonude;

    @ManyToMany(  cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {
                    @JoinColumn(
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(
                            nullable = false)})
    private List<RegistrovaniKorisnik> registrovaniKorisnik;

    public KulturnaPonuda() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public TipKulturnePonude getTipKulturnePonude() {
        return tipKulturnePonude;
    }

    public void setTipKulturnePonude(TipKulturnePonude tipKulturnePonude) {
        this.tipKulturnePonude = tipKulturnePonude;
    }

    public List<RegistrovaniKorisnik> getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public void setRegistrovaniKorisnik(List<RegistrovaniKorisnik> registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public Set<Fotografija> getFotogrfija() {
        return fotogrfija;
    }

    public void setFotogrfija(Set<Fotografija> fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public KulturnaPonuda(Integer id, String naziv, String geoSirina, String geoDuzina, String adresa, String opis, Admin admin, TipKulturnePonude tipKulturnePonude) {
        this.id = id;
        this.naziv = naziv;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
        this.opis = opis;
        this.admin = admin;
        this.tipKulturnePonude = tipKulturnePonude;
        this.fotogrfija = new HashSet<>();
        this.registrovaniKorisnik = new ArrayList<RegistrovaniKorisnik>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(String geoSirina) {
        this.geoSirina = geoSirina;
    }

    public String getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(String geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
