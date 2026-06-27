package com.spotify.music.crm.interfaces.rest;

import com.spotify.music.crm.application.commandservices.PlaylistCommandService;
import com.spotify.music.crm.interfaces.rest.resources.CreatePlaylistResource;
import com.spotify.music.crm.interfaces.rest.transform.PlaylistAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for Playlist operations.
 * @author Tu Nombre
 */
@RestController
@RequestMapping("/api/v1/users/{userId}/playlists")
@RequiredArgsConstructor
@Tag(name = "Playlists", description = "Playlist management endpoints")
public class PlaylistsController {

    private final PlaylistCommandService playlistCommandService;

    /**
     * Creates a new playlist for a user.
     * @param userId the user UUID from path
     * @param resource the playlist creation payload
     * @return 201 with created playlist
     */
    @PostMapping
    @Operation(summary = "Create a playlist")
    @ApiResponse(responseCode = "201", description = "Playlist created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Duplicate playlist name")
    public ResponseEntity<?> createPlaylist(
            @PathVariable String userId,
            @Valid @RequestBody CreatePlaylistResource resource) {

        var command = PlaylistAssembler.toCommand(resource, userId);
        var playlist = playlistCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PlaylistAssembler.toResource(playlist));
    }
}