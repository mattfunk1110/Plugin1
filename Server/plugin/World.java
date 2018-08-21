package plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class World implements CommandExecutor {
	 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(s.equalsIgnoreCase("world")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.DARK_AQUA + "Current worlds: " + ChatColor.AQUA + "Spawn" + ChatColor.DARK_AQUA + ", " 
						+ ChatColor.AQUA + "Survival" + ChatColor.DARK_AQUA + ", " + ChatColor.AQUA + "Moon" + ChatColor.DARK_AQUA + ", " 
							+ ChatColor.AQUA + "Nether" + ChatColor.DARK_AQUA + ", " + ChatColor.AQUA + "End");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("Spawn")) {
					if(p.getWorld() == Bukkit.getWorld("world")) {
						p.sendMessage(ChatColor.RED + "Already in the spawn world.");
					} else if(Bukkit.getWorld("world") == null) {
						WorldCreator wc = new WorldCreator("world");
						wc.environment(Environment.NORMAL);
						wc.generateStructures(true);
						Bukkit.createWorld(wc);
					} else {
						p.teleport(Bukkit.getWorld("world").getSpawnLocation());
						p.removePotionEffect(PotionEffectType.SLOW_FALLING);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.sendMessage(ChatColor.DARK_AQUA + "Welcome to the spawn world.");
					}
				}
				if(args[0].equalsIgnoreCase("survival")) {
					if(p.getWorld() == Bukkit.getWorld("main")) {
						p.sendMessage(ChatColor.RED + "Already in the survival world.");
					} else if(Bukkit.getWorld("main") == null) {
						WorldCreator wc = new WorldCreator("survival");
						wc.environment(Environment.NORMAL);
						wc.generateStructures(true);
						Bukkit.createWorld(wc);
					} else {
						p.teleport(Bukkit.getWorld("main").getSpawnLocation());
						p.removePotionEffect(PotionEffectType.SLOW_FALLING);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.sendMessage(ChatColor.DARK_AQUA + "Welcome to the survival world.");
					}
				}
				if(args[0].equalsIgnoreCase("moon")) {
					if(p.getWorld() == Bukkit.getWorld("moon")) {
						p.sendMessage(ChatColor.RED + "Already on the moon."); 
					} else if(Bukkit.getWorld("moon") == null) {
						WorldCreator wc = new WorldCreator("moon");
						wc.environment(Environment.NORMAL);
						wc.generateStructures(false);
						Bukkit.createWorld(wc);
					}  else {
						p.teleport(Bukkit.getWorld("moon").getSpawnLocation());
						p.sendMessage(ChatColor.DARK_AQUA + "Welcome to the moon.");
					}
				}
			}
		}
		return false;
	}
}