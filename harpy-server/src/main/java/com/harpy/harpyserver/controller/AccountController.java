package com.harpy.harpyserver.controller;

import com.harpy.harpyserver.entity.AccountData;
import com.harpy.harpyserver.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final FirebaseService firebaseService;

    @GetMapping
    public List<AccountData> getAllAccounts() {
        return this.firebaseService.getAllAccounts();
    }

}
