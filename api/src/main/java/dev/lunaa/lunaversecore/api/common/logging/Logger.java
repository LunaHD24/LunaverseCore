package dev.lunaa.lunaversecore.api.common.logging;

public interface Logger {

    void log(LogLevel level, String message);

    void log(LogLevel level, String message, Throwable throwable);

    void log(LogLevel level, Throwable throwable);

    void dev(String message);

    void dev(String message, Throwable throwable);

    void dev(Throwable throwable);

    void debug(String message);

    void debug(String message, Throwable throwable);

    void debug(Throwable throwable);

    void info(String message);

    void info(String message, Throwable throwable);

    void info(Throwable throwable);

    void warn(String message);

    void warn(String message, Throwable throwable);

    void warn(Throwable throwable);

    void error(String message);

    void error(String message, Throwable throwable);

    void error(Throwable throwable);

    void fatal(String message);

    void fatal(String message, Throwable throwable);

    void fatal(Throwable throwable);

}
