package com.pao.elearning.model;

public class Raspuns {
    private int id;
    private String text;

    // Marchează dacă răspunsul este varianta corectă.
    private boolean corect;

    public Raspuns(int id, String text, boolean corect) {
        this.id = id;
        this.text = text;
        this.corect = corect;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorect() {
        return corect;
    }

    public void setCorect(boolean corect) {
        this.corect = corect;
    }

    @Override
    public String toString() {
        return "Raspuns{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", corect=" + corect +
                '}';
    }
}