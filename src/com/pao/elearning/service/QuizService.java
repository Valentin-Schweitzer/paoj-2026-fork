package com.pao.elearning.service;

import com.pao.elearning.exception.QuizInvalidException;
import com.pao.elearning.model.Intrebare;
import com.pao.elearning.model.Quiz;
import com.pao.elearning.model.Raspuns;

import java.util.*;

public class QuizService {
    private static QuizService instance;

    private final List<Quiz> quizuri;
    private final Map<Integer, Quiz> quizuriDupaId;

    private QuizService() {
        this.quizuri = new ArrayList<>();
        this.quizuriDupaId = new HashMap<>();
    }

    public static QuizService getInstance() {
        if (instance == null) {
            instance = new QuizService();
        }
        return instance;
    }

    public void adaugaQuiz(Quiz quiz) throws QuizInvalidException {
        valideazaQuiz(quiz);

        quizuri.add(quiz);
        quizuriDupaId.put(quiz.getId(), quiz);
    }

    public Quiz cautaQuizDupaId(int id) {
        return quizuriDupaId.get(id);
    }

    public List<Quiz> listeazaToateQuizurile() {
        return new ArrayList<>(quizuri);
    }

    public void stergeQuiz(int id) {
        Quiz quiz = quizuriDupaId.get(id);

        if (quiz != null) {
            quizuri.remove(quiz);
            quizuriDupaId.remove(id);
        }
    }

    public double calculeazaScor(Quiz quiz, List<Integer> raspunsuriAlese) throws QuizInvalidException {
        valideazaQuiz(quiz);

        List<Intrebare> intrebari = quiz.getIntrebari();

        if (raspunsuriAlese.size() != intrebari.size()) {
            throw new QuizInvalidException("Numarul de raspunsuri alese nu corespunde cu numarul de intrebari.");
        }

        int raspunsuriCorecte = 0;

        // Verifică fiecare răspuns ales de cursant.
        for (int i = 0; i < intrebari.size(); i++) {
            Intrebare intrebare = intrebari.get(i);
            int idRaspunsAles = raspunsuriAlese.get(i);

            for (Raspuns raspuns : intrebare.getRaspunsuri()) {
                if (raspuns.getId() == idRaspunsAles && raspuns.isCorect()) {
                    raspunsuriCorecte++;
                }
            }
        }

        // Scorul este calculat procentual.
        return 100.0 * raspunsuriCorecte / intrebari.size();
    }

    private void valideazaQuiz(Quiz quiz) throws QuizInvalidException {
        if (quiz == null) {
            throw new QuizInvalidException("Quiz-ul nu poate fi null.");
        }

        if (quiz.getIntrebari().isEmpty()) {
            throw new QuizInvalidException("Quiz-ul trebuie sa contina cel putin o intrebare.");
        }
    }
}