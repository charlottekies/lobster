package com.charlotte.kies.model;


import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class GoogleUser {
    @Column
    private String aud;
    @Column
    private String azp;
    @Column
    private String email;
    @Column
    private boolean email_verified;
    @Column
    private long exp;
    @Column
    private String family_name;
    @Column
    private String given_name;
    @Column
    private long iat;
    @Column
    private String iss;
    @Column
    private String jti;
    @Column
    private String name;
    @Column
    private long nbf;
    @Column
    private String picture;
    @Column
    private String sub;


    /**** Constructor ****/
    public GoogleUser(String aud, String azp, String email, boolean email_verified, long exp, String family_name, String given_name, long iat, String iss, String jti, String name, long nbf, String picture, String sub) {
        this.aud = aud;
        this.azp = azp;
        this.email = email;
        this.email_verified = email_verified;
        this.exp = exp;
        this.family_name = family_name;
        this.given_name = given_name;
        this.iat = iat;
        this.iss = iss;
        this.jti = jti;
        this.name = name;
        this.nbf = nbf;
        this.picture = picture;
        this.sub = sub;
    }

    public GoogleUser() {};

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getAzp() {
        return azp;
    }

    public void setAzp(String azp) {
        this.azp = azp;
    }

    /**** Getters and Setters ****/



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNbf() {
        return nbf;
    }

    public void setNbf(long nbf) {
        this.nbf = nbf;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
