package com.pao.laboratory03.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercițiul 3 — Excepții (checked, unchecked, custom)
 *
 * Creează în acest pachet (lângă Main.java) două clase de excepții custom, apoi demonstrează-le aici.
 *
 * PASUL 1 — Creează InvalidAgeException.java (fișier separat):
 *   - Extinde RuntimeException (unchecked)
 *   - Constructor cu String message → apelează super(message)
 */

// ==================================================
// PASUL 1 — Creează InvalidAgeException.java (fișier separat):
// ==================================================

// Extinde RuntimeException (unchecked)
public class InvalidAgeException extends RuntimeException {
    // Constructor cu String message → apelează super(message)
    public InvalidAgeException(String message) {
        super(message);
    }
}
