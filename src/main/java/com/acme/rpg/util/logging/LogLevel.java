package com.acme.rpg.util.logging;

import com.acme.rpg.util.EnumConverter;
import com.acme.rpg.util.ReverseEnumMap;

/**
 * Created by Liodegar on 10/20/2018.
 */
public enum LogLevel implements EnumConverter<LogLevel> {
    FATAL(6, "FATAL"),
    ERROR(5, "ERROR"),
    WARN(4, "WARN"),
    INFO(3, "INFO"),
    DEBUG(2, "DEBUG"),
    TRACE(1, "TRACE");

    private static ReverseEnumMap<LogLevel> map = new ReverseEnumMap<>(LogLevel.class);
    private final byte id;
    private final String code;

    LogLevel(int id, String code) {
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
    public LogLevel convert(byte val) {
        return getFromValue(val);
    }

    @Override
    public LogLevel convert(String code) {
        return getFromCode(code);
    }

    public static LogLevel getFromValue(int val) {
        return map.get((byte) val);
    }

    public static LogLevel getFromCode(String code) {
        return map.get(code);
    }
}
