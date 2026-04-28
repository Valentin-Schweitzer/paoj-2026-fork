package com.pao.elearning.exception;

// Excepție folosită pentru quiz-uri invalide.
public class QuizInvalidException extends Exception {
    public QuizInvalidException(String mesaj) {
        super(mesaj);
    }
}