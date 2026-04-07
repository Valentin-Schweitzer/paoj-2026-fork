package com.pao.laboratory07.exercise1;

// Stările posibile ale unei comenzi
public enum OrderState {
    PLACED,
    PROCESSED,
    SHIPPED,
    DELIVERED,
    CANCELED;

    // Verifică dacă starea este finală
    public boolean isFinalState() {
        return this == DELIVERED || this == CANCELED;
    }
}