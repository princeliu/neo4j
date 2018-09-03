package com.semptian.neo4j.entity.base;

/**
 * @Author Princeliu
 * @Date 2018/8/31 14:54
 * @Description
 */
public enum DirectorLevelEnum {

    HIGH_LEVEL(1, "著名导演"),
    MEDIA_LEVEL(2,"一般导演"),
    NEW_COMING_LEVEL(3, "入门级导演");

    private int level;
    private String description;

    DirectorLevelEnum(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
