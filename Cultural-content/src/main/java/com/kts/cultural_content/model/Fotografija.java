package com.kts.cultural_content.model;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Entity
public class Fotografija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column
    private String lokacijaFajl;

    @Column
    private File foto;

    @Column
    Integer kulId;
    @Column
    Integer recId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private KulturnaPonuda kulturnaPonuda;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Recenzija recenzija;

    public Fotografija() {
    }

    public Fotografija(String lokacijaFajl, Integer kulId, Integer recId) {
        this.lokacijaFajl = lokacijaFajl;
        this.kulId = kulId;
        this.recId = recId;
    }

    @Override
    public boolean equals(Object obj) {
        Fotografija o = (Fotografija)obj;
        if(this.getId()==null || o.getId()==null){
            if (this.getRecId()==null || o.getRecId()==null)
                return this.getKulId().equals(o.getKulId());
            else
                return this.getRecId().equals(o.getRecId());
        }else
            return this.getId().equals(o.getId());
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public File getFoto() {
        return foto;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public Integer getRecId() {
        return recId;
    }

    public Integer getId() {
        return id;
    }


    public String getLokacijaFajl() {
        return lokacijaFajl;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setLokacijaFajl(String lokacijaFajl) {
        this.lokacijaFajl = lokacijaFajl;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }


    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public BufferedImage konverzijaUSliku(File slika){
        BufferedImage image;
        try {
            image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(slika);
            return image;
        }
        catch (IOException e){
            System.out.println("Nije dobra slika");
            return null;
        }
    }
}
