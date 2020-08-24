package ru.armagidon.poseplugin.configuration;

import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager extends SelfRepairableConfig
{

    public ConfigManager() {
        super("config");
    }

    @SneakyThrows
    protected YamlConfiguration generateDefaults(FileConfiguration configuration) {
        YamlConfiguration defaults = new YamlConfiguration();
        //Add defaults for config
        {
            defaults.set("locale", "en");
            defaults.set("check-for-updates", true);
            defaults.set("x-mode", false);
            {
                ConfigurationSection swim = defaults.createSection("swim");
                swim.set("static", false);
                swim.set("stand-up-when-damaged", true);
                swim.set("enabled", true);
            }
            {
                ConfigurationSection sit = defaults.createSection("sit");
                sit.set("stand-up-when-damaged", true);
            }
            {
                ConfigurationSection lay = defaults.createSection("lay");
                lay.set("stand-up-when-damaged", true);
                lay.set("view-distance", 20);
                lay.set("head-rotation", true);
                lay.set("swing-animation", true);
                lay.set("update-equipment", true);
                lay.set("update-overlays", true);
                lay.set("prevent-use-when-invisible", false);
            }
            if (configuration.getBoolean("x-mode")) {
                {
                    ConfigurationSection wave = defaults.createSection("wave");
                    wave.set("enabled", true);
                    wave.set("stand-up-when-damaged", true);
                    wave.set("disable-when-shift",false);
                }
                {
                    ConfigurationSection point = defaults.createSection("point");
                    point.set("enabled", true);
                    point.set("stand-up-when-damaged", true);
                    point.set("disable-when-shift",false);
                }
                {
                    ConfigurationSection handshake = defaults.createSection("handshake");
                    handshake.set("enabled",true);
                    handshake.set("stand-up-when-damaged",true);
                    handshake.set("disable-when-shift",false);
                }
            }
        }
        return defaults;
    }
}