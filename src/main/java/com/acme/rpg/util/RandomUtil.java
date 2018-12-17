package com.acme.rpg.util;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    /**
     * To avoid instantiation from outside
     */
    private RandomUtil() {
    }

    /**
     * Gets a pseudo random number from min to max (both inclusive)
     *
     * @param min The range min value
     * @param max The range max value
     * @return a random number in the specifed range
     */
    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(String.format("The min [%s] is greater than max [%s]", min, max));
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
