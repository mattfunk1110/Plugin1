package plugin.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.Money;

public class MoneyEvents implements Listener {

	private final JavaPlugin plugin;
	
	public MoneyEvents(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(b.getType() == Material.DIAMOND_ORE  && p.getGameMode() == GameMode.SURVIVAL) {
			if(p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
				Money.giveMoney(p.getUniqueId().toString(), 0);
			} else {
				Money.giveMoney(p.getUniqueId().toString(), 300);
				p.sendMessage(ChatColor.DARK_AQUA + "+300 coins.");
			}
		}
		if(b.getType() == Material.EMERALD_ORE  && p.getGameMode() == GameMode.SURVIVAL) {
			if(p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
				Money.giveMoney(p.getUniqueId().toString(), 0);
			} else {
				Money.giveMoney(p.getUniqueId().toString(), 400);
				p.sendMessage(ChatColor.DARK_AQUA + "+400 coins.");
			}
		}
		if(b.getType() == Material.COAL_ORE  && p.getGameMode() == GameMode.SURVIVAL) {
			if(p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
				Money.giveMoney(p.getUniqueId().toString(), 0);
			} else {
				Money.giveMoney(p.getUniqueId().toString(), 10);
				p.sendMessage(ChatColor.DARK_AQUA + "+10 coins.");
			}
		}
		if(((b.getType() == Material.WHEAT && b.getData() == 7) || (b.getType() == Material.CARROTS && b.getData() == 7) 
				|| (b.getType() == Material.POTATOES && b.getData() == 7) || (b.getType() == Material.BEETROOTS && b.getData() == 3) || 
					(b.getType() == Material.NETHER_WART_BLOCK && b.getData() == 7)) 
					&& p.getGameMode() == GameMode.SURVIVAL) {
			Random rand = new Random();
			int x = rand.nextInt(100) + 1;
			if(x <= 60) {
				Money.giveMoney(p.getUniqueId().toString(), 1);
				p.sendMessage(ChatColor.DARK_AQUA + "+1 coin.");
			}
		}
	}

	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent e) {
		
		if((e.getEntity().getKiller() instanceof Player) && (e.getEntity() instanceof Zombie) || (e.getEntity() instanceof Skeleton) || (e.getEntity() instanceof Creeper)
				|| (e.getEntity() instanceof Blaze) || (e.getEntity() instanceof Enderman) || (e.getEntity() instanceof Ghast) || (e.getEntity() instanceof Slime) 
					|| (e.getEntity() instanceof MagmaCube) || (e.getEntity() instanceof Witch)) {
			Player p = (Player) e.getEntity().getKiller();
			if(e.getEntity().getKiller() == p) {
				Random rand = new Random();
				int x = rand.nextInt(20) + 1;
				Money.giveMoney(p.getUniqueId().toString(), x);
				p.sendMessage(ChatColor.DARK_AQUA + "+" + x + " coins.");
			}
		}
		if((e.getEntity().getKiller() instanceof Player) && (e.getEntity() instanceof EnderDragon) || (e.getEntity() instanceof Wither)) {
			Player p = (Player) e.getEntity().getKiller();
			if(e.getEntity().getKiller() == p) {
				Random rand = new Random();
				int x = rand.nextInt(4750) + 1500;
				Money.giveMoney(p.getUniqueId().toString(), x);
				p.sendMessage(ChatColor.DARK_AQUA + "+" + ChatColor.AQUA + x + ChatColor.DARK_AQUA + " coins to killing the " + e.getEntity());
			}
		}
	}
}
