package com.pao.laboratory05.biblioteca;

import java.util.Comparator;

public class CarteAutorComparator implements Comparator<Carte> {

    // Compară două cărți după autor, alfabetic
    @Override
    public int compare(Carte carte1, Carte carte2) {
        // Ca la Song.java, string are deja compareTo, deci îl folosim
        return carte1.getAutor().compareTo(carte2.getAutor());
    }
}