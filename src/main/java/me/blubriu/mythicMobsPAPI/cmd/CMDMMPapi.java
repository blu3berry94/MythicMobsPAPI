package me.blubriu.mythicMobsPAPI.cmd;

import me.blubriu.mythicMobsPAPI.MythicMobsPAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CMDMMPapi implements CommandExecutor {
    private final MythicMobsPAPI mainPlugin;

    public CMDMMPapi(MythicMobsPAPI mainPlugin) {
        this.mainPlugin = mainPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mmpapi")) {
            if (args.length == 0) {
                return handleHelp(sender);
            } else {
                switch (args[0].toLowerCase()) {
                    case "info":
                        return handleInfo(sender);
                    case "reload":
                        return handleReload(sender);
                    default:
                        sender.sendMessage("§c[MythicMobsPAPI] Unknown subcommand!");
                        return true;
                }
            }
        }
        return false;
    }

    private boolean handleHelp(CommandSender sender) {
        if (sender.hasPermission("mmpapi.help")) {
            sender.sendMessage("§a");
            sender.sendMessage("§9MythicMobsPAPI v1.0");
            sender.sendMessage("by §3Blub");
            sender.sendMessage("§a");
            sender.sendMessage("§b/mmpapi info");
            sender.sendMessage("§3▸ §fList all usable placeholder");
            sender.sendMessage("§b/mmpapi reload");
            sender.sendMessage("§3▸ §fReload plugin");
            sender.sendMessage("§a");
            return true;
        } else {
            sender.sendMessage("§c[MythicMobsPAPI] You don't have permission!");
            return true;
        }
    }

    private boolean handleInfo(CommandSender sender) {
        if (sender.hasPermission("mmpapi.info")) {
            mainPlugin.reloadConfig();
            sender.sendMessage("§9[MythicMobsPAPI] §fAll Placeholders:");
            sender.sendMessage("§a");
            sender.sendMessage("§b%mmpapi_{spawner name}_cooldown%");
            sender.sendMessage("§3▸ §fGet spawner boss cooldown time");
            sender.sendMessage("§b%mmpapi_{spawner name}_warmup%");
            sender.sendMessage("§3▸ §fGet spawner boss warmup time");
            sender.sendMessage("§b%mmpapi_{spawner name}_respawnTime%");
            sender.sendMessage("§3▸ §fGet the exact time for a boss to respawn");
            sender.sendMessage("§b%mmpapi_{spawner name}_posX%");
            sender.sendMessage("§b%mmpapi_{spawner name}_posY%");
            sender.sendMessage("§b%mmpapi_{spawner name}_posZ%");
            sender.sendMessage("§3▸ §fGet the boss spawner position");
            sender.sendMessage("§b%mmpapi_{spawner name}_name%");
            sender.sendMessage("§3▸ §fGet the spawner name");
            sender.sendMessage("§a");
            return true;
        } else {
            sender.sendMessage("§c[MythicMobsPAPI] You don't have permission!");
            return true;
        }
    }

    private boolean handleReload(CommandSender sender) {
        if (sender.hasPermission("mmpapi.reload")) {
            mainPlugin.reloadConfig();
            sender.sendMessage("§a[MythicMobsPAPI] Plugin reloaded successfully!");
            return true;
        } else {
            sender.sendMessage("§c[MythicMobsPAPI] You don't have permission!");
            return true;
        }
    }
}