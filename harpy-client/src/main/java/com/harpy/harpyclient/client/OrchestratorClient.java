package com.harpy.harpyclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class OrchestratorClient {

    private final RestTemplate restTemplate;

    public String[] fetchCommands() {
        ResponseEntity<String[]> commandsFromServer = this.restTemplate.exchange("http://localhost:8080/command?capacity=3", HttpMethod.GET, null, String[].class);
        return commandsFromServer.getBody();
    }

}
