package com.pao.laboratory05.angajati;

import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati;

    // Constructor privat
    private AngajatService() {
        this.angajati = new Angajat[0];
    }

    // Holder intern pentru Singleton (pattern ca în Lab 01)
    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    // Metodă prin care obținem singura instanță
    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    // Adaugă un angajat în vector
    // resize + adaugă + printează confirmare
    public void addAngajat(Angajat a) {
        Angajat[] angajatiNoi = new Angajat[angajati.length + 1];

        System.arraycopy(angajati, 0, angajatiNoi, 0, angajati.length);
        angajatiNoi[angajati.length] = a;

        angajati = angajatiNoi;

        System.out.println("Angajat adăugat: " + a.getNume());
    }

    // Afișează toți angajații în ordinea din vector
    public void printAll() {
        for (Angajat angajat : angajati) {
            System.out.println(angajat);
        }
    }

    // Afișează angajații sortați descrescător după salariu
    public void listBySalary() {
        Angajat[] copie = angajati.clone();
        Arrays.sort(copie);

        for (Angajat angajat : copie) {
            System.out.println(angajat);
        }
    }

    // Afișează angajații din departamentul dat, dacă nu găsește niciun angajat, afișează mesajul dat
    public void findByDepartament(String numeDept) {
        boolean gasit = false;

        for (Angajat angajat : angajati) {
            if (angajat.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(angajat);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }
}