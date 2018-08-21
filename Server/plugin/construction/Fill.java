package plugin.construction;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class Fill implements CommandExecutor, Listener {
	
	public static HashMap<UUID, Integer> radius = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(s.equalsIgnoreCase("fill")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.DARK_AQUA + "Set a radius retard");
			}
			if(args.length == 1) {
				int x = Integer.parseInt(args[0]);
				p.sendMessage(ChatColor.DARK_AQUA + "Radius set to " + args[0]);
				radius.put(p.getUniqueId(), x);
			}
		
		
		
		
		}
		return false;
	}
	
	@EventHandler
	public static void onBlockPlaceEvent(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		Block b = e.getBlockPlaced();
		Block block;
		World w = b.getWorld();
		
		if(radius.containsKey(uuid)) {
			Bukkit.broadcastMessage("1");
			int radius1 = radius.get(uuid);
			
			Vector center = new BlockVector(b.getX(), b.getY(), b.getZ());
			Bukkit.broadcastMessage(center.toLocation(w) + " [center]");
			for (int x = -radius1; x <= radius1; x++) {
				Bukkit.broadcastMessage("2");
				for (int z = -radius1; z <= radius1; z++) {
					Vector position = center.clone().add(new Vector(x, 0, z));
					block = w.getBlockAt(position.toLocation(w));
					
					 if (center.distance(position) <= radius1 + 0.5 && block.isEmpty()) {
                         w.getBlockAt(position.toLocation(w)).setType(b.getType());
                         Bukkit.broadcastMessage(position.toLocation(w).getBlockX() + " [x]");
                         Bukkit.broadcastMessage(position.toLocation(w).getBlockZ() + " [z]");
                         
                     }
				}
			}
		}
		radius.remove(uuid);
	}
	
}
