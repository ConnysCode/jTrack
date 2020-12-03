package com.github.connyscode.ctils.jTrack;

import com.github.connyscode.ctils.jTrack.backend.Messaging;
import com.github.connyscode.ctils.jTrack.backend.types.TrackSearchCluster;
import com.github.connyscode.ctils.jTrack.backend.types.SearchResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.connyscode.ctils.jTrack.backend.jSoupUtils.getDoc;

public class jTrackClient {

    /**
     * Perform a Search for Tracks with parameters
     *
     * @param searchPhrase A Search Phrase
     * @return A List of SearchResults with a max. of 5 items
     * @see SearchResult
     */
    public List<SearchResult> performSearch(String searchPhrase) {
        return GetTrackSearch(new TrackSearchCluster(searchPhrase));
    }

    /**
     * Perform a Search for Tracks with parameters
     *
     * @param songAuthor Song Author
     * @param songName   Song Name
     * @return A List of SearchResults with a max. of 5 items
     * @see SearchResult
     */
    public List<SearchResult> performSearch(String songAuthor, String songName) {
        return GetTrackSearch(new TrackSearchCluster(songName, songAuthor));
    }

    /**
     * Get a List of SearchResults out of a SearchCluster
     *
     * @param searchCluster TrackSearchCluster with Song Name and Song Author
     * @return A List of SearchResults with a max. of 5 items
     * @see SearchResult
     */
    private List<SearchResult> GetTrackSearch(TrackSearchCluster searchCluster) {
        Document doc = null;
        List<SearchResult> searchResults = new ArrayList<>();
        StringBuilder useURL = new StringBuilder().append("https://search.azlyrics.com/search.php?q=");
        if (!searchCluster.getSearchPhrase().equalsIgnoreCase("")) {
            useURL.append(searchCluster.getSearchPhrase());
        } else {
            if (!searchCluster.getSongName().equalsIgnoreCase(""))
                useURL.append(searchCluster.getSearchPhrase()).append(" ").append(searchCluster.getSearchPhrase());
        }
        try {
            doc = getDoc(useURL.toString());

            if (doc == null) {
                Messaging.error("L6", "Could not read document! Please check your connection!");
                searchResults.add(new SearchResult(false));
                return searchResults;
            }

            Elements songCards = doc.select(".text-left.visitedlyr");
            int end = Math.min(songCards.size(), 5);
            Messaging.info("Song-Search powered by www.azlyrics.com!");
            for (int i = 0; i < end; i++) {
                Element songCard = songCards.get(i);
                searchResults.add(new SearchResult(
                        songCard.select("b").get(0).text().replaceAll("\"", ""),
                        songCard.select("b").get(1).text(),
                        songCard.select("a").attr("href")));
            }
            if (searchResults.size() == 0) searchResults.add(new SearchResult(false));
            return searchResults;
        } catch (IOException e) {
            searchResults.add(new SearchResult(false));
            Messaging.error("L7-IO", "Fetching of >" + useURL + "< was unsuccessful! Check your connection! If the error persists, please open an issue on GitHub!");
            return searchResults;
        } catch (IndexOutOfBoundsException e) {
            searchResults.add(new SearchResult(false));
            Messaging.error("L7-IOOB", "Well that should not have happened.. If the error persists, please open an issue on GitHub!");
            return searchResults;
        }
    }

    private Track GetTrackDetails(SearchResult searchResult) {
        Document doc;
        try {
            String useURL = searchResult.songUrl;
            doc = getDoc(useURL);

            if (doc == null) {
                Messaging.error("L3", "Could not read document! Please check your connection!");
                return new Track("", "", "", "", "www.azlyrics.com");
            }

            Elements songLyricsPit = doc.select(".col-xs-12.col-lg-8.text-center div");

            String lyrics = songLyricsPit.get(5).html().replaceAll("<br>", "").replaceAll("<!--(.*?)-->", "");

            Messaging.info("[jTrack] Song-Data of \"" + searchResult.songTitle + "\" powered by www.azlyrics.com!");
            return new Track(searchResult.songTitle, searchResult.songAuthor, lyrics, searchResult.songUrl, "www.azlyrics.com");
        } catch (IOException e) {
            Messaging.error("L5-IO", "Fetching of >" + searchResult.songUrl + "< was unsuccessful! Check your connection! If the error persists, please open an issue on GitHub!");
            return new Track("", "", "", "", "www.azlyrics.com");
        } catch (IndexOutOfBoundsException e) {
            Messaging.error("L5-IOOB", "Well that should not have happened.. If the error persists, please open an issue on GitHub!");
            return new Track("", "", "", "", "www.azlyrics.com");
        }
    }


    /**
     * Get a Track out of a SearchResult
     *
     * @param result A single SearchResult
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Track
     */
    public Track getTrack(SearchResult result) {
        if (result.wasSuccessful()) {
            return GetTrackDetails(result);
        } else {
            Messaging.error("L4", "SearchResult was unsuccessful! Please try again.");
            return new Track();
        }
    }

    /**
     * Get a Track out of a SearchResult
     *
     * @param result A list of SearchResults
     * @param index  The Index of the SearchResult that you want to read
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Track
     */
    public Track getTrack(List<SearchResult> result, int index) {
        if (result.get(index).wasSuccessful()) {
            return GetTrackDetails(result.get(index));
        } else {
            Messaging.error("L4", "SearchResult was unsuccessful! Please try again.");
            return new Track();
        }
    }

    /**
     * Get a Track out of a SearchResult
     * Will always use Index 0 of 'result'
     *
     * @param result A list of SearchResults
     * @return The Track that holds all Info like: Author, Title, ...
     * @see Track
     */
    public Track getTrack(List<SearchResult> result) {
        if (result.get(0).wasSuccessful()) {
            return GetTrackDetails(result.get(0));
        } else {
            Messaging.error("L4", "SearchResult was unsuccessful! Please try again.");
            return new Track();
        }
    }


}
