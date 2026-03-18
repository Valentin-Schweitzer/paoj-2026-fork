package com.pao.laboratory03.exercise.exception;

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
 * 4. exception/InvalidGradeException.java — EXCEPȚIE CUSTOM
 *    - extends RuntimeException
 *    - Constructor cu String message → super(message)
 */

// ==================================================
// PASUL 4 — exception/InvalidGradeException.java — EXCEPȚIE CUSTOM
// ==================================================

// extends RuntimeException
public class InvalidGradeException extends RuntimeException {
    // Constructor cu String message → super(message)
    public InvalidGradeException(String message) {
        super(message);
    }
}