package com.pao.laboratory05.playlist;

import java.util.Arrays;

public class Playlist {
    // numele playlist-ului
    private String name;

    // array de Song-uri, inițial gol
    private Song[] songs;

    public Playlist(String name) {
        // constructor: setează numele și pornește cu array gol
        this.name = name;
        this.songs = new Song[0];
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        // adaugă un song nou folosind pattern-ul de resize
        Song[] newSongs = new Song[songs.length + 1];
        System.arraycopy(songs, 0, newSongs, 0, songs.length);
        newSongs[songs.length] = song;
        songs = newSongs;
    }

    public void printSortedByTitle() {
        // clonează array-ul ca să nu modifice ordinea originală
        Song[] copy = songs.clone();

        // sortare după titlu (folosește compareTo din Song)
        Arrays.sort(copy);

        for (Song song : copy) {
            System.out.println(song);
        }
    }

    public void printSortedByDuration() {
        // clonează array-ul ca să nu modifice ordinea originală
        Song[] copy = songs.clone();

        // sortare după durată folosind comparator extern
        Arrays.sort(copy, new SongDurationComparator());

        for (Song song : copy) {
            System.out.println(song);
        }
    }

    public int getTotalDuration() {
        // calculează suma duratelor tuturor song-urilor
        int total = 0;

        for (Song song : songs) {
            total += song.durationSeconds();
        }

        return total;
    }
}