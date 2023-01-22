package ro.spotify.rest.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.spotify.rest.repository.SongsAlbumsRepository;
import ro.spotify.model.SongsAlbums;

import java.util.List;


@Service
@AllArgsConstructor
public class SongsAlbumsService {

    private final SongsAlbumsRepository songsAlbumsRepository;
    public List<SongsAlbums> findAll() {
        return songsAlbumsRepository.findAll();
    }

    public SongsAlbums getSongsAlbums(Integer id){
        return songsAlbumsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public SongsAlbums create(SongsAlbums songsAlbum) {
        return songsAlbumsRepository.save(songsAlbum);
    }

    public SongsAlbums update(Integer id, SongsAlbums songsAlbum) {
        SongsAlbums songsAlbumsFromDB = songsAlbumsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        songsAlbumsFromDB.setName(songsAlbum.getName());
        songsAlbumsFromDB.setType(songsAlbum.getType());
        songsAlbumsFromDB.setGenre(songsAlbum.getGenre());
        songsAlbumsFromDB.setApparitionYear(songsAlbum.getApparitionYear());
        //songsAlbumsFromDB.setArtists(songsAlbum.getArtists());

        return songsAlbumsRepository.save(songsAlbumsFromDB);
    }

    public void delete(Integer id) {
        songsAlbumsRepository.deleteById(id);
    }
}
