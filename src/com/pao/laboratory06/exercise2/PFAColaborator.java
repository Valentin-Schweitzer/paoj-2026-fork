package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends PersoanaFizica {
    // cheltuieli lunare pentru PFA
    private double cheltuieliLunare;

    // salariul minim brut anual folosit in calcule
    private static final double SALARIU_MINIM_BRUT_ANUAL = 4050 * 12;

    // constructor gol
    public PFAColaborator() {
        this.tip = TipColaborator.PFA;
    }

    // constructor cu parametri
    public PFAColaborator(String nume, String prenume, double venitBrutLunar, double cheltuieliLunare) {
        super(nume, prenume, venitBrutLunar, TipColaborator.PFA);
        this.cheltuieliLunare = cheltuieliLunare;
    }

    // citesc datele specifice pentru PFA
    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        cheltuieliLunare = in.nextDouble();
        tip = TipColaborator.PFA;
    }

    // afisez datele in formatul cerut
    @Override
    public void afiseaza() {
        System.out.printf("PFA: %s %s, venit net anual: %.2f lei%n",
                nume, prenume, calculeazaVenitNetAnual());
    }

    // intorc tipul contractului
    @Override
    public String tipContract() {
        return "PFA";
    }

    // calculez venitul net anual pentru PFA
    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (venitBrutLunar - cheltuieliLunare) * 12;

        double impozit = 0.10 * venitNet;
        double cass;
        double cas;

        // calculez CASS
        if (venitNet < 6 * SALARIU_MINIM_BRUT_ANUAL) {
            cass = 0.10 * (6 * SALARIU_MINIM_BRUT_ANUAL);
        } else if (venitNet <= 72 * SALARIU_MINIM_BRUT_ANUAL) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * (72 * SALARIU_MINIM_BRUT_ANUAL);
        }

        // calculez CAS
        if (venitNet < 12 * SALARIU_MINIM_BRUT_ANUAL) {
            cas = 0;
        } else if (venitNet <= 24 * SALARIU_MINIM_BRUT_ANUAL) {
            cas = 0.25 * (12 * SALARIU_MINIM_BRUT_ANUAL);
        } else {
            cas = 0.25 * (24 * SALARIU_MINIM_BRUT_ANUAL);
        }

        return venitNet - impozit - cass - cas;
    }
}