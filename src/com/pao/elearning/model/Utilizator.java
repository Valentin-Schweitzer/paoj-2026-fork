package com.pao.elearning.model;

// Clasă de bază pentru toate tipurile de utilizatori.
public abstract class Utilizator {
    protected int id;
    protected String nume;
    protected String email;

    public Utilizator(int id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
    }

    // Rolul este definit separat în fiecare subclasă.
    public abstract String getRol();

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}