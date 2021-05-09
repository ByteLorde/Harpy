package com.harpy.harpyserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommandService {

    public String[] getCommands(int capacity) {
        String[] commands = new String[capacity];

        for (int i = 0; i < capacity; i++) {
            commands[i] = "java -jar C:\\Users\\bytel\\DreamBot\\BotData\\client.jar";
        }

        return commands;
    }
}
