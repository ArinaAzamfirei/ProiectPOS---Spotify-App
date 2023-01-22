package ro.spotify.rest.service;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.spotify.model.SongsAlbums;
import ro.spotify.rest.repository.ArtistsRepository;
import ro.spotify.model.Artist;
import ro.spotify.rest.repository.SongsAlbumsRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistsRepository artistsRepository;
    private final SongsAlbumsRepository songsAlbumsRepository;

    public List<Artist> findAll() {
        return artistsRepository.findAll();
    }

    public Artist getArtistById( Integer id) {
        return artistsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Artist create(Artist artist) {
        return artistsRepository.save(artist);
    }

    public Artist update(final Integer id, Artist artist) {
        Artist artistFromDB = artistsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        artistFromDB.setName(artist.getName());
        artistFromDB.setActivityStatus(artist.getActivityStatus());
        artistFromDB.setSongsAlbums(artist.getSongsAlbums());

        return artistsRepository.save(artistFromDB);
    }

    public void delete(Integer id) {
        artistsRepository.deleteById(id);
    }

    public List<SongsAlbums> getSongsFromArtist(Integer id) {
        Artist artist = artistsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return artist.getSongsAlbums();
    }

    public Artist addSongToArtist(Integer id, SongsAlbums songsAlbums) {
        //SongsAlbums songsAlbums = songsAlbumsRepository.findById(songId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Artist artist = artistsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<SongsAlbums> listOfSongsAlbums = artist.getSongsAlbums();
        listOfSongsAlbums.add(songsAlbums);
        artist.setSongsAlbums(listOfSongsAlbums);

        songsAlbumsRepository.save(songsAlbums);
        return artistsRepository.save(artist);
    }
}
