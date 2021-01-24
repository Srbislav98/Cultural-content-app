package com.kts.cultural_content.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UcitavanjeRecenzije {
    private Integer id;

    private Integer ocena;

    private String komentar;

    private Integer regId;

    private Integer kulId;

    //private File foto;



    public UcitavanjeRecenzije(Integer id, Integer ocena, String komentar, Integer regId, Integer kulId) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.regId = regId;
        this.kulId = kulId;

    }

    public UcitavanjeRecenzije() {
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
}
