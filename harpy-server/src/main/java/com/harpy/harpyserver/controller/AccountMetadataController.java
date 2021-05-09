package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.entity.AccountMetadata;
import com.harpy.harpyserver.repository.AccountMetadataRepository;
import com.harpy.harpyserver.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account-metadata")
public class AccountMetadataController {

    private final AccountMetadataRepository accountMetadataRepository;

    @GetMapping
    public List<AccountMetadata> getCommands() {
        return accountMetadataRepository.findAll();
    }

}
