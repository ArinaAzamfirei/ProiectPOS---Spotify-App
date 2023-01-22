package com.spotify.SpotifyPlaylist.repository;

import com.spotify.SpotifyPlaylist.model.Playlist;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PlaylistRepo extends MongoRepository<Playlist, String> {
    @Query("{ 'userId': '?0'}")
    Playlist findByUserId(String userId);

    public long count();

}