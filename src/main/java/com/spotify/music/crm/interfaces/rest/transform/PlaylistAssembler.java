package com.spotify.music.crm.interfaces.rest.transform;

import com.spotify.music.crm.domain.model.aggregates.Playlist;
import com.spotify.music.crm.domain.model.commands.CreatePlaylistCommand;
import com.spotify.music.crm.interfaces.rest.resources.CreatePlaylistResource;
import com.spotify.music.crm.interfaces.rest.resources.PlaylistResource;

import java.util.List;
import java.util.UUID;

/**
 * Assembler for Playlist conversions.
 * @author Tu Nombre
 */
public class PlaylistAssembler {

    public static CreatePlaylistCommand toCommand(CreatePlaylistResource resource, String userId) {
        List<CreatePlaylistCommand.SongCommand> songs = resource.songs() == null ? List.of() :
                resource.songs().stream()
                        .map(s -> new CreatePlaylistCommand.SongCommand(
                                UUID.fromString(s.songId()), s.title(), s.artist(), s.duration()))
                        .toList();

        return new CreatePlaylistCommand(
                UUID.fromString(userId),
                resource.playlistName(),
                resource.description(),
                resource.isPublic(),
                songs
        );
    }

    public static PlaylistResource toResource(Playlist playlist) {
        var songs = playlist.getSongs().stream()
                .map(s -> new PlaylistResource.SongResponse(
                        s.getSongId().toString(), s.getTitle(), s.getArtist(), s.getDuration()))
                .toList();

        return new PlaylistResource(
                playlist.getId(),
                playlist.getPlaylistId().toString(),
                playlist.getUserId().toString(),
                playlist.getPlaylistName(),
                playlist.getDescription(),
                playlist.getIsPublic(),
                playlist.getAuditInfo().getCreatedAt().toString(),
                playlist.getAuditInfo().getLastModifiedAt().toString(),
                playlist.getAuditInfo().getCreatedBy(),
                songs
        );
    }
}