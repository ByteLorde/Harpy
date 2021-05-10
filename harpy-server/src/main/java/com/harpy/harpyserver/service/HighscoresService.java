package com.harpy.harpyserver.service;

import com.harpy.harpyserver.client.RunescapeClient;
import com.harpy.harpyserver.model.PlayerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HighscoresService {

    private final RunescapeClient runescapeClient;

    public PlayerStats getStatsForPlayer(String username) {
        return this.runescapeClient.fetchHighscores(username);
    }

}

