package me.blubriu.mythicMobsPAPI.util;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.utils.lib.lang3.exception.ExceptionUtils;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import me.blubriu.mythicMobsPAPI.model.MobTimerInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class MobTimerUtil {
    public static List<MobTimerInfo> getAllMobTimeInfos() {
        try {
            Collection<MythicSpawner> spawners = MythicBukkit.inst().getSpawnerManager().getSpawners();
            List<MobTimerInfo> mobTimerInfos = new ArrayList<>();
            for (MythicSpawner spawner : spawners) {
                MobTimerInfo mobInfo = new MobTimerInfo();
                mobInfo.setId(spawner.getName());
                MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(spawner.getTypeName()).orElse(null);
                if (mythicMob != null) {
                    mobInfo.setMobName(mythicMob.getConfig().getString("Display"));
                    mobInfo.setHasBossBar(mythicMob.usesBossBar());
                }
                int remainingCooldownSeconds = spawner.getRemainingCooldownSeconds();
                int remainingWarmupSeconds = spawner.getRemainingWarmupSeconds();
                mobInfo.setRemainingCooldownSeconds(remainingCooldownSeconds);
                mobInfo.setRemainingWarnupSeconds(remainingWarmupSeconds);
                int totalTime = remainingCooldownSeconds + remainingWarmupSeconds;
                Date respawnTime = DateUtils.addSeconds(Calendar.getInstance().getTime(), totalTime);
                respawnTime = DateUtils.truncate(respawnTime, 14);
                mobInfo.setRespawnTime(respawnTime);
                mobInfo.setBlockX(spawner.getLocation().getBlockX());
                mobInfo.setBlockY(spawner.getLocation().getBlockY());
                mobInfo.setBlockZ(spawner.getLocation().getBlockZ());
                mobTimerInfos.add(mobInfo);
            }
            return mobTimerInfos;
        } catch (Exception e) {
            LoggerUtils.error("getAllMobTimeInfos error:{}", new Object[] { e });
            LoggerUtils.error(ExceptionUtils.getStackTrace(e), new Object[0]);
            return null;
        }
    }

    public static boolean spawnMob(String id) {
        try {
            Collection<MythicSpawner> spawners = MythicBukkit.inst().getSpawnerManager().getSpawners();
            if (!spawners.isEmpty())
                for (MythicSpawner spawner : spawners) {
                    if (StringUtils.equals(id, spawner.getName())) {
                        spawner.setRemainingCooldownSeconds(5L);
                        spawner.setRemainingWarmupSeconds(0L);
                        return true;
                    }
                }
        } catch (Exception e) {
            LoggerUtils.error("spawnMob error:{}", new Object[] { e });
            LoggerUtils.error(ExceptionUtils.getStackTrace(e), new Object[0]);
        }
        return false;
    }

    public static MobTimerInfo getMobTimerInfoById(String id) {
        try {
            Collection<MythicSpawner> spawners = MythicBukkit.inst().getSpawnerManager().getSpawners();
            Date now = Calendar.getInstance().getTime();
            if (spawners != null && !spawners.isEmpty())
                for (MythicSpawner spawner : spawners) {
                    if (StringUtils.equals(id, spawner.getName())) {
                        MobTimerInfo mobTimerInfo = new MobTimerInfo();
                        mobTimerInfo.setId(spawner.getName());
                        MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(spawner.getTypeName()).orElse(null);
                        if (mythicMob != null) {
                            mobTimerInfo.setMobName(mythicMob.getConfig().getString("Display"));
                            mobTimerInfo.setHasBossBar(mythicMob.usesBossBar());
                        }
                        int remainingCooldownSeconds = spawner.getRemainingCooldownSeconds();
                        int remainingWarmupSeconds = spawner.getRemainingWarmupSeconds();
                        mobTimerInfo.setRemainingCooldownSeconds(remainingCooldownSeconds);
                        mobTimerInfo.setRemainingWarnupSeconds(remainingWarmupSeconds);
                        int totalTime = remainingCooldownSeconds + remainingWarmupSeconds;
                        Date respawnTime = DateUtils.addSeconds(now, totalTime);
                        respawnTime = DateUtils.truncate(respawnTime, 14);
                        mobTimerInfo.setRespawnTime(respawnTime);
                        mobTimerInfo.setBlockX(spawner.getLocation().getBlockX());
                        mobTimerInfo.setBlockY(spawner.getLocation().getBlockY());
                        mobTimerInfo.setBlockZ(spawner.getLocation().getBlockZ());
                        return mobTimerInfo;
                    }
                }
        } catch (Exception e) {
            LoggerUtils.error("getMobTimerInfoById error:{}", new Object[] { e });
            LoggerUtils.error(ExceptionUtils.getStackTrace(e), new Object[0]);
        }
        return null;
    }
}

