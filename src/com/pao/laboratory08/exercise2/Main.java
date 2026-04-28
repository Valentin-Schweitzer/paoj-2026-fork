package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    private static final String OUTPUT_PATH = "src/com/pao/laboratory08/exercise2/5" +
            "rezultate.txt";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md

        // 1. Citește studenții din FILE_PATH cu BufferedReader
        ArrayList<Student> studenti = citesteStudenti();

        // 2. Citește pragul de vârstă din stdin cu Scanner
        Scanner scanner = new Scanner(System.in);
        int prag = scanner.nextInt();

        // 3. Filtrează studenții cu varsta >= prag
        ArrayList<Student> studentiFiltrati = filtreazaStudenti(studenti, prag);

        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        scrieStudenti(studentiFiltrati);

        // 5. Afișează sumarul la consolă
        afiseazaRezultat(studentiFiltrati, prag);
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

    // Returnează doar studenții care au vârsta mai mare sau egală cu pragul.
    private static ArrayList<Student> filtreazaStudenti(ArrayList<Student> studenti, int prag) {
        ArrayList<Student> studentiFiltrati = new ArrayList<>();

        for (Student student : studenti) {
            if (student.getVarsta() >= prag) {
                studentiFiltrati.add(student);
            }
        }

        return studentiFiltrati;
    }

    // Scrie studenții filtrați în fișierul rezultate.txt.
    private static void scrieStudenti(ArrayList<Student> studenti) throws IOException {
        BufferedWriter fout = new BufferedWriter(new FileWriter(OUTPUT_PATH));

        for (Student student : studenti) {
            fout.write(student.toString());
            fout.newLine();
        }

        fout.close();
    }

    // Afișează sumarul și studenții filtrați la consolă.
    private static void afiseazaRezultat(ArrayList<Student> studentiFiltrati, int prag) {
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + studentiFiltrati.size() + " studenti");
        System.out.println();

        for (Student student : studentiFiltrati) {
            System.out.println(student);
        }

        System.out.println();
        System.out.println("Scris in: " + OUTPUT_PATH);
    }
}