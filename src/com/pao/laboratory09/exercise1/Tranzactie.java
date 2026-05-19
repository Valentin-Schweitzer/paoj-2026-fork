package com.pao.laboratory09.exercise1;

import java.io.Serializable;

// Reprezintă o tranzacție bancară care poate fi serializată.
public class Tranzactie implements Serializable {
    // Versiunea clasei folosită la serializare.
    private static final long serialVersionUID = 1L;

    int id;
    double suma;
    String data;
    String contSursa;
    String contDestinatie;
    TipTranzactie tip;

    // Câmpul transient nu este salvat în fișier la serializare.
    transient String note;

    // Construiește o tranzacție cu toate datele citite din input.
    public Tranzactie(int id, double suma, String data, String contSursa,
                      String contDestinatie, TipTranzactie tip) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.contSursa = contSursa;
        this.contDestinatie = contDestinatie;
        this.tip = tip;
    }

    // Afișează tranzacția în formatul cerut în enunț.
    public void afisare() {
        System.out.printf("[%d] %s %s: %.2f RON | %s -> %s%n",
                id, data, tip, suma, contSursa, contDestinatie);
    }
}