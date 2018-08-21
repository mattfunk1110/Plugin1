package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		
		if(p.isOp()) {
			if(s.equalsIgnoreCase("gamemode")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.DARK_AQUA + "Enter a gamemode, dumbass.");
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s") && p.getGameMode() != GameMode.SURVIVAL) {
						if(p.getGameMode() == GameMode.SURVIVAL) {
							p.sendMessage(ChatColor.RED + "Gamemode already set to survival.");
						} else {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to survival.");
						}
					}
					if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c") && p.getGameMode() != GameMode.CREATIVE) {
						if(p.getGameMode() == GameMode.CREATIVE) {
							p.sendMessage(ChatColor.RED + "Gamemode already set to creative.");
						} else {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to creative.");
						}
					}
					if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a") && p.getGameMode() != GameMode.ADVENTURE) {
						if(p.getGameMode() == GameMode.ADVENTURE) {
							p.sendMessage(ChatColor.RED + "Gamemode already set to adventure.");
						} else {
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to adventure.");
						}
					}
					if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp") && p.getGameMode() != GameMode.SPECTATOR) {
						if(p.getGameMode() == GameMode.SPECTATOR) {
							p.sendMessage(ChatColor.RED + "Gamemode already set to spectator.");
						} else {
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to spectator.");
						}
					}
				}
				if(args.length == 2) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + "Player not found.");
					} else {
						if(args[1].equalsIgnoreCase("0") || args[1].equalsIgnoreCase("s") && target.getGameMode() != GameMode.SURVIVAL) {
							if(target.getGameMode() == GameMode.SURVIVAL) {
								p.sendMessage(ChatColor.RED + "Player already in survival mode.");
							} else {
								target.setGameMode(GameMode.SURVIVAL);
								target.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to survival.");
								p.sendMessage(ChatColor.DARK_AQUA + "Set " +ChatColor.AQUA + args[0] + ChatColor.DARK_AQUA +  "'s gamemode to survival.");
							}
						}
						if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("c") && target.getGameMode() != GameMode.CREATIVE) {
							if(target.getGameMode() == GameMode.CREATIVE) {
								p.sendMessage(ChatColor.RED + "Player already in creative mode.");
							} else {
								target.setGameMode(GameMode.CREATIVE);
								target.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to creative.");
								p.sendMessage(ChatColor.DARK_AQUA + "Set " +ChatColor.AQUA + args[0] + ChatColor.DARK_AQUA +  "'s gamemode to creative.");
							}
						}
						if(args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("a") && target.getGameMode() != GameMode.ADVENTURE) {
							if(target.getGameMode() == GameMode.ADVENTURE) {
								p.sendMessage(ChatColor.RED + "Player already in adventure mode.");
							} else {
								target.setGameMode(GameMode.ADVENTURE);
								target.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to adventure.");
								p.sendMessage(ChatColor.DARK_AQUA + "Set " +ChatColor.AQUA + args[0] + ChatColor.DARK_AQUA +  "'s gamemode to adventure.");
							}
						}
						if(args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("sp") && target.getGameMode() != GameMode.SPECTATOR) {
							if(target.getGameMode() == GameMode.SPECTATOR) {
								p.sendMessage(ChatColor.RED + "Player already in spectator mode.");
							} else {
								target.setGameMode(GameMode.SPECTATOR);
								target.sendMessage(ChatColor.DARK_AQUA + "Gamemode set to spectator.");
								p.sendMessage(ChatColor.DARK_AQUA + "Set " +ChatColor.AQUA + args[0] + ChatColor.DARK_AQUA +  "'s gamemode to spectator.");
							}
						}
					}
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "Unable to use command.");
		}
		return false;
	}
}
