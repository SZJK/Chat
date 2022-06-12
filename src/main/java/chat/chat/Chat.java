package chat.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("§aChat Plugin Load！\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("§aChat Plugin shutdown！\n");
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String joinplayername = event.getPlayer().getName();
        if (player.isOp()) {
            event.setJoinMessage("§d§l------------------------------\n§d§l| §dO§bP§f " + joinplayername + "§fJoin Server!  §d§l\n§d§l------------------------------\n§f[§a+§f]" + joinplayername);
        }
        else {
            event.setJoinMessage("§f[§a+§f]"+joinplayername);
        }
    }

    @EventHandler
    public void onPlayerLeftServer(PlayerQuitEvent event) {
        String leaveplayername = event.getPlayer().getName();
        Player player = event.getPlayer();
        if (player.isOp()) {
            event.setQuitMessage("§d§l------------------------------\n§d§l| §dO§bP§f " + leaveplayername + "§fLeave Server!  §d§l\n§d§l------------------------------\n§f[§c-§f]" + leaveplayername);
        }
        else {
            event.setQuitMessage("§f[§c-§f]"+leaveplayername);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player Player = event.getPlayer();
        String PlayerName = event.getPlayer().getName();
        String Msg = event.getMessage();
        if(Player.isOp()){
            getServer().getOnlinePlayers().forEach(player -> {
                player.sendMessage("§b[§dO§bP§d]" + PlayerName + "§7: §f"+ Msg);
            });
        }
        else {
            getServer().getOnlinePlayers().forEach(player -> {
                player.sendMessage(PlayerName + "§7: §f" + Msg);
            });
        }
    }
}