package com.harpy.harpyserver.service;

import com.harpy.harpyserver.entity.AccountDetails;
import com.harpy.harpyserver.model.ScriptRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final AccountFulfillmentService accountFulfillmentService;
    private final TaskService taskService;
    private final DreambotCommandMapper dreambotCommandMapper;

    public List<String> getCommands(Integer capacity) {
        List<ScriptRequirement> scriptRequirements = this.taskService.getScriptRequirements(capacity);
        List<AccountDetails> accounts = this.accountFulfillmentService.getAccounts(scriptRequirements);

        return accounts.stream()
                .map(this.dreambotCommandMapper::map)
                .map(this.dreambotCommandMapper::map)
                .collect(Collectors.toList());
    }

}
