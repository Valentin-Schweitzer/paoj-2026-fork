package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 6. service/StudentService.java — SERVICIU (Singleton)
 *    - Câmp: List<Student> students (ArrayList)
 *    - Singleton pattern (constructor privat, getInstance())
 *    - Metode:
 *      a) void addStudent(String name, int age)
 *         → creează Student și adaugă în listă
 *         → dacă există deja un student cu același nume, aruncă RuntimeException
 *      b) Student findByName(String name)
 *         → caută în listă, aruncă StudentNotFoundException dacă nu găsește
 *      c) void addGrade(String studentName, Subject subject, double grade)
 *         → găsește studentul (findByName) și adaugă nota
 *      d) void printAllStudents()
 *         → afișează toți studenții cu notele lor
 *      e) void printTopStudents()
 *         → sortează studenții descrescător după medie și afișează
 *      f) Map<Subject, Double> getAveragePerSubject()
 *         → calculează media pe fiecare materie (din toți studenții care au notă)
 */

// ==================================================
// PASUL 6 — service/StudentService.java — SERVICIU (Singleton)
// ==================================================

public class StudentService {
    // Câmp: List<Student> students (ArrayList)
    private List<Student> students;

    // Singleton pattern (constructor privat, getInstance())
    private static StudentService instance;
    // constructor privat,
    private StudentService() {
        this.students = new ArrayList<>();
    }
    // getInstance()
    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    // a) void addStudent(String name, int age)
    public void addStudent(String name, int age) {
        for (Student student : students) {
            // → dacă există deja un student cu același nume, aruncă RuntimeException
            if (student.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Există deja un student cu numele " + name);
            }
        }

        // → creează Student și adaugă în listă
        Student student = new Student(name, age);
        students.add(student);
    }

    // b) Student findByName(String name)
    public Student findByName(String name) {
        // → caută în listă, aruncă StudentNotFoundException dacă nu găsește
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }

        throw new StudentNotFoundException("Studentul cu numele " + name + " nu a fost găsit.");
    }

    // c) void addGrade(String studentName, Subject subject, double grade)
    public void addGrade(String studentName, Subject subject, double grade) {
        // → găsește studentul (findByName) și adaugă nota
        Student student = findByName(studentName);
        student.addGrade(subject, grade);
    }

    // d) void printAllStudents()
    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu există studenți.");
            return;
        }

        // → afișează toți studenții cu notele lor
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". " + student);

            for (Map.Entry<Subject, Double> entry : student.getGrades().entrySet()) {
                System.out.println("   " + entry.getKey().name() + " = " + entry.getValue());
            }
        }
    }

    // e) void printTopStudents()
    public void printTopStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu există studenți.");
            return;
        }

        List<Student> sortedStudents = new ArrayList<>(students);

        // → sortează studenții descrescător după medie și afișează
        sortedStudents.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));

        System.out.println("=== Top studenți ===");
        for (int i = 0; i < sortedStudents.size(); i++) {
            Student student = sortedStudents.get(i);
            System.out.printf("%d. %s — media: %.2f%n", i + 1, student.getName(), student.getAverage());
        }
    }

    // f) Map<Subject, Double> getAveragePerSubject()
    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sumMap = new HashMap<>();
        Map<Subject, Integer> countMap = new HashMap<>();

        for (Student student : students) {
            for (Map.Entry<Subject, Double> entry : student.getGrades().entrySet()) {
                Subject subject = entry.getKey();
                Double grade = entry.getValue();

                sumMap.put(subject, sumMap.getOrDefault(subject, 0.0) + grade);
                countMap.put(subject, countMap.getOrDefault(subject, 0) + 1);
            }
        }

        Map<Subject, Double> averageMap = new HashMap<>();

        // → calculează media pe fiecare materie (din toți studenții care au notă)
        for (Subject subject : sumMap.keySet()) {
            double sum = sumMap.get(subject);
            int count = countMap.get(subject);
            averageMap.put(subject, sum / count);
        }

        return averageMap;
    }
}