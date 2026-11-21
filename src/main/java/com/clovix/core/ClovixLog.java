package com.clovix.core;
import com.clovix.Clovix;
public final class ClovixLog {
    private ClovixLog() {}
    public static void info(String message) { Clovix.LOGGER.info(message); }
    public static void warn(String message) { Clovix.LOGGER.warn(message); }
    public static void error(String message, Throwable error) { Clovix.LOGGER.error(message, error); }
}
