package me.rocket;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.rocket.commands.CommandSF;
import me.rocket.events.Events;
import me.rocket.utils.Chat;

public class Main extends JavaPlugin implements Listener {
	public HashMap<Player, ArrayList<Material>> denyList = new HashMap<Player, ArrayList<Material>>();
	public boolean enabled = false;

	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Events(this), this);
		System.out.println(Chat.format("&aPlugin events have been registered."));
		
		getCommand("sf").setExecutor(new CommandSF(this));
		System.out.println(Chat.format("&aPlugin command (/sf) has been registered."));
		
	}

	@Override
	public void onDisable() {
		
	}
	
	public boolean isPluginEnabled() {
		return enabled;
	}

	public void setPluginEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public ArrayList<Material> getDenyListForPlayer(Player player) {
		if (denyList.get(player) == null) {
			denyList.put(player, new ArrayList<Material>());
		}
		
		return denyList.get(player);
	}
}
