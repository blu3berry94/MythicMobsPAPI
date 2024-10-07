package me.blubriu.mythicMobsPAPI.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.Zrips.CMILib.Colors.CMIChatColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;

public class MinecraftTextUtils {
    private static final String MC_COLOR_REGEX = "(<#[a-fA-F0-9]{6}>|)";

    private static final String CMI_COLOR_REGEX = "(\\{#[a-fA-F0-9]{6}\\}|&[0-9a-fA-Fl-oL-OrR])";

    public static String trimGameText(String text) {
        if (StringUtils.isNotBlank(text)) {
            text = CMIChatColor.deColorize(text);
            return text.replaceAll("(<#[a-fA-F0-9]{6}>|)", "").replaceAll("(\\{#[a-fA-F0-9]{6}\\}|&[0-9a-fA-Fl-oL-OrR])", "");
        }
        return text;
    }

    public static Component parseText(String text) {
        if (StringUtils.isNotBlank(text)) {
            TextComponent component = Component.empty();
            Pattern pattern = Pattern.compile("(\\{#[a-fA-F0-9]{6}\\}|&[0-9a-fA-Fl-oL-OrR])");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                LegacyComponentSerializer serializer = LegacyComponentSerializer.builder().hexCharacter('<').hexColors().build();
                TextComponent parsedComponent = serializer.deserialize(translateColors(text));
                parsedComponent = (TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)parsedComponent.decoration(TextDecoration.ITALIC, false)).decoration(TextDecoration.BOLD, false)).decoration(TextDecoration.UNDERLINED, false)).decoration(TextDecoration.STRIKETHROUGH, false)).decoration(TextDecoration.OBFUSCATED, false);
                return (Component)parsedComponent;
            }
            pattern = Pattern.compile("(<#[a-fA-F0-9]{6}>|)");
            matcher = pattern.matcher(text);
            int lastEnd = 0;
            while (matcher.find()) {
                String colorCode = matcher.group();
                if (lastEnd != matcher.start())
                    component = (TextComponent)component.append((Component)Component.text(text.substring(lastEnd, matcher.start())));
                if (colorCode.startsWith("<#")) {
                    TextColor color = TextColor.color(Integer.parseInt(colorCode.substring(2, 8), 16));
                    component = (TextComponent)component.color(color);
                } else {
                    switch (colorCode.charAt(1)) {
                        case 'l':
                            component = (TextComponent)component.decoration(TextDecoration.BOLD, true);
                            break;
                    }
                }
                lastEnd = matcher.end();
            }
            if (lastEnd != text.length())
                component = (TextComponent)component.append((Component)Component.text(text.substring(lastEnd)));
            return (Component)component;
        }
        return (Component)Component.text(text);
    }

    public static String translateColors(String text) {
        if (StringUtils.isBlank(text))
            return text;
        return CMIChatColor.colorize(text);
    }

    public static String getMaterialName(String material) {
        if (StringUtils.contains(material, ":"))
            return material.split(":")[0];
        return material;
    }

    public static Integer getCommonDataId(String material) {
        if (StringUtils.contains(material, ":"))
            return Integer.valueOf(material.split(":")[1]);
        return null;
    }
}
