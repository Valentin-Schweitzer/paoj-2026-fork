package com.pao.laboratory06.exercise2;

// clasa abstracta pentru colaboratorii persoane fizice
public abstract class PersoanaFizica extends Colaborator {

    // constructor gol
    public PersoanaFizica() {
    }

    // constructor cu parametri
    public PersoanaFizica(String nume, String prenume, double venitBrutLunar, TipColaborator tip) {
        super(nume, prenume, venitBrutLunar, tip);
    }
}