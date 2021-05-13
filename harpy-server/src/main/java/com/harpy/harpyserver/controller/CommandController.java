package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public List<String> getCommands(@RequestParam Integer capacity) {
        return this.commandService.getCommands(capacity);
    }
}
