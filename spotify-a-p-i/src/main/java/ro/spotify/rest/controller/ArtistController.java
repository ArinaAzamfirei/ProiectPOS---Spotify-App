package ro.spotify.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.spotify.model.Artist;
import ro.spotify.rest.service.ArtistService;


import java.util.List;


@RestController
@RequestMapping(value = "/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistsService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtist() {
        return ResponseEntity.ok(artistsService.findAll());
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return ResponseEntity.ok(artistsService.create(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Integer id, @RequestBody Artist artist) {
        return ResponseEntity.ok(artistsService.update(id, artist));
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Integer id) {
         artistsService.delete(id);
    }

}
