package com.pao.laboratory07.exercise2;

public final class ComandaStandard extends Comanda {

    // prețul normal al comenzii
    private double pret;

    public ComandaStandard(String nume, double pret) {
        // inițializează numele și starea PLACED
        super(nume);
        this.pret = pret;
    }

    @Override
    public double pretFinal() {
        // la comanda standard nu există reducere
        return pret;
    }

    @Override
    public String descriere() {
        // afișăm exact în formatul cerut
        return String.format(
                "STANDARD: %s, pret: %.2f lei [%s]",
                nume,
                pretFinal(),
                stare
        );
    }
}