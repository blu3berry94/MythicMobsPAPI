package me.blubriu.mythicMobsPAPI.util;

public class TimeUtils {
    public static String secondsToTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = seconds % 3600 / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(secs) });
    }
}
