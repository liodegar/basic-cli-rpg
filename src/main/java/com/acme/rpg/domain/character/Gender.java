package com.acme.rpg.domain.character;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The player genders
 */
public enum Gender implements EnumConverter<Gender> {
    MALE(1, "Male"),
    FEMALE(2, "Female"),
    HERMAPHODITE(3, "Hermaphrodite");

    private static ReverseEnumMap<Gender> reverseEnumMap = new ReverseEnumMap<>(Gender.class);
    private final byte id;
    private final String code;

    Gender(int id, String code) {
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
    public Gender convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public Gender convert(String code) {
        return getFromCode(code);
    }


    public static Gender getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }


    public static Gender getFromCode(String code) {
        return reverseEnumMap.get(code);
    }

    public static List<Gender> getGenders() {
        return reverseEnumMap.getMap().entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }
}
