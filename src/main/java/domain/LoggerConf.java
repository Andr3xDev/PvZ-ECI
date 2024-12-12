package domain;

import java.util.logging.*;

public class LoggerConf {
    public static void setup() {
        Logger rootLogger = Logger.getLogger("");
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format("[%1$tF %1$tT] [%2$-7s] %3$s %n",
                        record.getMillis(),
                        record.getLevel().getLocalizedName(),
                        record.getMessage());
            }
        });

        rootLogger.addHandler(consoleHandler);
        rootLogger.setLevel(Level.ALL);
    }
}

