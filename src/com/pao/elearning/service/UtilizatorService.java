package com.pao.elearning.service;

import com.pao.elearning.model.Cursant;
import com.pao.elearning.model.Lector;
import com.pao.elearning.model.Utilizator;

import java.util.*;

public class UtilizatorService {
    private static UtilizatorService instance;

    private final List<Utilizator> utilizatori;
    private final Map<Integer, Utilizator> utilizatoriDupaId;
    private final Map<String, Utilizator> utilizatoriDupaEmail;

    private UtilizatorService() {
        this.utilizatori = new ArrayList<>();
        this.utilizatoriDupaId = new HashMap<>();
        this.utilizatoriDupaEmail = new HashMap<>();
    }

    public static UtilizatorService getInstance() {
        if (instance == null) {
            instance = new UtilizatorService();
        }
        return instance;
    }

    public void adaugaCursant(Cursant cursant) {
        adaugaUtilizator(cursant);
    }

    public void adaugaLector(Lector lector) {
        adaugaUtilizator(lector);
    }

    private void adaugaUtilizator(Utilizator utilizator) {
        utilizatori.add(utilizator);

        // Utilizatorii sunt indexați după id și email.
        utilizatoriDupaId.put(utilizator.getId(), utilizator);
        utilizatoriDupaEmail.put(utilizator.getEmail(), utilizator);
    }

    public Utilizator cautaUtilizatorDupaId(int id) {
        return utilizatoriDupaId.get(id);
    }

    public Utilizator cautaUtilizatorDupaEmail(String email) {
        return utilizatoriDupaEmail.get(email);
    }

    public List<Utilizator> listeazaTotiUtilizatorii() {
        return new ArrayList<>(utilizatori);
    }

    public List<Cursant> listeazaCursanti() {
        List<Cursant> cursanti = new ArrayList<>();

        // Selectează doar utilizatorii de tip Cursant.
        for (Utilizator utilizator : utilizatori) {
            if (utilizator instanceof Cursant cursant) {
                cursanti.add(cursant);
            }
        }

        return cursanti;
    }

    public List<Lector> listeazaLectori() {
        List<Lector> lectori = new ArrayList<>();

        // Selectează doar utilizatorii de tip Lector.
        for (Utilizator utilizator : utilizatori) {
            if (utilizator instanceof Lector lector) {
                lectori.add(lector);
            }
        }

        return lectori;
    }

    public void stergeUtilizator(int id) {
        Utilizator utilizator = utilizatoriDupaId.get(id);

        if (utilizator != null) {
            utilizatori.remove(utilizator);
            utilizatoriDupaId.remove(id);
            utilizatoriDupaEmail.remove(utilizator.getEmail());
        }
    }
}