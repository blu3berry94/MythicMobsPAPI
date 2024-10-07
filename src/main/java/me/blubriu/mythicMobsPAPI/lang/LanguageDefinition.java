package me.blubriu.mythicMobsPAPI.lang;

public enum LanguageDefinition {
    CONFIG_RELOAD_SUCCESS("CONFIG_RELOAD_SUCCESS", "config file reload successfully"),
    LANGUAGE_FILE_LOAD_SUCCESS("LANGUAGE_FILE_LOAD_SUCCESS", "language file load successfully"),
    LANGUAGE_FILE_LOAD_FAIL("LANGUAGE_FILE_LOAD_FAIL", "language file load fail"),
    LANGUAGE_FILE_UPDATE_SUCCESS("LANGUAGE_FILE_UPDATE_SUCCESS", "language file update successfully"),
    LANGUAGE_FILE_UPDATE_FAIL("LANGUAGE_FILE_UPDATE_FAIL", "language file update fail"),
    RESPAWN_SUCCESS("RESPAWN_SUCCESS", "successfully, mob will respawn in 5 seconds"),
    TELEPORT_TO_RESPAWN_LOCATION("TELEPORT_TO_RESPAWN_LOCATION", "to respawn location]"),
    RESPAWN_BOSS("RESPAWN_BOSS", "boss"),
    RESPAWN_REMAIN_TIME("RESPAWN_REMAIN_TIME", "remain time: "),
    ESTIMATED_RESPAWN_TIME("ESTIMATED_RESPAWN_TIME", "respawn time: "),
    PLACEHOLDER_API_NOT_FOUND("PLACEHOLDER_API_NOT_FOUND", "not found"),
    PLACEHOLDER_DEFAULT_DISPLAY_FORMAT("PLACEHOLDER_DEFAULT_DISPLAY_FORMAT", "remain time:{0}, respawn time: {1}");

    private String key;

    private String defaultMsg;

    LanguageDefinition(String key, String defaultMsg) {
        this.key = key;
        this.defaultMsg = defaultMsg;
    }

    public String getKey() {
        return this.key;
    }

    public String getDefaultMsg() {
        return this.defaultMsg;
    }
}