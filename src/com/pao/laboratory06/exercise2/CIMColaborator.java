package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends PersoanaFizica {
    // spune daca are bonus sau nu
    private boolean bonus;

    // constructor gol
    public CIMColaborator() {
        this.tip = TipColaborator.CIM;
    }

    // constructor cu parametri
    public CIMColaborator(String nume, String prenume, double venitBrutLunar, boolean bonus) {
        super(nume, prenume, venitBrutLunar, TipColaborator.CIM);
        this.bonus = bonus;
    }

    // citesc datele specifice pentru CIM
    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        String raspuns = in.next();
        bonus = raspuns.equalsIgnoreCase("DA");
        tip = TipColaborator.CIM;
    }

    // afisez datele in formatul cerut
    @Override
    public void afiseaza() {
        System.out.printf("CIM: %s %s, venit net anual: %.2f lei%n",
                nume, prenume, calculeazaVenitNetAnual());
    }

    // intorc tipul contractului
    @Override
    public String tipContract() {
        return "CIM";
    }

    // spun daca are bonus
    @Override
    public boolean areBonus() {
        return bonus;
    }

    // calculez venitul net anual pentru CIM
    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = venitBrutLunar * 12 * 0.55;

        if (bonus) {
            venitNet = venitNet * 1.10;
        }

        return venitNet;
    }
}