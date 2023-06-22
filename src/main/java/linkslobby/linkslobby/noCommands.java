package linkslobby.linkslobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class noCommands implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (!LinksLobby.allow_cmd.contains(e.getMessage().split(" ")[0].replace("/", ""))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(LinksLobby.disable_cmd);
        }
    }
}
