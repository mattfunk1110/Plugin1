package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.Main;

public class Nickname implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		
		if(p.isOp()) {
			if(s.equalsIgnoreCase("nickname") || s.equalsIgnoreCase("nick")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.DARK_AQUA + "Enter a nickname, dumbass.");
				} 
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("default")) {
						p.setDisplayName(p.getName());
						p.setPlayerListName(p.getName());
						p.setCustomName(p.getName());
						p.sendMessage(ChatColor.DARK_AQUA + "Nickname removed.");
						
						Main.config.set("Stats." + p.getUniqueId().toString() + ".Nickname", p.getName());
						Main.config.set("Stats." + p.getUniqueId().toString() + ".ListName", p.getName());
						Main.config.set("Stats." + p.getUniqueId().toString() + ".CustomName", p.getName());
						Main.saveFile();
					} else {
						p.setDisplayName(args[0]);
						p.setCustomName(args[0]);
						p.setPlayerListName(ChatColor.WHITE + args[0]);
						p.sendMessage(ChatColor.DARK_AQUA + "Nickname set to " + ChatColor.AQUA + args[0]);
						
						Main.config.set("Stats." + p.getUniqueId().toString() + ".Nickname", args[0]);
						Main.config.set("Stats." + p.getUniqueId().toString() + ".ListName", args[0]);
						Main.config.set("Stats." + p.getUniqueId().toString() + ".CustomName", args[0]);
						Main.saveFile();
					}
				}
				if(args.length == 2) {
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + "Player not found.");
					} else if (target.isOp() && target != null) {
						target.setDisplayName(args[1]);
						target.setCustomName(args[1]);
						target.setPlayerListName(ChatColor.WHITE + args[1]);
						
						Main.config.set("Stats." + target.getUniqueId().toString() + ".Nickname", args[1]);
						Main.config.set("Stats." + target.getUniqueId().toString() + ".ListName", args[1]);
						Main.config.set("Stats." + target.getUniqueId().toString() + ".CustomName", args[1]);
						Main.saveFile();
						
						target.sendMessage(ChatColor.DARK_AQUA + "Nickname set to " + ChatColor.AQUA + args[1]);
						p.sendMessage(ChatColor.DARK_AQUA + "Set " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA 
								+ "'s nickname to " + ChatColor.AQUA + args[1]);
					} else if (target != null && args[1].equalsIgnoreCase("default")) {
						target.setDisplayName(target.getName());
						target.setPlayerListName(target.getName());
						target.setCustomName(target.getName());
						target.sendMessage(ChatColor.DARK_AQUA + "Nickname removed.");
						p.sendMessage(ChatColor.DARK_AQUA + "Removed " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s nickname.");
						
						Main.config.set("Stats." + target.getUniqueId().toString() + ".Nickname", target.getName());
						Main.config.set("Stats." + target.getUniqueId().toString() + ".ListName", target.getPlayerListName());
						Main.config.set("Stats." + target.getUniqueId().toString() + ".CustomName", target.getCustomName());
						Main.saveFile();
					} else {
						target.setDisplayName(args[1]);
						target.setCustomName(args[1]);
						target.setPlayerListName(ChatColor.WHITE + args[1]);
						
						Main.config.set("Stats." + target.getUniqueId().toString() + ".Nickname", args[1]);
						Main.config.set("Stats." + target.getUniqueId().toString() + ".ListName", args[1]);
						Main.config.set("Stats." + target.getUniqueId().toString() + ".CustomName", args[1]);
						Main.saveFile();
						
						target.sendMessage(ChatColor.DARK_AQUA + "Nickname set to " + ChatColor.AQUA + args[1]);
						p.sendMessage(ChatColor.DARK_AQUA + "Set " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA 
								+ "'s nickname to " + ChatColor.AQUA + args[1]);
					}
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "Unable to use command.");
		}
		return false;
	}
}
