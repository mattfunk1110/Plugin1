package plugin.commands;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.Main;

public class Setspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(p.isOp()) {
			if(s.equalsIgnoreCase("setspawn")) {
				World w = p.getWorld();
				Location pl = p.getLocation();
				
				Main.config.set("World." + p.getWorld().getName() + ".SpawnX", pl.getX());
				Main.config.set("World." + p.getWorld().getName() + ".SpawnY", pl.getY());
				Main.config.set("World." + p.getWorld().getName() + ".SpawnZ", pl.getZ());
				Main.config.set("World." + p.getWorld().getName() + ".SpawnYaw", pl.getYaw());
				Main.config.set("World." + p.getWorld().getName() + ".SpawnPitch", pl.getPitch());
			
				p.sendMessage(ChatColor.DARK_AQUA + "Spawn point set.");
			}
		} else {
			p.sendMessage(ChatColor.RED + "Unable to use command.");
		}
		
		if(command.getName().equalsIgnoreCase("spawn")) {
			World w = p.getWorld();
			
			double x = Main.config.getDouble("World." + p.getWorld().getName() + ".SpawnX");
			double y = Main.config.getDouble("World." + p.getWorld().getName() + ".SpawnY");
			double z = Main.config.getDouble("World." + p.getWorld().getName() + ".SpawnZ");
			float yaw = (float) Main.config.getDouble("World." + p.getWorld().getName() + ".SpawnYaw");
			float pitch = (float) Main.config.getDouble("World." + p.getWorld().getName() + ".SpawnPitch");
			
			p.teleport(new Location(w, x, y, z, yaw, pitch));
			p.sendMessage(ChatColor.DARK_AQUA + "Welcome to spawn.");
		}
		
		
		return false;
	}
}
