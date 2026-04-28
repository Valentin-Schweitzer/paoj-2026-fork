package com.pao.elearning.model;

import java.time.LocalDate;

public class Inscriere {
    private int id;
    private Cursant cursant;
    private Curs curs;
    private LocalDate dataInscriere;

    // Indică dacă un curs a fost finalizat de cursant.
    private boolean finalizata;

    public Inscriere(int id, Cursant cursant, Curs curs) {
        this.id = id;
        this.cursant = cursant;
        this.curs = curs;

        // Data înscrierii este completată automat.
        this.dataInscriere = LocalDate.now();
        this.finalizata = false;
    }

    public int getId() {
        return id;
    }

    public Cursant getCursant() {
        return cursant;
    }

    public Curs getCurs() {
        return curs;
    }

    public LocalDate getDataInscriere() {
        return dataInscriere;
    }

    public boolean isFinalizata() {
        return finalizata;
    }

    public void setFinalizata(boolean finalizata) {
        this.finalizata = finalizata;
    }

    @Override
    public String toString() {
        return "Inscriere{" +
                "id=" + id +
                ", cursant=" + cursant.getNume() +
                ", curs=" + curs.getTitlu() +
                ", dataInscriere=" + dataInscriere +
                ", finalizata=" + finalizata +
                '}';
    }
}