package plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


public class Abilities implements Listener {
	
	private final JavaPlugin plugin;
	private int timer = 0;
	
	public Abilities(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler 
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(p.isSprinting()) {
			new BukkitRunnable() {

				@Override
				public void run() {
					if(p.isSprinting()) {
						timer++;
					} else {
						timer = 0;
					}
					if(timer >= 150 && p.isSprinting()) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999999, 0));
					}
					if(timer >= 640 && p.isSprinting()) {
						p.removePotionEffect(PotionEffectType.SPEED);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
					}
					if(timer >= 1580 && p.isSprinting()) {
						p.removePotionEffect(PotionEffectType.SPEED);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 4));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 2));
//						Bukkit.getPlayer(p.getName()).getWorld().playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1, 1);
					}
					if(timer >= 3880 && p.isSprinting()) {
						p.removePotionEffect(PotionEffectType.SPEED);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 6));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999999, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 99999999, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999999, 0));
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
//						Bukkit.getPlayer(p.getName()).getWorld().playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1, 1);
					}
				}
			}.runTaskTimer(plugin, 60, 20000);
		} if (p.isSprinting() == false && timer > 1) {
			p.removePotionEffect(PotionEffectType.SPEED);
			p.removePotionEffect(PotionEffectType.JUMP);	
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			p.removePotionEffect(PotionEffectType.HUNGER);
			p.removePotionEffect(PotionEffectType.GLOWING);
		}
	}
}
