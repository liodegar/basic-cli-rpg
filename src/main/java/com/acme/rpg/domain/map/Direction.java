package com.acme.rpg.domain.map;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

/**
 * Indicates the direction to where the player will be moved in the map
 * Created by Liodegar
 */
public enum Direction implements EnumConverter<Direction> {
    DOWN(1, "D"),
    UP(2, "U"),
    RIGHT(3, "R"),
    LEFT(4, "L");

    private static ReverseEnumMap<Direction> reverseEnumMap = new ReverseEnumMap<>(Direction.class);
    private final byte id;
    private final String code;

    Direction(int id, String code) {
        this.id = (byte) id;
        this.code = code;
    }

    @Override
    public byte getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }


    @Override
    public Direction convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public Direction convert(String code) {
        return getFromCode(code);
    }

    public static Direction getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }

    public static Direction getFromCode(String code) {
        return reverseEnumMap.get(code.toUpperCase());
    }

}
