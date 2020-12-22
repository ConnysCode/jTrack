package com.github.connyscode.ctils.jTrack.backend;

import com.github.connyscode.ctils.jTrack.Artist;
import com.github.connyscode.ctils.jTrack.Song;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.github.connyscode.ctils.jTrack.backend.Messaging.error;
import static com.github.connyscode.ctils.jTrack.backend.jSoupUtils.getDoc;

public class GeniusAPI {

    public static JSONArray performSongSearch(String searchPhrase, String ACCESS_TOKEN) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.genius.com/search?q=" + searchPhrase)
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            int responseCode = response.code();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                JSONParser parse = new JSONParser();
                JSONObject responseJSON = (JSONObject) ((JSONObject) parse.parse(response.body().string())).get("response");

                return (JSONArray) responseJSON.get("hits");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Song parseSong(long trackID, String ACCESS_TOKEN) throws SongNotFoundException {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.genius.com/songs/" + trackID + "?text_format=plain")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            int responseCode = response.code();
            if (responseCode != 200) {
                throw new SongNotFoundException("Song not found! - " + responseCode);
            } else {

                JSONParser parse = new JSONParser();
                JSONObject responseJSON = (JSONObject) ((JSONObject) parse.parse(response.body().string())).get("response");

                return new Song((JSONObject) responseJSON.get("song"));
            }
        } catch (IOException | ParseException e) {
            error("s01", "Song could not be recieved!");
            return null;
        }
    }

    public static Artist parseArtist(long artistID, String ACCESS_TOKEN) throws ArtistNotFoundException {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.genius.com/artists/" + artistID + "?text_format=plain")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();

            int responseCode = response.code();
            if (responseCode != 200) {
                throw new ArtistNotFoundException("Artist not found! - " + responseCode);
            } else {

                JSONParser parse = new JSONParser();
                JSONObject responseJSON = (JSONObject) ((JSONObject) parse.parse(response.body().string())).get("response");

                return new Artist((JSONObject) responseJSON.get("artist"));
            }
        } catch (IOException | ParseException e) {
            error("a01", "Artist could not be recieved!");
            return null;
        }
    }

    public static String getLyrics(String url) {
        Document doc;
        try {
            doc = getDoc(url);

            if (doc == null) {
                error("L3", "Could not read document! Please check your connection!");
                return "null";
            }
            Elements songLyricsPit = doc.select(".lyrics");
            String output;
            if (songLyricsPit.size() < 1) {
                songLyricsPit = doc.select(".SongPage__Section-sc-19xhmoi-3.cXvCRB");
                String prettyPrintedBodyFragment = Jsoup.clean(songLyricsPit.get(0).html(), "", Whitelist.none().addTags("br", "p"), new Document.OutputSettings().prettyPrint(true));
                output = Jsoup.clean(prettyPrintedBodyFragment, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
                output = output.replaceAll("(             )", "\n").replaceAll("\n ", "\n");
            } else {
                output = songLyricsPit.get(0).html().replaceAll("<br>", "\n");
                output = output.replaceAll("<[^>]*>", "").replaceFirst("\n", "");
            }
            return output.replaceAll("&amp;", "&");

        } catch (IOException e) {
            error("L5-IO", "Fetching of >" + url + "< was unsuccessful! Check your connection! If the error persists, please open an issue on GitHub!");
            return "null";
        } catch (IndexOutOfBoundsException e) {
            error("L5-IOOB", "Well that should not have happened.. If the error persists, please open an issue on GitHub!");
            return "null";
        }
    }

}
