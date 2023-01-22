package ro.spotify.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.spotify.model.Artist;
import ro.spotify.model.SongsAlbums;
import ro.spotify.rest.service.ArtistService;
import ro.spotify.rest.service.SongsAlbumsService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/songcollection")
@RequiredArgsConstructor
public class SongsAlbumsController {

    private final SongsAlbumsService songsAlbumsService;
    private final ArtistService artistsService;

    @GetMapping("/songs")
    public ResponseEntity<List<SongsAlbums>> getAllSongsAlbums() {
        return ResponseEntity.ok(songsAlbumsService.findAll());
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongsAlbums> getSongsAlbums(@PathVariable Integer id) {
        return ResponseEntity.ok(songsAlbumsService.getSongsAlbums(id));
    }

    @PostMapping
    public ResponseEntity<SongsAlbums> createSongsAlbums(@RequestBody SongsAlbums songsAlbum) {
        return ResponseEntity.ok(songsAlbumsService.create(songsAlbum));
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<SongsAlbums> updateSongsAlbums(@PathVariable Integer id, @RequestBody SongsAlbums songsAlbum) {
        return ResponseEntity.ok(songsAlbumsService.update(id, songsAlbum));
    }

    @DeleteMapping("/songs/{id}")
    public void deleteSongsAlbums(@PathVariable Integer id) {
        songsAlbumsService.delete(id);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable Integer id) {
        return ResponseEntity.ok(artistsService.getArtistById(id));
    }

    @GetMapping("/artists/{id}/songs")
    public ResponseEntity<List<SongsAlbums>> getSongsFromArtist(@PathVariable Integer id) {
        return ResponseEntity.ok(artistsService.getSongsFromArtist(id));
    }

    @PostMapping("/artists/{id}/addSong")
    public ResponseEntity<Artist> addSongToArtist(@PathVariable Integer id, @RequestBody SongsAlbums songsAlbums){
        return ResponseEntity.ok(artistsService.addSongToArtist(id,songsAlbums));
    }

}
