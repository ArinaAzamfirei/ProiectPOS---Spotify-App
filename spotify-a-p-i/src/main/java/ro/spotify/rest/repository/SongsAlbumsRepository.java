package ro.spotify.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.spotify.model.SongsAlbums;

public interface SongsAlbumsRepository extends JpaRepository<SongsAlbums, Integer> {
}
