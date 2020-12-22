package com.github.connyscode.ctils.jTrack;

import com.github.connyscode.ctils.jTrack.backend.GeniusAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.github.connyscode.ctils.jTrack.backend.Messaging.error;

public class Artist {

    private String artistName = "", artistUrl = "https://genius.com/", artistHeaderImageUrl = "https://genius.com/", artistImageUrl = "https://genius.com/", artistDescription = "";
    private long artistID = 0, artistFollowers = 0;
    private List<String> alternateNames = new ArrayList<>();
    private boolean artistVerified = false;

    /**
     * Create a Track
     *
     * @param trackObject song response from genius
     * @see Artist
     */
    public Artist(JSONObject trackObject) {
        try {
            JSONObject artist = trackObject;

            // Artist
            artistName = (String) artist.get("name");
            artistUrl = (String) artist.get("url");
            artistHeaderImageUrl = (String) artist.get("header_image_url");
            artistImageUrl = (String) artist.get("image_url");
            artistID = (long) artist.get("id");
            artistVerified = (boolean) artist.get("is_verified");
            if (artist.get("description") != null)
                artistDescription = (String) ((JSONObject) artist.get("description")).get("plain");
            artistFollowers = (long) artist.get("followers_count");

            JSONArray alts = (JSONArray) artist.get("alternate_names");
            for(int i = 0; i < alts.size(); i++){
                alternateNames.add((String) alts.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("t01", "Could not read complete Track data!");
        }
    }

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

    /**
     * @return The artist's Description
     */
    public String artistDescription() {
        return this.artistDescription;
    }

    /**
     * @return The artist's follower count
     */
    public long artistFollowers() {
        return this.artistFollowers;
    }

    /**
     * @return The artist's alternate Names
     */
    public List<String> alternateNames() {
        return this.alternateNames;
    }

    /**
     * @return The artist's Genius URL
     */
    public long artistGID() {
        return this.artistID;
    }

    /**
     * @return Is Artist verified
     */
    public boolean artistVerified() {
        return this.artistVerified;
    }
    ///* MISC *///

}
