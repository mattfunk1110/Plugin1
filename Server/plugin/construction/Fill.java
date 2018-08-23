package plugin.construction;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
		if(p.isOp() && s.equalsIgnoreCase("fill")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.DARK_AQUA + "Set a radius retard");
			}
			if(args.length == 1) {
				int x = Integer.parseInt(args[0]);
				p.sendMessage(ChatColor.DARK_AQUA + "Radius set to " + args[0]);
				radius.put(p.getUniqueId(), x);
			}
		} else {
			p.sendMessage(ChatColor.RED + "Unable to use command.");
			return true;
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
			int radius1 = radius.get(uuid);
			
			Vector center = new BlockVector(b.getX(), b.getY(), b.getZ());
			for (int x = -radius1; x <= radius1; x++) {
				for (int z = -radius1; z <= radius1; z++) {
					Vector position = center.clone().add(new Vector(x, 0, z));
					block = w.getBlockAt(position.toLocation(w));
					
					 if (center.distance(position) <= radius1 + 0.5 && (block.isEmpty() || block.getType() == Material.GRASS
							 || block.getType() == Material.TALL_GRASS || block.getType() == Material.FERN || block.getType() == Material.LARGE_FERN)) {
                         w.getBlockAt(position.toLocation(w)).setType(b.getType());
                         p.sendMessage(ChatColor.DARK_AQUA + "Area filled.");
                     }
				}
			}
		}
		radius.remove(uuid);
	}
	
}
