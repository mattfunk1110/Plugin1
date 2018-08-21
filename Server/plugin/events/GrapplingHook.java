package plugin.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Arrow.PickupStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GrapplingHook implements Listener {

	private final JavaPlugin plugin;
	private ItemStack GrapplingHook = new ItemStack(Material.CARROT_ON_A_STICK, 1);
	private ItemMeta grappleMeta = GrapplingHook.getItemMeta();
	private ArrayList<String> gH = new ArrayList<String>();
	
	public GrapplingHook(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	public ItemStack Grapple() {
		grappleMeta.setDisplayName(ChatColor.AQUA + "Grappling Hook");
		grappleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		if(!gH.contains(ChatColor.GRAY + "Grapple")) {
			gH.add(ChatColor.GRAY + "Grapple");
		}
		grappleMeta.setLore(gH);
		grappleMeta.equals(true);
		GrapplingHook.setItemMeta(grappleMeta);
		GrapplingHook.addEnchantment(Enchantment.DURABILITY, 1);
		return GrapplingHook;
	}
	
	@EventHandler
	public void signPlace(SignChangeEvent e) {
		if(e.getLine(0).contains("[Grappling Hook]")) {
			e.setLine(0, ChatColor.DARK_BLUE + "[" + ChatColor.GOLD + "Grappling Hook" + ChatColor.DARK_BLUE + "]");
		}
	}
	
	@EventHandler
	public void onPlayerClickEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		ItemStack i = p.getItemInHand();
		Grapple();
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains(ChatColor.DARK_BLUE + "[" + ChatColor.GOLD + "Grappling Hook" + ChatColor.DARK_BLUE + "]")) {
					p.getInventory().addItem(new ItemStack(GrapplingHook));
				}
			}
		}
		
		if(i.getType() == Material.CARROT_ON_A_STICK) {
			if(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
				Arrow arrow = p.launchProjectile(Arrow.class);
				arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
				Vector v = p.getLocation().getDirection();
				Bukkit.getPlayer(p.getName()).getWorld().playSound(p.getLocation(), Sound.ENTITY_LINGERING_POTION_THROW, 1, 1);
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						if(arrow.isInBlock()) {
							p.setVelocity(v.multiply(2.0D));
							arrow.remove();
						}	
					}
				};
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if(arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if(p.getItemInHand().getType() == Material.CARROT_ON_A_STICK) {
					if(p.getItemInHand() == GrapplingHook) {
						if(GrapplingHook.getItemMeta() == grappleMeta) {
							e.setDamage(0.0);
						}
					}
				}
			}
		}
	}
	
	
}