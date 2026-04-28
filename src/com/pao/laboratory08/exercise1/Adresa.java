package com.pao.laboratory08.exercise1;

public class Adresa implements Cloneable {
    private String oras;
    private String strada;

    // Constructorul inițializează adresa cu oraș și stradă.
    public Adresa(String oras, String strada) {
        this.oras = oras;
        this.strada = strada;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    // Creează o copie a obiectului Adresa.
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Formatul cerut pentru afișare.
    @Override
    public String toString() {
        return "Adresa{oras='" + oras + "', strada='" + strada + "'}";
    }
}