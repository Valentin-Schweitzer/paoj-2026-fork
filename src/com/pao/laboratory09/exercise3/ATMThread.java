package com.pao.laboratory09.exercise3;

// Reprezintă un ATM care trimite tranzacții către coada partajată
public class ATMThread extends Thread {
    private static int urmatorulId = 1;

    private final int atmId;
    private final CoadaTranzactii coada;

    // Construiește un ATM cu id-ul său și coada în care trimite tranzacții
    public ATMThread(int atmId, CoadaTranzactii coada) {
        this.atmId = atmId;
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                int id = genereazaId();
                double suma = 100 + atmId * 50 + i * 25;
                String data = "2024-01-" + String.format("%02d", id);

                Tranzactie tranzactie = new Tranzactie(id, suma, data);

                System.out.printf("[ATM-%d] trimite: Tranzactie #%d %.2f RON%n",
                        atmId, tranzactie.id, tranzactie.suma);

                coada.adauga(tranzactie, atmId);

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("[ATM-" + atmId + "] intrerupt.");
        }
    }

    // Generează un id unic pentru fiecare tranzacție
    private static synchronized int genereazaId() {
        return urmatorulId++;
    }
}