package com.github.connyscode.ctils.jTrack;

import com.github.connyscode.ctils.jTrack.backend.GeniusAPI;
import org.json.simple.JSONObject;

import static com.github.connyscode.ctils.jTrack.backend.Messaging.*;

public class Track {

    private String songName = "", songFullName = "", songAuthor = "", songHeaderImageUrl = "https://genius.com/", songImageUrl = "https://genius.com/", songImageThumbnailUrl = "https://genius.com/", songReleaseDate = "", songText = "", songUrl = "https://genius.com/";
    private String albumName = "", albumFullName = "", albumAuthor = "", albumImageUrl = "https://genius.com/", albumUrl = "https://genius.com/";
    private String artistName = "", artistUrl = "https://genius.com/", artistHeaderImageUrl = "https://genius.com/", artistImageUrl = "https://genius.com/";
    private long songID = 0, albumID = 0, artistID = 0, songPageViews = 0;
    private boolean artistVerified = false;

    /**
     * Create a Track
     *
     * @param trackObject song response from genius
     * @see Track
     */
    public Track(JSONObject trackObject) {
        try {


            boolean albumB = false, artistB = false;
            JSONObject song = trackObject;
            JSONObject album = new JSONObject();
            JSONObject artist = new JSONObject();
            JSONObject stats = (JSONObject) song.get("stats");
            if (song.get("album") != null) {
                album = (JSONObject) song.get("album");
                albumB = true;
            }
            if (song.get("primary_artist") != null){
                artist = (JSONObject) song.get("primary_artist");
                artistB = true;
            }

            // Song
            songName = (String) song.get("title");
            songFullName = (String) song.get("full_title");
            if (artistB)
                songAuthor = (String) artist.get("name");
            songHeaderImageUrl = (String) artist.get("header_image_url");
            songImageUrl = (String) song.get("song_art_image_url");
            songImageThumbnailUrl = (String) song.get("song_art_image_thumbnail_url");
            songReleaseDate = (String) song.get("release_date_for_display");
            songText = GeniusAPI.getLyrics((String) song.get("url"));
            songUrl = (String) song.get("url");
            songID = (long) song.get("id");
            if (stats.get("pageviews") != null)
                songPageViews = (long) stats.get("pageviews");

            // Album
            if (albumB) {
                albumName = (String) album.get("name");
                albumFullName = (String) album.get("full_title");
                albumAuthor = (String) ((JSONObject)album.get("artist")).get("name");
                albumImageUrl = (String) album.get("cover_art_url");
                albumUrl = (String) album.get("url");
                albumID = (long) album.get("id");
            }

            // Artist
            if (artistB) {
                artistName = (String) artist.get("name");
                artistUrl = (String) artist.get("url");
                artistHeaderImageUrl = (String) artist.get("header_image_url");
                artistImageUrl = (String) artist.get("image_url");
                artistID = (long) artist.get("id");
                artistVerified = (boolean) artist.get("is_verified");
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("t01", "Could not read complete Track data!");
        }
    }


    ///* SONG *///
    /**
     * @return The name of the Track
     */
    public String songName() {
        return this.songName;
    }
    /**
     * @return The full name of the Track<br>
     * e.g. 'Beat It by Michael Jackson'<br>
     * instead of 'Beat It'
     */
    public String songFullName() {
        return this.songFullName;
    }
    /**
     * @return The track's artist
     */
    public String songAuthor() {
        return this.songAuthor;
    }
    /**
     * @return The track's Header as an URL<br>(Mostly same as Track.songImageUrl())
     */
    public String songHeaderImageUrl() {
        return this.songHeaderImageUrl;
    }
    /**
     * @return The track's CoverArt as an URL
     */
    public String songImageUrl() {
        return this.songImageUrl;
    }
    /**
     * @return The track's CoverArt Thumbnail as an URL<br>(A smaller version of the CoverArt Image)
     */
    public String songImageThumbnailUrl() {
        return this.songImageThumbnailUrl;
    }
    /**
     * @return The track's Release Date
     */
    public String songReleaseDate() {
        return this.songReleaseDate;
    }
    /**
     * @return The track's Lyrics
     */
    public String songLyrics() {
        return this.songText;
    }
    /**
     * @return The track's Genius URL
     */
    public String songUrl() {
        return this.songUrl;
    }
    ///* SONG *///



    ///* ALBUM *///
    /**
     * @return The name of the Album
     */
    public String albumName() {
        return this.albumName;
    }
    /**
     * @return The full name of the Album<br>
     * e.g. 'Beat It by Michael Jackson'<br>
     * instead of 'Beat It'
     */
    public String albumFullName() {
        return this.albumFullName;
    }
    /**
     * @return The albums's artist
     */
    public String albumAuthor() {
        return this.albumAuthor;
    }
    /**
     * @return The albums's CoverArt as an URL
     */
    public String albumImageUrl() {
        return this.albumImageUrl;
    }
    /**
     * @return The albums's Genius URL
     */
    public String albumUrl() {
        return this.albumUrl;
    }
    ///* ALBUM *///



    ///* SONG *///
    /**
     * @return The name of the Artist
     */
    public String artistName() {
        return this.artistName;
    }
    /**
     * @return The artist's Header as an URL<br>(Mostly same as Track.artistImageUrl())
     */
    public String artistHeaderImageUrl() {
        return this.artistHeaderImageUrl;
    }
    /**
     * @return The artist's Profile Picture as an URL
     */
    public String artistImageUrl() {
        return this.artistImageUrl;
    }
    /**
     * @return The artist's Genius URL
     */
    public String artistUrl() {
        return this.artistUrl;
    }
    ///* SONG *///



    ///* MISC *///
    /**
     * @return The artist's Genius URL
     */
    public long songGID() {
        return this.songID;
    }
    public long albumGID() {
        return this.albumID;
    }
    public long artistGID() {
        return this.artistID;
    }
    public long songPageViews() {
        return this.songPageViews;
    }
    public boolean artistVerified() {
        return this.artistVerified;
    }
    ///* MISC *///

}
