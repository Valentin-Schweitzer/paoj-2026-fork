package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public interface IOperatiiCitireScriere {
    void citeste(Scanner in);   // citește datele obiectului de la tastatură

    void afiseaza();            // afișează datele obiectului

    String tipContract();       // întoarce tipul contractului

    default boolean areBonus() {
        return false;
    }
}