package com.pao.laboratory03.exercise.model;

import com.pao.laboratory03.exercise.exception.InvalidGradeException;
import com.pao.laboratory03.exercise.exception.InvalidStudentException;

import java.util.HashMap;
import java.util.Map;

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
 * 2. model/Student.java — CLASĂ
 *    - Câmpuri private: String name, int age, Map<Subject, Double> grades
 *    - Constructor: Student(String name, int age)
 *      → inițializează grades ca HashMap gol
 *      → validare: dacă age < 18 sau age > 60, aruncă InvalidStudentException
 *    - Metode: getName(), getAge(), getGrades()
 *    - addGrade(Subject subject, double grade)
 *      → dacă grade < 1 sau grade > 10, aruncă InvalidGradeException
 *      → pune nota în map (suprascrie dacă materia există deja)
 *    - double getAverage()
 *      → calculează media aritmetică a notelor (returnează 0 dacă nu are note)
 *    - toString() → "Student{name='Ana', age=20, avg=8.50}"
 */

// ==================================================
// PASUL 2 — model/Student.java — CLASĂ
// ==================================================

public class Student {
    // Câmpuri private: String name, int age, Map<Subject, Double> grades
    private String name;
    private int age;
    private Map<Subject, Double> grades;

    // Constructor: Student(String name, int age)
    public Student(String name, int age) {
        // → validare: dacă age < 18 sau age > 60, aruncă InvalidStudentException
        if (age < 18 || age > 60) {
            throw new InvalidStudentException("Vârsta " + age + " nu este validă (18-60)");
        }

        this.name = name;
        this.age = age;
        // → inițializează grades ca HashMap gol
        this.grades = new HashMap<>();
    }

    // Metode: getName(), getAge(), getGrades()
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Map<Subject, Double> getGrades() {
        return grades;
    }

    // addGrade(Subject subject, double grade)
    public void addGrade(Subject subject, double grade) {
        // dacă grade < 1 sau grade > 10, aruncă InvalidGradeException
        if (grade < 1 || grade > 10) {
            throw new InvalidGradeException("Nota trebuie să fie între 1 și 10.");
        }

        // pune nota în map (suprascrie dacă materia există deja)
        grades.put(subject, grade);
    }

    // double getAverage()
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }

        // → calculează media aritmetică a notelor (returnează 0 dacă nu are note)
        double sum = 0;
        for (double g : grades.values()) {
            sum += g;
        }

        return sum / grades.size();
    }

    // toString() → "Student{name='Ana', age=20, avg=8.50}"
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, avg=%.2f}", name, age, getAverage());
    }
}