package com.pao.laboratory05.angajati;

import java.util.Scanner;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();

        while (true) {
            System.out.println("===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            // citește opțiunea și execută acțiunea
            int optiune = Integer.parseInt(scanner.nextLine());

            if (optiune == 1) {
                System.out.print("Nume: ");
                String nume = scanner.nextLine();

                System.out.print("Departament (nume): ");
                String numeDepartament = scanner.nextLine();

                System.out.print("Departament (locatie): ");
                String locatieDepartament = scanner.nextLine();

                System.out.print("Salariu: ");
                double salariu = Double.parseDouble(scanner.nextLine());

                Departament departament = new Departament(numeDepartament, locatieDepartament);
                Angajat angajat = new Angajat(nume, departament, salariu);

                service.addAngajat(angajat);
            } else if (optiune == 2) {
                service.listBySalary();
            } else if (optiune == 3) {
                System.out.print("Departament: ");
                String numeDept = scanner.nextLine();

                service.findByDepartament(numeDept);
            } else if (optiune == 0) {
                System.out.println("La revedere!");
                break;
            } else {
                System.out.println("Opțiune invalidă.");
            }

            System.out.println();
        }

        scanner.close();
    }
}