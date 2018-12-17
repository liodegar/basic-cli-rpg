package com.acme.rpg.domain.character;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

import java.util.Comparator;

/**
 * The game levels
 */
public enum Level implements EnumConverter<Level> {
    ONE(1, "One", 0),
    TWO(2, "Two", 70),
    THREE(3, "Three", 150),
    FOUR(4, "Four", 230),
    FIVE(5, "Five", 280);

    private static ReverseEnumMap<Level> reverseEnumMap = new ReverseEnumMap<>(Level.class);
    private final byte id;
    private final String code;
    private final int experience;

    Level(int id, String code, int requiredExperience) {
        this.id = (byte) id;
        this.code = code;
        this.experience = requiredExperience;
    }

    @Override
    public byte getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public Level convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public Level convert(String code) {
        return getFromCode(code);
    }

    public static Level getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }

    public static Level getNextLevel(Level level) {
        return getFromValue(level.getId() + 1);
    }

    public static Level getHighestLevel() {
        return reverseEnumMap.getMap().values()
                .stream().max(Comparator.comparing(Level::getId))
                .orElse(null);
    }


    public static Level getFromCode(String code) {
        return reverseEnumMap.get(code);
    }
}
