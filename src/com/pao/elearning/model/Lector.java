package com.pao.elearning.model;

import java.util.Objects;

public class Lector extends Utilizator {
    // Domeniul în care lectorul predă cursuri.
    private String specializare;

    public Lector(int id, String nume, String email, String specializare) {
        super(id, nume, email);
        this.specializare = specializare;
    }

    @Override
    public String getRol() {
        return "Lector";
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", specializare='" + specializare + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lector lector)) return false;

        // Lectorii sunt identificați după id.
        return id == lector.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}