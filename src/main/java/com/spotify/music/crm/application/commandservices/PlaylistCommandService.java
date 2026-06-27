package com.spotify.music.crm.application.commandservices;

import com.spotify.music.crm.domain.model.aggregates.Playlist;
import com.spotify.music.crm.domain.model.commands.CreatePlaylistCommand;

/**
 * Command service contract for Playlist operations.
 * @author Tu Nombre
 */
public interface PlaylistCommandService {
    /**
     * Handles playlist creation.
     * @param command the create command
     * @return the created Playlist
     */
    Playlist handle(CreatePlaylistCommand command);
}