package net.zusz.zcoffeecraft2.block.custom.enums;

import net.minecraft.util.StringRepresentable;

public enum RoastType implements StringRepresentable {
    LIGHT("light"),
    MEDIUM("medium"),
    DARK("dark");

    private final String name;

    RoastType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}