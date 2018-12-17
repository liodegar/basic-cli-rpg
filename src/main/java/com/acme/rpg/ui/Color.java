package com.acme.rpg.ui;

import com.acme.rpg.domain.character.Level;
import com.acme.rpg.domain.character.Race;
import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

/**
 * Created by Liodegar
 */
public enum Color implements EnumConverter<Color> {

    RESET(0, "Reset"),
    BOLD(1, "Bold"),
    ITALIC(3, "Italic"),
    UNDERLINE(4, "Underline"),

    BLACK(30, "Black"),
    RED(31, "Red"),
    GREEN(32, "Green"),
    YELLOW(33, "Yellow"),
    BLUE(34, "Blue"),
    MAGENTA(35, "Magenta"),
    CYAN(36, "Cyan"),
    WHITE(37, "White"),

    STRONG_GREEN(922, "Strong creen");

    private static ReverseEnumMap<Color> reverseEnumMap = new ReverseEnumMap<>(Color.class);
    private final byte id;
    private final String code;

    Color(int id, String code) {
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
    public Color convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public Color convert(String code) {
        return getFromCode(code);
    }

    public static Color getFromValue(int val) {
        return reverseEnumMap.get((byte) val);
    }

    public static Color getFromCode(String code) {
        return reverseEnumMap.get(code);
    }

    public String format(String input) {
        return new StringBuilder(100)
                .append((char) 27)
                .append("[").append(getId()).append("m")
                .append(input)
                .append((char) 27)
                .append("[")
                .append(RESET.getId())
                .append("m").toString();
    }

}
