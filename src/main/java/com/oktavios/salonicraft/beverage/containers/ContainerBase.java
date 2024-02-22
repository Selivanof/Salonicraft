package com.oktavios.salonicraft.beverage.containers;

import net.minecraft.item.GlassBottleItem;

public class ContainerBase extends GlassBottleItem {

    private final String container_name_extension;
    public ContainerBase(Settings settings, String container_extension) {
        super(settings);
        container_name_extension = container_extension;
    }

    public String getContainerNameExtension() {
        return container_name_extension;
    }

}
