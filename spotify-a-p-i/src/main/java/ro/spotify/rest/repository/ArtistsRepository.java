package ro.spotify.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.spotify.model.Artist;

public interface ArtistsRepository extends JpaRepository<Artist, Integer> {
}
