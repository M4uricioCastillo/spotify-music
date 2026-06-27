package com.spotify.music.crm.interfaces.rest.resources;

import java.util.List;

/**
 * Response resource for a Playlist.
 * @author Tu Nombre
 */
public record PlaylistResource(
        Long id,
        String playlistId,
        String userId,
        String playlistName,
        String description,
        Boolean isPublic,
        String createdAt,
        String lastModifiedAt,
        String createdBy,
        List<SongResponse> songs
) {
    public record SongResponse(String songId, String title, String artist, Integer duration) {}
}