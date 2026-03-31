package com.pao.laboratory06.exercise2;

// clasa abstracta pentru colaboratorii persoane juridice
public abstract class PersoanaJuridica extends Colaborator {

    // constructor gol
    public PersoanaJuridica() {
    }

    // constructor cu parametri
    public PersoanaJuridica(String nume, String prenume, double venitBrutLunar, TipColaborator tip) {
        super(nume, prenume, venitBrutLunar, tip);
    }
}