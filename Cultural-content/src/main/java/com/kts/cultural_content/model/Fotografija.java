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
    Integer komId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private KulturnaPonuda kulturnaPonuda;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Komentar komentar;

    public Fotografija() {
    }

    public Fotografija(String lokacijaFajl, Integer kulId, Integer komId) {
        this.lokacijaFajl = lokacijaFajl;
        this.kulId = kulId;
        this.komId = komId;
    }

    @Override
    public boolean equals(Object obj) {
        Fotografija o = (Fotografija)obj;
        if(this.getId()==null || o.getId()==null){
            if (this.getKomId()==null || o.getKomId()==null)
                return this.getKulId().equals(o.getKulId());
            else
                return this.getKomId().equals(o.getKomId());
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

    public void setKomId(Integer komId) {
        this.komId = komId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public Integer getKomId() {
        return komId;
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

    public void setKomentar(Komentar komentar) {
        this.komentar = komentar;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public Komentar getKomentar() {
        return komentar;
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
