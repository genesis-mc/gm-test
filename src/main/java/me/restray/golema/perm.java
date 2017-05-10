package me.restray.golema;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

import static me.restray.golema.adminEnum.*;

/**
 * Created by Restray on 09/05/2017.
 */
public class perm {

    HashMap<UUID, adminEnum> playersList;
    main m;

    public perm(main m){
        playersList = new HashMap<UUID, adminEnum>();
        this.m = m;
    }

    //Creation des méthodes pour créer et enlever les joueurs
    public void createPlayer(UUID uuid, adminEnum grade){
        playersList.put(uuid, grade);
    }
    public void removePlayer(UUID uuid){
        playersList.remove(uuid);
    }

    //Création de méthodes pour récuperer les grades des joueurs et savoir si le joueur existe
    public adminEnum getPlayer(UUID uuid){
        adminEnum info = playersList.get(uuid);
        return info;
    }

    //Creation de méthodes pour set le grade d'un joueur
    public void setGradePlayer(UUID uuid, adminEnum grade){
        playersList.remove(uuid);
        playersList.put(uuid, grade);
        Bukkit.getPlayer(uuid).sendMessage("Tu es maintenant :"+ grade);
    }


}
