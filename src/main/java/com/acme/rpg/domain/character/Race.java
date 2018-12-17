package com.acme.rpg.domain.character;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The player races
 */
public enum Race implements EnumConverter<Race> {
    HUMAN(1, "Human"),
    MUTANT(2, "Mutant"),
    ROBOT(3, "Robot"),
    DWARF(4, "Dwarf"),
    ELF(5, "Elf"),
    ORC(6, "Orc");


    private static ReverseEnumMap<Race> reverseEnumMap = new ReverseEnumMap<>(Race.class);
    private final byte id;
    private final String code;

    Race(int id, String code) {
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
    public Race convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public Race convert(String code) {
        return getFromCode(code);
    }


    public static Race getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }


    public static Race getFromCode(String code) {
        return reverseEnumMap.get(code);
    }

    public static List<Race> getRaces() {
        return reverseEnumMap.getMap().entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
    }
}

