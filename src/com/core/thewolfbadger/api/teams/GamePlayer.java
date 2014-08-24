package com.core.thewolfbadger.api.teams;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/23/14
 * Time: 9:54 PM
 */
public class GamePlayer {
    private UUID playerID;
    private int score;
    private ItemStack[] contents;
    private ItemStack[] armor;
    private int kills;
    private int deaths;
    public GamePlayer(Player p) {
        this.playerID = p.getUniqueId();
        this.contents = p.getInventory().getContents();
        this.armor = p.getInventory().getArmorContents();
    }
    public void setScore(Integer i) {
        this.score = i;
    }
    public Integer getScore() {
        return this.score;
    }
    public void setKills(Integer i) {
        this.kills = i;
    }
    public Integer getKills() {
        return this.kills;
    }
    public void setDeaths(Integer i) {
        this.deaths = i;
    }
    public Integer getDeaths() {
        return this.deaths;
    }
    public ItemStack[] getArmor() {
        return this.armor;
    }
    public ItemStack[] getInventoryContents() {
        return this.contents;
    }
    public UUID getPlayerID() {
        return this.playerID;
    }
}
