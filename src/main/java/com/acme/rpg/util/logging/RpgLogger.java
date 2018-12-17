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
 * <p>
 * Changes:
 */
package com.acme.rpg.util.logging;

import java.util.function.Supplier;

/**
 * Class RpgLogger
 * This is the Rpg implementation of the Universal Logger Interface.
 * This class use delegation to call the methods of the specified UniversalLogger�s implementation
 * and also can offer more methods not considered in this interface.
 *
 * @author Liodegar Bracamonte
 */
public class RpgLogger implements UniversalLogger {
    /**
     * UniversalLogger object used like wrapper
     */
    private UniversalLogger logger;

    /**
     * Retrieves a CantvLogger object using the logger's name
     * @param name The logger's name
     * @return CantvLogger object
     */
    public static final RpgLogger getLogger(String name) {
        return RpgLoggerFactory.getLogger(name);
    }

    /**
     * Retrieves a CantvLogger object using the clazz.getName() idiom
     * @param clazz Class object
     * @return CantvLogger object
     */
    public static final RpgLogger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Copy constructor used to wrap an UniversalLogger object
     * @param logger UniversalLogger object
     */
    protected RpgLogger(UniversalLogger logger) {
        this.logger = logger;
    }

    /**
     * Returns the underlying UniversalLogger object
     * @return UniversalLogger object
     */
    private UniversalLogger getLogger() {
        if (logger == null) {
            logger = RpgLogger.getLogger(this.getClass());
        }
        return logger;
    }

    /**
     * Log a message object with the <code>FATAL</code> level.
     * @param message
     */
    public void fatal(Object message) {
        getLogger().fatal(message);
    }

    /**
     * Log a message object with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     * @param message
     * @param throwable
     */
    public void fatal(Object message, Throwable throwable) {
        getLogger().fatal(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void fatal(Supplier<?> message) {
        getLogger().fatal(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void fatal(Supplier<?> message, Throwable throwable) {
        getLogger().fatal(message, throwable);
    }

    /**
     * Log a message object with the <code>ERROR</code> level.
     * @param message
     */
    public void error(Object message) {
        getLogger().error(message);
    }

    /**
     * Log a message object with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     * @param message
     * @param throwable
     */
    public void error(Object message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void error(Supplier<?> message) {
        getLogger().error(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void error(Supplier<?> message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    /**
     * Log a message object with the <code>WARN</code> level.
     * @param message
     */
    public void warn(Object message) {
        getLogger().warn(message);
    }

    /**
     * Log a message object with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     * @param message
     * @param throwable
     */
    public void warn(Object message, Throwable throwable) {
        getLogger().warn(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void warn(Supplier<?> message) {
        getLogger().warn(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void warn(Supplier<?> message, Throwable throwable) {
        getLogger().warn(message, throwable);
    }

    /**
     * Log a message object with the <code>INFO</code> level.
     * @param message
     */
    public void info(Object message) {
        getLogger().info(message);
    }

    /**
     * Log a message object with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     * @param message
     * @param throwable
     */
    public void info(Object message, Throwable throwable) {
        getLogger().info(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void info(Supplier<?> message) {
        getLogger().info(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void info(Supplier<?> message, Throwable throwable) {
        getLogger().info(message, throwable);
    }

    /**
     * Log a message object with the <code>DEBUG</code> level.
     * @param message
     */
    public void debug(Object message) {
        getLogger().debug(message);
    }

    /**
     * Log a message object with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     * @param message
     * @param throwable
     */
    public void debug(Object message, Throwable throwable) {
        getLogger().debug(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void debug(Supplier<?> message) {
        getLogger().debug(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void debug(Supplier<?> message, Throwable throwable) {
        getLogger().debug(message, throwable);
    }

    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void trace(Object message) {
        getLogger().trace(message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void trace(Supplier<?> message) {
        getLogger().trace(message);
    }

    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void trace(Object message, Throwable throwable) {
        getLogger().trace(message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void trace(Supplier<?> message, Throwable throwable) {
        getLogger().trace(message, throwable);
    }

    /**
     * Log a message object with the given logLevel including the stack trace of the Throwable parameter.
     * @param logLevel
     * @param message
     * @param throwable
     */
    public void log(LogLevel logLevel, Object message, Throwable throwable) {
        getLogger().log(logLevel, message, throwable);
    }

    /**
     * Log a message object with the given logLevel
     * @param logLevel
     * @param message
     */
    public void log(LogLevel logLevel, Object message) {
        getLogger().log(logLevel, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is enabled for ths level) including the stack trace of the Throwable parameter.
     *
     * @param level     The level for this log
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void log(LogLevel level, Supplier<?> message, Throwable throwable) {
        getLogger().log(level, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is enabled for ths level)
     *
     * @param level   the level for this log
     * @param message the message object to log
     */
    @Override
    public void log(LogLevel level, Supplier<?> message) {
        getLogger().log(level, message);
    }

    /**
     * Checks whether if this Logger is enabled for the given LogLevel
     *
     * @param level the Level to check
     * @return boolean - true if this Logger is enabled for level, false otherwise.
     */
    @Override
    public boolean isEnabled(LogLevel level) {
        return getLogger().isEnabled(level);
    }

    /**
     * Returns the logger name.
     * @return String
     */
    public String getName() {
        return getLogger().getName();
    }

    /**
     * Calling this method will <em>safely</em> close and remove any resources hold by the Logging framework.
     */
    public void shutdown() {
        getLogger().shutdown();
    }
}
