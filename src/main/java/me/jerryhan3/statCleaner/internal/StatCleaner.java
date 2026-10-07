/*
 * Copyright (c) 2025-2026. JerryHan3. 
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this software. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package me.jerryhan3.statCleaner.internal;

import me.jerryhan3.statCleaner.api.StatCleanerApi;
import me.jerryhan3.statCleaner.api.provider.StatCleanerProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import me.jerryhan3.statCleaner.internal.command.CommandMain;
import me.jerryhan3.statCleaner.internal.command.CommandReset;
import me.jerryhan3.statCleaner.internal.command.TabCompleter.TabMain;
import me.jerryhan3.statCleaner.internal.command.TabCompleter.TabReset;
import me.jerryhan3.statCleaner.internal.utils.VersionDetector;

import java.util.Objects;

public final class StatCleaner extends JavaPlugin {
    private StatCleanerApi api;
    private MessageManager messageManager;
    private CommandReset commandReset;

    @Override
    public void onEnable() {
        // Config
        this.saveDefaultConfig();
        // Messages
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) {
                getLogger().severe("Failed to initialize plugin folder!");
            }
        }
        messageManager = new MessageManager(this);
        commandReset = new CommandReset(this);
        // Command
        Objects.requireNonNull(this.getCommand("statreset")).setExecutor(commandReset);
        Objects.requireNonNull(this.getCommand("statreset")).setTabCompleter(new TabReset());
        Objects.requireNonNull(this.getCommand("statcleaner")).setExecutor(new CommandMain(this));
        Objects.requireNonNull(this.getCommand("statcleaner")).setTabCompleter(new TabMain());
        // Complete log
        getLogger().info("StatCleaner " + getDescription().getVersion() + " has been successfully loaded!");
        // Version warning
        if (!VersionDetector.isVersionAtLeast(1,9)) getLogger().warning("You are using a version that hasn't be fully supported yet. Some stats like saturation or attributes will be skipped!");

        // Start API
        this.api = commandReset.statCleanerApi;
        getServer().getServicesManager().register(StatCleanerApi.class, api, this, ServicePriority.Normal);
        StatCleanerProvider.register(api);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
