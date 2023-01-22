package com.spotify.SpotifyPlaylist.model;

import lombok.Data;

import java.util.List;

@Data
public class SongsAlbums {

    private Integer id;

    private String name;

    private String genre;

    private Integer apparitionYear;

    private String type;

    //private List<Artist> listOfArtists;
}
