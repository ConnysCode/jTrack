
<div align="center">
<a href="https://search.maven.org/search?q=g:com.github.connyscode.ctils"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.github.connyscode.ctils/jtrack?style=for-the-badge"></a>
    <img alt="Maintenance" src="https://img.shields.io/maintenance/yes/2020?style=for-the-badge">
<img alt="GitHub" src="https://img.shields.io/github/license/connyscode/jtrack?style=for-the-badge">
</div>

# jTrack

jTrack is a small and simple Java api to access information about tracks and artists from the Genius service.<br>
Titles, Authors, Lyrics,...


## How to use
To use jTrack you need a Genius API Client and an ``ACCESS_TOKEN``. You can generate this [here](https://genius.com/api-clients/new).<br>
After you created an API Client, you need to generate an ``ACCESS_TOKEN``! Do not give this token to anyone.
> ``ICON URL``, ``APP WEBSITE URL`` and ``REDIRECT URI`` are not important.
### Create a jTrackClient
```Java
jTrackClient client = new jTrackClient("ACCESS_TOKEN");
List<SearchResult> searchResults = client.performSongSearch("Die Coolsten!");
```

### Read a Track
```Java
Song song = client.getSong(searchResults.get(0));
System.out.println(song.songLyrics()); // Prints the Lyrics
System.out.println(song.songName()); // Prints the Song's Name
```

### Read an Artist
```Java
Artist artist = client.getSong(1648251);
Artist artist = client.getSong(song.artistGID());
System.out.println(artist.artistName()); // Prints the Artist's Name
System.out.println(artist.artistDescription()); // Prints the Artist's Description
```

#### Look at the JavaDocs <a href="https://connyscode.github.io/jTrack/">here</a>!

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

<br><br>

# jTrack 1.0.5.1 and lower

jTrack is a small and powerful library for collecting data of tracks.  
Titles, Authors, Lyrics,...


## How to use

```Java
jTrackClient client = new jTrackClient();
List<SearchResult> searchResults = client.performSearch("Gimme! Gimme! Gimme!");

Track track = client.getTrack(searchResults);
System.out.println(track.songText());
```


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
> All of these three functions do the same thing, but are here to give you the best and fastest solution depending on your particular situation.## Installation

## Installation

```xml
<dependency>
    <!-- jTrack - Collecting Track Data @ https://github.com/ConnysCode/jTrack -->
    <groupId>com.github.connyscode.ctils</groupId>
    <artifactId>jtrack</artifactId>
        <version>1.0.5.1</version>
</dependency>
```
![Maven Central](https://img.shields.io/maven-central/v/com.github.connyscode.ctils/jtrack)
## License
[MIT](https://choosealicense.com/licenses/mit/)
