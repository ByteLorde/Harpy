package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.entity.AccountData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public List<AccountData> getAllAccounts() {
        return new ArrayList<>();
    }

}
