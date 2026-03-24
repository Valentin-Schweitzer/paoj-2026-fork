package com.pao.laboratory05.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati;
    private AuditEntry[] auditLog;

    // Constructor privat
    private AngajatService() {
        this.angajati = new Angajat[0];
        this.auditLog = new AuditEntry[0];
    }

    // Holder intern pentru Singleton
    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    // Metodă prin care obținem singura instanță
    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    // Adaugă o intrare nouă în audit log
    private void logAction(String action, String target) {
        AuditEntry entry = new AuditEntry(
                action,
                target,
                LocalDateTime.now().toString()
        );

        AuditEntry[] auditNou = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, auditNou, 0, auditLog.length);
        auditNou[auditLog.length] = entry;
        auditLog = auditNou;
    }

    // Adaugă un angajat în vector
    // resize + adaugă + printează confirmare
    public void addAngajat(Angajat a) {
        Angajat[] angajatiNoi = new Angajat[angajati.length + 1];

        System.arraycopy(angajati, 0, angajatiNoi, 0, angajati.length);
        angajatiNoi[angajati.length] = a;

        angajati = angajatiNoi;

        // salvăm în audit faptul că am adăugat un angajat
        logAction("ADD", a.getNume());

        System.out.println("Angajat adăugat: " + a.getNume());
    }

    // Afișează toți angajații în ordinea din vector
    public void printAll() {
        for (Angajat angajat : angajati) {
            System.out.println(angajat);
        }
    }

    // Afișează angajații sortați descrescător după salariu
    public void listBySalary() {
        Angajat[] copie = angajati.clone();
        Arrays.sort(copie);

        for (Angajat angajat : copie) {
            System.out.println(angajat);
        }
    }

    // Afișează angajații din departamentul dat
    // și salvează acțiunea în audit
    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);

        boolean gasit = false;

        for (Angajat angajat : angajati) {
            if (angajat.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(angajat);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }

    // Afișează toate intrările din audit log
    public void printAuditLog() {
        for (AuditEntry entry : auditLog) {
            System.out.println(entry);
        }
    }
}