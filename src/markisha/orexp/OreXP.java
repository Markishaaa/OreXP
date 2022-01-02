package markisha.orexp;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import markisha.events.GiveXP;

public class OreXP extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new GiveXP(), this);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OreXP]: Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OreXP]: Plugin disabled!");
	}
	
}
