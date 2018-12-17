package com.acme.rpg.util;

import com.acme.rpg.util.logging.RpgLogger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Liodegar
 */
public final class ConcurrentUtil {

    public static final RpgLogger logger = RpgLogger.getLogger(ConcurrentUtil.class);

    /**
     * To avoid instantiation from outside
     */
    private ConcurrentUtil() {
    }

    public static final void sleepForSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            logger.error("Exception during sleep operation", e);
        }
    }

    public static final void sleepForMilliSeconds(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("Exception during sleep operation", e);
        }
    }
}
