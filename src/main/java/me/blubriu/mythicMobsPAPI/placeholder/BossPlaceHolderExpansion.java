package me.blubriu.mythicMobsPAPI.placeholder;

import java.text.MessageFormat;
import java.util.Date;

import me.blubriu.mythicMobsPAPI.MythicMobsPAPI;
import me.blubriu.mythicMobsPAPI.util.LanguageUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.blubriu.mythicMobsPAPI.lang.LanguageDefinition;
import me.blubriu.mythicMobsPAPI.model.MobTimerInfo;
import me.blubriu.mythicMobsPAPI.util.LoggerUtils;
import me.blubriu.mythicMobsPAPI.util.MobTimerUtil;
import me.blubriu.mythicMobsPAPI.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BossPlaceHolderExpansion extends PlaceholderExpansion {
    private final MythicMobsPAPI plugin;

    public BossPlaceHolderExpansion(MythicMobsPAPI plugin) {
        this.plugin = plugin;
    }

    @NotNull
    public String getIdentifier() {
        return "mmpapi";
    }

    @NotNull
    public String getAuthor() {
        return "Blub";
    }

    @NotNull
    public String getVersion() {
        return "1.0";
    }

    @Nullable
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (StringUtils.isNotBlank(params)) {
            String[] split = StringUtils.split(params, "_");
            String id = split[0];
            String param = split[1];
            if (StringUtils.isBlank(id)) {
                LoggerUtils.warn("id is now valid", new Object[0]);
            } else {
                MobTimerInfo mobTimerInfo = MobTimerUtil.getMobTimerInfoById(id);
                if (mobTimerInfo != null) {
                    String log = ToStringBuilder.reflectionToString(mobTimerInfo, ToStringStyle.MULTI_LINE_STYLE);
                    LoggerUtils.debug(log, new Object[0]);
                    int remainingCooldownSeconds = mobTimerInfo.getRemainingCooldownSeconds();
                    Date respawnTime = mobTimerInfo.getRespawnTime();
                    respawnTime = DateUtils.setMilliseconds(respawnTime, 0);
                    String respawnTimeString = DateFormatUtils.format(respawnTime, "dd-MM-yyyy HH:mm:ss");
                    String cooldownTimeString = TimeUtils.secondsToTime(remainingCooldownSeconds);
                    if (StringUtils.isNotBlank(param)) {
                        if (StringUtils.equalsIgnoreCase("coolDown", param))
                            return cooldownTimeString;
                        if (StringUtils.equalsIgnoreCase("respawnTime", param))
                            return respawnTimeString;
                        if (StringUtils.equalsIgnoreCase("name", param))
                            return mobTimerInfo.getMobName();
                        if (StringUtils.equalsIgnoreCase("posX", param))
                            return String.valueOf(mobTimerInfo.getBlockX());
                        if (StringUtils.equalsIgnoreCase("posY", param))
                            return String.valueOf(mobTimerInfo.getBlockY());
                        if (StringUtils.equalsIgnoreCase("posZ", param))
                            return String.valueOf(mobTimerInfo.getBlockZ());
                        if (StringUtils.equalsIgnoreCase("warmUp", param)) {
                            int remainingWarmupSeconds = mobTimerInfo.getRemainingWarnupSeconds();
                            return TimeUtils.secondsToTime(remainingWarmupSeconds);
                        }
                    } else {
                        return MessageFormat.format(LanguageUtils.getMessage(LanguageDefinition.PLACEHOLDER_DEFAULT_DISPLAY_FORMAT), new Object[] { cooldownTimeString, respawnTimeString });
                    }
                }
            }
        }
        return null;
    }
}