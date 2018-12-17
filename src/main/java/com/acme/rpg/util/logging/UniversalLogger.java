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
 * Class UniversalLogger
 * Any logger must implement this interface. In this framework any implementation of this interface is wrapped by CantvLogger.
 * This Logging API offer the basic desired operations for any Logging framework.
 *
 * @author Liodegar Bracamonte
 */
public interface UniversalLogger {

    /**
     * Logs a message object with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void fatal(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void fatal(Supplier<?> message);


    /**
     * Logs a message object with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void fatal(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the FATAL level) with the <code>FATAL</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void fatal(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void error(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void error(Supplier<?> message);


    /**
     * Logs a message object with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void error(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the ERROR level) with the <code>ERROR</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void error(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void warn(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void warn(Supplier<?> message);


    /**
     * Logs a message object with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void warn(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the WARN level) with the <code>WARN</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void warn(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void info(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void info(Supplier<?> message);


    /**
     * Logs a message object with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void info(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the INFO level) with the <code>INFO</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void info(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void debug(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void debug(Supplier<?> message);


    /**
     * Logs a message object with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void debug(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the DEBUG level) with the <code>DEBUG</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void debug(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void trace(Object message);

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     */
    void trace(Supplier<?> message);


    /**
     * Logs a message object with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void trace(Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is the TRACE level) with the <code>TRACE</code> level including the stack trace of the Throwable parameter.
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void trace(Supplier<?> message, Throwable throwable);

    /**
     * Logs a message object with the given grade including the stack trace of the Throwable parameter.
     * @param level The level for this log
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void log(LogLevel level, Object message, Throwable throwable);

    /**
     * Logs a message (only to be constructed if the logging level is enabled for ths level) including the stack trace of the Throwable parameter.
     * @param level The level for this log
     * @param message the message object to log
     * @param throwable the exception to log, including its stack trace
     */
    void log(LogLevel level, Supplier<?> message, Throwable throwable);


    /**
     * Logs a message object with the given grade
     * @param level the level for this log
     * @param message the message object to log
     */
    void log(LogLevel level, Object message);

    /**
     * Logs a message (only to be constructed if the logging level is enabled for ths level)
     * @param level the level for this log
     * @param message the message object to log
     */
    void log(LogLevel level, Supplier<?> message);

    /**
     * Checks whether if this Logger is enabled for the given LogLevel
     * @param level the Level to check
     * @return boolean - true if this Logger is enabled for level, false otherwise.
     */
    boolean isEnabled(LogLevel level);

    /**
     * Returns the logger name.
     * @return String
     */
    String getName();

    /**
     * Calling this method will <em>safely</em> close and remove any resources hold by the Logging framework.
     */
    void shutdown();
}
