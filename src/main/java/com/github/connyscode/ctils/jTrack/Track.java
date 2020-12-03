package com.github.connyscode.ctils.jTrack;

public class Track {

    private final String songTitle, songAuthor, songText, songUrl, songService;

    /**
     * Create a Track
     *
     * @see Track
     */
    Track() {
        this.songTitle = "";
        this.songAuthor = "";
        this.songText = "";
        this.songUrl = "";
        this.songService = "";
    }

    /**
     * Create a Track
     *
     * @param songTitle Song Title
     * @param songAuthor Song Author
     * @param songText Song Text
     * @param songUrl Song URL
     * @param songService Song Service
     * @see Track
     */
    Track(String songTitle, String songAuthor, String songText, String songUrl, String songService) {
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
        this.songText = songText;
        this.songUrl = songUrl;
        this.songService = songService;
    }

    public String songTitle() {
        return songTitle;
    }
    public String songArtist() {
        return songAuthor;
    }
    public String songText() {
        return songText;
    }
    public String songUrl() {
        return songUrl;
    }
    public String songService() {
        return songService;
    }

}
