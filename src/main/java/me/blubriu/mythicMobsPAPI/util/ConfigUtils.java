package me.blubriu.mythicMobsPAPI.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.blubriu.mythicMobsPAPI.MythicMobsPAPI;
import me.blubriu.mythicMobsPAPI.model.MobDefine;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {
    public static List<MobDefine> getMobDefineList(MythicMobsPAPI plugin) {
        FileConfiguration config = plugin.getConfig();
        List<MobDefine> mobDefineList = new ArrayList<>();
        List<Map<?, ?>> mobsList = config.getMapList("MobDefines");
        for (Map<?, ?> mobDefineMap : mobsList) {
            MobDefine mobDefine = new MobDefine();
            mobDefine.setId(((Integer)mobDefineMap.get("id")).intValue());
            mobDefine.setSpawnerMobName((String)mobDefineMap.get("spawnerMobName"));
            mobDefine.setAlias((List)mobDefineMap.get("alias"));
            mobDefine.setDisplayName((String)mobDefineMap.get("displayName"));
            mobDefineList.add(mobDefine);
        }
        return mobDefineList;
    }
}
