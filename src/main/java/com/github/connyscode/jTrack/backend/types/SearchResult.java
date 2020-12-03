package com.github.connyscode.jTrack.backend.types;

public class SearchResult {
    public String songTitle, songAuthor, songUrl, searchURL;
    private boolean wasSuccessful;

    /**
     * Create a SearchResult
     *
     * @param songTitle Song Title
     * @param songAuthor Song Author
     * @param songUrl Song URL
     * @see SearchResult
     */
    public SearchResult(String songTitle, String songAuthor, String songUrl) {
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
        this.songUrl = songUrl;
        this.wasSuccessful = true;
    }

    /**
     * Create an empty SearchResult with a success statement
     *
     * @param success Successful?
     * @see SearchResult
     */
    public SearchResult(boolean success) {
        this.songTitle = "";
        this.songAuthor = "";
        this.songUrl = "";
        this.wasSuccessful = success;
    }

    /**
     * Check if the SearchResult was successful
     *
     * @see SearchResult
     * @return Returns is the SearchResult was successful
     */
    public boolean wasSuccessful() {
        return wasSuccessful;
    }
}
