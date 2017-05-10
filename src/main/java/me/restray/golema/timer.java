package me.restray.golema;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Restray on 09/05/2017.
 */
public class timer {
    private HashMap<UUID, Integer> time = new HashMap<UUID, Integer>();

    public timer(){

    }

    public void addPlayer(UUID uuid){
        time.put(uuid, 0);
    }
    public void removePlayer(UUID uuid){
        time.remove(uuid);
    }

    public boolean playerExist(UUID uuid){
        if (time.containsKey(uuid)){
            return true;
        }
        else {
            return false;
        }
    }

    public void addOneToTimer(UUID uuid){
        int v = time.get(uuid)+1;
        time.remove(uuid);
        time.put(uuid, v);
    }

    public int getTimePlayer(UUID uuid) {
        int v = time.get(uuid);
        return v;
    }
}
