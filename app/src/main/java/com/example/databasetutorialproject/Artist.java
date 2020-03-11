package com.example.databasetutorialproject;

public class Artist {

    String id;
    String artistName;
    String genre;


    public Artist (){

    }

    public Artist(String artistName, String genre, String id) {
        this.artistName = artistName;
        this.genre = genre;
        this.id = id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
