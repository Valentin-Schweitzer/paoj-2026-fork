package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

// Reprezintă banda partajată dintre ATM-uri și procesator
public class CoadaTranzactii {
    private static final int CAPACITATE = 5;

    private final Queue<Tranzactie> coada = new LinkedList<>();

    // Adaugă o tranzacție în coadă, așteptând dacă banda este plină
    public synchronized void adauga(Tranzactie tranzactie, int atmId) throws InterruptedException {
        while (coada.size() == CAPACITATE) {
            System.out.println("[ATM-" + atmId + "] astept loc...");
            wait();
        }

        coada.add(tranzactie);

        notifyAll();
    }

    // Extrage o tranzacție din coadă, așteptând dacă banda este goală
    public synchronized Tranzactie extrage(ProcessorThread processor) throws InterruptedException {
        while (coada.isEmpty() && processor.activ) {
            wait();
        }

        if (coada.isEmpty()) {
            return null;
        }

        Tranzactie tranzactie = coada.remove();

        notifyAll();

        return tranzactie;
    }

    // Trezește firele care așteaptă pe coadă
    public synchronized void trezesteFirele() {
        notifyAll();
    }
}