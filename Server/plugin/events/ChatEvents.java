package plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.Main;

@SuppressWarnings("deprecation")
public class ChatEvents implements Listener {

	private final JavaPlugin plugin;
	
	public ChatEvents(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(!Main.config.contains("Stats." + p.getUniqueId().toString() + "")) {
			Main.config.set("Stats." + p.getUniqueId().toString() + ".Coins", 0);
			Main.config.set("Stats." + p.getUniqueId().toString() + ".Name", p.getName());
			Main.config.set("Stats." + p.getUniqueId().toString() + ".Nickname", p.getDisplayName());
			Main.config.set("Stats." + p.getUniqueId().toString() + ".ListName", p.getPlayerListName());
			Main.config.set("Stats." + p.getUniqueId().toString() + ".CustomName", p.getCustomName());
			Main.saveFile();
		}
		Main.config.set("Stats." + p.getUniqueId().toString() + ".Name", p.getName());
		Main.saveFile();
		
		String username = Main.config.getString("Stats." + p.getUniqueId() + ".Nickname");
		e.setJoinMessage(ChatColor.GRAY + username + " connected.");
		p.setPlayerListName(username);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(ChatColor.GRAY + Main.config.getString("Stats." + p.getUniqueId() + ".Nickname") +  " disconnected.");
	}
	
	@EventHandler
	public void onPlayerChatEvent(PlayerChatEvent e ) {
		Player p = (Player) e.getPlayer();
		String username = Main.config.getString("Stats." + p.getUniqueId() + ".Nickname");
		
		if(p.isOp()) {
			p.setDisplayName(ChatColor.DARK_RED + username + ChatColor.WHITE);
		} else {
			p.setDisplayName(ChatColor.GOLD + username + ChatColor.WHITE);
		}
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		
		if(p.getKiller() instanceof Zombie) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a zombie.");
		}
		if(p.getKiller() instanceof Skeleton) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was shot to death by a skeleton.");
		}
		if(p.getKiller() instanceof Spider) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a spider.");
		}
		if(p.getKiller() instanceof Enderman) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by an enderman.");
		}
		if(p.getKiller() instanceof CaveSpider) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a cave spider.");
		}
		if(p.getKiller() instanceof Drowned) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a drowned zombie.");
		}
		if(p.getKiller() instanceof Husk) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a Husk.");
		}
		if(p.getKiller() instanceof Witch) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by magic.");
		}
		if(p.getKiller() instanceof Slime) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a slime.");
		}
		if(p.getKiller() instanceof Creeper) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was blown up by a creeper.");
		}
		if(p.getKiller() instanceof CaveSpider) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a cave spider.");
		}
		if(p.getKiller() instanceof Drowned) {
			e.setDeathMessage(ChatColor.YELLOW + "" + p.getDisplayName() + " was killed by a drowned zombie.");
		}
		
	}
}