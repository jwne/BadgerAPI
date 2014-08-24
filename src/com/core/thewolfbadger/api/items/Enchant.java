package com.core.thewolfbadger.api.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/24/14
 * Time: 9:53 AM
 */
public class Enchant {
    private Enchantment ench;
    private int level;
    private ItemStack item;
    public Enchant(ItemStack i, Enchantment enchant, int level) {
        this.ench = enchant;
        this.level = level;
        this.item = i;
    }
    public ItemStack toEnchant() {
        this.item.addEnchantment(this.ench, this.level);
        return this.item;
    }
}
