package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.Set;

public class KomentarDTO {
    private Integer id;

    private String vrednost;

    private RegistrovaniKorisnik registrovaniKorisnik;

    private KulturnaPonuda kulturnaPonuda;

    private Set<Fotografija> fotogrfija = new HashSet<>();

    public KomentarDTO() {
    }

    public KomentarDTO(Integer id, String vrednost, RegistrovaniKorisnik registrovaniKorisnik, KulturnaPonuda kulturnaPonuda, Set<Fotografija> fotogrfija) {
        this.id = id;
        this.vrednost = vrednost;
        this.registrovaniKorisnik = registrovaniKorisnik;
        this.kulturnaPonuda = kulturnaPonuda;
        this.fotogrfija = fotogrfija;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setFotogrfija(Set<Fotografija> fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public Integer getId() {
        return id;
    }

    public String getVrednost() {
        return vrednost;
    }

    public RegistrovaniKorisnik getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public Set<Fotografija> getFotogrfija() {
        return fotogrfija;
    }
}
