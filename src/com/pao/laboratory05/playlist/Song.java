package com.pao.laboratory05.playlist;

public record Song(String title, String artist, int durationSeconds) implements Comparable<Song> {
    // compareTo: sortare după titlu (alfabetic)
    @Override
    public int compareTo(Song other) {
        // Hint: String are deja compareTo — folosește-l
        return this.title.compareTo(other.title());
    }
}