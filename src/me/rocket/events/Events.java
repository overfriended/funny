package me.rocket.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.rocket.Main;

public class Events implements Listener {
	Main plugin;
	
	public Events(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (!plugin.isPluginEnabled()) {
			return;
		}
		
		Location from = e.getFrom();
        Location to = e.getTo();
        
        int toX = to.getBlockX();
        int toY = to.getBlockY();
        int toZ = to.getBlockZ();
        
        int fromX = from.getBlockX();
        int fromY = from.getBlockY();
        int fromZ = from.getBlockZ();
        
        from = new Location(e.getPlayer().getWorld(), fromX, fromY, fromZ);
        to = new Location(e.getPlayer().getWorld(), toX, toY, toZ);
        
        if (to.getBlockX() != from.getBlockX() || to.getBlockZ() != from.getBlockZ()) {
        	World world = Bukkit.getWorld(e.getPlayer().getWorld().getName());
            Block newBlock = world.getBlockAt(toX, toY -1, toZ);
            ItemStack item = new ItemStack(newBlock.getType(), 1);
            
            if (!plugin.getDenyListForPlayer(e.getPlayer()).contains(newBlock.getType())) {
	            if (e.getPlayer().getInventory().firstEmpty() == -1) {
	            	e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
	            } else {
	            	e.getPlayer().getInventory().addItem(item);
	            }
        	}
        }
	}
}
