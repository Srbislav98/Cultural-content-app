package com.kts.cultural_content.dto;

import java.io.File;

public class RecenzijaDTO implements Comparable{
    private Integer id;

    private Integer ocena;

    private String komentar;

    private Integer regId;

    private Integer kulId;

    private String foto;



    public RecenzijaDTO(Integer id, Integer ocena, String komentar, Integer regId, Integer kulId, String foto) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.regId = regId;
        this.kulId = kulId;
        this.foto = foto;
    }

    public RecenzijaDTO() {
    }

    public void setFoto(String foto) {
        this.foto = foto;
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


    @Override
    public int compareTo(Object o){
        RecenzijaDTO e = (RecenzijaDTO) o;
        return getOcena().compareTo(e.getOcena());
    }

}


