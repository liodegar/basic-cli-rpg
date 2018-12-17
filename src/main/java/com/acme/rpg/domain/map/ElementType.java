package com.acme.rpg.domain.map;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

/**
 * Element types of the game map
 * Created by Liodegar
 */
public enum ElementType implements EnumConverter<ElementType> {
    EMPTY(1, "Empty", "."),
    PLAYER(2, "Player", "X"),
    ENEMY(3, "Enemy", "$"),
    WALL(4, "", "#");

    private static ReverseEnumMap<ElementType> reverseEnumMap = new ReverseEnumMap<>(ElementType.class);
    private final byte id;
    private final String code;
    private final String character;

    ElementType(int id, String code, String character) {
        this.id = (byte) id;
        this.code = code;
        this.character = character;
    }

    @Override
    public byte getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getCharacter() {
        return character;
    }

    @Override
    public ElementType convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public ElementType convert(String code) {
        return getFromCode(code);
    }


    public static ElementType getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }


    public static ElementType getFromCode(String code) {
        return reverseEnumMap.get(code);
    }
}
