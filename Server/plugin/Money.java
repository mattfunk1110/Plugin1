package plugin;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		
		Player p = (Player) sender;
		if(s.equalsIgnoreCase("money")) {
			if(args.length == 0) {
				String amount = Main.config.getString("Stats." + p.getUniqueId() + ".Coins");
				if(amount == null) {
					p.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "Money" + ChatColor.GREEN + "]" 
							+ ChatColor.DARK_AQUA + " You have " + ChatColor.AQUA + "0" + ChatColor.DARK_AQUA + " coins.");
				} else {
					String newAmount = newFormatString(amount);
					p.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "Money" + ChatColor.GREEN + "]" 
						+ ChatColor.DARK_AQUA + " You have " + ChatColor.AQUA + newAmount + ChatColor.DARK_AQUA + " coins.");
				}
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("pay")) {
					p.sendMessage(ChatColor.DARK_AQUA + "Pay who, dumbass?");
				}
				if(args[0].equalsIgnoreCase("tostore")) {
					p.sendMessage(ChatColor.RED + "Not yet implemented");
				}
				if(args[0].equalsIgnoreCase("fromstore")) {
					p.sendMessage(ChatColor.RED + "Not yet implemented");
				}
				if(args[0].equalsIgnoreCase("give")) {
					p.sendMessage(ChatColor.DARK_AQUA + "How much dumbass?");
				}
			}
			
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("give")) {
					if(args[1].equalsIgnoreCase(args[1])) {
						int give = Integer.parseInt(args[1]);
						String newGive = newFormat(give);
						giveMoney(p.getUniqueId().toString(), give);
						p.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "Money" + ChatColor.GREEN + "]" 
								+ ChatColor.DARK_AQUA + " Gave yourself " + ChatColor.AQUA + newGive + ChatColor.DARK_AQUA + " coins.");
					}
				}
			}
			
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("pay")) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + args[1] + " is not online.");
					} else {
						if(args[2].equals(args[2])) {
							int amount = Main.config.getInt("Stats." + p.getUniqueId() + ".Coins");
							int pay = Integer.parseInt(args[2]);
							if(amount < pay) {
								p.sendMessage(ChatColor.RED + "You do not have " + pay + " coins.");
							} else {
								String newPay = newFormat(pay);
								
								p.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "Money" + ChatColor.GREEN + "]"
										+ ChatColor.DARK_AQUA + " Paid " + ChatColor.AQUA + newPay + ChatColor.DARK_AQUA + " coins to " + target.getCustomName());
								takeMoney(p.getUniqueId().toString(), pay);
								target.sendMessage(ChatColor.GREEN + "[" + ChatColor.YELLOW + "Money" + ChatColor.GREEN + "]" 
										+ ChatColor.DARK_AQUA + " Received " + ChatColor.AQUA + newPay + ChatColor.DARK_AQUA + " coins from " + p.getCustomName());
								giveMoney(target.getUniqueId().toString(), pay);
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static String newFormat (int i) {
		if(i >= 0) {
			DecimalFormat newFormat = new DecimalFormat("#,###");
			String parsed = newFormat.format(i);
			return parsed;
		} return null;
	}
	
	public static String newFormatString (String i) {
		if(i != null) {
			DecimalFormat newFormat = new DecimalFormat("#,###");
			int amount = Integer.parseInt(i);
			String parsed = newFormat.format(amount);
			return parsed;
		} return null;
	}
	
	public static void giveMoney(String uuid, int i) {
		Main.config.set("Stats." + uuid + ".Coins", 
				Main.config.getInt("Stats." + uuid + ".Coins", 0) + i);
		Main.saveFile();
	}
	
	public static void takeMoney(String uuid, int i) {
		Main.config.set("Stats." + uuid + ".Coins",
				Main.config.getInt("Stats." + uuid + ".Coins", 0) - 1);
		Main.saveFile();
	}
}
