package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public abstract class Colaborator implements IOperatiiCitireScriere {
    // date comune pentru orice colaborator
    protected String nume;
    protected String prenume;
    protected double venitBrutLunar;
    protected TipColaborator tip;

    // constructor gol
    public Colaborator() {
    }

    // constructor cu parametri
    public Colaborator(String nume, String prenume, double venitBrutLunar, TipColaborator tip) {
        this.nume = nume;
        this.prenume = prenume;
        this.venitBrutLunar = venitBrutLunar;
        this.tip = tip;
    }

    // Getteri
    public String getNume() {
        return nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public double getVenitBrutLunar() {
        return venitBrutLunar;
    }
    public TipColaborator getTip() {
        return tip;
    }

    // citesc datele comune
    @Override
    public void citeste(Scanner in) {
        nume = in.next();
        prenume = in.next();
        venitBrutLunar = in.nextDouble();
    }

    // fiecare subclasa își calculează separat venitul net anual
    public abstract double calculeazaVenitNetAnual();
}