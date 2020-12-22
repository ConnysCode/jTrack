package com.github.connyscode.ctils.jTrack.backend.types;

public class SongSearchResult {
    public String songTitle, songUrl, songArtImage;
    public int id;

    /**
     * Create a SearchResult
     *
     * @param songTitle Song Title
     * @param songUrl Song URL
     * @param id Song api ID
     * @see SongSearchResult
     */
    public SongSearchResult(String songTitle, String songUrl, String songArtImage, int id) {
        this.songTitle = songTitle;
        this.songUrl = songUrl;
        this.songArtImage = songArtImage;
        this.id = id;
    }
}
