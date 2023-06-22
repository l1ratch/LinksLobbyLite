package linkslobby.linkslobby;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessage implements Listener {
    @EventHandler
    public void MessageJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> list;
        list = LinksLobby.getInstance().getConfig().getStringList("JoinMessage");
        for (String s : list)
            p.sendMessage(s);
    }
}