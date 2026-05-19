package com.pao.laboratory09.exercise3;

// Reprezintă o tranzacție trimisă de un ATM către procesator
public class Tranzactie {
    int id;
    double suma;
    String data;

    // Construiește o tranzacție cu id, sumă și dată
    public Tranzactie(int id, double suma, String data) {
        this.id = id;
        this.suma = suma;
        this.data = data;
    }
}