package de.laurinhummel.randomitem;

import de.laurinhummel.randomitem.commands.TimerCommand;
import de.laurinhummel.randomitem.mngrs.BarManager;
import de.laurinhummel.randomitem.mngrs.RandomItemManager;
import de.laurinhummel.randomitem.mngrs.TimerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin {
    private static Main plugin;
    private TimerManager timerManager;
    public static List<Material> items = new ArrayList<>();

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        timerManager = new TimerManager(false, 0);
        PluginManager pluginManager = Bukkit.getPluginManager();

        getCommand("timer").setExecutor(new TimerCommand());

        pluginManager.registerEvents(new BarManager(), this);

        items.addAll(Arrays.<Material>stream(Material.values()).toList());

        FileConfiguration config = getPlugin().getConfig();
        getConfig();
        config.addDefault("seconds", 30);
        config.options().copyDefaults(true);
        saveConfig();

        getBarManager().buildBar();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
    public TimerManager getTimer() { return timerManager; }
    public RandomItemManager getItemManager() { return new RandomItemManager(); }
    public BarManager getBarManager() { return new BarManager(); }
}
