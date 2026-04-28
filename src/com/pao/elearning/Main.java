package com.pao.elearning;

import com.pao.elearning.exception.CursNegasitException;
import com.pao.elearning.exception.QuizInvalidException;
import com.pao.elearning.model.*;
import com.pao.elearning.service.*;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final boolean ECHO_INPUT = Boolean.getBoolean("echoInput");

    private static final UtilizatorService utilizatorService = UtilizatorService.getInstance();
    private static final CursService cursService = CursService.getInstance();
    private static final QuizService quizService = QuizService.getInstance();
    private static final InscriereService inscriereService = InscriereService.getInstance();
    private static final CertificatService certificatService = CertificatService.getInstance();

    // Rezultatele sunt păstrate aici deoarece nu avem încă un RezultatQuizService separat.
    private static final List<RezultatQuiz> rezultateQuiz = new ArrayList<>();

    private static int nextUtilizatorId = 1;
    private static int nextCursId = 1;
    private static int nextModulId = 1;
    private static int nextLectieId = 1;
    private static int nextQuizId = 1;
    private static int nextIntrebareId = 1;
    private static int nextRaspunsId = 1;
    private static int nextRezultatId = 1;
    private static int nextInscriereId = 1;
    private static int nextCertificatId = 1;

    public static void main(String[] args) {
        incarcaDateInitiale();
        meniuPrincipal();
    }

    private static void meniuPrincipal() {
        boolean ruleaza = true;

        while (ruleaza) {
            System.out.println("\n=== PLATFORMĂ E-LEARNING ===");
            System.out.println("1. Autentificare lector");
            System.out.println("2. Autentificare cursant");
            System.out.println("3. Înregistrare lector nou");
            System.out.println("4. Înregistrare cursant nou");
            System.out.println("5. Listează utilizatorii");
            System.out.println("0. Ieșire");

            int optiune = citesteInt("Alege opțiunea: ");

            switch (optiune) {
                case 1 -> autentificareLector();
                case 2 -> autentificareCursant();
                case 3 -> inregistreazaLector();
                case 4 -> inregistreazaCursant();
                case 5 -> afiseazaUtilizatori();
                case 0 -> {
                    ruleaza = false;
                    System.out.println("Aplicația s-a închis.");
                }
                default -> System.out.println("Opțiune invalidă.");
            }
        }
    }

    private static void meniuLector(Lector lector) {
        boolean autentificat = true;

        while (autentificat) {
            System.out.println("\n=== MENIU LECTOR: " + lector.getNume() + " ===");
            System.out.println("1. Adaugă curs");
            System.out.println("2. Adaugă modul într-un curs");
            System.out.println("3. Adaugă lecție într-un modul");
            System.out.println("4. Adaugă quiz pentru un curs");
            System.out.println("5. Adaugă întrebare într-un quiz");
            System.out.println("6. Listează cursurile sortate după titlu");
            System.out.println("7. Caută curs după titlu");
            System.out.println("8. Listează quiz-urile");
            System.out.println("0. Logout");

            int optiune = citesteInt("Alege opțiunea: ");

            switch (optiune) {
                case 1 -> adaugaCurs(lector);
                case 2 -> adaugaModulInCurs();
                case 3 -> adaugaLectieInModul();
                case 4 -> adaugaQuizPentruCurs();
                case 5 -> adaugaIntrebareLaQuizExistent();
                case 6 -> afiseazaCursuri(cursService.listeazaCursuriSortateDupaTitlu());
                case 7 -> cautaCursDupaTitlu();
                case 8 -> afiseazaQuizuri(quizService.listeazaToateQuizurile());
                case 0 -> autentificat = false;
                default -> System.out.println("Opțiune invalidă.");
            }
        }
    }

    private static void meniuCursant(Cursant cursant) {
        boolean autentificat = true;

        while (autentificat) {
            System.out.println("\n=== MENIU CURSANT: " + cursant.getNume() + " ===");
            System.out.println("1. Listează cursurile disponibile");
            System.out.println("2. Înscrie-te la un curs");
            System.out.println("3. Listează cursurile mele");
            System.out.println("4. Rezolvă quiz");
            System.out.println("5. Afișează rezultatele mele");
            System.out.println("6. Afișează clasamentul cursanților după scor");
            System.out.println("7. Generează certificat");
            System.out.println("8. Listează certificatele mele");
            System.out.println("0. Logout");

            int optiune = citesteInt("Alege opțiunea: ");

            switch (optiune) {
                case 1 -> afiseazaCursuri(cursService.listeazaCursuriSortateDupaTitlu());
                case 2 -> inscrieCursantLaCurs(cursant);
                case 3 -> afiseazaCursurileUnuiCursant(cursant);
                case 4 -> rezolvaQuiz(cursant);
                case 5 -> afiseazaRezultatePentruCursant(cursant);
                case 6 -> afiseazaClasamentDupaScor();
                case 7 -> genereazaCertificat(cursant);
                case 8 -> afiseazaCertificatePentruCursant(cursant);
                case 0 -> autentificat = false;
                default -> System.out.println("Opțiune invalidă.");
            }
        }
    }

    private static void autentificareLector() {
        String email = citesteText("Email lector: ");
        Utilizator utilizator = utilizatorService.cautaUtilizatorDupaEmail(email);

        if (utilizator instanceof Lector lector) {
            meniuLector(lector);
        } else {
            System.out.println("Nu există niciun lector cu acest email.");
        }
    }

    private static void autentificareCursant() {
        String email = citesteText("Email cursant: ");
        Utilizator utilizator = utilizatorService.cautaUtilizatorDupaEmail(email);

        if (utilizator instanceof Cursant cursant) {
            meniuCursant(cursant);
        } else {
            System.out.println("Nu există niciun cursant cu acest email.");
        }
    }

    private static void inregistreazaLector() {
        String nume = citesteText("Nume lector: ");
        String email = citesteEmailNou();
        String specializare = citesteText("Specializare: ");

        Lector lector = new Lector(nextUtilizatorId++, nume, email, specializare);
        utilizatorService.adaugaLector(lector);

        System.out.println("Lector înregistrat: " + lector);
    }

    private static void inregistreazaCursant() {
        String nume = citesteText("Nume cursant: ");
        String email = citesteEmailNou();
        int nivelExperienta = citesteInt("Nivel experiență: ");

        Cursant cursant = new Cursant(nextUtilizatorId++, nume, email, nivelExperienta);
        utilizatorService.adaugaCursant(cursant);

        System.out.println("Cursant înregistrat: " + cursant);
    }

    private static void adaugaCurs(Lector lector) {
        String titlu = citesteText("Titlu curs: ");
        String descriere = citesteText("Descriere curs: ");

        Curs curs = new Curs(nextCursId++, titlu, descriere, lector);
        cursService.adaugaCurs(curs);

        System.out.println("Curs adăugat: " + curs);
    }

    private static void adaugaModulInCurs() {
        Curs curs = alegeCursDupaId();
        if (curs == null) {
            return;
        }

        String titlu = citesteText("Titlu modul: ");
        ModulCurs modul = new ModulCurs(nextModulId++, titlu);
        curs.adaugaModul(modul);

        System.out.println("Modul adăugat în cursul " + curs.getTitlu() + ": " + modul);
    }

    private static void adaugaLectieInModul() {
        Curs curs = alegeCursDupaId();
        if (curs == null) {
            return;
        }

        if (curs.getModule().isEmpty()) {
            System.out.println("Cursul nu are module. Adaugă mai întâi un modul.");
            return;
        }

        afiseazaModule(curs);
        int modulId = citesteInt("Id modul: ");
        ModulCurs modul = gasesteModulDupaId(curs, modulId);

        if (modul == null) {
            System.out.println("Modulul nu a fost găsit.");
            return;
        }

        String titlu = citesteText("Titlu lecție: ");
        String continut = citesteText("Conținut lecție: ");
        int durataMinute = citesteInt("Durată în minute: ");

        Lectie lectie = new Lectie(nextLectieId++, titlu, continut, durataMinute);
        modul.adaugaLectie(lectie);

        System.out.println("Lecție adăugată: " + lectie);
    }

    private static void adaugaQuizPentruCurs() {
        Curs curs = alegeCursDupaId();
        if (curs == null) {
            return;
        }

        String titlu = citesteText("Titlu quiz: ");
        Quiz quiz = new Quiz(nextQuizId++, titlu, curs);

        System.out.println("Quiz-ul trebuie să aibă cel puțin o întrebare.");
        adaugaIntrebareInQuiz(quiz);

        try {
            quizService.adaugaQuiz(quiz);
            System.out.println("Quiz adăugat: " + quiz);
        } catch (QuizInvalidException e) {
            System.out.println("Eroare la adăugarea quiz-ului: " + e.getMessage());
        }
    }

    private static void adaugaIntrebareLaQuizExistent() {
        Quiz quiz = alegeQuizDupaId();
        if (quiz == null) {
            return;
        }

        adaugaIntrebareInQuiz(quiz);
        System.out.println("Întrebare adăugată în quiz-ul: " + quiz.getTitlu());
    }

    private static void adaugaIntrebareInQuiz(Quiz quiz) {
        String textIntrebare = citesteText("Text întrebare: ");
        Intrebare intrebare = new Intrebare(nextIntrebareId++, textIntrebare);

        int numarRaspunsuri = citesteIntMinim("Număr răspunsuri posibile: ", 2);
        List<Raspuns> raspunsuri = new ArrayList<>();

        for (int i = 1; i <= numarRaspunsuri; i++) {
            String textRaspuns = citesteText("Răspuns " + i + ": ");
            raspunsuri.add(new Raspuns(nextRaspunsId++, textRaspuns, false));
        }

        int variantaCorecta = citesteIntInterval("Numărul răspunsului corect: ", 1, numarRaspunsuri);
        raspunsuri.get(variantaCorecta - 1).setCorect(true);

        for (Raspuns raspuns : raspunsuri) {
            intrebare.adaugaRaspuns(raspuns);
        }

        quiz.adaugaIntrebare(intrebare);
    }

    private static void inscrieCursantLaCurs(Cursant cursant) {
        Curs curs = alegeCursDupaId();
        if (curs == null) {
            return;
        }

        if (esteInscrisLaCurs(cursant, curs)) {
            System.out.println("Cursantul este deja înscris la acest curs.");
            return;
        }

        Inscriere inscriere = inscriereService.inscrieCursantLaCurs(nextInscriereId++, cursant, curs);
        System.out.println("Înscriere realizată: " + inscriere);
    }

    private static void afiseazaCursurileUnuiCursant(Cursant cursant) {
        List<Curs> cursuri = inscriereService.listeazaCursurileUnuiCursant(cursant);
        afiseazaCursuri(cursuri);
    }

    private static void rezolvaQuiz(Cursant cursant) {
        List<Quiz> quizuriDisponibile = quizuriPentruCursant(cursant);

        if (quizuriDisponibile.isEmpty()) {
            System.out.println("Nu există quiz-uri disponibile pentru cursurile la care ești înscris.");
            return;
        }

        afiseazaQuizuri(quizuriDisponibile);
        int quizId = citesteInt("Id quiz: ");
        Quiz quiz = gasesteQuizInLista(quizuriDisponibile, quizId);

        if (quiz == null) {
            System.out.println("Quiz-ul nu a fost găsit în lista disponibilă.");
            return;
        }

        List<Integer> raspunsuriAlese = new ArrayList<>();

        for (Intrebare intrebare : quiz.getIntrebari()) {
            System.out.println("\nÎntrebare: " + intrebare.getText());

            for (Raspuns raspuns : intrebare.getRaspunsuri()) {
                System.out.println(raspuns.getId() + ". " + raspuns.getText());
            }

            int raspunsId = citesteInt("Alege id-ul răspunsului: ");
            raspunsuriAlese.add(raspunsId);
        }

        try {
            double scor = quizService.calculeazaScor(quiz, raspunsuriAlese);
            RezultatQuiz rezultat = new RezultatQuiz(nextRezultatId++, cursant, quiz, scor);
            rezultateQuiz.add(rezultat);

            System.out.println("Scor obținut: " + scor);
            System.out.println("Rezultat salvat: " + rezultat);

            if (scor >= 50.0) {
                inscriereService.marcheazaCursFinalizat(cursant, quiz.getCurs());
                System.out.println("Cursul a fost marcat ca finalizat.");
            }
        } catch (QuizInvalidException e) {
            System.out.println("Quiz invalid: " + e.getMessage());
        }
    }

    private static void afiseazaRezultatePentruCursant(Cursant cursant) {
        boolean existaRezultate = false;

        for (RezultatQuiz rezultat : rezultateQuiz) {
            if (rezultat.getCursant().equals(cursant)) {
                System.out.println(rezultat);
                existaRezultate = true;
            }
        }

        if (!existaRezultate) {
            System.out.println("Cursantul nu are rezultate salvate.");
        }
    }

    private static void afiseazaClasamentDupaScor() {
        if (rezultateQuiz.isEmpty()) {
            System.out.println("Nu există rezultate pentru clasament.");
            return;
        }

        Map<Cursant, Double> celMaiBunScor = new HashMap<>();

        for (RezultatQuiz rezultat : rezultateQuiz) {
            celMaiBunScor.merge(
                    rezultat.getCursant(),
                    rezultat.getScor(),
                    Math::max
            );
        }

        List<Map.Entry<Cursant, Double>> clasament = new ArrayList<>(celMaiBunScor.entrySet());
        clasament.sort(Map.Entry.<Cursant, Double>comparingByValue().reversed());

        System.out.println("\n=== CLASAMENT DUPĂ CEL MAI BUN SCOR ===");
        int pozitie = 1;

        for (Map.Entry<Cursant, Double> entry : clasament) {
            System.out.println(pozitie + ". " + entry.getKey().getNume() + " - " + entry.getValue());
            pozitie++;
        }
    }

    private static void genereazaCertificat(Cursant cursant) {
        List<Inscriere> inscrieri = inscriereService.listeazaInscrieriPentruCursant(cursant);
        List<Inscriere> inscrieriFinalizate = new ArrayList<>();

        for (Inscriere inscriere : inscrieri) {
            if (inscriere.isFinalizata()) {
                inscrieriFinalizate.add(inscriere);
            }
        }

        if (inscrieriFinalizate.isEmpty()) {
            System.out.println("Nu ai cursuri finalizate pentru care se poate genera certificat.");
            return;
        }

        System.out.println("\nCursuri finalizate:");
        for (Inscriere inscriere : inscrieriFinalizate) {
            System.out.println(inscriere.getCurs().getId() + ". " + inscriere.getCurs().getTitlu());
        }

        int cursId = citesteInt("Id curs pentru certificat: ");
        Curs curs = gasesteCursFinalizat(inscrieriFinalizate, cursId);

        if (curs == null) {
            System.out.println("Cursul nu a fost găsit în lista de cursuri finalizate.");
            return;
        }

        Certificat certificatExistent = gasesteCertificat(cursant, curs);
        if (certificatExistent != null) {
            System.out.println("Există deja certificat pentru acest curs:");
            System.out.println(certificatExistent);
            return;
        }

        Certificat certificat = certificatService.genereazaCertificat(nextCertificatId++, cursant, curs);
        System.out.println("Certificat generat: " + certificat);
    }

    private static void afiseazaCertificatePentruCursant(Cursant cursant) {
        List<Certificat> certificate = certificatService.listeazaCertificatePentruCursant(cursant);

        if (certificate.isEmpty()) {
            System.out.println("Cursantul nu are certificate generate.");
            return;
        }

        for (Certificat certificat : certificate) {
            System.out.println(certificat);
        }
    }

    private static void cautaCursDupaTitlu() {
        String titlu = citesteText("Text căutat în titlu: ");
        List<Curs> rezultate = cursService.cautaCursuriDupaTitlu(titlu);
        afiseazaCursuri(rezultate);
    }

    private static Curs alegeCursDupaId() {
        afiseazaCursuri(cursService.listeazaToateCursurile());
        int id = citesteInt("Id curs: ");

        try {
            return cursService.cautaCursDupaId(id);
        } catch (CursNegasitException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Quiz alegeQuizDupaId() {
        List<Quiz> quizuri = quizService.listeazaToateQuizurile();
        afiseazaQuizuri(quizuri);

        if (quizuri.isEmpty()) {
            return null;
        }

        int id = citesteInt("Id quiz: ");
        Quiz quiz = quizService.cautaQuizDupaId(id);

        if (quiz == null) {
            System.out.println("Quiz-ul nu a fost găsit.");
        }

        return quiz;
    }

    private static ModulCurs gasesteModulDupaId(Curs curs, int modulId) {
        for (ModulCurs modul : curs.getModule()) {
            if (modul.getId() == modulId) {
                return modul;
            }
        }

        return null;
    }

    private static Quiz gasesteQuizInLista(List<Quiz> quizuri, int quizId) {
        for (Quiz quiz : quizuri) {
            if (quiz.getId() == quizId) {
                return quiz;
            }
        }

        return null;
    }

    private static Curs gasesteCursFinalizat(List<Inscriere> inscrieriFinalizate, int cursId) {
        for (Inscriere inscriere : inscrieriFinalizate) {
            if (inscriere.getCurs().getId() == cursId) {
                return inscriere.getCurs();
            }
        }

        return null;
    }

    private static Certificat gasesteCertificat(Cursant cursant, Curs curs) {
        for (Certificat certificat : certificatService.listeazaCertificatePentruCursant(cursant)) {
            if (certificat.getCurs().equals(curs)) {
                return certificat;
            }
        }

        return null;
    }

    private static boolean esteInscrisLaCurs(Cursant cursant, Curs curs) {
        for (Inscriere inscriere : inscriereService.listeazaInscrieriPentruCursant(cursant)) {
            if (inscriere.getCurs().equals(curs)) {
                return true;
            }
        }

        return false;
    }

    private static List<Quiz> quizuriPentruCursant(Cursant cursant) {
        List<Quiz> rezultat = new ArrayList<>();
        List<Curs> cursuriCursant = inscriereService.listeazaCursurileUnuiCursant(cursant);

        for (Quiz quiz : quizService.listeazaToateQuizurile()) {
            if (cursuriCursant.contains(quiz.getCurs())) {
                rezultat.add(quiz);
            }
        }

        return rezultat;
    }

    private static void afiseazaUtilizatori() {
        List<Utilizator> utilizatori = utilizatorService.listeazaTotiUtilizatorii();

        if (utilizatori.isEmpty()) {
            System.out.println("Nu există utilizatori.");
            return;
        }

        for (Utilizator utilizator : utilizatori) {
            System.out.println(utilizator.getId() + " | " + utilizator.getRol() + " | " + utilizator.getNume() + " | " + utilizator.getEmail());
        }
    }

    private static void afiseazaCursuri(Collection<Curs> cursuri) {
        if (cursuri.isEmpty()) {
            System.out.println("Nu există cursuri de afișat.");
            return;
        }

        for (Curs curs : cursuri) {
            System.out.println(curs.getId() + ". " + curs.getTitlu() + " | lector: " + curs.getLector().getNume());
        }
    }

    private static void afiseazaModule(Curs curs) {
        System.out.println("\nModulele cursului " + curs.getTitlu() + ":");

        for (ModulCurs modul : curs.getModule()) {
            System.out.println(modul.getId() + ". " + modul.getTitlu());
        }
    }

    private static void afiseazaQuizuri(Collection<Quiz> quizuri) {
        if (quizuri.isEmpty()) {
            System.out.println("Nu există quiz-uri de afișat.");
            return;
        }

        for (Quiz quiz : quizuri) {
            System.out.println(quiz.getId() + ". " + quiz.getTitlu() + " | curs: " + quiz.getCurs().getTitlu());
        }
    }

    private static String citesteText(String mesaj) {
        System.out.print(mesaj);
        String linie = scanner.nextLine().trim();
        afiseazaInputDacaEsteNecesar(linie);
        return linie;
    }

    private static int citesteInt(String mesaj) {
        while (true) {
            System.out.print(mesaj);
            String linie = scanner.nextLine().trim();
            afiseazaInputDacaEsteNecesar(linie);

            try {
                return Integer.parseInt(linie);
            } catch (NumberFormatException e) {
                System.out.println("Introdu un număr întreg valid.");
            }
        }
    }

    private static void afiseazaInputDacaEsteNecesar(String linie) {
        if (ECHO_INPUT) {
            System.out.println(linie);
        }
    }

    private static int citesteIntMinim(String mesaj, int minim) {
        while (true) {
            int valoare = citesteInt(mesaj);

            if (valoare >= minim) {
                return valoare;
            }

            System.out.println("Valoarea trebuie să fie cel puțin " + minim + ".");
        }
    }

    private static int citesteIntInterval(String mesaj, int minim, int maxim) {
        while (true) {
            int valoare = citesteInt(mesaj);

            if (valoare >= minim && valoare <= maxim) {
                return valoare;
            }

            System.out.println("Valoarea trebuie să fie între " + minim + " și " + maxim + ".");
        }
    }

    private static String citesteEmailNou() {
        while (true) {
            String email = citesteText("Email: ");

            if (utilizatorService.cautaUtilizatorDupaEmail(email) == null) {
                return email;
            }

            System.out.println("Există deja un utilizator cu acest email.");
        }
    }

    private static void incarcaDateInitiale() {
        Lector lector = new Lector(nextUtilizatorId++, "Andrei Popescu", "andrei@email.com", "Programare Java");
        Cursant cursant = new Cursant(nextUtilizatorId++, "Maria Ionescu", "maria@email.com", 1);

        utilizatorService.adaugaLector(lector);
        utilizatorService.adaugaCursant(cursant);

        Curs cursJava = new Curs(nextCursId++, "Introducere în Java", "Curs pentru începători", lector);
        Curs cursOop = new Curs(nextCursId++, "Programare Orientată pe Obiecte", "Clase, obiecte și moștenire", lector);

        cursService.adaugaCurs(cursJava);
        cursService.adaugaCurs(cursOop);

        ModulCurs modul = new ModulCurs(nextModulId++, "Bazele limbajului Java");
        Lectie lectie = new Lectie(nextLectieId++, "Clase și obiecte", "Introducere în clase și obiecte.", 45);
        modul.adaugaLectie(lectie);
        cursJava.adaugaModul(modul);

        Quiz quiz = new Quiz(nextQuizId++, "Quiz OOP", cursJava);
        Intrebare intrebare = new Intrebare(nextIntrebareId++, "Ce este o clasă?");
        intrebare.adaugaRaspuns(new Raspuns(nextRaspunsId++, "Un șablon pentru obiecte", true));
        intrebare.adaugaRaspuns(new Raspuns(nextRaspunsId++, "O variabilă simplă", false));
        quiz.adaugaIntrebare(intrebare);

        try {
            quizService.adaugaQuiz(quiz);
        } catch (QuizInvalidException e) {
            System.out.println("Eroare la încărcarea datelor inițiale: " + e.getMessage());
        }

        inscriereService.inscrieCursantLaCurs(nextInscriereId++, cursant, cursJava);

        System.out.println("Date inițiale încărcate.");
        System.out.println("Lector demo: andrei@email.com");
        System.out.println("Cursant demo: maria@email.com");
    }
}
