package com.acme.rpg.util;

import com.acme.rpg.service.GameServiceImpl;
import com.acme.rpg.util.logging.RpgLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic registry to get the beans managed by the application.
 * Created by Liodegar
 */
public final class ApplicationContext {

    private static final RpgLogger logger = RpgLogger.getLogger(GameServiceImpl.class);

    private static final Map<String, Object> registry = new HashMap<>();

    /**
     * To avoid instantiation from outside
     */
    private ApplicationContext() {
    }

    /**
     * Gets a class from the registry or creates a new one if it doesn't exist
     *
     * @param key an instance of Class
     * @return <T> an instance of the class represented by this object.
     */
    public static <T> T get(Class<T> key) {
        try {
            return (T) registry.computeIfAbsent(key.getName(), k -> ReflectionUtil.newInstance(key));
        } catch (Exception e) {
            logger.error("Exception encountered while getting key=" + key, e);
            return null;
        }
    }

    /**
     * Gets a class from the registry or creates a new one if it doesn't exist
     *
     * @param className the fully qualified name of the desired class.
     * @return <T> an instance of the class represented by this object.
     */
    public static <T> T get(String className) {
        try {
            return (T) get(ReflectionUtil.getClass(className));
        } catch (Exception e) {
            logger.error("Exception encountered while getting className=" + className, e);
            return null;
        }
    }


    /**
     * Gets an implementation class from the registry or creates a new one if it doesn't exist of the given interface
     *
     * @param key the interface class.
     * @return <T> an instance of the class represented by this object
     * or null if there is no a implementation that fulfils the following naming convention: InterfaceName + "Impl"
     */
    public static <T> T getDefaultImplFromInterface(Class<T> key) {
        try {
            String defaultClassName = key.getName() + "Impl";
            return (T) registry.computeIfAbsent(key.getName(), k -> ReflectionUtil.newInstance(defaultClassName));

        } catch (Exception e) {
            logger.error("Exception encountered while getting default impl for key=" + key, e);
            return null;
        }
    }
}
