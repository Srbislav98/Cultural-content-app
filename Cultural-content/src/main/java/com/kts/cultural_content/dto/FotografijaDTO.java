package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.KulturnaPonuda;

public class FotografijaDTO {

    private Integer id;
    private String naziv;
    private String lokacijaFajl;
    private KulturnaPonuda kulturnaPonuda;
    private Komentar komentar;

    public FotografijaDTO(Integer id, String naziv, String lokacijaFajl, KulturnaPonuda kulturnaPonuda, Komentar komentar) {
        this.id = id;
        this.naziv = naziv;
        this.lokacijaFajl = lokacijaFajl;
        this.kulturnaPonuda = kulturnaPonuda;
        this.komentar = komentar;
    }

    public FotografijaDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setLokacijaFajl(String lokacijaFajl) {
        this.lokacijaFajl = lokacijaFajl;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setKomentar(Komentar komentar) {
        this.komentar = komentar;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getLokacijaFajl() {
        return lokacijaFajl;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public Komentar getKomentar() {
        return komentar;
    }
}
