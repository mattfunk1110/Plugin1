package plugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.commands.Clear;
import plugin.commands.Gamemode;
import plugin.commands.God;
import plugin.commands.Nickname;
import plugin.commands.Setspawn;
import plugin.construction.Fill;
import plugin.construction.SniperTool;
import plugin.construction.Sphere;
import plugin.events.Abilities;
import plugin.events.ChatEvents;
import plugin.events.GrapplingHook;
import plugin.events.Kits;
import plugin.events.MoneyEvents;
import plugin.events.WorldEvents;


public class Main extends JavaPlugin {
	
	public static Main plugin = null;
	public static FileConfiguration config;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile desc = this.getDescription();
		System.out.println(desc.getName() + " version " + desc.getVersion() + " is enabled!");
		
		plugin = this;
		config = getConfig();
		
		registerCommands();
		registerEvents();
	}
	
	@Override 
	public void onDisable() {
		plugin = null;
	}
	
	public static void saveFile() {
		plugin.saveConfig();
	}
	
	public static void saveConfig(FileConfiguration config) {
		File file = new File("config.yml");
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerCommands() {
		getCommand("nickname").setExecutor(new Nickname());
		getCommand("money").setExecutor(new Money());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("god").setExecutor(new God());
		getCommand("setspawn").setExecutor(new Setspawn());
		getCommand("spawn").setExecutor(new Setspawn());
		getCommand("world").setExecutor(new World());
		getCommand("fill").setExecutor(new Fill());
		getCommand("sphere").setExecutor(new Sphere());
		getCommand("brush").setExecutor(new SniperTool());
		
	}
	
	public void registerEvents() {
		new ChatEvents(this);
		new MoneyEvents(this);
		new WorldEvents(this);
		new Kits(this);
		new GrapplingHook(this);
		new Abilities(this);
		new SelectionTool(this);
		Bukkit.getServer().getPluginManager().registerEvents(new Fill(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Sphere(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SniperTool(), this);
		
	}
}
