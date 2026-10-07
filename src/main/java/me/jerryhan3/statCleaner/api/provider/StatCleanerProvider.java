package me.jerryhan3.statCleaner.api.provider;

import me.jerryhan3.statCleaner.api.StatCleanerApi;
import org.bukkit.Bukkit;

public class StatCleanerProvider {
    private static StatCleanerApi instance;

    public static StatCleanerApi get() {
        if (instance == null) {
            instance = Bukkit.getServicesManager().load(StatCleanerApi.class);
        }
        return instance;
    }

    public static void register(StatCleanerApi api) {
        instance = api;
    }
}
