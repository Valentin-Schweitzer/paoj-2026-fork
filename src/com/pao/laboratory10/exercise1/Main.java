package com.pao.laboratory10.exercise1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Implementează conform Readme.md
        //
        // Folosește LinkedList<Tranzactie> ca structură internă.
        LinkedList<Tranzactie> coada = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        // Citește comenzi din stdin până la EOF:
        while (scanner.hasNext()) {
            String comanda = scanner.next();

            if (comanda.equals("ENQUEUE")) {
                //   ENQUEUE id suma data tip   → addLast  (niciun output)
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

                Tranzactie tranzactie = new Tranzactie(id, suma, data, tip);

                coada.addLast(tranzactie);
            } else if (comanda.equals("DEQUEUE")) {
                //   DEQUEUE                    → removeFirst sau "Coada goala."
                //                                format: "Procesat: [id] data tip: suma RON"
                if (coada.isEmpty()) {
                    System.out.println("Coada goala.");
                } else {
                    Tranzactie tranzactie = coada.removeFirst();
                    System.out.println("Procesat: " + tranzactie);
                }
            } else if (comanda.equals("PUSH")) {
                //   PUSH id suma data tip      → addFirst  (niciun output)
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

                Tranzactie tranzactie = new Tranzactie(id, suma, data, tip);

                coada.addFirst(tranzactie);
            } else if (comanda.equals("POP")) {
                //   POP                        → removeFirst sau "Coada goala."
                //                                format: "Extras: [id] data tip: suma RON"
                if (coada.isEmpty()) {
                    System.out.println("Coada goala.");
                } else {
                    Tranzactie tranzactie = coada.removeFirst();
                    System.out.println("Extras: " + tranzactie);
                }
            } else if (comanda.equals("REMOVE_DEBIT")) {
                //   REMOVE_DEBIT               → Iterator.remove() pe toate DEBIT
                //                                afișează "Eliminat N tranzactii DEBIT."
                int eliminate = 0;

                Iterator<Tranzactie> iterator = coada.iterator();

                while (iterator.hasNext()) {
                    Tranzactie tranzactie = iterator.next();

                    if (tranzactie.getTip() == TipTranzactie.DEBIT) {
                        iterator.remove();
                        eliminate++;
                    }
                }

                System.out.println("Eliminat " + eliminate + " tranzactii DEBIT.");
            } else if (comanda.equals("REMOVE_BELOW")) {
                //   REMOVE_BELOW threshold     → Iterator.remove() pe suma < threshold
                //                                afișează "Eliminat N tranzactii sub threshold RON."
                double threshold = scanner.nextDouble();

                int eliminate = 0;

                Iterator<Tranzactie> iterator = coada.iterator();

                while (iterator.hasNext()) {
                    Tranzactie tranzactie = iterator.next();

                    if (tranzactie.getSuma() < threshold) {
                        iterator.remove();
                        eliminate++;
                    }
                }

                System.out.printf("Eliminat %d tranzactii sub %.2f RON.%n", eliminate, threshold);
            } else if (comanda.equals("PRINT")) {
                //   PRINT                      → afișează toate, câte una pe linie
                for (Tranzactie tranzactie : coada) {
                    System.out.println(tranzactie);
                }
            } else if (comanda.equals("SIZE")) {
                //   SIZE                       → "Dimensiune coada: N"
                System.out.println("Dimensiune coada: " + coada.size());
            }
        }

        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-10 CREDIT: 500.00 RON
    }
}