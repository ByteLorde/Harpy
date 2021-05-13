package com.harpy.harpyserver.model;

import com.harpy.harpyserver.util.NumberUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AccountStatsFactory {

    public static AccountStats createRandomPlayerStats() {
        AccountStats accountStats = new AccountStats();
        Field[] fields = accountStats.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.set(accountStats, NumberUtil.getRandomNumber(1, 99));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Arrays.stream(fields).forEach(field -> {
            try {
                System.out.println("Field: " + field.getName() + " Value: " + field.get(accountStats));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return accountStats;
    }
}
