package com.spotify.SpotifyPlaylist.service;

import com.spotify.SpotifyPlaylist.model.Playlist;
import com.spotify.SpotifyPlaylist.model.SongsAlbums;
import com.spotify.SpotifyPlaylist.model.SongsAlbumsDTO;
import com.spotify.SpotifyPlaylist.repository.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaylistService{
    private final PlaylistRepo playlistRepo;

    public Playlist createPlaylist(Playlist playlist){
        return playlistRepo.save(playlist);
    }

    public Playlist addSongToPlaylist(String songAlbumId, String playlistId){
        Playlist playlist = playlistRepo.findById(playlistId).orElseThrow(); // TODO: playlist not found exception
        List<SongsAlbumsDTO> listOfSongsAlbumsDTO = playlist.getListOfSongsAlbumsDTO();

        RestTemplate restTemplate = new RestTemplate();
        SongsAlbums songsAlbums = restTemplate.getForObject("http://localhost:8081/api/songcollection/songs/{id}",
                SongsAlbums.class, Map.of("id",songAlbumId));

        SongsAlbumsDTO songsAlbumsDTO = SongsAlbumsDTO.builder()
                .id(songsAlbums.getId())
                .name(songsAlbums.getName())
                .build();

       // songsAlbumsDTO.add(Link.of("http://localhost:8081/api/songcollection/songs/" + songAlbumId.toString()));
        listOfSongsAlbumsDTO.add(songsAlbumsDTO);

        playlist.setListOfSongsAlbumsDTO(listOfSongsAlbumsDTO);

        return playlistRepo.save(playlist);
    }

    public Playlist getPlaylistFromId(String id) {
        return playlistRepo.findById(id).orElseThrow(); // TODO: playlist not found exception
    }

    public Playlist getPlaylistsFromUserId(String userId) {
        return playlistRepo.findByUserId(userId);
    }
}
