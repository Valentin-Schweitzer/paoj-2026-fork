package com.pao.elearning.model;

public class Lectie {
    private int id;
    private String titlu;
    private String continut;

    // Durata lecției este memorată în minute.
    private int durataMinute;

    public Lectie(int id, String titlu, String continut, int durataMinute) {
        this.id = id;
        this.titlu = titlu;
        this.continut = continut;
        this.durataMinute = durataMinute;
    }

    public int getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public int getDurataMinute() {
        return durataMinute;
    }

    public void setDurataMinute(int durataMinute) {
        this.durataMinute = durataMinute;
    }

    @Override
    public String toString() {
        return "Lectie{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", durataMinute=" + durataMinute +
                '}';
    }
}