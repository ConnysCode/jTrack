<div align="center">
<img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.github.connyscode.ctils/jtrack?style=for-the-badge">
<img alt="Maintenance" src="https://img.shields.io/maintenance/yes/2020?style=for-the-badge">
<img alt="GitHub" src="https://img.shields.io/github/license/connyscode/jtrack?style=for-the-badge">
</div>

# jTrack

jTrack is a small and powerful library for collecting data of tracks.  
Titles, Authors, Lyrics,...


## How to use

```Java
jTrackClient client = new jTrackClient();
List<SearchResult> searchResults = client.performSearch("Gimme! Gimme! Gimme!");
// List<SearchResult> searchResults = client.performSearch("ABBA", "Gimme"); -> More specific
Track track = client.getTrack(searchResults);
System.out.println(track.songText());
System.out.println(track.songArtist());
```

<br>

## Some tips
### Perform Search
```java
client.performSearch(String searchPhrase);
client.performSearch(String songName, String songAuthor);
```


### Get Track Infos
You have several options to get the information of a track out of a ``SearchResult``.
```java
List<SearchResult> searchResults = client.performSearch("Gimme! Gimme! Gimme!");
// searchResults Index 0: "Gimme! Gimme! Gimme! (A Man After Midnight)"
// searchResults Index 1: "Gimme What I Want"
// searchResults Index 2: "Gimme Gimme Gimme"

Track track;
track = client.getTrack(searchResults);         // "Gimme What I Want"
track = client.getTrack(searchResults.get(1));  // "Gimme! Gimme! Gimme! (A Man After Midnight)"
track = client.getTrack(searchResults, 2);      // "Gimme Gimme Gimme"
```
> All of these three functions do the same thing, but are here to give you the best and fastest solution depending on your particular situation.

## Installation

jTrack is a part of the Maven Central Repository. So you can easily use jTrack by adding this to your dependencies:

```xml
<dependency>
    <!-- jTrack - Collecting Track Data @ https://github.com/ConnysCode/jTrack -->
    <groupId>com.github.connyscode.ctils</groupId>
    <artifactId>jtrack</artifactId>
        <version>version</version>
</dependency>
```
![Maven Central](https://img.shields.io/maven-central/v/com.github.connyscode.ctils/jtrack)

## License
[MIT](https://choosealicense.com/licenses/mit/)
