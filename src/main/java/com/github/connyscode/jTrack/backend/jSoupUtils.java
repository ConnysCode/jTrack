package com.github.connyscode.jTrack.backend;


import com.github.connyscode.jTrack.backend.types.TrackSearchCluster;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class jSoupUtils {

    /**
     * Get a Document from an URL
     *
     * @param  url     Destination URL
     * @see Document
     */
    public static Document getDoc(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .get();
    }

}
