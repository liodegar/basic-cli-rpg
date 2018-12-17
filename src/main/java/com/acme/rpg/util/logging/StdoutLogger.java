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
 * Class StdoutLogger
 * <p>
 * Convenience class for generating a simple logger in the "standard" output stream (stdout).
 * Not used directly, but through the CantvLogger class if it is configured to wrap a Log4Jlogger object
 * in the cantv.properties file. This logger will be used in case of any exceptions occur in the logger retrieving.
 *
 * @author Liodegar Bracamonte
 */
public class StdoutLogger implements UniversalLogger {

    /**
     * The loggers's name
     */
    private String name;

    /**
     * Creates a new StdoutLogger using the logger's name
     *
     * @param name the logger's name
     */
    public StdoutLogger(String name) {
        this.name = name;
    }

    /**
     * Logs a message object with the <code>FATAL</code> level.
     *
     * @param message the message object to log
     */
    public void fatal(Object message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Logs a message object with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void fatal(Object message, Throwable throwable) {
        log(LogLevel.FATAL, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void fatal(Supplier<?> message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void fatal(Supplier<?> message, Throwable throwable) {
        log(LogLevel.FATAL, message, throwable);
    }

    /**
     * Logs a message object with the <code>ERROR</code> level.
     *
     * @param message the message object to log
     */
    public void error(Object message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a message object with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void error(Object message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void error(Supplier<?> message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void error(Supplier<?> message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }

    /**
     * Logs a message object with the <code>WARN</code> level.
     *
     * @param message the message object to log
     */
    public void warn(Object message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Logs a message object with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void warn(Object message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void warn(Supplier<?> message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void warn(Supplier<?> message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }

    /**
     * Logs a message object with the <code>INFO</code> level.
     *
     * @param message the message object to log
     */
    public void info(Object message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a message object with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void info(Object message, Throwable throwable) {
        log(LogLevel.INFO, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void info(Supplier<?> message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void info(Supplier<?> message, Throwable throwable) {
        log(LogLevel.INFO, message, throwable);
    }

    /**
     * Logs a message object with the <code>DEBUG</code> level.
     *
     * @param message the message object to log
     */
    public void debug(Object message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a message object with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void debug(Object message, Throwable throwable) {
        log(LogLevel.DEBUG, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void debug(Supplier<?> message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void debug(Supplier<?> message, Throwable throwable) {
        log(LogLevel.DEBUG, message, throwable);
    }

    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void trace(Object message) {
        log(LogLevel.TRACE, message);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message the message object to log
     */
    @Override
    public void trace(Supplier<?> message) {
        log(LogLevel.TRACE, message);
    }

    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void trace(Object message, Throwable throwable) {
        log(LogLevel.TRACE, message, throwable);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     *
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    @Override
    public void trace(Supplier<?> message, Throwable throwable) {
        log(LogLevel.TRACE, message, throwable);
    }

    /**
     * Logs a message object with the given logLevel including the stack trace of the Throwable parameter.
     *
     * @param logLevel  The LogLevel object indicating the level for this log
     * @param message   the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    public void log(LogLevel logLevel, Object message, Throwable throwable) {
        log(logLevel, message);
        throwable.printStackTrace(System.out);
    }

    /**
     * Logs a message object with the given logLevel
     *
     * @param logLevel The LogLevel object indicating the level for this log
     * @param message  the message object to log
     */
    public void log(LogLevel logLevel, Object message) {
        StringBuffer result = new StringBuffer(200);
        result.append("[").append(logLevel.getCode()).append("] ").append(message);
        System.out.println(result);
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
        if (isEnabled(level)) {
            log(level, message.get(), throwable);
        }
    }

    /**
     * Logs a message (only to be constructed if the logging level is enabled for ths level)
     *
     * @param level   the level for this log
     * @param message the message object to log
     */
    @Override
    public void log(LogLevel level, Supplier<?> message) {
        if (isEnabled(level)) {
            log(level, message.get());
        }
    }

    /**
     * Checks whether if this Logger is enabled for the given LogLevel
     *
     * @param level the Level to check
     * @return boolean - true if this Logger is enabled for level, false otherwise.
     */
    @Override
    public boolean isEnabled(LogLevel level) {
        return level.getId() >= LogLevel.DEBUG.getId();
    }

    /**
     * Returns the logger name.
     *
     * @return the logger name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Calling this method will <em>safely</em> close and remove any resources hold by the Logging framework.
     */
    public void shutdown() {
        debug("StdoutLogger.shutdown() method invoked");
    }
}
