package com.spotify.music.crm.domain.model.commands;

import java.util.List;
import java.util.UUID;

/**
 * Command for creating a playlist.
 * @param userId the user UUID
 * @param playlistName the playlist name
 * @param description optional description
 * @param isPublic whether the playlist is public
 * @param songs list of song commands
 * @author Tu Nombre
 */
public record CreatePlaylistCommand(
        UUID userId,
        String playlistName,
        String description,
        Boolean isPublic,
        List<SongCommand> songs
) {
    public record SongCommand(UUID songId, String title, String artist, Integer duration) {}
}