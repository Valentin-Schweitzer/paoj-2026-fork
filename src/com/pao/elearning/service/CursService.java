package com.pao.elearning.service;

import com.pao.elearning.exception.CursNegasitException;
import com.pao.elearning.model.Curs;

import java.util.*;

public class CursService {
    private static CursService instance;

    private final List<Curs> cursuri;
    private final Map<Integer, Curs> cursuriDupaId;
    private final Set<Curs> cursuriSortateDupaTitlu;

    // Comparator folosit pentru ordonarea cursurilor după titlu și id.
    private final Comparator<Curs> comparatorDupaTitlu =
            Comparator.comparing(Curs::getTitlu).thenComparingInt(Curs::getId);

    private CursService() {
        this.cursuri = new ArrayList<>();
        this.cursuriDupaId = new HashMap<>();
        this.cursuriSortateDupaTitlu = new TreeSet<>(comparatorDupaTitlu);
    }

    public static CursService getInstance() {
        if (instance == null) {
            instance = new CursService();
        }
        return instance;
    }

    public void adaugaCurs(Curs curs) {
        cursuri.add(curs);
        cursuriDupaId.put(curs.getId(), curs);
        cursuriSortateDupaTitlu.add(curs);
    }

    public Curs cautaCursDupaId(int id) throws CursNegasitException {
        Curs curs = cursuriDupaId.get(id);

        if (curs == null) {
            throw new CursNegasitException("Nu exista niciun curs cu id-ul " + id + ".");
        }

        return curs;
    }

    public List<Curs> cautaCursuriDupaTitlu(String titlu) {
        List<Curs> rezultat = new ArrayList<>();

        // Căutarea se face fără a ține cont de litere mari sau mici.
        for (Curs curs : cursuri) {
            if (curs.getTitlu().toLowerCase().contains(titlu.toLowerCase())) {
                rezultat.add(curs);
            }
        }

        return rezultat;
    }

    public List<Curs> listeazaToateCursurile() {
        return new ArrayList<>(cursuri);
    }

    public Set<Curs> listeazaCursuriSortateDupaTitlu() {
        Set<Curs> rezultat = new TreeSet<>(comparatorDupaTitlu);
        rezultat.addAll(cursuriSortateDupaTitlu);
        return rezultat;
    }

    public void stergeCurs(int id) throws CursNegasitException {
        Curs curs = cautaCursDupaId(id);

        cursuri.remove(curs);
        cursuriDupaId.remove(id);
        cursuriSortateDupaTitlu.remove(curs);
    }
}