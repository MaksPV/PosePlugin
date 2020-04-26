package ru.armagidon.sit.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.scheduler.BukkitRunnable;
import ru.armagidon.sit.SitPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class UpdateChecker extends BukkitRunnable
{

    @Override
    public void run() {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=76990").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String newest = reader.lines().collect(Collectors.toList()).get(0);
            reader.close();

            String current = SitPlugin.getInstance().getDescription().getVersion();

            if (!current.equalsIgnoreCase(newest)) {
                sendNotification("§6║ §b[New version §a"+newest+" §bis available now!]§6 ║");
            } else {
                SitPlugin.getInstance().getLogger().info("Plugin Up to Date");
            }
        } catch (IOException var7) {
            Bukkit.getLogger().severe("§cCheck update fail: " + var7.getMessage());
        }

    }

    public void sendNotification(String message){
            Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(p-> {
                p.sendMessage("§6╔==================================╗");
                p.sendMessage("§6║                                                   ║");
                TextComponent link = new TextComponent("§bOpen");
                TextComponent msg = new TextComponent(message);
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new BaseComponent[]{link}));
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://www.spigotmc.org/resources/poseplugin-choose-your-favorite-pose.76990/"));
                p.spigot().sendMessage(msg);
                p.sendMessage("§6║                                                   ║");
                p.sendMessage("§6╚==================================╝");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            });
    }
}