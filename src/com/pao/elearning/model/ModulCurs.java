package com.pao.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class ModulCurs {
    private int id;
    private String titlu;

    // Un modul conține una sau mai multe lecții.
    private List<Lectie> lectii;

    public ModulCurs(int id, String titlu) {
        this.id = id;
        this.titlu = titlu;

        // Lista pornește goală și se completează ulterior.
        this.lectii = new ArrayList<>();
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

    public List<Lectie> getLectii() {
        return lectii;
    }

    public void adaugaLectie(Lectie lectie) {
        this.lectii.add(lectie);
    }

    @Override
    public String toString() {
        return "ModulCurs{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", lectii=" + lectii.size() +
                '}';
    }
}