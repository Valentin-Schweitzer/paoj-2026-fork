package com.pao.laboratory05.biblioteca;

import java.util.Arrays;
import java.util.Comparator;

public class BibliotecaService {
    private Carte[] carti;

    // Constructor privat
    private BibliotecaService() {
        this.carti = new Carte[0];
    }

    // Holder intern pentru Singleton (pattern ca în Lab 01)
    private static class Holder {
        private static final BibliotecaService INSTANCE = new BibliotecaService();
    }

    // Metodă prin care obținem singura instanță
    public static BibliotecaService getInstance() {
        return Holder.INSTANCE;
    }

    // Adaugă o carte în vector
    // resize + adaugă + printează confirmare
    public void addCarte(Carte carte) {
        Carte[] cartiNoi = new Carte[carti.length + 1];

        System.arraycopy(carti, 0, cartiNoi, 0, carti.length);
        cartiNoi[carti.length] = carte;

        carti = cartiNoi;

        System.out.println("Carte adăugată: " + carte.getTitlu());
    }

    // Afișează cărțile sortate după rating (folosește ordinea naturală din Comparable)
    public void listSortedByRating() {
        Carte[] copie = carti.clone();
        Arrays.sort(copie);

        for (Carte carte : copie) {
            System.out.println(carte);
        }
    }

    // Afișează cărțile sortate după comparatorul primit ca parametru
    public void listSortedBy(Comparator<Carte> comparator) {
        Carte[] copie = carti.clone();
        Arrays.sort(copie, comparator);

        for (Carte carte : copie) {
            System.out.println(carte);
        }
    }
}