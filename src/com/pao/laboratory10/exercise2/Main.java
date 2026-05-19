package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Implementează conform Readme.md
        Scanner scanner = new Scanner(System.in);

        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip) — pot exista duplicate de id
        //    Stochează-le toate într-un ArrayList<Tranzactie> (cu duplicate, ordine inserare)
        int n = scanner.nextInt();

        ArrayList<Tranzactie> tranzactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

            Tranzactie tranzactie = new Tranzactie(id, suma, data, tip);

            tranzactii.add(tranzactie);
        }

        // 2. Procesează comenzile din stdin până la EOF:
        while (scanner.hasNext()) {
            String comanda = scanner.next();

            if (comanda.equals("UNIQUE_IDS")) {
                //   UNIQUE_IDS      → LinkedHashSet<Integer> cu id-urile în ordinea primei apariții
                //                     afișează: "IDs unice (N): [1, 2, 3, ...]"
                LinkedHashSet<Integer> idsUnice = new LinkedHashSet<>();

                for (Tranzactie tranzactie : tranzactii) {
                    idsUnice.add(tranzactie.getId());
                }

                System.out.println("IDs unice (" + idsUnice.size() + "): " + idsUnice);
            } else if (comanda.equals("MONTHLY_REPORT")) {
                //   MONTHLY_REPORT  → TreeMap<String, ...> grupat pe yyyy-MM (substring 0-7 din data)
                //                     pentru fiecare lună, sumele CREDIT și DEBIT
                //                     format: "yyyy-MM: CREDIT X.XX RON, DEBIT Y.YY RON"
                TreeMap<String, double[]> raportLunar = new TreeMap<>();

                for (Tranzactie tranzactie : tranzactii) {
                    String luna = tranzactie.getData().substring(0, 7);

                    if (!raportLunar.containsKey(luna)) {
                        raportLunar.put(luna, new double[2]);
                    }

                    double[] sume = raportLunar.get(luna);

                    if (tranzactie.getTip() == TipTranzactie.CREDIT) {
                        sume[0] += tranzactie.getSuma();
                    } else if (tranzactie.getTip() == TipTranzactie.DEBIT) {
                        sume[1] += tranzactie.getSuma();
                    }
                }

                for (Map.Entry<String, double[]> entry : raportLunar.entrySet()) {
                    String luna = entry.getKey();
                    double[] sume = entry.getValue();

                    System.out.printf(
                            "%s: CREDIT %.2f RON, DEBIT %.2f RON%n",
                            luna,
                            sume[0],
                            sume[1]
                    );
                }
            } else if (comanda.equals("TOP")) {
                //   TOP n           → primele n tranzacții după suma descrescătoare (nu modifică lista)
                //                     afișează "Top n:" urmat de n linii
                int topN = scanner.nextInt();

                ArrayList<Tranzactie> copie = new ArrayList<>(tranzactii);

                Collections.sort(
                        copie,
                        Comparator.comparingDouble(Tranzactie::getSuma).reversed()
                );

                System.out.println("Top " + topN + ":");

                int limita = Math.min(topN, copie.size());

                for (int i = 0; i < limita; i++) {
                    System.out.println(copie.get(i));
                }
            } else if (comanda.equals("SORT_ASC")) {
                //   SORT_ASC        → Collections.sort cu suma crescătoare; afișează lista sortată
                Collections.sort(
                        tranzactii,
                        Comparator.comparingDouble(Tranzactie::getSuma)
                );

                for (Tranzactie tranzactie : tranzactii) {
                    System.out.println(tranzactie);
                }
            } else if (comanda.equals("SORT_DESC")) {
                //   SORT_DESC       → Collections.sort cu suma descrescătoare; afișează lista sortată
                Collections.sort(
                        tranzactii,
                        Comparator.comparingDouble(Tranzactie::getSuma).reversed()
                );

                for (Tranzactie tranzactie : tranzactii) {
                    System.out.println(tranzactie);
                }
            } else if (comanda.equals("REVERSE")) {
                //   REVERSE         → Collections.reverse; afișează lista
                Collections.reverse(tranzactii);

                for (Tranzactie tranzactie : tranzactii) {
                    System.out.println(tranzactie);
                }
            } else if (comanda.equals("MIN_MAX")) {
                //   MIN_MAX         → Collections.min/max după suma
                //                     "MIN: [id] data tip: suma RON"
                //                     "MAX: [id] data tip: suma RON"
                Comparator<Tranzactie> comparatorSuma = Comparator.comparingDouble(Tranzactie::getSuma);

                Tranzactie minim = Collections.min(tranzactii, comparatorSuma);
                Tranzactie maxim = Collections.max(tranzactii, comparatorSuma);

                System.out.println("MIN: " + minim);
                System.out.println("MAX: " + maxim);
            } else if (comanda.equals("CME_DEMO")) {
                //   CME_DEMO        → încearcă for(t : lista) lista.remove(t) în try-catch
                //                     afișează "ConcurrentModificationException prins: modificare in iteratie detectata."
                try {
                    for (Tranzactie tranzactie : tranzactii) {
                        tranzactii.remove(tranzactie);
                    }
                } catch (ConcurrentModificationException e) {
                    System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                }
            }
        }

        // Format linie tranzacție: [id] data tip: suma RON
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON
    }
}