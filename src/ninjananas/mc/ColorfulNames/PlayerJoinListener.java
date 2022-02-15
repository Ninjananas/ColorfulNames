package ninjananas.mc.ColorfulNames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	private Plugin plugin;

	public PlayerJoinListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.plugin.refreshPlayerColorFromConfig(event.getPlayer());
	}
}