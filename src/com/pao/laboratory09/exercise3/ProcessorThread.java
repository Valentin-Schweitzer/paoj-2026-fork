package com.pao.laboratory09.exercise3;

// Reprezintă procesatorul care extrage tranzacții și generează facturi
public class ProcessorThread implements Runnable {
    volatile boolean activ = true;

    private final CoadaTranzactii coada;
    private int totalProcesate = 0;

    // Construiește procesatorul cu coada din care va extrage tranzacții
    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Tranzactie tranzactie = coada.extrage(this);

                if (tranzactie == null) {
                    break;
                }

                Thread.sleep(80);

                System.out.printf("[Processor] Factura #%d - %.2f RON | %s%n",
                        tranzactie.id, tranzactie.suma, tranzactie.data);

                totalProcesate++;
            }
        } catch (InterruptedException e) {
            System.out.println("[Processor] intrerupt.");
        }
    }

    // Returnează numărul total de tranzacții procesate
    public int getTotalProcesate() {
        return totalProcesate;
    }
}