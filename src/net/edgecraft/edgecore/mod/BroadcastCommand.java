package net.edgecraft.edgecore.mod;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.chat.ChatHandler;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;

public class BroadcastCommand extends AbstractCommand {

	@Override
	public String[] getNames() {
		String[] names = { "broadcast", "say" };
		return names;
	}

	@Override
	public Level getLevel() {
		return Level.TEAM;
	}

	@Override
	public boolean validArgsRange(String[] args) {
		return args.length > 1;
	}

	@Override
	public void sendUsage(CommandSender sender) {
		sender.sendMessage(EdgeCore.usageColor + "/broadcast <message>");
		sender.sendMessage(EdgeCore.usageColor + "/say <message>");
	}

	@Override
	public boolean runImpl(Player player, User user, String[] args) throws Exception {
		return command(player, args);
	}

	@Override
	public boolean sysAccess(CommandSender sender, String[] args) {
		return command(sender, args);
	}

	private boolean command(CommandSender sender, String[] args) {
		
		String message = "";
		
		for(int i = 0; i < args.length; i++){
			
			if(message == "") message += args[0];
			else message += " " + args[0];
			
		}
		
		EdgeCore.getChat().broadcast(ChatColor.GREEN + "[Internal Radio Broadcast] " + ChatColor.GOLD + message);
		
		return true;
	}
	
}