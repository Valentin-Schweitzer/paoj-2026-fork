package com.pao.elearning.model;

import java.time.LocalDate;

// Clasă imutabilă pentru certificatul emis unui cursant.
public final class Certificat {
    private final int id;
    private final Cursant cursant;
    private final Curs curs;
    private final LocalDate dataEmitere;

    public Certificat(int id, Cursant cursant, Curs curs) {
        this.id = id;
        this.cursant = cursant;
        this.curs = curs;

        // Data emiterii este stabilită la crearea certificatului.
        this.dataEmitere = LocalDate.now();
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

    public LocalDate getDataEmitere() {
        return dataEmitere;
    }

    @Override
    public String toString() {
        return "Certificat{" +
                "id=" + id +
                ", cursant=" + cursant.getNume() +
                ", curs=" + curs.getTitlu() +
                ", dataEmitere=" + dataEmitere +
                '}';
    }
}