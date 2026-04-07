package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda {

    // prețul initial al comenzii
    private double pret;

    // procentul de reducere
    private int discountProcent;

    public ComandaRedusa(String nume, double pret, int discountProcent) {
        // inițializează numele și starea PLACED
        super(nume);
        this.pret = pret;
        this.discountProcent = discountProcent;
    }

    @Override
    public double pretFinal() {
        // aplicăm reducerea la prețul inițial
        return pret * (1 - discountProcent / 100.0);
    }

    @Override
    public String descriere() {
        // afișăm prețul final și procentul de reducere
        return String.format(
                "DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s]",
                nume,
                pretFinal(),
                discountProcent,
                stare
        );
    }
}