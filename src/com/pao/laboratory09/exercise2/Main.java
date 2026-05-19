package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip)
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        File folder = new File("output");
        folder.mkdirs();

        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream (format binar, RECORD_SIZE=32 bytes/înreg.)
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                TipTranzactie tip = TipTranzactie.valueOf(scanner.next());

                //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
                byte[] idBytes = ByteBuffer.allocate(4)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putInt(id)
                        .array();

                out.write(idBytes);

                //    - bytes 4-11:  suma (double, little-endian via ByteBuffer)
                byte[] sumaBytes = ByteBuffer.allocate(8)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putDouble(suma)
                        .array();

                out.write(sumaBytes);

                //    - bytes 12-21: data (String, 10 chars ASCII, paddat cu spații la dreapta)
                byte[] dataBytes = new byte[10];
                Arrays.fill(dataBytes, (byte) ' ');

                byte[] dataRaw = data.getBytes(StandardCharsets.US_ASCII);
                System.arraycopy(dataRaw, 0, dataBytes, 0, Math.min(dataRaw.length, 10));

                out.write(dataBytes);

                //    - byte 22:     tip (0=CREDIT, 1=DEBIT)
                out.writeByte(tipToByte(tip));

                //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
                out.writeByte(0);

                //    - bytes 24-31: padding (zerouri)
                out.write(new byte[8]);
            }
        }

        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile:
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String comanda = scanner.next();

                if (comanda.equals("READ")) {
                    //    - READ idx       → seek(idx * RECORD_SIZE), citește și afișează înregistrarea
                    int idx = scanner.nextInt();

                    readAndPrintRecord(raf, idx);
                } else if (comanda.equals("UPDATE")) {
                    //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), scrie noul status (0/1/2)
                    //                       afișează "Updated [idx]: STATUS"
                    int idx = scanner.nextInt();
                    String status = scanner.next();

                    raf.seek((long) idx * RECORD_SIZE + 23);
                    raf.write(statusToByte(status));

                    System.out.println("Updated [" + idx + "]: " + status);
                } else if (comanda.equals("PRINT_ALL")) {
                    //    - PRINT_ALL      → citește și afișează toate înregistrările
                    for (int i = 0; i < n; i++) {
                        readAndPrintRecord(raf, i);
                    }
                }
            }
        }

        // Format linie output:
        //   [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>
    }

    // Citește și afișează o înregistrare de la indexul dat.
    private static void readAndPrintRecord(RandomAccessFile raf, int idx) throws IOException {
        byte[] record = new byte[RECORD_SIZE];

        raf.seek((long) idx * RECORD_SIZE);
        raf.readFully(record);

        ByteBuffer buffer = ByteBuffer.wrap(record)
                .order(ByteOrder.LITTLE_ENDIAN);

        int id = buffer.getInt();
        double suma = buffer.getDouble();

        String data = new String(record, 12, 10, StandardCharsets.US_ASCII).trim();

        TipTranzactie tip = byteToTip(record[22]);
        String status = byteToStatus(record[23]);

        System.out.printf("[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n",
                idx, id, data, tip, suma, status);
    }

    // Transformă tipul tranzacției în byte.
    private static byte tipToByte(TipTranzactie tip) {
        if (tip == TipTranzactie.CREDIT) {
            return 0;
        }

        return 1;
    }

    // Transformă byte-ul citit din fișier în tipul tranzacției.
    private static TipTranzactie byteToTip(byte value) {
        if (value == 0) {
            return TipTranzactie.CREDIT;
        }

        return TipTranzactie.DEBIT;
    }

    // Transformă statusul primit ca text în byte.
    private static byte statusToByte(String status) {
        if (status.equals("PENDING")) {
            return 0;
        } else if (status.equals("PROCESSED")) {
            return 1;
        }

        return 2;
    }

    // Transformă byte-ul citit din fișier în status text.
    private static String byteToStatus(byte value) {
        if (value == 0) {
            return "PENDING";
        } else if (value == 1) {
            return "PROCESSED";
        }

        return "REJECTED";
    }
}