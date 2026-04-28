package com.pao.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class Intrebare {
    private int id;
    private String text;

    // Lista de răspunsuri posibile pentru această întrebare.
    private List<Raspuns> raspunsuri;

    public Intrebare(int id, String text) {
        this.id = id;
        this.text = text;

        // Inițial, întrebarea nu are răspunsuri adăugate.
        this.raspunsuri = new ArrayList<>();
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

    public List<Raspuns> getRaspunsuri() {
        return raspunsuri;
    }

    public void adaugaRaspuns(Raspuns raspuns) {
        this.raspunsuri.add(raspuns);
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", raspunsuri=" + raspunsuri.size() +
                '}';
    }
}