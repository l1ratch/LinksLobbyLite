package linkslobby.linkslobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class LinksLobby extends JavaPlugin implements Listener {
    private static final String prefix = "§7[§cLinksLobbyLite§7]§a ";
    private static LinksLobby instance;
    public static ArrayList<String> allow_cmd;
    public static String disable_cmd;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        Bukkit.getPluginManager().registerEvents(new JoinMessage(), (Plugin)this);
        getServer().getPluginManager().registerEvents(this, this);
        loadConfig(getConfig());
        getServer().getPluginManager().registerEvents(new noCommands(), (Plugin)this);
        log("------------------------------");
        log("Плагин LinksLobbyLite включен!");
        log("Написал: Link_play            ");
        log("------------------------------");
    }

    public static LinksLobby getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        log("-------------------------------");
        log("Плагин LinksLobbyLite выключен!");
        log("Написал: Link_play             ");
        log("-------------------------------");
    }

    @EventHandler
    public void FoodLevel(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getActivePotionEffects().clear();
        e.setJoinMessage("");
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void noChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        p.sendMessage(getConfig().getString("BlockActive").replaceAll("&", "§"));
    }

    public static String replacer(String what) {
        return ChatColor.translateAlternateColorCodes('&', what);
    }

    public static void loadConfig(FileConfiguration cfg) {
        disable_cmd = replacer(cfg.getString("BlockActive"));
        allow_cmd = (ArrayList<String>)cfg.getStringList("allow_cmd");
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + message);
    }

    public static String getPrefix() {
        return prefix;
    }
}
