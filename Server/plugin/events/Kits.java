package plugin.events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Kits implements Listener {
	
	private final JavaPlugin plugin;

	private static HashMap<UUID, String> map = new HashMap<>();
	
	public static ItemStack starterHelmet = new ItemStack(Material.LEATHER_HELMET);
	public static ItemStack starterChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	public static ItemStack starterLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
	public static ItemStack starterBoots = new ItemStack(Material.LEATHER_BOOTS);
	public static ItemStack starterSword = new ItemStack(Material.STONE_SWORD);
	public static ItemStack KitFood = new ItemStack(Material.COOKED_BEEF);
	
	private boolean fullArmor = false;
	
	public Kits(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onSignPlaceEvent(SignChangeEvent e) {
		if(e.getLine(0).contains("[Kit]")) {
			e.setLine(0, ChatColor.DARK_RED + "[Kit]");
		}
		if(e.getLine(1).contains("Starter")) {
			e.setLine(1, ChatColor.BLUE + "Starter");
		}
	}
	
	@EventHandler
	public void onPlayerClickEvent(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains(ChatColor.DARK_RED + "[Kit]")) {
					if(sign.getLine(1).contains(ChatColor.BLUE + "Starter")) {
						Player p = e.getPlayer();
						starterHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
						setOwner(starterHelmet, ChatColor.AQUA + "" + p.getPlayerListName() + "'s Helmet");
						starterChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
						setOwner(starterChestplate, ChatColor.AQUA + "" + p.getPlayerListName() + "'s Chestplate");
						starterLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
						setOwner(starterLeggings, ChatColor.AQUA + "" + p.getPlayerListName() + "'s Leggings");
						starterBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
						setOwner(starterBoots, ChatColor.AQUA + "" + p.getPlayerListName() + "'s Boots");
						starterSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						setOwner(starterSword, ChatColor.AQUA + "" + p.getPlayerListName() + "'s Sword");
						KitFood.setAmount(24);
						setOwner(KitFood, "Kit Food");
						
						if(map.containsKey(p.getUniqueId()) && map.containsValue(p.getDisplayName())) {
							p.sendMessage(ChatColor.RED + "Only one kit allowed per life.");
//						}
//						else if ((!p.getInventory().contains(starterSword)) && (!p.getInventory().contains(starterHelmet)) && (!p.getInventory().contains(starterChestplate)) 
//								&& (!p.getInventory().contains(starterLeggings)) && (!p.getInventory().contains(starterBoots)) && (!p.getInventory().contains(starterFood))) {
//							map.remove(p.getUniqueId(), p.getDisplayName());
						} else {
							map.put(p.getUniqueId(), p.getDisplayName());
								
							p.getInventory().addItem(starterSword);
							p.getInventory().addItem(starterHelmet);
							p.getInventory().addItem(starterChestplate);
							p.getInventory().addItem(starterLeggings);
							p.getInventory().addItem(starterBoots);
							p.getInventory().addItem(KitFood);
								
							p.sendMessage(ChatColor.DARK_AQUA + "Received Starter Kit");
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropEvent(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		Item item = e.getItemDrop();
		
		if(item.getItemStack() == starterHelmet) {
			item.remove();
		}
		if(e.getItemDrop().getItemStack().getType() == Material.LEATHER_CHESTPLATE) {
			if(item.hasMetadata(p.getCustomName() + "'s Chestplate")); {
				item.remove();
			}
		}
		if(e.getItemDrop().getItemStack().getType() == Material.LEATHER_LEGGINGS) {
			if(item.hasMetadata(ChatColor.AQUA + "" + p.getPlayerListName() + "'s Leggings")); {
				item.remove();
			}
		}
		if(e.getItemDrop().getItemStack().getType() == Material.LEATHER_BOOTS) {
			if(item.hasMetadata(p.getCustomName() + "'s Boots")); {
				item.remove();
			}
		}
		if(e.getItemDrop().getItemStack() == starterSword) {
				item.remove();
		}
		if(e.getItemDrop().getItemStack().getType() == Material.COOKED_BEEF) {
			if(item.hasMetadata("Starter Food")); {
				item.remove();
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(p instanceof Player) {
			map.remove(p.getUniqueId(), p.getDisplayName());
			if(e.getDrops().contains(starterHelmet)) {
				e.getDrops().remove(starterHelmet);
			}
			if(e.getDrops().contains(starterChestplate)) {
				e.getDrops().remove(starterChestplate);
			}
			if(e.getDrops().contains(starterLeggings)) {
				e.getDrops().remove(starterLeggings);
			}
			if(e.getDrops().contains(starterBoots)) {
				e.getDrops().remove(starterBoots);
			}
			if(e.getDrops().contains(starterSword)) {
				e.getDrops().remove(starterSword);
			}
			if(e.getDrops().contains(KitFood)) {
				e.getDrops().remove(KitFood);
			}
		}
	}

	public ItemStack setOwner(ItemStack item, String name) {
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
	}
}