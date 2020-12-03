package com.github.connyscode.ctils.jTrack.backend.types;

public final class TrackSearchCluster
{
    private String songName, songAuthor, searchPhrase = "";

    /**
     * Create a TrackSearchCluster
     *
     * @param  searchPhrase     A Search Phrase
     * @see TrackSearchCluster
     */
    public TrackSearchCluster(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    /**
     * Create a TrackSearchCluster
     *
     * @param  songName     A Songs Name
     * @param  songAuthor   A Songs Author
     * @see TrackSearchCluster
     */
    public TrackSearchCluster(String songName, String songAuthor) {
        this.songName = songName;
        this.songAuthor = songAuthor;
    }

    /**
     * Get the Song Name out of a TrackSearchCluster
     *
     * @see TrackSearchCluster
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Get the Song Author out of a TrackSearchCluster
     *
     * @see TrackSearchCluster
     */
    public String getSongAuthor() {
        return songAuthor;
    }

    /**
     * Get the Search Phrase out of a TrackSearchCluster
     *
     * @see TrackSearchCluster
     */
    public String getSearchPhrase() {
        return searchPhrase;
    }
}