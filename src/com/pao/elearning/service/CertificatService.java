package com.pao.elearning.service;

import com.pao.elearning.model.Certificat;
import com.pao.elearning.model.Curs;
import com.pao.elearning.model.Cursant;

import java.util.ArrayList;
import java.util.List;

public class CertificatService {
    private static CertificatService instance;

    // Lista în care sunt salvate certificatele generate.
    private final List<Certificat> certificate;

    private CertificatService() {
        this.certificate = new ArrayList<>();
    }

    public static CertificatService getInstance() {
        if (instance == null) {
            instance = new CertificatService();
        }
        return instance;
    }

    public Certificat genereazaCertificat(int id, Cursant cursant, Curs curs) {
        Certificat certificat = new Certificat(id, cursant, curs);
        certificate.add(certificat);
        return certificat;
    }

    public List<Certificat> listeazaToateCertificatele() {
        return new ArrayList<>(certificate);
    }

    public List<Certificat> listeazaCertificatePentruCursant(Cursant cursant) {
        List<Certificat> rezultat = new ArrayList<>();

        // Selectează doar certificatele cursantului primit ca parametru.
        for (Certificat certificat : certificate) {
            if (certificat.getCursant().equals(cursant)) {
                rezultat.add(certificat);
            }
        }

        return rezultat;
    }
}