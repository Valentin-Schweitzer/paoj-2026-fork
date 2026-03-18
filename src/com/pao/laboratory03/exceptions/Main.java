package com.pao.laboratory03.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercițiul 3 — Excepții (checked, unchecked, custom)
 *
 * PASUL 3 — În acest Main.java, implementează și demonstrează:
 *
 *   a) UNCHECKED EXCEPTIONS — NullPointerException, ArrayIndexOutOfBoundsException:
 *      - Creează o metodă riskyMethod() care aruncă NullPointerException
 *      - Prinde-o cu try-catch, afișează mesajul erorii
 *      - Adaugă un bloc finally care se execută mereu
 *
 *   b) CUSTOM EXCEPTIONS — InvalidAgeException, DuplicateEntryException:
 *      - Creează o metodă validateAge(int age) care aruncă InvalidAgeException
 *        dacă age < 0 sau age > 150
 *      - Creează o metodă addToList(List<String> list, String name) care aruncă
 *        DuplicateEntryException dacă name există deja în listă
 *      - Demonstrează ambele cu try-catch
 *
 *   c) MULTI-CATCH:
 *      - Prinde InvalidAgeException | DuplicateEntryException într-un singur catch
 *
 *   d) CATCH ORDERING:
 *      - Demonstrează că prinderea specifică (InvalidAgeException) trebuie
 *        să fie ÎNAINTE de cea generală (RuntimeException)
 *
 *   e) THROW vs THROWS:
 *      - Creează o metodă cu semnătura: void process(int age) throws InvalidAgeException
 *      - Apeleaz-o din main cu try-catch
 *
 * Output așteptat:
 *
 * === a) Unchecked — NullPointerException ===
 * Prins: Cannot invoke "String.length()" because "s" is null
 * Finally se execută mereu!
 *
 * === b) Custom exceptions ===
 * InvalidAgeException: Vârsta -5 nu este validă (0-150)
 * DuplicateEntryException: 'Ana' există deja în listă
 *
 * === c) Multi-catch ===
 * Excepție prinsă: Vârsta 200 nu este validă (0-150)
 *
 * === d) Catch ordering (specific → general) ===
 * InvalidAgeException prinsă specific: Vârsta -1 nu este validă (0-150)
 *
 * === e) Throw vs throws ===
 * Metoda process() a aruncat: Vârsta 999 nu este validă (0-150)
 */

// ==================================================
// PASUL 3 — În acest Main.java, implementează și demonstrează:
// ==================================================

public class Main {
    public static void main(String[] args) {
        // TODO: implementează pașii de mai sus
        // Hint: creează mai întâi InvalidAgeException.java și DuplicateEntryException.java

        // a) UNCHECKED EXCEPTIONS — NullPointerException, ArrayIndexOutOfBoundsException:
        System.out.println("=== a) Unchecked — NullPointerException ===");
        try {
            riskyMethod();
        } catch (NullPointerException e) {
            System.out.println("Prins: " + e.getMessage());
        } finally {
            System.out.println("Finally se execută mereu!");
        }

        System.out.println();


        // b) CUSTOM EXCEPTIONS — InvalidAgeException, DuplicateEntryException:
        System.out.println("=== b) Custom exceptions ===");
        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("InvalidAgeException: " + e.getMessage());
        }

        List<String> names = new ArrayList<>();
        names.add("Ana");

        try {
            addToList(names, "Ana");
        } catch (DuplicateEntryException e) {
            System.out.println("DuplicateEntryException: " + e.getMessage());
        }

        System.out.println();


        // c) MULTI-CATCH:
        System.out.println("=== c) Multi-catch ===");
        try {
            validateAge(200);
            addToList(names, "Ana");
        } catch (InvalidAgeException | DuplicateEntryException e) {
            System.out.println("Excepție prinsă: " + e.getMessage());
        }

        System.out.println();


        // d) CATCH ORDERING:
        System.out.println("=== d) Catch ordering (specific → general) ===");
        try {
            validateAge(-1);
        } catch (InvalidAgeException e) {
            System.out.println("InvalidAgeException prinsă specific: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException prinsă general: " + e.getMessage());
        }

        System.out.println();


        // e) THROW vs THROWS:
        System.out.println("=== e) Throw vs throws ===");
        try {
            process(999);
        } catch (InvalidAgeException e) {
            System.out.println("Metoda process() a aruncat: " + e.getMessage());
        }
    }

    // Folosită la subpunctul a)
    // Metoda provoacă intenționat un NullPointerException
    public static void riskyMethod() {
        String s = null;
        System.out.println(s.length());
    }

    // Folosită la subpunctele b), c), d) și e)
    // Verifică dacă vârsta este validă; dacă nu, aruncă InvalidAgeException
    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Vârsta " + age + " nu este validă (0-150)");
        }
    }

    // Folosită la subpunctele b) și c)
    // Adaugă un nume în listă, dar aruncă DuplicateEntryException dacă există deja
    public static void addToList(List<String> list, String name) {
        if (list.contains(name)) {
            throw new DuplicateEntryException("'" + name + "' există deja în listă");
        }
        list.add(name);
    }

    // Folosită la subpunctul e)
    // Exemplu pentru diferența dintre throw și throws:
    // metoda declară cu throws că poate arunca excepția,
    // iar în interior se apelează validateAge() care dă efectiv throw
    public static void process(int age) throws InvalidAgeException {
        validateAge(age);
    }
}

