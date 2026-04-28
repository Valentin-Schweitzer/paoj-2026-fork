package com.pao.elearning.model;

import java.util.Objects;

public class Cursant extends Utilizator {
    // Nivelul de experiență al cursantului pe platformă.
    private int nivelExperienta;

    public Cursant(int id, String nume, String email, int nivelExperienta) {
        super(id, nume, email);
        this.nivelExperienta = nivelExperienta;
    }

    @Override
    public String getRol() {
        return "Cursant";
    }

    public int getNivelExperienta() {
        return nivelExperienta;
    }

    public void setNivelExperienta(int nivelExperienta) {
        this.nivelExperienta = nivelExperienta;
    }

    @Override
    public String toString() {
        return "Cursant{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", nivelExperienta=" + nivelExperienta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cursant cursant)) return false;

        // Cursanții sunt identificați după id.
        return id == cursant.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}