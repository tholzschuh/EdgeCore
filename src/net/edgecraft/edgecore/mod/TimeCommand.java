package net.edgecraft.edgecore.mod;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.edgecraft.edgecore.EdgeCore;
import net.edgecraft.edgecore.EdgeCoreAPI;
import net.edgecraft.edgecore.command.AbstractCommand;
import net.edgecraft.edgecore.command.Level;
import net.edgecraft.edgecore.user.User;

public class TimeCommand extends AbstractCommand {

	@Override
	public String[] getNames() {
		String[] names = { "time" };
		return names;
	}

	@Override
	public Level getLevel() {
		return Level.ADMIN;
	}

	@Override
	public boolean validArgsRange(String[] args) {
		return ( args.length == 1 || args.length == 2);
	}

	@Override
	public void sendUsage(CommandSender sender) {
		
		if( sender instanceof Player ) {
			User u = EdgeCoreAPI.userAPI().getUser( ((Player)sender).getName() );
			
			if( !Level.canUse(u, getLevel()) ) return;
		}
		
		sender.sendMessage( EdgeCore.usageColor + "/time" );
		sender.sendMessage( EdgeCore.usageColor + "/time day" );
		sender.sendMessage( EdgeCore.usageColor + "/time night" );
		sender.sendMessage( EdgeCore.usageColor + "/time <time>" );
		return;
	}

	@Override
	public boolean runImpl(Player player, User user, String[] args) throws Exception {
		
		return timeCommand( player, args );
		
	}

	@Override
	public boolean sysAccess(CommandSender sender, String[] args) {
		return timeCommand( sender, args );
	}
	
	private boolean timeCommand( CommandSender sender, String[] args ) {
		
		if( args.length == 1 ) {
			
			sender.sendMessage( Long.toString(Bukkit.getWorlds().get(0).getTime()) );
			
			return true;
		}
		
		int time;
		
		if( args[1].equalsIgnoreCase("day") ) time = 0;
		else if( args[1].equalsIgnoreCase("night")) time = 12500;
		else time = Integer.valueOf( args[1] );
		
		for( World w : Bukkit.getWorlds() ) {
			w.setTime( time );
		}
		
		return true;
	}

}