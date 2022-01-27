package de.laurinhummel.randomitem.mngrs;

import de.laurinhummel.randomitem.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class BarManager implements Listener {

    private final double timerSeconds = Main.getPlugin().getConfig().getInt("seconds");
    private int displaySec = (int) timerSeconds;

    public static BossBar bar;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FileConfiguration config = Main.getPlugin().getConfig();
        bar = Bukkit.createBossBar("Initiate..." , BarColor.GREEN, BarStyle.SOLID);
        bar.addPlayer(e.getPlayer());
        bar.setProgress(1.0);
    }

    public void buildBar() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            double st = 1.0;
            final double toRemove = 1/timerSeconds;
            @Override
            public void run() {
                if(Objects.equals(Main.getPlugin().getTimer().isRunning(), true)) {
                    bar.setTitle(ChatColor.BLUE + "Next item in " + ChatColor.GREEN + displaySec + "s");
                    displaySec = displaySec - 1;
                    FileConfiguration config = Main.getPlugin().getConfig();
                    st = st - toRemove;
                    bar.setProgress(st);
                    if(st <= toRemove) {
                        st = 1.0 + toRemove;
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            p.getInventory().addItem(Main.getPlugin().getItemManager().getRandomItem());
                        }
                        displaySec = (int) timerSeconds;
                    }
                }
            }
        }, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }
}
