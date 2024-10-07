package me.blubriu.mythicMobsPAPI.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.StringUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;

public class LoggerUtils {
    private static String logLevel = "INFO";
    private static final BukkitAudiences audiences = BukkitAudiences.create(Bukkit.getPluginManager().getPlugin("MythicMobsPAPI"));

    public static void init(String logLevel) {
        LoggerUtils.logLevel = StringUtils.isBlank(logLevel) ? "INFO" : logLevel.toUpperCase();
    }

    public static void debug(String message, Object... params) {
        if (isDebugEnabled()) {
            message = processMessage(message, params);
            if (StringUtils.isNotBlank(message)) {
                TextComponent component = Component.text(message, (TextColor)NamedTextColor.WHITE);
                audiences.sender(Bukkit.getConsoleSender()).sendMessage(component);
            }
        }
    }

    public static void info(String message, Object... params) {
        if (isInfoEnabled()) {
            message = processMessage(message, params);
            if (StringUtils.isNotBlank(message)) {
                TextComponent component = Component.text(message, (TextColor)NamedTextColor.GREEN);
                audiences.sender(Bukkit.getConsoleSender()).sendMessage(component);
            }
        }
    }

    public static void warn(String message, Object... params) {
        if (isWarnEnabled()) {
            message = processMessage(message, params);
            if (StringUtils.isNotBlank(message)) {
                TextComponent component = Component.text(message, (TextColor)NamedTextColor.YELLOW);
                audiences.sender(Bukkit.getConsoleSender()).sendMessage(component);
            }
        }
    }

    public static void error(String message, Object... params) {
        if (isErrorEnabled()) {
            message = processMessage(message, params);
            if (StringUtils.isNotBlank(message)) {
                TextComponent component = Component.text(message, (TextColor)NamedTextColor.RED);
                audiences.sender(Bukkit.getConsoleSender()).sendMessage(component);
            }
        }
    }

    private static String processMessage(String message, Object[] params) {
        String prefix = "";
        if (message == null || params == null || params.length == 0) {
            if (StringUtils.isNotBlank(message))
                return prefix + prefix;
            return message;
        }
        StringBuilder result = (new StringBuilder()).append(prefix).append(message);
        int index = 0;
        for (Object value : params) {
            int placeholderPos = result.indexOf("{}");
            if (placeholderPos == -1)
                break;
            result.replace(placeholderPos, placeholderPos + 2, value.toString());
            index++;
        }
        for (int i = index; i < params.length; i++)
            result.append(" ").append(params[i]);
        return result.toString();
    }

    public static boolean isDebugEnabled() {
        return LogLevel.canLog(logLevel, LogLevel.DEBUG);
    }

    public static boolean isInfoEnabled() {
        return LogLevel.canLog(logLevel, LogLevel.INFO);
    }

    public static boolean isWarnEnabled() {
        return LogLevel.canLog(logLevel, LogLevel.WARN);
    }

    public static boolean isErrorEnabled() {
        return LogLevel.canLog(logLevel, LogLevel.ERROR);
    }

    private enum LogLevel {
        DEBUG(4),
        INFO(3),
        WARN(2),
        ERROR(1);

        final int level;

        LogLevel(int level) {
            this.level = level;
        }

        private int getLevel() {
            return this.level;
        }

        public static boolean canLog(String level, LogLevel logLevel) {
            return (findLogLevel(level) >= logLevel.getLevel());
        }

        private static int findLogLevel(String level1) {
            for (LogLevel logLevel : values()) {
                if (StringUtils.equalsIgnoreCase(level1, logLevel.name()))
                    return logLevel.getLevel();
            }
            return 0;
        }
    }
}
