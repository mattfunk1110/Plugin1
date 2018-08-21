package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(p.isOp()) {
			if(s.equalsIgnoreCase("god")) {
				if(args.length == 0 && p.isInvulnerable() == false) {
					p.setInvulnerable(true);
					p.sendMessage(ChatColor.DARK_AQUA + "Godmode enabled.");
					return true;
				} 
				if (args.length == 0 && p.isInvulnerable() == true) {
					p.setInvulnerable(false);
					p.sendMessage(ChatColor.DARK_AQUA + "Godmode disabled.");
					return false;
				} else {
					if(args.length == 1) {
						Player target = Bukkit.getPlayerExact(args[0]);
						if(target == null) {
							p.sendMessage(ChatColor.RED + "Player not found.");
						} else {
							if(args.length == 1 && target.isInvulnerable() == false) {
								target.setInvulnerable(true);
								target.sendMessage(ChatColor.DARK_AQUA + "Godmode enabled.");
								p.sendMessage(ChatColor.DARK_AQUA + "Enabled godmode for " + ChatColor.AQUA + args[0]);
								return true;
							} else {
								target.setInvulnerable(false);
								target.sendMessage(ChatColor.DARK_AQUA + "Godmode disabled.");
								p.sendMessage(ChatColor.DARK_AQUA + "Disabled godmode for " + ChatColor.AQUA + args[0]);
								return false;
							}
						}
					}
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "Unable to use command.");
		}
		return false;
	}
}
