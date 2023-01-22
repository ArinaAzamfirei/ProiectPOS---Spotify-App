package com.spotify.SpotifyPlaylist;

import com.spotify.SpotifyPlaylist.repository.PlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = PlaylistRepo.class)
public class SpotifyPlaylistApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpotifyPlaylistApplication.class, args);
	}
}
