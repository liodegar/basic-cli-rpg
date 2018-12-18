package com.acme.rpg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for handling strings operations
 * Created by Liodegar
 */
public final class StringUtils {

    public static final String SPACE = " ";

    /**
     * To avoid instantiation from outside
     */
    private StringUtils() {
    }

    /**
     * Splits a text containing words into chunks that are less or equals to the given length
     *
     * @param text        The test containing the words
     * @param chunkLength The chunk length
     * @return a List of string
     * @throws IllegalArgumentException if the chunkLength is invalid
     */
    public static List<String> getLines(String text, int chunkLength) {
        if (chunkLength <= 0) {
            throw new IllegalArgumentException("The chunkLength is invalid=" + chunkLength);
        }
        List<String> result = new ArrayList<>();
        if (text != null && text.length() >= chunkLength) {
            String[] words = text.trim().split("\\s+");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (builder.length() + word.length() <= chunkLength) {
                    builder.append(word + SPACE);
                } else {
                    builder.deleteCharAt(builder.length() - 1);
                    result.add(builder.toString());
                    builder.setLength(0);
                    builder.append(word + SPACE);
                }

                if (i == words.length - 1) {
                    builder.deleteCharAt(builder.length() - 1);
                    result.add(builder.toString());
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

}
