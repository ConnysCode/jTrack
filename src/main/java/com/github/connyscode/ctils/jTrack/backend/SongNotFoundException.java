package com.github.connyscode.ctils.jTrack.backend;

public class SongNotFoundException extends Exception {
    public SongNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
