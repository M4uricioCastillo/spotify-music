package com.spotify.music.crm.infrastructure.persistence.jpa;

import com.spotify.music.crm.domain.model.aggregates.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Playlist aggregate.
 * @author Tu Nombre
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    boolean existsByUserIdAndPlaylistName(UUID userId, String playlistName);
}