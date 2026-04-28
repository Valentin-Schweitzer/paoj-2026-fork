package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md

        // 1. Citește studenții din FILE_PATH cu BufferedReader
        ArrayList<Student> studenti = citesteStudenti();

        // 2. Citește comanda din stdin: PRINT, SHALLOW <nume> sau DEEP <nume>
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String comanda = br.readLine().trim();

        String[] parts = comanda.split(" ", 2);
        String tipComanda = parts[0];

        // 3. Execută comanda:
        if (tipComanda.equals("PRINT")) {
            // - PRINT → afișează toți studenții
            afiseazaStudenti(studenti);
        } else if (tipComanda.equals("SHALLOW")) {
            // - SHALLOW <nume> → shallow clone + modifică orașul clonei la "MODIFICAT" + afișează
            String nume = parts[1].trim();
            Student student = cautaStudent(studenti, nume);

            Student clona = student.shallowClone();
            clona.getAdresa().setOras("MODIFICAT");

            System.out.println("Original: " + student);
            System.out.println("Clona: " + clona);
        } else if (tipComanda.equals("DEEP")) {
            // - DEEP <nume> → deep clone + modifică orașul clonei la "MODIFICAT" + afișează
            String nume = parts[1].trim();
            Student student = cautaStudent(studenti, nume);

            Student clona = student.deepClone();
            clona.getAdresa().setOras("MODIFICAT");

            System.out.println("Original: " + student);
            System.out.println("Clona: " + clona);
        }
    }

    // Citește studenții din fișier și îi salvează într-o listă.
    private static ArrayList<Student> citesteStudenti() throws IOException {
        ArrayList<Student> studenti = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String linie;

        while ((linie = br.readLine()) != null) {
            // Sărim peste liniile goale din fișier.
            if (linie.trim().isEmpty()) {
                continue;
            }

            String[] parts = linie.split(",");

            String nume = parts[0].trim();
            int varsta = Integer.parseInt(parts[1].trim());
            String oras = parts[2].trim();
            String strada = parts[3].trim();

            Adresa adresa = new Adresa(oras, strada);
            Student student = new Student(nume, varsta, adresa);

            studenti.add(student);
        }

        br.close();

        return studenti;
    }

    // Afișează toți studenții, câte unul pe linie.
    private static void afiseazaStudenti(ArrayList<Student> studenti) {
        for (Student student : studenti) {
            System.out.println(student);
        }
    }

    // Caută un student după nume.
    private static Student cautaStudent(ArrayList<Student> studenti, String nume) {
        for (Student student : studenti) {
            if (student.getNume().equals(nume)) {
                return student;
            }
        }

        return null;
    }
}