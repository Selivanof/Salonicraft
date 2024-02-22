package com.oktavios.salonicraft.util;

// Using mB as the unit of measurement to be consistent with the majority of the modding community
public class FluidUtils {

    public static long convertToMilibuckets(long droplets) {
        return (droplets / 81);
    }

    public static long convertToDroplets(long mB) {
        return mB * 81;
    }

}
