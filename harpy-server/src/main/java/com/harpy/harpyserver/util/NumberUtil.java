package com.harpy.harpyserver.util;

public class NumberUtil {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
