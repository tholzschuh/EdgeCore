package net.edgecraft.edgecraft.util;

import java.io.File;

import net.edgecraft.edgecraft.EdgeCraft;
import net.edgecraft.edgecraft.classes.UserManager;
import net.edgecraft.edgecraft.mysql.DatabaseHandler;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
	
	private EdgeCraft plugin;
	private FileConfiguration config;
	
	/**
	 * Constructor
	 * @param instance
	 */
	public ConfigHandler(EdgeCraft instance) {
		setPlugin( instance );
	}
	
	/**
	 * Loads the config of the EdgeCraft-Instance.
	 */
	public void loadConfig() {
			
			// Config itself.
			setConfig( getPlugin().getConfig() );
		
			// Database
	    	getConfig().addDefault( DatabaseHandler.DatabaseHost, DatabaseHandler.unset);
	    	getConfig().addDefault( DatabaseHandler.DatabaseUser, DatabaseHandler.unset);
	    	getConfig().addDefault( DatabaseHandler.DatabasePW, DatabaseHandler.unset);
	    	getConfig().addDefault( DatabaseHandler.DatabaseDB, DatabaseHandler.unset);
	   
	    	// Language
	    	getConfig().addDefault("Language.Default", "de");

	    	// User
	    	getConfig().addDefault("User.DefaultLevel", Integer.valueOf(0));
	    
	    	// Economy
	    	getConfig().addDefault("Economy.Currency", "$");	

	    	getConfig().options().copyDefaults(true);
	    	getPlugin().saveConfig();

	    if (!new File(getPlugin().getDataFolder() + "src/config.yml").exists());
	    	getPlugin().saveDefaultConfig();		
	}
	
	/**
	 * Updates all local settings using the configuration.
	 * @param instance
	 */
	public final void update(EdgeCraft instance) {

		DatabaseHandler.setHost( getConfig().getString(DatabaseHandler.DatabaseHost));
		DatabaseHandler.setUser( getConfig().getString( DatabaseHandler.DatabaseUser ));
		DatabaseHandler.setPW( getConfig().getString( DatabaseHandler.DatabasePW ));
		DatabaseHandler.setDB( getConfig().getString( DatabaseHandler.DatabaseDB ));

		
		LanguageHandler.setDefaultLanguage( this.config.getString("Language.Default") );
		
		UserManager.setDefaultLevel( this.config.getInt("User.DefaultLevel") );
		
		EdgeCraft.setCurrency( this.config.getString("Economy.Currency") );
	}
	
	/**
	 * Returns the used EdgeCraft-instance.
	 * @return EdgeCraft
	 */
	private EdgeCraft getPlugin() {
		
		return plugin;
	}
	
	/**
	 * Returns the used file-configuration.
	 * @return FileConfiguration
	 */
	public FileConfiguration getConfig() {
		return config;
	}
	
	/**
	 * Sets the EdgeCraft-instance.
	 * @param instance
	 */
	protected void setPlugin( EdgeCraft instance ) {
		if( instance != null )
			plugin = instance;
	}

	/**
	 * Sets the file-configuration.
	 * @param config
	 */
	protected void setConfig( FileConfiguration config ) {
		if( config != null )
			this.config = config;
	}
	
}
