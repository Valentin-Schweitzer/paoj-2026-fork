package com.pao.laboratory03.exercise.model;

/**
 * Exercițiul 4 (Integrator) — Sistem de gestiune studenți + note
 *
 * Combină Map, Enum și Excepții custom într-un mini-sistem interactiv.
 * Studenții creează TOATE clasele de la zero.
 *
 * ═══════════════════════════════════════════════════════════════
 *  CLASE DE CREAT (fișiere separate în subpachetele corespunzătoare):
 * ═══════════════════════════════════════════════════════════════
 *
 * 1. model/Subject.java — ENUM
 *    - Constante: PAOJ, BD, SO, RC (sau alte materii)
 *    - Câmpuri: String fullName, int credits
 *    - Constructor privat, getteri
 *    - toString() → "PAOJ (Programare Avansată pe Obiecte, 6 credite)"
 */

// ==================================================
// PASUL 1 — model/Subject.java — ENUM
// ==================================================

public enum Subject {
    // Constante: PAOJ, BD, SO, RC (sau alte materii)
    PAOJ("Programare Avansată pe Obiecte", 6),
    BD("Baze de Date", 5),
    SO("Sisteme de Operare", 5),
    RC("Rețele de Calculatoare", 4);

    // Câmpuri: String fullName, int credits
    private String fullName;
    private int credits;

    // Constructor privat
    private Subject(String fullName, int credits) {
        this.fullName = fullName;
        this.credits = credits;
    }

    // Getteri
    public String getFullName() {
        return fullName;
    }
    public int getCredits() {
        return credits;
    }

    // toString() → "PAOJ (Programare Avansată pe Obiecte, 6 credite)"
    @Override
    public String toString() {
        return name() + " (" + fullName + ", " + credits + " credite)";
    }
}