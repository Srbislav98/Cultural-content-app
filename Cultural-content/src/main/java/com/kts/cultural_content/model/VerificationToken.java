package com.kts.cultural_content.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Entity
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = RegistrovaniKorisnik.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private RegistrovaniKorisnik user;

    public VerificationToken(){

    }
    public VerificationToken(String token, RegistrovaniKorisnik user) {
        this.token = token;
        this.user = user;
    }

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegistrovaniKorisnik getUser() {
        return user;
    }

    public void setUser(RegistrovaniKorisnik user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
// standard constructors, getters and setters
    @Override
    public boolean equals(Object obj) {
        VerificationToken o = (VerificationToken) obj;
        return this.getId().equals(o.getId());
    }
}
