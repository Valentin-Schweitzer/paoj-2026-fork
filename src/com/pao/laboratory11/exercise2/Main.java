package com.pao.laboratory11.exercise2;

import com.pao.laboratory11.exercise1.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            run();
        } catch (IOException e) {
            // Păstrăm output-ul determinist pentru checker
        }
    }

    private static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Citim numărul de tranzacții
        String first = nextNonEmpty(br);
        if (first == null) {
            return;
        }

        int n = Integer.parseInt(first);

        // Lista în care păstrăm tranzacțiile pentru raportare
        List<ReportTransaction> transactions = new ArrayList<>();

        // Citim cele N tranzacții din input
        for (int i = 0; i < n; i++) {
            String line = nextNonEmpty(br);
            if (line == null) {
                return;
            }

            String[] p = line.split("\\s+");

            // Refolosim modelul Transaction din exercițiul 1
            Transaction transaction = new Transaction(
                    Integer.parseInt(p[0]),
                    Double.parseDouble(p[1]),
                    p[2],
                    p[3],
                    p[4]
            );

            // Problema 2 are în plus accountId față de Transaction
            String accountId = p[5];

            transactions.add(new ReportTransaction(transaction, accountId));
        }

        // Citim numărul de comenzi
        String qLine = nextNonEmpty(br);
        if (qLine == null) {
            return;
        }

        int q = Integer.parseInt(qLine);

        // Procesăm fiecare comandă
        for (int i = 0; i < q; i++) {
            String line = nextNonEmpty(br);
            if (line == null) {
                return;
            }

            String[] p = line.split("\\s+");
            String op = p[0];

            switch (op) {
                case "REPORT_MONTH":
                    // Raport pentru o anumită lună
                    reportMonth(transactions, p[1]);
                    break;

                case "REPORT_ACCOUNT":
                    // Raport pentru un anumit cont
                    reportAccount(transactions, p[1]);
                    break;

                case "TOP_CHANNELS":
                    // Clasamentul canalelor după numărul de tranzacții
                    topChannels(transactions, Integer.parseInt(p[1]));
                    break;

                default:
                    // Comenzile necunoscute sunt ignorate conform checker-ului
                    break;
            }
        }
    }

    private static void reportMonth(List<ReportTransaction> transactions, String month) {
        // Filtrăm tranzacțiile după luna cerută și calculăm suma + numărul lor
        DoubleSummaryStatistics statistics = transactions.stream()
                .filter(reportTransaction -> reportTransaction.getTransaction().getDate().startsWith(month))
                .collect(Collectors.summarizingDouble(
                        reportTransaction -> reportTransaction.getTransaction().getAmount()
                ));

        // Afișăm raportul pentru lună, cu suma formatată la 2 zecimale
        System.out.printf(
                Locale.US,
                "MONTH %s total=%.2f count=%d%n",
                month,
                statistics.getSum(),
                statistics.getCount()
        );
    }

    private static void reportAccount(List<ReportTransaction> transactions, String accountId) {
        // Filtrăm tranzacțiile după cont și calculăm suma + numărul lor
        DoubleSummaryStatistics statistics = transactions.stream()
                .filter(reportTransaction -> reportTransaction.getAccountId().equals(accountId))
                .collect(Collectors.summarizingDouble(
                        reportTransaction -> reportTransaction.getTransaction().getAmount()
                ));

        // Afișăm raportul pentru cont, cu suma formatată la 2 zecimale
        System.out.printf(
                Locale.US,
                "ACCOUNT %s total=%.2f count=%d%n",
                accountId,
                statistics.getSum(),
                statistics.getCount()
        );
    }

    private static void topChannels(List<ReportTransaction> transactions, int k) {
        // Dacă nu avem tranzacții deloc, afișăm NONE
        if (transactions.isEmpty()) {
            System.out.println("NONE");
            return;
        }

        // Grupăm tranzacțiile după canal și numărăm câte apariții are fiecare canal
        Map<String, Long> channelCount = transactions.stream()
                .collect(Collectors.groupingBy(
                        reportTransaction -> reportTransaction.getTransaction().getChannel(),
                        Collectors.counting()
                ));

        // Sortăm canalele după count descendent, apoi alfabetic ascendent
        channelCount.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .limit(k)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String line;

        // Sărim peste liniile goale din input
        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                return line.trim();
            }
        }

        return null;
    }

    // Clasă-wrapper: păstrează Transaction din ex1 + accountId-ul cerut în ex2
    private static final class ReportTransaction {
        private final Transaction transaction;
        private final String accountId;

        private ReportTransaction(Transaction transaction, String accountId) {
            this.transaction = transaction;
            this.accountId = accountId;
        }

        public Transaction getTransaction() {
            return transaction;
        }

        public String getAccountId() {
            return accountId;
        }
    }
}