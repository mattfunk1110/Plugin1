package plugin.construction;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class SniperTool implements CommandExecutor, Listener {

	public static HashMap<UUID, Integer> radius = new HashMap<>();
	public static boolean sphereBool = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		Player p = (Player) sender;
		if(p.isOp()) {
			if(s.equalsIgnoreCase("brush")) {
				if(p.getItemInHand().getType() != Material.ARROW) {
					p.sendMessage(ChatColor.RED + "Must be holding an arrow.");
				} else {
					if(p.getItemInHand().getType() == Material.ARROW && args.length == 0) {
							p.sendMessage(ChatColor.DARK_AQUA + "What brush, dumbass");
					}
					if(p.getItemInHand().getType() == Material.ARROW && args.length == 1) {
						if(args[0].equalsIgnoreCase("off")) {
							sphereBool = false;
							radius.remove(p.getUniqueId());
							p.sendMessage(ChatColor.DARK_AQUA +"Active brush removed.");
						} else {
							p.sendMessage(ChatColor.DARK_AQUA + "Enter a radius retard");
						}
					} 
					if(p.getItemInHand().getType() == Material.ARROW && args.length == 2) {
						if(args[0].equalsIgnoreCase("sphere")) {
							sphereBool = true;
							int x = Integer.parseInt(args[1]);
							p.sendMessage(ChatColor.DARK_AQUA + "Brush radius for" + ChatColor.AQUA + " SPHERE " + ChatColor.DARK_AQUA + "set to " + ChatColor.AQUA + x);
							radius.put(p.getUniqueId(), x);
						}
					}
					if(p.getItemInHand().getType() == Material.ARROW && args.length == 3) {
						if(args[0].equalsIgnoreCase("sphere")) {
							sphereBool = true;
							int x = Integer.parseInt(args[1]);
							p.sendMessage(ChatColor.DARK_AQUA + "Brush radius for" + ChatColor.AQUA + " SPHERE " + ChatColor.DARK_AQUA + "set to " + ChatColor.AQUA + x);
							p.sendMessage(ChatColor.DARK_AQUA + "Material: " + ChatColor.AQUA + args[2]);
							radius.put(p.getUniqueId(), x);
						}
					}
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		Block b = p.getTargetBlock((Set<Material>) null, 100);
		Block block;
		World w = b.getWorld();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getType() == Material.ARROW && e.getHand() != EquipmentSlot.OFF_HAND) {
				if(sphereBool == true) {
					if(radius.containsKey(uuid)) {
						int radius1 = radius.get(uuid);
						
						Vector center = new BlockVector(b.getX(), b.getY(), b.getZ());
						for (int x = -radius1; x <= radius1; x++) {
							for(int y = -radius1; y <= radius1; y++) {
								for (int z = -radius1; z <= radius1; z++) {
									Vector position = center.clone().add(new Vector(x, y, z));
									block = w.getBlockAt(position.toLocation(w));
									
									 if (center.distance(position) <= radius1 + 0.5 && (block.isEmpty() || block.getType() == Material.GRASS
											 || block.getType() == Material.TALL_GRASS || block.getType() == Material.FERN || block.getType() == Material.LARGE_FERN)) {
				                         w.getBlockAt(position.toLocation(w)).setType(b.getType());
				                     }
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
}
