package com.kts.cultural_content.model;

import com.kts.cultural_content.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity

@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class KulturnaPonuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Column(nullable=false, unique = true)//
    private Integer id;

    @Column(nullable=false, unique = true)
    private String naziv;

    @Column(nullable=false)
    private String adresa;

    @Column(nullable=true)
    private String opis;

    @OneToMany( mappedBy = "kulturnaPonuda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Fotografija> fotogrfija = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Admin admin;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private TipKulturnePonude tipKulturnePonude;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Lokacija lokacija;



    @OneToMany( mappedBy = "kulturnaPonuda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    @OneToMany( mappedBy = "kulturnaPonuda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Novost> novosti = new HashSet<>();

    @ManyToMany(mappedBy = "kulturnaPonuda", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<RegistrovaniKorisnik> registrovaniKorisnik = new HashSet<>();

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

    public void setRegistrovaniKorisnik(Set<RegistrovaniKorisnik> registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public Set<RegistrovaniKorisnik> getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public Set<Fotografija> getFotogrfija() {
        return fotogrfija;
    }

    public void setFotogrfija(Set<Fotografija> fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    /*public KulturnaPonuda(Integer id, String naziv, String geoSirina, String geoDuzina, String adresa, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
        this.opis = opis;
        this.admin = new Admin(100, "", "", "", "", "", null);
    }*/

    public KulturnaPonuda(Integer id, String naziv, String adresa, String opis, TipKulturnePonude tip, Admin ad, Lokacija lokacija) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.opis = opis;
        this.admin = ad;
        this.tipKulturnePonude = tip;
        this.lokacija = lokacija;
    }

    public Integer getIdt(){
        return this.tipKulturnePonude.getId();
    }



    public void setNovosti(Set<Novost> novosti) {
        this.novosti = novosti;
    }



    public Set<Novost> getNovosti() {
        return novosti;
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

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public Float prosecnaOcena(){
        Float d = 0.0f;

        for (Recenzija i : this.getRecenzije()){
            d += i.getOcena();

        }
        if(this.getRecenzije().size()!=0)
            d = d/this.getRecenzije().size();
        return d;
    }
    @Override
    public boolean equals(Object obj) {
        KulturnaPonuda o = (KulturnaPonuda) obj;
        return this.getId().equals(o.getId());
    }

}
