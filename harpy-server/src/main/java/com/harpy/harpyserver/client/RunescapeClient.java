package com.harpy.harpyserver.client;

import com.harpy.harpyserver.model.PlayerStats;
import com.harpy.harpyserver.model.PlayerStatsFactory;
import org.springframework.stereotype.Service;

@Service
public class RunescapeClient {

    public PlayerStats fetchHighscores(String username) {
        return PlayerStatsFactory.createRandomPlayerStats();
    }

}
