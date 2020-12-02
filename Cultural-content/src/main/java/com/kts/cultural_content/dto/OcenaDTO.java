package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;

public class OcenaDTO {

    private Integer id;
    private Integer vrednost;
    private RegistrovaniKorisnik registrovaniKorisnik;
    private KulturnaPonuda kulturnaPonuda;

    public OcenaDTO() {
    }

    public OcenaDTO(Integer id,Integer vrednost, RegistrovaniKorisnik registrovaniKorisnik, KulturnaPonuda kulturnaPonuda) {
        this.id = id;
        this.vrednost = vrednost;
        this.registrovaniKorisnik = registrovaniKorisnik;
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setVrednost(Integer vrednost) {
        this.vrednost = vrednost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Integer getVrednost() {
        return vrednost;
    }

    public RegistrovaniKorisnik getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }
}
