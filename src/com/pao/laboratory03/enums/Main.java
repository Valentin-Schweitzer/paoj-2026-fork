package com.pao.laboratory03.enums;

/**
 * Exercițiul 2 — Enum-uri
 *
 * PASUL 2 — În acest Main.java:
 *   a) Parcurge toate valorile cu Priority.values() și afișează:
 *      "emoji name (level=X, color=Y)"
 *   b) Folosește switch pe un Priority și afișează un mesaj specific.
 *   c) Convertește un String în Priority cu Priority.valueOf("HIGH") — afișează rezultatul.
 *   d) Demonstrează compararea: folosește == între două enum-uri (NU .equals()).
 *   e) Afișează name() și ordinal() pentru fiecare constantă.
 *
 * Output așteptat:
 *
 * === Toate prioritățile ===
 * 🟢 LOW (level=1, color=green)
 * 🟡 MEDIUM (level=2, color=yellow)
 * 🟠 HIGH (level=3, color=orange)
 * 🔴 CRITICAL (level=4, color=red)
 *
 * === Switch pe prioritate ===
 * ⚠️ Atenție! Prioritate ridicată!
 *
 * === valueOf ===
 * Priority.valueOf("HIGH") = HIGH
 *
 * === Comparare enum ===
 * HIGH == HIGH? true
 * HIGH == LOW? false
 *
 * === name() și ordinal() ===
 * LOW: name=LOW, ordinal=0
 * MEDIUM: name=MEDIUM, ordinal=1
 * HIGH: name=HIGH, ordinal=2
 * CRITICAL: name=CRITICAL, ordinal=3
 */

// ==================================================
// PASUL 2 — În acest Main.java:
// ==================================================
public class Main {
    public static void main(String[] args) {
        // a) Parcurge toate valorile cu Priority.values() și afișează:
        System.out.println("=== Toate prioritățile ===");
        for (Priority p : Priority.values()) {
            System.out.println(
                    // "emoji name (level=X, color=Y)"
                    p.getEmoji() + " " + p.name() + " (level=" + p.getLevel() + ", color=" + p.getColor() + ")"
            );
        }

        // b) Folosește switch pe un Priority și afișează un mesaj specific.
        System.out.println("\n=== Switch pe prioritate ===");
        Priority selected = Priority.HIGH;
        switch (selected) {
            case LOW:
                System.out.println("Prioritate mică.");
                break;
            case MEDIUM:
                System.out.println("Prioritate medie.");
                break;
            case HIGH:
                System.out.println("⚠️ Atenție! Prioritate ridicată!");
                break;
            case CRITICAL:
                System.out.println("🚨 Alertă critică!");
                break;
        }

        // c) Convertește un String în Priority cu Priority.valueOf("HIGH") — afișează rezultatul.
        System.out.println("\n=== valueOf ===");
        Priority fromString = Priority.valueOf("HIGH");
        System.out.println("Priority.valueOf(\"HIGH\") = " + fromString);

        // d) Demonstrează compararea: folosește == între două enum-uri (NU .equals()).
        System.out.println("\n=== Comparare enum ===");
        System.out.println("HIGH == HIGH? " + (Priority.HIGH == fromString));
        System.out.println("HIGH == LOW? " + (Priority.HIGH == Priority.LOW));

        // e) Afișează name() și ordinal() pentru fiecare constantă.
        System.out.println("\n=== name() și ordinal() ===");
        for (Priority p : Priority.values()) {
            System.out.println(
                    p + ": name=" + p.name() + ", ordinal=" + p.ordinal()
            );
        }
    }
}

