package com.pao.laboratory07.exercise2;

public final class ComandaGratuita extends Comanda {

    public ComandaGratuita(String nume) {
        // inițializează numele și starea PLACED
        super(nume);
    }

    @Override
    public double pretFinal() {
        // comanda gratuită nu costă nimic
        return 0.0;
    }

    @Override
    public String descriere() {
        // afișăm formatul cerut pentru gift
        return String.format(
                "GIFT: %s, gratuit [%s]",
                nume,
                stare
        );
    }
}