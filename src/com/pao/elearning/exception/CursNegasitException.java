package com.pao.elearning.exception;

// Excepție folosită când un curs nu poate fi găsit.
public class CursNegasitException extends Exception {
    public CursNegasitException(String mesaj) {
        super(mesaj);
    }
}