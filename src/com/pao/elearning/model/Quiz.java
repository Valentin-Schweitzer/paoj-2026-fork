package com.pao.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String titlu;
    private Curs curs;

    // Lista de întrebări care formează quiz-ul.
    private List<Intrebare> intrebari;

    public Quiz(int id, String titlu, Curs curs) {
        this.id = id;
        this.titlu = titlu;
        this.curs = curs;

        // Întrebările sunt adăugate după crearea quiz-ului.
        this.intrebari = new ArrayList<>();
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

    public Curs getCurs() {
        return curs;
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void adaugaIntrebare(Intrebare intrebare) {
        this.intrebari.add(intrebare);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", curs=" + curs.getTitlu() +
                ", intrebari=" + intrebari.size() +
                '}';
    }
}