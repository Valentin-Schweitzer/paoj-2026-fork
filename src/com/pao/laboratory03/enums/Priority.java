package com.pao.laboratory03.enums;

/**
 * Exercițiul 2 — Enum-uri
 *
 * Creează în acest pachet (lângă acest Main.java) un enum și apoi folosește-l aici.
 *
 * PASUL 1 — Creează enum-ul Priority.java (fișier separat în același pachet):
 *   - Constante: LOW, MEDIUM, HIGH, CRITICAL
 *   - Câmpuri private: int level, String color
 *   - Constructor privat: Priority(int level, String color)
 *   - Getteri: getLevel(), getColor()
 *   - Metodă abstractă: String getEmoji() — fiecare constantă o implementează diferit
 *     LOW → "🟢", MEDIUM → "🟡", HIGH → "🟠", CRITICAL → "🔴"
 *   - Valorile sugerate:
 *     LOW(1, "green"), MEDIUM(2, "yellow"), HIGH(3, "orange"), CRITICAL(4, "red")
 */

// ==================================================
// PASUL 1 — Creează enum-ul Priority.java (fișier separat în același pachet):
// ==================================================
public enum Priority {
    // Constante: LOW
    LOW(1, "green") {
        @Override
        public String getEmoji() {
            return "🟢";
        }
    },
    // Constante: MEDIUM
    MEDIUM(2, "yellow") {
        @Override
        public String getEmoji() {
            return "🟡";
        }
    },
    // Constante: HIGH
    HIGH(3, "orange") {
        @Override
        public String getEmoji() {
            return "🟠";
        }
    },
    // Constante: CRITICAL
    CRITICAL(4, "red") {
        @Override
        public String getEmoji() {
            return "🔴";
        }
    };

    // Câmpuri private: int level, String color
    private int level;
    private String color;

    // Constructor privat: Priority(int level, String color)
    Priority(int level, String color) {
        this.level = level;
        this.color = color;
    }

    // Getteri: getLevel(), getColor()
    public int getLevel() {
        return level;
    }
    public String getColor() {
        return color;
    }

    // Metodă abstractă: String getEmoji() — fiecare constantă o implementează diferit
    public abstract String getEmoji();
}
