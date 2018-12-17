/**
 * Copyright 2004 CANTV. All rights reserved.
 * CANTV PROPRIETARY/CONFIDENTIAL. Use is subject to license
 * terms.
 * <p>
 * This software is the confidential and proprietary information
 * of CANTV. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered
 * into with CANTV.
 * <p>
 * Creation date: 2004-10-20
 */
package com.acme.rpg.util.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * Class RpgLoggerFactory
 * This is the factory to create new instances of RpgLogger.
 * The creation of a RpgLogger also creates the wrapped object (an implementation of UniversalLogger)
 * to which the method calls are delegated.
 *
 * @author Liodegar Bracamonte
 */
public final class RpgLoggerFactory {

    /**
     * The default logging class name.
     */
    private static final String DEFAULT_LOGGING_CLASS_NAME = "com.acme.rpg.util.logging.StdoutLogger";

    /**
     * Position to stores the RpgLogger objects
     */
    private static Map<String, RpgLogger> loggers = new HashMap<>();

    /**
     * To avoid the instantiation outside this class
     */
    private RpgLoggerFactory() {
    }

    /**
     * Gets a RpgLogger objects using a logger name
     *
     * @param name The RpgLogger's name
     * @return RpgLogger A RpgLogger object
     */
    public static synchronized RpgLogger getLogger(String name) {
        return loggers.computeIfAbsent(name, key -> new RpgLogger(createUniversalLogger(key)));
    }


    /**
     * Returns a UniversalLogger object using a class name defined in cantv.properties
     * This method is intended to be used to create any UniversalLogger object.
     *
     * @param name The logger name
     * @return UniversalLogger If className cannot be retrieved from hera-logging.properties, returns a StdoutLogger instance,
     * otherwise creates a new UniversalLogger instance using a reflection mechanism. If during the reflection process
     * occur any exception a StdoutLogger instance is returned.
     */
    private static UniversalLogger createUniversalLogger(String name) {
        String className = LoggingConfigurator.getLoggingClassName();
        try {
            if (className == null || DEFAULT_LOGGING_CLASS_NAME.equals(className)) {
                return new StdoutLogger(name);
            }
            Class<UniversalLogger> clazz = (Class<UniversalLogger>) Class.forName(className);
            return clazz.getConstructor(new Class[]{String.class}).newInstance(name);

        } catch (Exception e) {
            System.out.println("RpgLoggerFactory.createUniversalLogger. UniversalLogger cannot be created in successfully way. Error creating the class='" + className + "'");
            System.out.println("RpgLoggerFactory.createUniversalLogger StdoutLogger will be returned");
            e.printStackTrace();
            return new StdoutLogger(name);
        }
    }
}
