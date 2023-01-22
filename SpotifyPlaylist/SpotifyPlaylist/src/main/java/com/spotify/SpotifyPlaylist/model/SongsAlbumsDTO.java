package com.spotify.SpotifyPlaylist.model;

import lombok.Builder;
import org.springframework.hateoas.RepresentationModel;

@Builder
public class SongsAlbumsDTO /*extends RepresentationModel<SongsAlbumsDTO>*/{
    private Integer id;
    private String name;
}
