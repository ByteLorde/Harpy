package com.harpy.harpyserver.model;

public enum Skill {

    ATTACK("Attack"),
    DEFENCE("Defence"),
    STRENGTH("Strength"),
    HITPOINTS("Hitpoints"),
    RANGE("Range"),
    PRAYER("Prayer"),
    MAGIC("Magic"),
    COOKING("Cooking"),
    WOODCUTTING("Woodcutting"),
    FLETCHING("Fletching"),
    FISHING("Fishing"),
    FIREMAKING("Firemaking"),
    CRAFTING("Crafting"),
    SMITHING("Smithing"),
    MINING("Mining"),
    HERBLORE("Herblore"),
    AGILITY("Agility"),
    THIEVING("Thieving"),
    SLAYER("Slayer"),
    FARMING("Farming"),
    RUNECRAFTING("Runecrafting"),
    HUNTER("Hunting"),
    CONSTRUCTION("Construction");
    private String name;

    private Skill(String name) {
        this.name = name;
    }

    public Skill forName(String name) {
        for (Skill skill : values()) {
            if (name.equalsIgnoreCase(skill.name)) {
                return skill;
            }
        }
        return null;
    }
}
