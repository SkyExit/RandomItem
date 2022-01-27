package de.laurinhummel.randomitem.commands;

import de.laurinhummel.randomitem.Main;
import de.laurinhummel.randomitem.mngrs.TimerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        TimerManager timer = Main.getPlugin().getTimer();
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Please do /timer <start/play/resume/pause/stop/reset>");
            return true;
        }
        switch (args[0]) {
            case "start", "play", "resume" -> {
                timer.setRunning(true);
                player.sendMessage(ChatColor.AQUA + "Timer " + ChatColor.GOLD + "started!");
            }
            case "pause", "stop" -> {
                timer.setRunning(false);
                player.sendMessage(ChatColor.DARK_RED + "Timer " + ChatColor.RED + "stopped!");
            }
            case "reset" -> {
                timer.setRunning(false);
                timer.resetTimer();
                player.sendMessage(ChatColor.GRAY + "Timer " + ChatColor.DARK_GRAY + "RESET!");
            }
            default -> player.sendMessage(ChatColor.RED + "Please do /timer <start/play/resume/pause/stop/reset>");

        }
        return false;
    }
}
