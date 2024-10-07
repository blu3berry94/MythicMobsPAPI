package me.blubriu.mythicMobsPAPI.model;

import java.util.List;

public class MobDefine {
    private int id;

    private String spawnerMobName;

    private List<String> alias;

    private String displayName;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpawnerMobName() {
        return this.spawnerMobName;
    }

    public void setSpawnerMobName(String spawnerMobName) {
        this.spawnerMobName = spawnerMobName;
    }

    public List<String> getAlias() {
        return this.alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}