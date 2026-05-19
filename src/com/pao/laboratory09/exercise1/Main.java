package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data contSursa contDestinatie tip)
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        List<Tranzactie> tranzactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            String contSursa = scanner.next();
            String contDestinatie = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

            Tranzactie tranzactie = new Tranzactie(
                    id,
                    suma,
                    data,
                    contSursa,
                    contDestinatie,
                    tip
            );

            // 2. Setează câmpul note = "procesat" pe fiecare tranzacție înainte de serializare
            tranzactie.note = "procesat";

            tranzactii.add(tranzactie);
        }

        File folder = new File("output");
        folder.mkdirs();

        // 3. Serializează lista de tranzacții în OUTPUT_FILE cu ObjectOutputStream (try-with-resources)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            out.writeObject(tranzactii);
        }

        List<Tranzactie> tranzactiiCitite;

        // 4. Deserializează lista din OUTPUT_FILE cu ObjectInputStream (try-with-resources)
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            tranzactiiCitite = (List<Tranzactie>) in.readObject();
        }

        // 5. Procesează comenzile din stdin până la EOF:
        while (scanner.hasNext()) {
            String comanda = scanner.next();

            if (comanda.equals("LIST")) {
                //    - LIST          → afișează toate tranzacțiile, câte una pe linie
                for (Tranzactie tranzactie : tranzactiiCitite) {
                    tranzactie.afisare();
                }
            } else if (comanda.equals("FILTER")) {
                //    - FILTER yyyy-MM → afișează tranzacțiile cu data care începe cu yyyy-MM
                //                       sau "Niciun rezultat." dacă nu există
                String luna = scanner.next();

                boolean gasit = false;

                for (Tranzactie tranzactie : tranzactiiCitite) {
                    if (tranzactie.data.startsWith(luna)) {
                        tranzactie.afisare();
                        gasit = true;
                    }
                }

                if (!gasit) {
                    System.out.println("Niciun rezultat.");
                }
            } else if (comanda.equals("NOTE")) {
                //    - NOTE id        → afișează "NOTE[id]: <valoarea câmpului note>"
                //                       sau "NOTE[id]: not found" dacă id-ul nu există
                int idCautat = scanner.nextInt();

                Tranzactie tranzactieGasita = null;

                for (Tranzactie tranzactie : tranzactiiCitite) {
                    if (tranzactie.id == idCautat) {
                        tranzactieGasita = tranzactie;
                        break;
                    }
                }

                if (tranzactieGasita == null) {
                    System.out.println("NOTE[" + idCautat + "]: not found");
                } else {
                    System.out.println("NOTE[" + idCautat + "]: " + tranzactieGasita.note);
                }
            }
        }

        // Format linie tranzacție:
        //   [id] data tip: suma RON | contSursa -> contDestinatie
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON | RO01SRC1 -> RO01DST1
    }
}