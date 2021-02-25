package me.rocket.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rocket.Main;
import me.rocket.utils.Chat;

public class CommandSF implements CommandExecutor {
	
	Main plugin;
	
	public CommandSF(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		ArrayList<Material> denyList = plugin.getDenyListForPlayer(player);
		
		if (args.length > 0) {
			switch (args[0].toLowerCase()) {
				case "pickup":
					if (player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getType().isBlock()) {
						if (denyList.contains(player.getInventory().getItemInMainHand().getType())) {
							denyList.remove(player.getInventory().getItemInMainHand().getType());
							player.sendMessage(Chat.format("&cSF> &7Block &c" + player.getInventory().getItemInMainHand().getType().toString() + " &7was removed from your blocked pickups list. You can now pick it up."));
						} else {
							denyList.add(player.getInventory().getItemInMainHand().getType());
							player.sendMessage(Chat.format("&aSF> &7Block &a" + player.getInventory().getItemInMainHand().getType().toString() + " &7was added to your blocked pickups list. You can no longer pick it up."));
						}
					} else {
						player.sendMessage(Chat.format("&cSF> &7Please hold a block in your hand in order to modify your &cblocked pickups&7!"));
					}
					break;
				case "toggle":
					if (player.isOp()) {
						plugin.setPluginEnabled(!plugin.isPluginEnabled());
						
						if (plugin.isPluginEnabled() == true) {
							player.sendMessage(Chat.format("&aSF> &7Plugin has been &aenabled&7!"));
						} else {
							player.sendMessage(Chat.format("&cSF> &7Plugin has been &cdisabled&7!"));
						}
					}
					break;
				default:
					player.sendMessage(Chat.format("&cSF> &7Usage: &c/sf <pickup|toggle>\n&7Plugin by &cAppo&7."));
					break;
			}
		} else {
			player.sendMessage(Chat.format("&cSF> &7Usage: &c/sf <pickup|toggle>&7"));
		}
		
		return false;
	}
	
}
