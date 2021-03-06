package lucien.SabatageSwap.game;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lucien.SabarageSwap.events.CancelledHandler;
import lucien.SabarageSwap.events.PlayerDeathHandler;
import lucien.SabarageSwap.events.PlayerInteractHandler;
import lucien.SabarageSwap.events.PlayerJoinHandler;
import lucien.SabarageSwap.events.PlayerMoveHandler;

public class Main extends JavaPlugin {
    //Stores "this" plugin for use in other classes
    public static Main plugin;

    //Sets preGame to true, and postGame to false upon server load
    public static boolean preGame = true;
    public static boolean postGame = false;
    
    @Override
    public void onEnable() {
	//Stores "this" for use in other classes
	plugin = this;

	//Sets vanilla gameRules
	setGamerules();

	//Prepares game functions
	registerHandlers();
	PreGameManager.generatePreGameItems();
	
	getCommand("swap").setExecutor(new SwapCommand());
    }

    private void setGamerules() {
	World world = Bukkit.getServer().getWorlds().get(0);
	world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
	world.setGameRule(GameRule.SPAWN_RADIUS, 1);
	world.setGameRule(GameRule.MOB_GRIEFING, false);
	world.setSpawnLocation(0, 100, 0);
    }

    private void registerHandlers() {
	PluginManager pluginManager = getServer().getPluginManager();
	pluginManager.registerEvents(new PlayerInteractHandler(), this);
	pluginManager.registerEvents(new PlayerJoinHandler(), this);
	pluginManager.registerEvents(new PlayerMoveHandler(), this);
	pluginManager.registerEvents(new PlayerDeathHandler(), this);
	pluginManager.registerEvents(new CancelledHandler(), this);
    }
}