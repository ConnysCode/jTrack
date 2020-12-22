package com.github.connyscode.ctils.jTrack.backend;

public class ArtistNotFoundException extends Exception {
    public ArtistNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}