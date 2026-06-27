package com.spotify.music.crm.domain.model.aggregates;

import com.spotify.music.crm.domain.model.commands.CreatePlaylistCommand;
import com.spotify.music.crm.domain.model.entities.Song;
import com.spotify.music.crm.domain.model.valueobjects.AuditInfo;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root for Playlist.
 * @author Tu Nombre
 */
@Getter
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID playlistId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 100)
    private String playlistName;

    @Column(length = 250)
    private String description;

    @Column(nullable = false)
    private Boolean isPublic;

    @Embedded
    private AuditInfo auditInfo;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

    protected Playlist() {}

    /**
     * Creates a Playlist from a command.
     * @param command the create command
     */
    public Playlist(CreatePlaylistCommand command) {
        this.playlistId = UUID.randomUUID();
        this.userId = command.userId();
        this.playlistName = command.playlistName();
        this.description = command.description();
        this.isPublic = command.isPublic();
        this.auditInfo = new AuditInfo(command.userId().toString());

        if (command.songs() != null) {
            for (var s : command.songs()) {
                var song = new Song(s.songId(), s.title(), s.artist(), s.duration());
                song.setPlaylist(this);
                this.songs.add(song);
            }
        }
    }
}