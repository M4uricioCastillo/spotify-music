package com.spotify.music.crm.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * Request resource for creating a playlist.
 * @author Tu Nombre
 */
public record CreatePlaylistResource(
        @NotBlank(message = "Playlist name is required")
        @Size(max = 100, message = "Playlist name must not exceed 100 characters")
        String playlistName,

        @Size(max = 250, message = "Description must not exceed 250 characters")
        String description,

        @NotNull(message = "isPublic is required")
        Boolean isPublic,

        List<SongResource> songs
) {
    public record SongResource(
            @NotNull String songId,
            @NotBlank String title,
            @NotBlank String artist,
            @NotNull Integer duration
    ) {}
}