package com.spotify.music.crm.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

/**
 * Entity representing a song in a playlist.
 * @author Tu Nombre
 */
@Getter
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID songId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private com.spotify.music.crm.domain.model.aggregates.Playlist playlist;

    protected Song() {}

    public Song(UUID songId, String title, String artist, Integer duration) {
        if (songId == null) throw new IllegalArgumentException("Song ID cannot be null");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        if (artist == null || artist.isBlank()) throw new IllegalArgumentException("Artist cannot be blank");
        if (duration == null || duration <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public void setPlaylist(com.spotify.music.crm.domain.model.aggregates.Playlist playlist) {
        this.playlist = playlist;
    }
}