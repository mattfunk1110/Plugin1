package plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Clear implements CommandExecutor, Listener {

	private int x;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(p.isOp() && s.equalsIgnoreCase("clear")) {
			if(p.getWorld().getEntities() instanceof ItemStack) {
				p.getWorld().getEntities().clear();
			}
			
		} else {
			p.sendMessage(ChatColor.DARK_AQUA + "Unable to use command.");
		}
		return false;
	}
}
