package com.spotify.music.crm.application.internal.commandservices;

import com.spotify.music.crm.application.commandservices.PlaylistCommandService;
import com.spotify.music.crm.domain.model.aggregates.Playlist;
import com.spotify.music.crm.domain.model.commands.CreatePlaylistCommand;
import com.spotify.music.crm.infrastructure.persistence.jpa.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of PlaylistCommandService.
 * @author Tu Nombre
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistCommandServiceImpl implements PlaylistCommandService {

    private final PlaylistRepository playlistRepository;

    @Override
    @Transactional
    public Playlist handle(CreatePlaylistCommand command) {
        if (playlistRepository.existsByUserIdAndPlaylistName(command.userId(), command.playlistName()))
            throw new IllegalArgumentException("User already has a playlist with this name");

        var playlist = new Playlist(command);
        log.info("Creating playlist: {}", command.playlistName());
        return playlistRepository.save(playlist);
    }
}