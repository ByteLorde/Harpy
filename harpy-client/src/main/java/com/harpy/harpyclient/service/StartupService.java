package com.harpy.harpyclient.service;

import com.harpy.harpyclient.client.OrchestratorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class StartupService {

    private final OrchestratorClient orchestratorClient;
    private final CommandHandlerService commandHandlerService;

    @PostConstruct
    public void onStart() {
        String[] commands = this.orchestratorClient.fetchCommands();
        this.handleCommands(commands);
    }

    public void handleCommands(String[] commands) {
        Arrays.stream(commands).forEach(this.commandHandlerService::handleCommandAsync);
    }
}
