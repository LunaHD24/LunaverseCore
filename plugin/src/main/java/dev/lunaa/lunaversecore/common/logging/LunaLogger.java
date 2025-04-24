package dev.lunaa.lunaversecore.common.logging;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.common.logging.LogLevel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LunaLogger implements dev.lunaa.lunaversecore.api.common.logging.Logger {

    private static java.util.logging.Logger logger;

    public LunaLogger(Logger logger) {
        LunaLogger.logger = logger;
    }

    @Override
    public void log(LogLevel level, String message) {
        log(level, message, null);
    }

    @Override
    public void log(LogLevel level, String message, Throwable throwable) {
        Level logLevel = Level.INFO;
        String colorPrefix = "";

        switch (level) {
            case DEV -> {
                if (!LunaverseCore.isInDevelopmentMode()) logLevel = Level.FINE;
                colorPrefix = "\u001B[38;5;33m";
            }
            case DEBUG -> {
                if (!LunaverseCore.isInDebugMode()) logLevel = Level.FINE;
                colorPrefix = "\u001B[38;5;135m";
            }
            case WARN -> {
                logLevel = Level.WARNING;
                colorPrefix = "\u001B[33;1m";
            }
            case ERROR -> {
                logLevel = Level.SEVERE;
                colorPrefix = "\u001B[31;1m";
            }
            case FATAL -> {
                logLevel = Level.SEVERE;
                colorPrefix = "\u001B[38;5;88m";
            }
        };

        logger.log(logLevel, colorPrefix + message + "\u001b[0m", throwable);
    }

    @Override
    public void log(LogLevel level, Throwable throwable) {
        log(level, null, throwable);
    }

    @Override
    public void dev(String message) {
        log(LogLevel.DEV, message);
    }

    @Override
    public void dev(String message, Throwable throwable) {
        log(LogLevel.DEV, message, throwable);
    }

    @Override
    public void dev(Throwable throwable) {
        log(LogLevel.DEV, throwable);
    }

    @Override
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        log(LogLevel.DEBUG, message, throwable);
    }

    @Override
    public void debug(Throwable throwable) {
        log(LogLevel.DEBUG, throwable);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        log(LogLevel.INFO, message, throwable);
    }

    @Override
    public void info(Throwable throwable) {
        log(LogLevel.INFO, throwable);
    }

    @Override
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        log(LogLevel.WARN, message, throwable);
    }

    @Override
    public void warn(Throwable throwable) {
        log(LogLevel.WARN, throwable);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable);
    }

    @Override
    public void error(Throwable throwable) {
        log(LogLevel.ERROR, throwable);
    }

    @Override
    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    @Override
    public void fatal(String message, Throwable throwable) {
        log(LogLevel.FATAL, message, throwable);
    }

    @Override
    public void fatal(Throwable throwable) {
        log(LogLevel.FATAL, throwable);
    }
}
