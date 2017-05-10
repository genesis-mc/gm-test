package me.restray.golema;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Restray on 09/05/2017.
 */
public class command implements CommandExecutor {
    main m;

    public command(main m){
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (label.equalsIgnoreCase("timer")){
                int time = m.task.getTimePlayer(((Player) sender).getUniqueId());
                sender.sendMessage("Ton temps sur le serveur : " + time + "s");
                return true;
            }
        }
        return false;
    }
}
