package com.github.connyscode.ctils.jTrack;

import com.github.connyscode.ctils.jTrack.backend.ArtistNotFoundException;
import com.github.connyscode.ctils.jTrack.backend.GeniusAPI;
import com.github.connyscode.ctils.jTrack.backend.SongNotFoundException;
import com.github.connyscode.ctils.jTrack.backend.types.SongSearchResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class jTrackClient {

    private String ACCESS_TOKEN;

    public jTrackClient(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    /**
     * Perform a Search for Songs with parameters
     *
     * @param searchPhrase A Search Phrase
     * @see SongSearchResult
     */
    public List<SongSearchResult> performSongSearch(String searchPhrase) {
        return GetSongSearch(Objects.requireNonNull(GeniusAPI.performSongSearch(searchPhrase, ACCESS_TOKEN)));
    }

    /**
     * Get a List of SearchResults
     *
     * @param jsonSearchResults jsonArray containing all search results
     * @return A List of SearchResults
     * @see SongSearchResult
     */
    private List<SongSearchResult> GetSongSearch(JSONArray jsonSearchResults) {
        List<SongSearchResult> results = new ArrayList<>();
        for (Object jsonSearchResult : jsonSearchResults) {
            if ((((JSONObject) jsonSearchResult).get("type")).equals("song")) {
                JSONObject object = (JSONObject) ((JSONObject) jsonSearchResult).get("result");
                results.add(new SongSearchResult(object.get("full_title").toString(), object.get("url").toString(), object.get("song_art_image_url").toString(), Integer.parseInt(object.get("id").toString())));
            }
        }
        return results;
    }


    private Song GetSongDetails(long songID) throws SongNotFoundException {
        return GeniusAPI.parseSong(songID, ACCESS_TOKEN);
    }
    private Artist GetArtistDetails(long artistID) throws ArtistNotFoundException {
        return GeniusAPI.parseArtist(artistID, ACCESS_TOKEN);
    }

    /**
     * Get a Song out of a SearchResult
     *
     * @param result A single SearchResult
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Song
     */
    public Song getSong(SongSearchResult result) throws SongNotFoundException {
        return GetSongDetails(result.id);
    }

    /**
     * Get a Song out of a songID
     *
     * @param songID A song ID
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Song
     */
    public Song getSong(long songID) throws SongNotFoundException {
        return GetSongDetails(songID);
    }

    /**
     * Get an Artist out of an artistGID
     *
     * @param artistGID An artistGID
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Song
     */
    public Artist getArtist(long artistGID) throws ArtistNotFoundException {
        return GetArtistDetails(artistGID);
    }
}
