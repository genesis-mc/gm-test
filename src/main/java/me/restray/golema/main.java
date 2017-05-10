package me.restray.golema;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Restray on 08/05/2017.
 */
public final class main extends JavaPlugin {

    timer task;

    @Override
    public void onEnable(){
        System.out.println("[golema]Start Plugin");
        Bukkit.getServer().getPluginManager().registerEvents(new ListenerGolema(this), this);
        getCommand("timer").setExecutor(new command(this));
        this.saveDefaultConfig();

        // Add a timer for know the time of the personn on the server
        task = new timer();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (task.playerExist(p.getUniqueId())){
                        task.addOneToTimer(p.getUniqueId());
                    }
                    else{
                        task.addPlayer(p.getUniqueId());
                    }
                }
            }
        }, 20L, 20L);
    }

    @Override
    public void onDisable(){
        System.out.println("[golema]End Plugin");
    }

}
