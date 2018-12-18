package com.acme.rpg.util;

import com.acme.rpg.util.logging.RpgLogger;

/**
 * Utility class for handling reflection requirements
 * Created by Liodegar
 */
public final class ReflectionUtil {

    public static final RpgLogger logger = RpgLogger.getLogger(ReflectionUtil.class);

    /**
     * To avoid instantiation from outside
     */
    private ReflectionUtil() {
    }

    /**
     * Creates a new instance of the class represented by this {@code Class}
     * object. The class is instantiated as if by a {@code new}
     * expression with an empty argument list.
     *
     * @param className the fully qualified name of the desired class.
     * @return a newly allocated instance of the class represented by this object.
     */
    public static <T> T newInstance(String className) {
        return newInstance(getClass(className));
    }

    /**
     * Creates a new instance of the class represented by this {@code Class}
     * object. The class is instantiated as if by a {@code new}
     * expression with an empty argument list.
     *
     * @param clazz an instance of Class
     * @return a newly allocated instance of the class represented by this object.
     */
    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            logger.error("Exception encountered while creating instance for=" + clazz, e);
            return null;
        }
    }

    /**
     * Returns the {@code Class} object associated with the class or
     * interface with the given string name.
     *
     * @param className the fully qualified name of the desired class.
     * @return the {@code Class} object for the class with the  specified name.
     */
    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            logger.error("Exception encountered while creating instance for=" + className, e);
            return null;
        }
    }

}
