package com.harpy.harpyserver.service;

import com.google.api.client.util.ArrayMap;
import com.harpy.harpyserver.entity.AccountDetails;
import com.harpy.harpyserver.model.ScriptRequirement;
import com.harpy.harpyserver.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountFulfillmentService {

    private final AccountRepository accountRepository;

    private Stream<AccountDetails> findAccountsWithRequirement(ScriptRequirement requirement) {

        final List<String> questRequirements = requirement.quests;
        final Map<String, Integer> statRequirements = requirement.stats;

        return this.accountRepository.getAvailableAccounts().stream().filter(accountData -> {

            List<String> questsCompleted = accountData.completedQuests;

            if (questRequirements != null) {
                for (String quest: questRequirements) {
                    if (!questsCompleted.contains(quest)) {
                        return false;
                    }
                }
            }

            if (statRequirements != null) {
                Map<String, Integer> accountStats = accountData.lastRecordedStats;
                for (String skillKey: statRequirements.keySet()) {
                    Integer requiredSkillLevel = statRequirements.get(skillKey);
                    Integer accountSkillLevel = accountStats.get(skillKey);
                    if (requiredSkillLevel > accountSkillLevel) {
                        return false;
                    }
                }
            }
            return true;
        });
    }

    private AccountDetails getRandomAccountWithRequirements(ScriptRequirement requirement) {
        List<AccountDetails> accountsMatching = this.findAccountsWithRequirement(requirement).collect(Collectors.toList());
        return accountsMatching.get(new Random().nextInt(accountsMatching.size()));
    }

    public List<AccountDetails> getAccounts(List<ScriptRequirement> scriptRequirement) {
        List<AccountDetails> accounts = new ArrayList<>();
        scriptRequirement.forEach(requirement -> {

                    AccountDetails accountDetails = this.getRandomAccountWithRequirements(requirement);
                    List<String> accountNamesUsed = accounts.stream().map(AccountDetails::getUsername).collect(Collectors.toList());
                    int accountsChecked = 0;
                    while (accountNamesUsed.contains(accountDetails.getUsername())) {
                        System.out.println(accountDetails.getUsername() + " already reserved in: " + accountNamesUsed);

                        accountDetails = this.getRandomAccountWithRequirements(requirement);
                        accountsChecked++;

                        if (accountsChecked  >= 100) {
                            System.out.println("Couldn't find account that satisfies: " + requirement);
                            System.out.println("Accounts: " + accounts);
                            return; // Throw error or something here
                        }
                    }
                    accounts.add(accountDetails);
                });
        return accounts;
    }

}
