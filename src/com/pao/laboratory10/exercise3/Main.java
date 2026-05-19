package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Vezi Readme.md pentru cerințe
        List<Tranzactie> tranzactii = Arrays.asList(
                new Tranzactie(1, 1500.00, "2024-01-15", TipTranzactie.CREDIT, "CONT_A"),
                new Tranzactie(2, 750.50, "2024-01-22", TipTranzactie.DEBIT, "CONT_B"),
                new Tranzactie(3, 200.00, "2024-02-05", TipTranzactie.CREDIT, "CONT_A"),
                new Tranzactie(4, 1200.00, "2024-02-18", TipTranzactie.DEBIT, "CONT_C"),
                new Tranzactie(5, 3000.00, "2024-03-01", TipTranzactie.CREDIT, "CONT_D"),
                new Tranzactie(6, 450.75, "2024-03-07", TipTranzactie.DEBIT, "CONT_B"),
                new Tranzactie(7, 900.00, "2024-01-30", TipTranzactie.CREDIT, "CONT_A"),
                new Tranzactie(8, 100.00, "2024-02-12", TipTranzactie.DEBIT, "CONT_E"),
                new Tranzactie(9, 650.25, "2024-03-20", TipTranzactie.CREDIT, "CONT_C"),
                new Tranzactie(10, 80.00, "2024-02-25", TipTranzactie.DEBIT, "CONT_D")
        );

        // 1. filter(tip == CREDIT) - Lista tuturor tranzacțiilor CREDIT
        System.out.println("1. Tranzactii CREDIT:");

        tranzactii.stream()
                .filter(tranzactie -> tranzactie.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        System.out.println();

        // 2. mapToDouble(suma).sum() - Total procesat
        System.out.println("2. Total procesat:");

        double totalProcesat = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .sum();

        System.out.printf("Total procesat: %.2f RON%n", totalProcesat);

        System.out.println();

        // 3. Collectors.groupingBy(luna, summingDouble(suma)) - Sume per lună
        System.out.println("3. Total per luna:");

        Map<String, Double> totalPeLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        tranzactie -> tranzactie.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));

        totalPeLuna.forEach((luna, total) ->
                System.out.printf("%s: %.2f RON%n", luna, total)
        );

        System.out.println();

        // 4. sorted(comparingDouble.reversed()).limit(3) - Top 3 tranzacții
        System.out.println("4. Top 3 tranzactii:");

        tranzactii.stream()
                .sorted(Comparator.comparingDouble(Tranzactie::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println();

        // 5. map(contSursa).distinct().collect(toList()) - Conturi sursă unice
        System.out.println("5. Conturi sursa unice:");

        List<String> conturiSursaUnice = tranzactii.stream()
                .map(Tranzactie::getContSursa)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Conturi sursa unice: " + conturiSursaUnice);

        System.out.println();

        // 6. mapToDouble(suma).average() - Suma medie
        System.out.println("6. Suma medie:");

        double sumaMedie = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .average()
                .orElse(0.0);

        System.out.printf("Suma medie: %.2f RON%n", sumaMedie);

        System.out.println();

        // 7. Collectors.groupingBy(luna) cu format extras
        System.out.println("7. Extrase de cont lunare:");

        Map<String, List<Tranzactie>> tranzactiiPeLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        tranzactie -> tranzactie.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ));

        tranzactiiPeLuna.forEach((luna, listaTranzactii) -> {
            double totalLuna = listaTranzactii.stream()
                    .mapToDouble(Tranzactie::getSuma)
                    .sum();

            System.out.printf(
                    "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                    luna,
                    listaTranzactii.size(),
                    totalLuna
            );
        });
    }
}