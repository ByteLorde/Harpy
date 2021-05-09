package com.harpy.harpyclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrchestratorClient {


    public String[] fetchCommands() {
        return new String[] {
                "java -jar C:\\Users\\bytel\\DreamBot\\BotData\\client.jar",
                "java -jar C:\\Users\\bytel\\DreamBot\\BotData\\client.jar",
                "java -jar C:\\Users\\bytel\\DreamBot\\BotData\\client.jar"
        };
    }

}
