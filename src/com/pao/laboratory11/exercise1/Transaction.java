package com.pao.laboratory11.exercise1;

// Reprezintă o tranzacție bancară verificată de motorul antifraudă
public class Transaction {
    private int id;
    private double amount;
    private String date;
    private String country;
    private String channel;

    // Construiește o tranzacție cu toate datele citite din input
    public Transaction(int id, double amount, String date, String country, String channel) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.country = country;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public String getChannel() {
        return channel;
    }
}