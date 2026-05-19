package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Creează coada partajată dintre ATM-uri și procesator
        CoadaTranzactii coada = new CoadaTranzactii();

        // Creează cele 3 ATM-uri producătoare
        ATMThread atm1 = new ATMThread(1, coada);
        ATMThread atm2 = new ATMThread(2, coada);
        ATMThread atm3 = new ATMThread(3, coada);

        // Creează procesatorul consumator
        ProcessorThread processorThread = new ProcessorThread(coada);
        Thread processor = new Thread(processorThread);

        // Pornește toate firele.
        atm1.start();
        atm2.start();
        atm3.start();
        processor.start();

        // Așteaptă terminarea celor 3 ATM-uri
        atm1.join();
        atm2.join();
        atm3.join();

        // Oprește procesatorul după ce ATM-urile au terminat de produs
        processorThread.activ = false;

        // Trezește procesatorul dacă a rămas blocat așteptând în coadă
        coada.trezesteFirele();

        // Așteaptă terminarea procesatorului
        processor.join();

        // Afișează totalul tranzacțiilor procesate
        System.out.println("Toate tranzactiile procesate. Total: " + processorThread.getTotalProcesate());
    }
}