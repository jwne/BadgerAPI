package com.core.thewolfbadger.api.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/23/14
 * Time: 9:51 PM
 */
public class Team {
    private String name;
    private int score;
    private Set<GamePlayer> players = new HashSet<GamePlayer>();
    private Set<UUID> playerIDs = new HashSet<UUID>();
    public Team(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.score = 0;
    }
    public void addPlayer(Player p) {
        GamePlayer gp = new GamePlayer(p);
        this.players.add(gp);
        this.playerIDs.add(p.getUniqueId());
    }
    public void removePlayer(Player p) {
        for(GamePlayer gps : this.players) {
            if(gps.getPlayerID() == p.getUniqueId()) {
                this.players.remove(gps);
                this.playerIDs.remove(gps.getPlayerID());
                break;
            }
        }
    }
    public void removePlayer(OfflinePlayer op) {
        for(GamePlayer gps : this.players) {
            if(gps.getPlayerID() == op.getUniqueId()) {
                this.players.remove(gps);
                this.playerIDs.remove(gps.getPlayerID());
                break;
            }
        }
    }
    public void setPlayers(Set<Player> players) {
        for(Player player : players) {
            GamePlayer gp = new GamePlayer(player);
            this.players.add(gp);
            this.playerIDs.add(player.getUniqueId());
        }
    }
    public Set<Player> getPlayers() {
        HashSet<Player> set = new HashSet<Player>();
        for(UUID ids : this.playerIDs) {
            if(Bukkit.getPlayer(ids) !=null) {
                //Online
                set.add(Bukkit.getPlayer(ids));
            }
        }
        return set;
    }
}
