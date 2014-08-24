package com.core.thewolfbadger.api.api;

import com.core.thewolfbadger.api.cuboid.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/23/14
 * Time: 10:12 PM
 */
public class BadAPI {
    Random random = new Random();
    private static BadAPI api = new BadAPI();
    public static BadAPI get() {
        return api;
    }
    public String getStringFromLocation(Location loc) {
        String location = loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
        return location;
    }
    public Location getLocationFromString(String string){
        String[] loc = string.split(";");
        World world = Bukkit.getWorld(loc[0]);
        Double x = Double.parseDouble(loc[1]);
        Double y = Double.parseDouble(loc[2]);
        Double z = Double.parseDouble(loc[3]);
        Float yaw = Float.parseFloat(loc[4]);
        Float pitch = Float.parseFloat(loc[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }
    public Cuboid getCuboidFromString(String s1) {
        String[] strings = s1.split("//:");
        Location l1 = this.getLocationFromString(strings[0]);
        Location l2 = this.getLocationFromString(strings[1]);
        Cuboid cuboid = new Cuboid(l1, l2);
        return cuboid;
    }
    public String getStringFromCuboid(Cuboid cube) {
        Location l1 = cube.getLoc1();
        Location l2 = cube.getLoc2();
        String finalString = this.getStringFromLocation(l1)+"//:"+this.getStringFromLocation(l2);
        return finalString;
    }
    public void createScoreboard(Player p, String name, String type, String scoreNames, String scores, DisplaySlot slot) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective jective = board.registerNewObjective(ChatColor.translateAlternateColorCodes('&', name), ChatColor.translateAlternateColorCodes('&', type));
        jective.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        jective.setDisplaySlot(slot);
        String[] scoreNamesSym = scoreNames.split(", ");
        String[] scoresSym = scores.split(", ");
        for(int i=0;i<scoreNamesSym.length;i++) {
            String scoreName = scoreNamesSym[i];
            String score = scoresSym[i];
            Score newScore = jective.getScore(Bukkit.getOfflinePlayer(ChatColor.translateAlternateColorCodes('&', scoreName)));
            newScore.setScore(Integer.valueOf(score));
        }
        p.setScoreboard(board);
    }
    public void resetPlayer(Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        p.setLevel(0);
        p.setExhaustion(0F);
        p.setExp(0F);
        p.setHealth(20D);
        p.setFoodLevel(0);
    }
    public int getRandom(int min, int max) {
        return this.random.nextInt(max-min)+min;
    }
    public Random getRandom() {
        return this.random;
    }
}
