package com.harpy.harpyclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Service
public class CommandHandlerService {

    @Async
    public void handleCommandAsync(String command) {
        this.handleCommand(command);
    }

    public void handleCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
