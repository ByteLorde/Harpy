package com.harpy.harpyserver.client;

import com.harpy.harpyserver.model.AccountStats;
import com.harpy.harpyserver.model.AccountStatsFactory;
import org.springframework.stereotype.Service;

@Service
public class RunescapeClient {

    public AccountStats fetchHighscores(String username) {
        return AccountStatsFactory.createRandomPlayerStats();
    }

}
