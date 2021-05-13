package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.entity.AccountDetails;
import com.harpy.harpyserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping
    public List<AccountDetails> getAllAccounts() {
        return this.accountRepository.getAllAccounts();
    }

}
