package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends PersoanaJuridica {
    // cheltuieli lunare pentru SRL
    private double cheltuieliLunare;

    // constructor gol
    public SRLColaborator() {
        this.tip = TipColaborator.SRL;
    }

    // constructor cu parametri
    public SRLColaborator(String nume, String prenume, double venitBrutLunar, double cheltuieliLunare) {
        super(nume, prenume, venitBrutLunar, TipColaborator.SRL);
        this.cheltuieliLunare = cheltuieliLunare;
    }

    // citesc datele specifice pentru SRL
    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        cheltuieliLunare = in.nextDouble();
        tip = TipColaborator.SRL;
    }

    // afisez datele in formatul cerut
    @Override
    public void afiseaza() {
        System.out.printf("SRL: %s %s, venit net anual: %.2f lei%n",
                nume, prenume, calculeazaVenitNetAnual());
    }

    // intorc tipul contractului
    @Override
    public String tipContract() {
        return "SRL";
    }

    // calculez venitul net anual pentru SRL
    @Override
    public double calculeazaVenitNetAnual() {
        return (venitBrutLunar - cheltuieliLunare) * 12 * 0.84;
    }
}