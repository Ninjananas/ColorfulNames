package ninjananas.mc.ColorfulNames;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

	private static final ChatColor [] allColors = {
			ChatColor.AQUA,
			ChatColor.BLACK,
			ChatColor.BLUE,
			ChatColor.DARK_AQUA,
			ChatColor.DARK_BLUE,
			ChatColor.DARK_GRAY,
			ChatColor.DARK_GREEN,
			ChatColor.DARK_PURPLE,
			ChatColor.DARK_RED,
			ChatColor.GOLD,
			ChatColor.GRAY,
			ChatColor.GREEN,
			ChatColor.LIGHT_PURPLE,
			ChatColor.RED,
			ChatColor.WHITE,
			ChatColor.YELLOW,
	};
	
    @Override
    public void onEnable() {
    	this.saveDefaultConfig();
    	this.getCommand("changenamecolor").setExecutor(new ChangeNameColorCommand(this));
    	this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
    }
    
    public static void refreshPlayerColor(Player player, String color) {
    	String newName = "" + color + player.getName() + ChatColor.RESET;
		player.setDisplayName(newName);
		player.setPlayerListName(newName);
    }
    
    public static void refreshPlayerColor(Player player, ChatColor color) {
    	String newName = "" + color + player.getName() + ChatColor.RESET;
		player.setDisplayName(newName);
		player.setPlayerListName(newName);
    }
    
    public void refreshPlayerColorFromConfig(Player player) {
    	String color = this.getConfig().getString("name_colors." + player.getUniqueId());
    	if (color != null) {
    		Plugin.refreshPlayerColor(player, color);
    	} else if (this.getConfig().getBoolean("autocolor")) {
    		ChatColor chatColor = Plugin.allColors[Math.abs(player.getUniqueId().hashCode()) % Plugin.allColors.length];
    		Plugin.refreshPlayerColor(player, chatColor);
    	}
    }
}