package plugin;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

public class SelectionTool implements Listener {
	
	private JavaPlugin plugin;
	
	public SelectionTool(JavaPlugin plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static HashMap<UUID, Location> firstPoint = new HashMap<>();
	public static HashMap<UUID, Location> secondPoint = new HashMap<>();
	
	public static int getLength(Location one, Location two) {
		Integer i = Integer.valueOf(1 + Math.abs(one.getBlockX() - two.getBlockX()));
		return i;
	}
	
	public static int getWidth(Location one, Location two) {
		Integer i = Integer.valueOf(1 + Math.abs(one.getBlockZ() - two.getBlockZ()));
		return i;
	}
	
	@EventHandler
	public static void playerSelect(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		
		
		if(a == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.STICK && p.getItemInHand() != null) {
			if(e.getClickedBlock() != null && e.getHand() != EquipmentSlot.OFF_HAND) {
				UUID uuid = p.getUniqueId();
				Location loc = e.getClickedBlock().getLocation();
					
				Integer selectedPoints = countPoints(uuid);
				if(selectedPoints.intValue() == 1) {
					clearPoints(uuid);
					firstPoint.put(uuid, loc);
					p.sendMessage(ChatColor.DARK_AQUA + "Start point set.");
					return;
				}
				if(selectedPoints.intValue() == 2) {
					secondPoint.put(uuid, loc);
					if(compareWorlds(point1(uuid), point2(uuid))) {
						p.sendMessage(ChatColor.DARK_AQUA + "End point set." + " (" + getLength(point1(uuid), point2(uuid)) + "x" + 
								getWidth(point1(uuid), point2(uuid)) + ")");
						clearPoints(uuid);
						return;
					}
					clearPoints(uuid);
					firstPoint.put(uuid, loc);
					p.sendMessage(ChatColor.DARK_AQUA + "Start point set.");
					return;
				}
				firstPoint.put(uuid, loc);
				p.sendMessage(ChatColor.DARK_AQUA + "Start point set.");
				return;
			}
		}
	}
	
	public static boolean compareWorlds(Location point1, Location point2) {
		if(point1.getWorld().getName().equalsIgnoreCase(point2.getWorld().getName())) {
			return true;
		} else 
			return false;
	}
	
	public static Location point1(UUID uuid) {
		if(firstPoint.containsKey(uuid)) {
			return (Location)firstPoint.get(uuid);
		} return null;
	}
	
	public static Location point2(UUID uuid) {
		if(secondPoint.containsKey(uuid)) {
			return (Location)secondPoint.get(uuid);
		} return null;
	}
	
	private static void clearPoints(UUID uuid) {
		if(firstPoint.containsKey(uuid)) {
			firstPoint.remove(uuid);
		}
		if(secondPoint.containsKey(uuid)) {
			secondPoint.remove(uuid);
		}
	}
	
	private static int countPoints(UUID uuid) {
		if(firstPoint.containsKey(uuid)) {
			return Integer.valueOf(2);
		} 
		if(secondPoint.containsKey(uuid)) {
			return Integer.valueOf(1);
		} else 
			return Integer.valueOf(0);
	}
}
