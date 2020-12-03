package com.github.connyscode.ctils.jTrack.backend;

public class Messaging {
    /**
     * Print an Error
     */
    public static void error(String errorTag, String text) {
        System.out.println("[jTRACK] [ERROR-" + errorTag + "] " + text);
    }

    /**
     * Print a Warning
     */
    public static void warning(String warningTag, String text) {
        System.out.println("[jTRACK] [WARNING-" + warningTag + "] " + text);
    }

    /**
     * Print an Info
     */
    public static void info(String text) {
        System.out.println("[jTRACK] [INFO] " + text);
    }
}
