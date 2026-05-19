package com.pao.laboratory10.exercise3;

// Reprezintă o tranzacție bancară folosită în demonstrația cu Stream API
public class Tranzactie {
    private int id;
    private double suma;
    private String data;
    private TipTranzactie tip;
    private String contSursa;

    // Construiește o tranzacție cu toate datele necesare pentru rapoarte
    public Tranzactie(int id, double suma, String data, TipTranzactie tip, String contSursa) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
        this.contSursa = contSursa;
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

    public String getContSursa() {
        return contSursa;
    }

    // Returnează tranzacția în formatul cerut în enunț
    @Override
    public String toString() {
        return String.format("[%d] %s %s: %.2f RON", id, data, tip, suma);
    }
}