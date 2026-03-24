package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte> {

    // Câmpuri private
    private String titlu;
    private String autor;
    private int an;
    private double rating;

    // Constructor complet
    public Carte(String titlu, String autor, int an, double rating) {
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
        this.rating = rating;
    }

    // Getter pentru titlu
    public String getTitlu() {
        return titlu;
    }

    // Getter pentru autor
    public String getAutor() {
        return autor;
    }

    // Getter pentru an
    public int getAn() {
        return an;
    }

    // Getter pentru rating
    public double getRating() {
        return rating;
    }

    // Afișare obiect
    @Override
    public String toString() {
        return "Carte{titlu='" + titlu + "', autor='" + autor + "', an=" + an + ", rating=" + rating + "}";
    }

    // Sortare după rating descrescător
    @Override
    public int compareTo(Carte altaCarte) {
        return Double.compare(altaCarte.rating, this.rating);
        // Obs: Double.compare este inversat intenționat ["altaCarte, this" și nu "this, altaCarte"],
        // ca să fie sortate descrescător
    }
}