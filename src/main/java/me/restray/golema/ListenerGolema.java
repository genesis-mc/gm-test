package me.restray.golema;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Restray on 09/05/2017.
 * For all the listeners
 */
public class ListenerGolema implements Listener{
    main m;

    public ListenerGolema(main m){
        this.m = m;
    }

    perm permission = new perm(m);

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(4);
        System.out.println("NB RANDOM : " + randomInt);

        Player p = e.getPlayer();

        m.task.addPlayer(p.getUniqueId());

        if (randomInt == 0 ){
            permission.createPlayer(p.getUniqueId(), adminEnum.JOUEUR);
        }
        else if (randomInt == 1 ){
            permission.createPlayer(p.getUniqueId(), adminEnum.VIP);
        }
        else if (randomInt == 2 ){
            permission.createPlayer(p.getUniqueId(), adminEnum.HELPEURS);
        }
        else if (randomInt == 3 ){
            permission.createPlayer(p.getUniqueId(), adminEnum.MODERATEUR);
        }
        else if (randomInt == 4 ){
            permission.createPlayer(p.getUniqueId(), adminEnum.ADMIN);
        }

        e.setJoinMessage(gradePlayer(p.getUniqueId()) + " " + p.getName() + " a rejoint le serveur");
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        m.task.removePlayer(p.getUniqueId());

        permission.removePlayer(p.getUniqueId());
    }

    //On Damage Function
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();

            p.sendMessage(ChatColor.RED + "Le PVP n'est pas actif");
        }
        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e){

        Player p = e.getPlayer();
        String grade = "[" + permission.getPlayer(p.getUniqueId()) + "] ";

        e.setCancelled(true);
        String msg = e.getMessage();
        Bukkit.broadcastMessage(grade + p.getName() + " : " + msg);
    }

    public String gradePlayer(UUID uuid){

        adminEnum rank = permission.getPlayer(uuid);

        switch ( rank ){
            case ADMIN:{
                return m.getConfig().getString("grade.admin.prefix") + ChatColor.WHITE;
            }
            case MODERATEUR:{
                return m.getConfig().getString("grade.modo.prefix") + ChatColor.WHITE;
            }
            case HELPEURS:{
                return m.getConfig().getString("grade.helpeur.prefix") + ChatColor.WHITE;
            }
            case VIP:{
                return m.getConfig().getString("grade.vip.prefix") + ChatColor.WHITE;
            }
            case JOUEUR:{
                return m.getConfig().getString("grade.joueur.prefix") + ChatColor.WHITE;
            }
        }
        return "";
    }
}
