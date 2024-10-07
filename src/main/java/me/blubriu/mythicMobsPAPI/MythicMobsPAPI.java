package me.blubriu.mythicMobsPAPI;

import me.blubriu.mythicMobsPAPI.cmd.CMDMMPapi;
import me.blubriu.mythicMobsPAPI.placeholder.BossPlaceHolderExpansion;
import me.blubriu.mythicMobsPAPI.util.LanguageUtils;
import me.blubriu.mythicMobsPAPI.util.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicMobsPAPI extends JavaPlugin implements Listener {
    public void onEnable() {
        PluginCommand command = getCommand("mmpapi");
        command.setExecutor(new CMDMMPapi(this));
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§b");
        console.sendMessage("§b  __  __       _   _     _      __  __       _         ____   _    ____ ___ ");
        console.sendMessage("§b |  \\/  |_   _| |_| |__ (_) ___|  \\/  | ___ | |__  ___|  _ \\ / \\  |  _ \\_ _|");
        console.sendMessage("§b | |\\/| | | | | __| '_ \\| |/ __| |\\/| |/ _ \\| '_ \\/ __| |_) / _ \\ | |_) | | ");
        console.sendMessage("§b | |  | | |_| | |_| | | | | (__| |  | | (_) | |_) \\__ \\  __/ ___ \\|  __/| | ");
        console.sendMessage("§b |_|  |_|\\__, |\\__|_| |_|_|\\___|_|  |_|\\___/|_.__/|___/_| /_/   \\_\\_|  |___|");
        console.sendMessage("§b         |___/  ");
        console.sendMessage("§b                                                           by §3Blub §9(_blueblub)");
        console.sendMessage("§b");
        console.sendMessage("§a[MythicMobsPAPI] Loading MythicMobsPAPI...");
        LoggerUtils.info("MythicMobsPAPI is launching...", new Object[0]);
        LoggerUtils.init(getConfig().getString("log_level"));
        saveDefaultConfig();
        getServer().getScheduler().runTaskAsynchronously((Plugin)this, () -> {
            try {
                LanguageUtils.init(this);
            } catch (Exception e) {
                LoggerUtils.error("Language file initialize error", new Object[] { e });
            }
        });
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            (new BossPlaceHolderExpansion(this)).register();
        } else {
            LoggerUtils.warn("PlaceholderAPI not found", new Object[0]);
        }
        LoggerUtils.info("MythicMobsPAPI launched successfully", new Object[0]);
    }

    public void onDisable() {}
}

