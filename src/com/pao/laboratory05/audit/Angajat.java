package com.pao.laboratory05.audit;

public class Angajat implements Comparable<Angajat> {

    // Câmpuri private
    private String nume;
    private Departament departament;
    private double salariu;


    // Constructor complet
    public Angajat(String nume, Departament departament, double salariu) {
        this.nume = nume;
        this.departament = departament;
        this.salariu = salariu;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Getter pentru departament
    public Departament getDepartament() {
        return departament;
    }

    // Getter pentru salariu
    public double getSalariu() {
        return salariu;
    }

    // Afișare obiect
    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", departament=" + departament +
                ", salariu=" + salariu +
                '}';
    }

    // Sortare după salariu descrescător
    @Override
    public int compareTo(Angajat other) {
        return Double.compare(other.salariu, this.salariu);
        // Obs: Double.compare este inversat intenționat ["other, this" și nu "this, other"],
        // ca să fie sortate descrescător
    }
}