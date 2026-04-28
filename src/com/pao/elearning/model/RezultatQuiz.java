package com.pao.elearning.model;

import java.time.LocalDateTime;

public class RezultatQuiz {
    private int id;
    private Cursant cursant;
    private Quiz quiz;
    private double scor;

    // Momentul în care quiz-ul a fost rezolvat.
    private LocalDateTime dataRezolvare;

    public RezultatQuiz(int id, Cursant cursant, Quiz quiz, double scor) {
        this.id = id;
        this.cursant = cursant;
        this.quiz = quiz;
        this.scor = scor;

        // Data rezolvării este setată automat.
        this.dataRezolvare = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Cursant getCursant() {
        return cursant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public double getScor() {
        return scor;
    }

    public void setScor(double scor) {
        this.scor = scor;
    }

    public LocalDateTime getDataRezolvare() {
        return dataRezolvare;
    }

    @Override
    public String toString() {
        return "RezultatQuiz{" +
                "id=" + id +
                ", cursant=" + cursant.getNume() +
                ", quiz=" + quiz.getTitlu() +
                ", scor=" + scor +
                ", dataRezolvare=" + dataRezolvare +
                '}';
    }
}