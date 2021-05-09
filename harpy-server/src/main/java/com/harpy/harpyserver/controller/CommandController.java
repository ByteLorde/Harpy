package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public String[] getCommands(@RequestParam int capacity) {
        return commandService.getCommands(capacity);
    }

}
