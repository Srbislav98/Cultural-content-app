package com.kts.cultural_content.dto;

public class RecenzijaDTO {
    private Integer id;


    private Integer ocena;


    private String komentar;


    private Integer regId;


    private Integer kulId;

    private String fotoLokacija;

    public RecenzijaDTO(Integer id, Integer ocena, String komentar, Integer regId, Integer kulId, String fotoLokacija) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.regId = regId;
        this.kulId = kulId;
        this.fotoLokacija = fotoLokacija;
    }

    public RecenzijaDTO() {
    }

    public void setFotoLokacija(String fotoLokacija) {
        this.fotoLokacija = fotoLokacija;
    }

    public String getFotoLokacija() {
        return fotoLokacija;
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
