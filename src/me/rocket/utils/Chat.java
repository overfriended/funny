package me.rocket.utils;

import net.md_5.bungee.api.ChatColor;

public class Chat {
	
	public static String format(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

}
