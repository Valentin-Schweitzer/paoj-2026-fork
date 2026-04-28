package com.pao.elearning.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curs {
    private int id;
    private String titlu;
    private String descriere;
    private Lector lector;

    // Modulele care alcătuiesc cursul.
    private List<ModulCurs> module;

    public Curs(int id, String titlu, String descriere, Lector lector) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.lector = lector;

        // Cursul pornește fără module adăugate.
        this.module = new ArrayList<>();
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public List<ModulCurs> getModule() {
        return module;
    }

    public void adaugaModul(ModulCurs modul) {
        this.module.add(modul);
    }

    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", lector=" + lector.getNume() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curs curs)) return false;

        // Cursurile sunt comparate după id.
        return id == curs.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}