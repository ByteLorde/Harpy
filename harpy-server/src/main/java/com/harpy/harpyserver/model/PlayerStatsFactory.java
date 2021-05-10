package com.harpy.harpyserver.model;

import com.harpy.harpyserver.util.NumberUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PlayerStatsFactory {

    public static PlayerStats createRandomPlayerStats() {
        PlayerStats playerStats = new PlayerStats();
        Field[] fields = playerStats.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.set(playerStats, NumberUtil.getRandomNumber(1, 99));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Arrays.stream(fields).forEach(field -> {
            try {
                System.out.println("Field: " + field.getName() + " Value: " + field.get(playerStats));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return playerStats;
    }
}
