package com.acme.rpg.util.logging;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Liodegar on 10/21/2018.
 */
public class HeraLoggerFactoryTest {

    @Test
    public void getLogger() {
        RpgLogger rpgLogger = RpgLoggerFactory.getLogger("myClass");
        Assert.assertNotNull(rpgLogger);

        RpgLogger rpgLogger2 = RpgLoggerFactory.getLogger("myClass");
        Assert.assertSame(rpgLogger, rpgLogger2);
    }
}
