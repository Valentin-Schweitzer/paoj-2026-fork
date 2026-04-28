package com.pao.elearning.service;

import com.pao.elearning.model.Curs;
import com.pao.elearning.model.Cursant;
import com.pao.elearning.model.Inscriere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InscriereService {
    private static InscriereService instance;

    private final List<Inscriere> inscrieri;

    // Grupează înscrierile după id-ul cursantului.
    private final Map<Integer, List<Inscriere>> inscrieriDupaCursantId;

    private InscriereService() {
        this.inscrieri = new ArrayList<>();
        this.inscrieriDupaCursantId = new HashMap<>();
    }

    public static InscriereService getInstance() {
        if (instance == null) {
            instance = new InscriereService();
        }
        return instance;
    }

    public Inscriere inscrieCursantLaCurs(int id, Cursant cursant, Curs curs) {
        Inscriere inscriere = new Inscriere(id, cursant, curs);

        inscrieri.add(inscriere);

        // Adaugă înscrierea în lista asociată cursantului.
        inscrieriDupaCursantId
                .computeIfAbsent(cursant.getId(), k -> new ArrayList<>())
                .add(inscriere);

        return inscriere;
    }

    public List<Inscriere> listeazaToateInscrierile() {
        return new ArrayList<>(inscrieri);
    }

    public List<Inscriere> listeazaInscrieriPentruCursant(Cursant cursant) {
        return new ArrayList<>(
                inscrieriDupaCursantId.getOrDefault(cursant.getId(), new ArrayList<>())
        );
    }

    public List<Curs> listeazaCursurileUnuiCursant(Cursant cursant) {
        List<Curs> cursuri = new ArrayList<>();

        // Extrage cursurile din înscrierile cursantului.
        for (Inscriere inscriere : listeazaInscrieriPentruCursant(cursant)) {
            cursuri.add(inscriere.getCurs());
        }

        return cursuri;
    }

    public void marcheazaCursFinalizat(Cursant cursant, Curs curs) {
        for (Inscriere inscriere : inscrieri) {
            if (inscriere.getCursant().equals(cursant) && inscriere.getCurs().equals(curs)) {
                inscriere.setFinalizata(true);
                return;
            }
        }
    }
}