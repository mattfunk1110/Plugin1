package plugin.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WorldEvents implements Listener {

	private Random rand = new Random();
	private final int x = rand.nextInt(10);
	private final int a = rand.nextInt(10);
	private final int b = rand.nextInt(10);
	private final int c = rand.nextInt(10);
	private final int d = rand.nextInt(10);
	private final int f = rand.nextInt(10);
	private final JavaPlugin plugin;
	
	public WorldEvents(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onPlayerJumpEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		double jump = e.getTo().getY();
		double oldJump = e.getFrom().getY();
		
		if(p.getWorld().getName().equalsIgnoreCase("moon")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 999999999, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 1));
			if(jump > oldJump + .25) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 1));
			}
		}
	}
	
	@EventHandler
	public void signPlace(SignChangeEvent e) {
		if(e.getLine(0).contains("[World]")) {
			e.setLine(0, ChatColor.DARK_BLUE + "[" + ChatColor.DARK_GREEN + "World" + ChatColor.DARK_BLUE + "]");
		}
		if(e.getLine(1).contains("moon")) {
			e.setLine(1, ChatColor.DARK_PURPLE + "moon");
		}
		if(e.getLine(1).contains("spawn")) {
			e.setLine(1, ChatColor.DARK_PURPLE + "spawn");
		}
	}
	
	@EventHandler
	public void onPlayerClickEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains(ChatColor.DARK_BLUE + "[" + ChatColor.DARK_GREEN + "World" + ChatColor.DARK_BLUE + "]")) {
					if(sign.getLine(1).contains(ChatColor.DARK_PURPLE + "moon")) {
						p.teleport(Bukkit.getWorld("moon").getSpawnLocation());
					}
				}
				if(sign.getLine(0).contains(ChatColor.DARK_BLUE + "[" + ChatColor.DARK_GREEN + "World" + ChatColor.DARK_BLUE + "]")) {
					if(sign.getLine(1).contains(ChatColor.DARK_PURPLE + "spawn")) {
							p.removePotionEffect(PotionEffectType.SLOW_FALLING);
							p.removePotionEffect(PotionEffectType.SLOW);
							p.teleport(Bukkit.getWorld("world").getSpawnLocation());
					}	
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMobSpawnEvent(CreatureSpawnEvent e) {
		World w = e.getEntity().getLocation().getWorld();
		
		if(w.getName().equalsIgnoreCase("moon")) {
			if(e.getEntityType() == EntityType.ZOMBIE) {
				Zombie zombie = (Zombie) e.getEntity();
				zombie.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 99999999, 1));
				if(x >= 4) {
					ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, a);
					zombie.getEquipment().setHelmetDropChance((float) 0.25012);
					zombie.getEquipment().setHelmet(helmet);
					
					ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, b);
					zombie.getEquipment().setChestplateDropChance((float) 0.17793);
					zombie.getEquipment().setChestplate(chestplate);
					
					ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, c);
					zombie.getEquipment().setLeggingsDropChance((float) 0.21231);
					zombie.getEquipment().setLeggings(leggings);
					
					ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, d);
					zombie.getEquipment().setBootsDropChance((float) 0.23233);
					zombie.getEquipment().setBoots(boots);
					
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, f);
					sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
					zombie.getEquipment().setItemInHandDropChance((float) 0.09123);
					zombie.getEquipment().setItemInHand(sword);

					zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
				}
				if(x <= 3) {
					ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, a);
					zombie.getEquipment().setHelmetDropChance((float) 0.05642);
					zombie.getEquipment().setHelmet(helmet);
					
					ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, b);
					zombie.getEquipment().setHelmetDropChance((float) 0.04963);
					zombie.getEquipment().setHelmet(chestplate);
					
					ItemStack leggings = new ItemStack(Material.GOLDEN_LEGGINGS);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, c);
					zombie.getEquipment().setHelmetDropChance((float) 0.07111);
					zombie.getEquipment().setHelmet(leggings);
					
					ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, d);
					zombie.getEquipment().setHelmetDropChance((float) 0.06236);
					zombie.getEquipment().setHelmet(boots);
					
					ItemStack bow = new ItemStack(Material.BOW);
					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, f);
					bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
					bow.addEnchantment(Enchantment.MENDING, 1);
					zombie.getEquipment().setHelmetDropChance((float) 0.04123);
					zombie.getEquipment().setHelmet(bow);
				}
			}
			if(e.getEntityType() == EntityType.SKELETON) {
				Skeleton skeleton = (Skeleton) e.getEntity();
				skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 9999999, 1));
				if(x >= 2) {	
					ItemStack helmet = new ItemStack(Material.IRON_HELMET);
					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, a);
					skeleton.getEquipment().setHelmetDropChance((float) 0.13632);
					skeleton.getEquipment().setHelmet(helmet);
					
					ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, b);
					skeleton.getEquipment().setChestplateDropChance((float) 0.15342);
					skeleton.getEquipment().setChestplate(chestplate);
					
					ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
					leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, c);
					skeleton.getEquipment().setLeggingsDropChance((float) 0.18012);
					skeleton.getEquipment().setLeggings(leggings);
					
					ItemStack boots = new ItemStack(Material.IRON_BOOTS);
					boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, d);
					skeleton.getEquipment().setBootsDropChance((float) 0.11235);
					skeleton.getEquipment().setBoots(boots);
					
					ItemStack bow = new ItemStack(Material.BOW);
					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, f);
					bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
					bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
					bow.addEnchantment(Enchantment.MENDING, 1);
					skeleton.getEquipment().setItemInHandDropChance((float) 0.02123);
					skeleton.getEquipment().setItemInHand(bow);
				}
			}
		}
	}
	
	@EventHandler 
	public void ThunderChangeEvent(ThunderChangeEvent e) {
		
		boolean thunder = e.toThunderState();
	
		if(thunder) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onRainChangeEvent(WeatherChangeEvent e) {
		
		boolean rain = e.toWeatherState();
		
		if(rain) {
			e.setCancelled(true);
		}
	}
}