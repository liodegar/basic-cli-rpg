package com.acme.rpg.util;

import com.acme.rpg.util.logging.RpgLogger;

import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class ConfigurationProperty that enables the properties retrieving from application.properties file
 *
 * @author Liodegar Bracamonte
 */
public final class ConfigurationProperty {

    public static final RpgLogger logger = RpgLogger.getLogger(ConfigurationProperty.class);

    /**
     * The base name to build the ResourceBundle object used for configuration.
     */
    public static final String APPLICATION_NAME = "application";

    /**
     * RPG Topic prefix
     */
    public static final String TOPIC_PREFIX = "rpg.topic.key-";


    /**
     * The private modifier is to avoid the instantiation outside this class
     */
    private ConfigurationProperty() {
    }

    /**
     * Gets a string for the given key from <code>application.properties</code>.
     *
     * @param key the key for the desired string
     * @return the string for the given key or null if no object for the given key can be found
     * @throws java.lang.NullPointerException if <code>key</code> is <code>null</code>
     */
    public static String getString(String key) {
        return getStringFromBaseName(key, APPLICATION_NAME);
    }

    /**
     * Gets a string for the given key from <code>application.properties</code>.
     *
     * @param key the key for the desired string
     * @return the string for the given key or defaultValue if no object for the given key can be found
     * @throws java.lang.NullPointerException if <code>key</code> is <code>null</code>
     */
    public static String getString(String key, String defaultValue) {
        return getString(key) == null ? defaultValue : getString(key);
    }

    /**
     * Gets the  RPG topic class names from <code>application.properties</code>.
     *
     * @return the a Set containing the RPG topic class names
     */
    public static Set<String> getTopicClassNames() {
        return getValuesFromKeyWithPrefix(TOPIC_PREFIX);
    }

    /**
     * Gets the  RPG topic class names from <code>application.properties</code>.
     *
     * @return the a Set containing the RPG topic class names
     */
    public static Set<String> getValuesFromKeyWithPrefix(String prefix) {
        ResourceBundle resourceBundle = getProperties(APPLICATION_NAME);
        return resourceBundle.keySet().stream()
                .filter(key -> key.startsWith(prefix))
                .map(key -> getString(key))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * Gets a string for the given key using the ResourceBundle object.
     *
     * @param key      the key for the desired string
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @return the string for the given key or null if no object for the given key can be found
     */
    private static String getStringFromBaseName(String key, String baseName) {
        try {
            ResourceBundle bundle = getProperties(baseName);
            if (bundle == null) {
                logger.error("The baseName properties could not be retrieved=" + baseName);
                return null;
            }
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            logger.error("The key cannot be retrieved=" + key);
            return null;
        }
    }

    /**
     * Gets a resource bundle using the specified base name
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @return The ResourceBundle object if a resource bundle for the specified base baseName can be found, otherwise returns null.
     */
    private static ResourceBundle getProperties(String baseName) {
        try {
            return ResourceBundle.getBundle(baseName);
        } catch (Exception e) {
            logger.error("The baseName properties could not be retrieved=" + baseName);
            return null;
        }
    }
}
