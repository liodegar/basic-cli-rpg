package com.acme.rpg.util.logging;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This class is intended to test the proper functionality of StdoutLogger class
 * Created by Liodegar on 10/21/2018.
*/
public class StdoutLoggerTest {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String EMPTY_STRING = "";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private StdoutLogger systemUnderTest = new StdoutLogger("myLoggerName");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // --------------------------------------------------  Trace level  ------------------------------------------------
    @Test
    public void logTraceMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.TRACE, message);
        Assert.assertEquals("[TRACE] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logTraceMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.TRACE, message, exception);
        Assert.assertTrue(outContent.toString().contains("[TRACE] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logTraceSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.TRACE, () -> message);
        Assert.assertEquals(EMPTY_STRING, outContent.toString());
    }

    @Test
    public void logTraceSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.TRACE, () -> message, exception);
        Assert.assertEquals(EMPTY_STRING, outContent.toString());
    }

    // --------------------------------------------------  Debug level  ------------------------------------------------
    @Test
    public void logDebugMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.DEBUG, message);
        Assert.assertEquals("[DEBUG] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logDebugMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.DEBUG, message, exception);
        Assert.assertTrue(outContent.toString().contains("[DEBUG] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logDebugSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.DEBUG, () -> message);
        Assert.assertEquals("[DEBUG] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logDebugSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.DEBUG, () -> message, exception);
        Assert.assertTrue(outContent.toString().contains("[DEBUG] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    // --------------------------------------------------  Info level  -------------------------------------------------
    @Test
    public void logInfoMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.INFO, message);
        Assert.assertEquals("[INFO] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logInfoMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.INFO, message, exception);
        Assert.assertTrue(outContent.toString().contains("[INFO] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logInfoSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.INFO, () -> message);
        Assert.assertEquals("[INFO] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logInfoSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.INFO, () -> message, exception);
        Assert.assertTrue(outContent.toString().contains("[INFO] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    // --------------------------------------------------  Warn level  -------------------------------------------------

    @Test
    public void logWarnMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.WARN, message);
        Assert.assertEquals("[WARN] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logWarnMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.WARN, message, exception);
        Assert.assertTrue(outContent.toString().contains("[WARN] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logWarnSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.WARN, () -> message);
        Assert.assertEquals("[WARN] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logWarnSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.WARN, () -> message, exception);
        Assert.assertTrue(outContent.toString().contains("[WARN] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    // --------------------------------------------------  Error level  ------------------------------------------------
    @Test
    public void logErrorMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.ERROR, message);
        Assert.assertEquals("[ERROR] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logErrorMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.ERROR, message, exception);
        Assert.assertTrue(outContent.toString().contains("[ERROR] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logErrorSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.ERROR, () -> message);
        Assert.assertEquals("[ERROR] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logErrorSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.ERROR, () -> message, exception);
        Assert.assertTrue(outContent.toString().contains("[ERROR] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    // --------------------------------------------------  Fatal level  ------------------------------------------------
    @Test
    public void logFatalMessage() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.FATAL, message);
        Assert.assertEquals("[FATAL] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logFatalMessageWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.FATAL, message, exception);
        Assert.assertTrue(outContent.toString().contains("[FATAL] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

    @Test
    public void logFatalSupplier() {
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.FATAL, () -> message);
        Assert.assertEquals("[FATAL] Hello World!" + LINE_SEPARATOR, outContent.toString());
    }

    @Test
    public void logFatalSupplierWithException() {
        Exception exception = new IllegalArgumentException("The param is invalid");
        String message = "Hello World!";
        systemUnderTest.log(LogLevel.FATAL, () -> message, exception);
        Assert.assertTrue(outContent.toString().contains("[FATAL] Hello World!" + LINE_SEPARATOR));
        Assert.assertTrue(outContent.toString().contains("java.lang.IllegalArgumentException: The param is invalid"));
    }

}
