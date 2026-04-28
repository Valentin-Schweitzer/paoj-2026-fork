package com.pao.elearning;

import com.pao.elearning.exception.CursNegasitException;
import com.pao.elearning.exception.QuizInvalidException;
import com.pao.elearning.model.*;
import com.pao.elearning.service.*;

import java.util.List;

public class Main_vechi {
    public static void main(String[] args) {
        // Obținerea instanțelor serviciilor folosite în aplicație.
        UtilizatorService utilizatorService = UtilizatorService.getInstance();
        CursService cursService = CursService.getInstance();
        QuizService quizService = QuizService.getInstance();
        InscriereService inscriereService = InscriereService.getInstance();
        CertificatService certificatService = CertificatService.getInstance();

        System.out.println("=== 1. Inregistrare lector ===");
        Lector lector = new Lector(1, "Andrei Popescu", "andrei@email.com", "Programare Java");
        utilizatorService.adaugaLector(lector);
        System.out.println(lector);

        System.out.println("\n=== 2. Inregistrare cursant ===");
        Cursant cursant = new Cursant(2, "Maria Ionescu", "maria@email.com", 1);
        utilizatorService.adaugaCursant(cursant);
        System.out.println(cursant);

        System.out.println("\n=== 3. Adaugare cursuri ===");
        Curs cursJava = new Curs(1, "Introducere in Java", "Curs pentru incepatori", lector);
        Curs cursOop = new Curs(2, "Programare Orientata pe Obiecte", "Clase, obiecte si mostenire", lector);

        cursService.adaugaCurs(cursJava);
        cursService.adaugaCurs(cursOop);

        System.out.println(cursService.listeazaToateCursurile());

        System.out.println("\n=== 4. Adaugare modul intr-un curs ===");
        ModulCurs modul = new ModulCurs(1, "Bazele limbajului Java");
        cursJava.adaugaModul(modul);
        System.out.println(cursJava.getModule());

        System.out.println("\n=== 5. Adaugare lectie intr-un modul ===");
        Lectie lectie = new Lectie(1, "Clase si obiecte", "Continut lectie despre clase si obiecte.", 45);
        modul.adaugaLectie(lectie);
        System.out.println(modul.getLectii());

        System.out.println("\n=== 6. Adaugare quiz pentru curs ===");
        Quiz quiz = new Quiz(1, "Quiz OOP", cursJava);

        System.out.println("\n=== 7. Adaugare intrebare si raspunsuri in quiz ===");
        Intrebare intrebare = new Intrebare(1, "Ce este o clasa?");
        intrebare.adaugaRaspuns(new Raspuns(1, "Un sablon pentru obiecte", true));
        intrebare.adaugaRaspuns(new Raspuns(2, "O variabila simpla", false));
        quiz.adaugaIntrebare(intrebare);

        // Quiz-ul este adăugat doar dacă trece validarea.
        try {
            quizService.adaugaQuiz(quiz);
            System.out.println(quizService.listeazaToateQuizurile());
        } catch (QuizInvalidException e) {
            System.out.println("Exceptie quiz tratata: " + e.getMessage());
        }

        System.out.println("\n=== 8. Inscriere cursant la curs ===");
        Inscriere inscriere = inscriereService.inscrieCursantLaCurs(1, cursant, cursJava);
        System.out.println(inscriere);

        System.out.println("\n=== 9. Cursantul rezolva quiz-ul si se calculeaza scorul ===");
        try {
            // Lista conține id-urile răspunsurilor alese de cursant.
            double scor = quizService.calculeazaScor(quiz, List.of(1));
            RezultatQuiz rezultat = new RezultatQuiz(1, cursant, quiz, scor);
            System.out.println(rezultat);
        } catch (QuizInvalidException e) {
            System.out.println("Exceptie quiz tratata: " + e.getMessage());
        }

        System.out.println("\n=== 10. Finalizare curs si generare certificat ===");
        inscriereService.marcheazaCursFinalizat(cursant, cursJava);
        Certificat certificat = certificatService.genereazaCertificat(1, cursant, cursJava);
        System.out.println(inscriereService.listeazaInscrieriPentruCursant(cursant));
        System.out.println(certificat);

        System.out.println("\n=== 11. Afisare cursuri la care este inscris cursantul ===");
        System.out.println(inscriereService.listeazaCursurileUnuiCursant(cursant));

        System.out.println("\n=== 12. Cautare curs dupa titlu ===");
        System.out.println(cursService.cautaCursuriDupaTitlu("Java"));

        System.out.println("\n=== 13. Afisare cursuri sortate dupa titlu ===");
        System.out.println(cursService.listeazaCursuriSortateDupaTitlu());

        System.out.println("\n=== 14. Afisare utilizatori ===");
        System.out.println(utilizatorService.listeazaTotiUtilizatorii());

        System.out.println("\n=== 15. Tratare exceptie custom pentru curs inexistent ===");
        try {
            Curs cursInexistent = cursService.cautaCursDupaId(999);
            System.out.println(cursInexistent);
        } catch (CursNegasitException e) {
            System.out.println("Exceptie curs tratata: " + e.getMessage());
        }

        System.out.println("\n=== 16. Tratare exceptie custom pentru quiz invalid ===");
        try {
            Quiz quizInvalid = new Quiz(2, "Quiz gol", cursJava);
            quizService.adaugaQuiz(quizInvalid);
        } catch (QuizInvalidException e) {
            System.out.println("Exceptie quiz tratata: " + e.getMessage());
        }
    }
}