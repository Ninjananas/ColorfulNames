package ninjananas.mc.ColorfulNames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ChangeNameColorCommand implements CommandExecutor, TabCompleter {
	private Plugin plugin;
	
	private static final HashMap<String, ChatColor> colorMap = new HashMap<String, ChatColor>() {
		private static final long serialVersionUID = 1L;
		{
			// Standard mappings
			put("aqua", ChatColor.AQUA);
			put("black", ChatColor.BLACK);
			put("blue", ChatColor.BLUE);
			put("darkaqua", ChatColor.DARK_AQUA);
			put("darkblue", ChatColor.DARK_BLUE);
			put("darkgray", ChatColor.DARK_GRAY);
			put("darkgreen", ChatColor.DARK_GREEN);
			put("darkpurple", ChatColor.DARK_PURPLE);
			put("darkred", ChatColor.DARK_RED);
			put("gold", ChatColor.GOLD);
			put("gray", ChatColor.GRAY);
			put("green", ChatColor.GREEN);
			put("lightpurple", ChatColor.LIGHT_PURPLE);
			put("red", ChatColor.RED);
			put("white", ChatColor.WHITE);
			put("yellow", ChatColor.YELLOW);

			// Aliases
			put("magenta", ChatColor.LIGHT_PURPLE);
			put("orange", ChatColor.GOLD);
		}
	};
	
	public ChangeNameColorCommand(Plugin plugin) {
		this.plugin = plugin;
	}
	
    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        if (args.length != 1) {
        	return null;
        }
        String arg = args[0];
        List<String> list = new ArrayList<>();
        for (String i: ChangeNameColorCommand.colorMap.keySet()) {
        	if (i.startsWith(arg)) {
        		list.add(i);
        	}
        }
        return list;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		
		if (args.length == 0) {
			sender.sendMessage("You need to specify a color!");
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage("Wrong number of arguments!");
			return false;
		}
		
		Player player = (Player) sender;
		String colorString = args[0].toLowerCase();
		ChatColor color = ChangeNameColorCommand.colorMap.get(colorString);
		
		if (color == null) {
			return false;
		}
		
		
		this.plugin.getConfig().set("name_colors." + player.getUniqueId(), "" + color);
		this.plugin.saveConfig();
		Plugin.refreshPlayerColor(player, "" + color);
		
		return true;
	}
}
