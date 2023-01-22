package com.spotify.SpotifyPlaylist.controller;

import com.spotify.SpotifyPlaylist.model.Playlist;
import com.spotify.SpotifyPlaylist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistFromId(@PathVariable String id){
        return ResponseEntity.ok(playlistService.getPlaylistFromId(id));
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.createPlaylist(playlist));
    }

    @PostMapping("/{playlistId}/addSong/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable String playlistId, @PathVariable String songId){
        return ResponseEntity.ok(playlistService.addSongToPlaylist(songId,playlistId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Playlist> getAllPlaylistsFromUserId(@PathVariable String userId ){
        return ResponseEntity.ok(playlistService.getPlaylistsFromUserId(userId));
    }
}
