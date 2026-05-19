package com.pao.laboratory10.exercise1;

// Reprezintă o tranzacție bancară din coadă
public class Tranzactie {
    private int id;
    private double suma;
    private String data;
    private TipTranzactie tip;

    // Construiește o tranzacție cu toate datele citite din input
    public Tranzactie(int id, double suma, String data, TipTranzactie tip) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public double getSuma() {
        return suma;
    }

    public String getData() {
        return data;
    }

    public TipTranzactie getTip() {
        return tip;
    }

    // Returnează tranzacția în formatul cerut în enunț
    @Override
    public String toString() {
        return String.format("[%d] %s %s: %.2f RON", id, data, tip, suma);
    }
}