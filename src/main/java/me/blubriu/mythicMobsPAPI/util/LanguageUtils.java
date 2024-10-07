package me.blubriu.mythicMobsPAPI.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import me.blubriu.mythicMobsPAPI.MythicMobsPAPI;
import me.blubriu.mythicMobsPAPI.lang.LanguageDefinition;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LanguageUtils {
    private static final String LANGUAGE_FILE_NAME = "language.properties";

    private static final Properties properties = new Properties();

    public static void init(MythicMobsPAPI autoIssue) throws IOException {
        File file = new File(autoIssue.getDataFolder(), "language.properties");
        if (!file.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                try {
                    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                    try {
                        for (LanguageDefinition definition : LanguageDefinition.values())
                            properties.setProperty(definition.getKey(), definition.getDefaultMsg());
                        properties.store(osw, "MythicMobsPAPI Language File");
                        osw.close();
                    } catch (Throwable throwable) {
                        try {
                            osw.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    fos.close();
                } catch (Throwable throwable) {
                    try {
                        fos.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                    throw throwable;
                }
            } catch (IOException e) {
                LoggerUtils.error(", new Object[] { e }");
            }
        } else {
            FileInputStream fileInputStream = null;
            InputStreamReader reader = null;
            try {
                fileInputStream = new FileInputStream("" + autoIssue.getDataFolder() + "/language.properties");
                reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                properties.load(reader);
                if (!properties.isEmpty()) {
                    LoggerUtils.info(getMessage(LanguageDefinition.LANGUAGE_FILE_LOAD_SUCCESS), new Object[0]);
                } else {
                    LoggerUtils.error(getMessage(LanguageDefinition.LANGUAGE_FILE_LOAD_FAIL), new Object[0]);
                }
            } catch (Exception e) {
                LoggerUtils.error(", new Object[0]");
            } finally {
                if (reader != null)
                    reader.close();
                if (fileInputStream != null)
                    fileInputStream.close();
                Properties copyProperties = new Properties();
                copyProperties.putAll(properties);
                boolean rewrite = false;
                for (LanguageDefinition definition : LanguageDefinition.values()) {
                    if (!copyProperties.containsKey(definition.getKey())) {
                        rewrite = true;
                        copyProperties.setProperty(definition.getKey(), definition.getDefaultMsg());
                    }
                }
                if (rewrite) {
                    file.deleteOnExit();
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        try {
                            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                            try {
                                copyProperties.store(osw, "MythicMobsPAPI Language File");
                                LoggerUtils.info(getMessage(LanguageDefinition.LANGUAGE_FILE_UPDATE_SUCCESS), new Object[0]);
                                osw.close();
                            } catch (Throwable throwable) {
                                try {
                                    osw.close();
                                } catch (Throwable throwable1) {
                                    throwable.addSuppressed(throwable1);
                                }
                                throw throwable;
                            }
                            fos.close();
                        } catch (Throwable throwable) {
                            try {
                                fos.close();
                            } catch (Throwable throwable1) {
                                throwable.addSuppressed(throwable1);
                            }
                            throw throwable;
                        }
                    } catch (IOException e) {
                        LoggerUtils.error(getMessage(LanguageDefinition.LANGUAGE_FILE_LOAD_FAIL), new Object[] { e });
                    }
                }
            }
        }
    }

    public static String getMessage(@NotNull LanguageDefinition definition) {
        return getMessage(definition, null);
    }

    public static String getMessage(@NotNull LanguageDefinition definition, @Nullable Map<String, Object> paramMap) {
        String str = properties.getProperty(definition.getKey());
        if (StringUtils.isBlank(str))
            str = definition.getDefaultMsg();
        if (paramMap == null || paramMap.isEmpty())
            return str;
        String[] args = StringUtils.substringsBetween(str, "{", "}");
        if (args != null)
            for (String arg : args)
                str = StringUtils.replace(str, "{" + arg + "}", paramMap.containsKey(arg) ? paramMap.get(arg).toString() : ("{" + arg + "}"));
        return str;
    }

    public static boolean containsKey(LanguageDefinition definition) {
        return (properties.containsKey(definition.getKey()) && StringUtils.isNotBlank(properties.getProperty(definition.getKey())));
    }

    public static Component getMessageComponent(@NotNull LanguageDefinition definition) {
        return MinecraftTextUtils.parseText(getMessage(definition));
    }

    public static Component getMessageComponent(@NotNull LanguageDefinition definition, @Nullable Map<String, Object> paramMap) {
        return MinecraftTextUtils.parseText(getMessage(definition, paramMap));
    }
}

