package me.blubriu.mythicMobsPAPI.model;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobTimerInfo {
    private static final Logger logger = LogManager.getLogger(MobTimerInfo.class);

    private String id;

    private String mobName;

    private int remainingCooldownSeconds;

    private int remainingWarnupSeconds;

    private Date respawnTime;

    private int blockX;

    private int blockY;

    private int blockZ;

    private boolean hasBossBar;

    public String getMobName() {
        return this.mobName;
    }

    public void setMobName(String mobName) {
        this.mobName = mobName;
    }

    public int getRemainingCooldownSeconds() {
        return this.remainingCooldownSeconds;
    }

    public void setRemainingCooldownSeconds(int remainingCooldownSeconds) {
        this.remainingCooldownSeconds = remainingCooldownSeconds;
    }

    public int getRemainingWarnupSeconds() {
        return this.remainingWarnupSeconds;
    }

    public void setRemainingWarnupSeconds(int remainingWarnupSeconds) {
        this.remainingWarnupSeconds = remainingWarnupSeconds;
    }

    public Date getRespawnTime() {
        return this.respawnTime;
    }

    public void setRespawnTime(Date respawnTime) {
        this.respawnTime = respawnTime;
    }

    public int getBlockX() {
        return this.blockX;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public int getBlockY() {
        return this.blockY;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public int getBlockZ() {
        return this.blockZ;
    }

    public void setBlockZ(int blockZ) {
        this.blockZ = blockZ;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasBossBar() {
        return this.hasBossBar;
    }

    public void setHasBossBar(boolean hasBossBar) {
        this.hasBossBar = hasBossBar;
    }
}
