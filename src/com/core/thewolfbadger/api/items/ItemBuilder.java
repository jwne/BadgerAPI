package com.core.thewolfbadger.api.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.Potion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ItemBuilder class is a class to create items very easily.
 * <p/>
 * ItemStack item = new ItemBuilder(Material.IRON_SWORD).withEnchantment(Enchantment.DAMAGE_ALL, 1, true)
 * .withLore("First line!").withLores(new String[]{"Second line!", "Thrid line!"})
 * .withAmount(2).withDurability(5).toItemStack();
 *
 * @author TheKomputerKing (Original Author)
 * @author CraftThatBlock
 */

public class ItemBuilder {
    private ItemStack material;
    private int amount = 1;
    private String name;
    private List<String> lores = new ArrayList<String>();
    private List<Enchant> enchantments = new ArrayList<Enchant>();
    private short durability = -101;
    private MaterialData data;
    private Potion potion;
    private Color color;

    public ItemBuilder(ItemStack i) {
        this.material = i;
    }

    public ItemBuilder(ItemStack i, int amount) {
        this.material = i;
        this.amount = amount;
    }

    /**
     * Set the amount of items
     *
     * @param amount Amount
     * @return ItemBuilder
     */
    public ItemBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Set the name of the item
     *
     * @param name Name
     * @return ItemBuilder
     */
    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Add multiple lore lines
     *
     * @param lores Lores
     * @return ItemBuilder
     */
    public ItemBuilder withLores(String[] lores) {
        Collections.addAll(this.lores, lores);
        return this;
    }

    /**
     * Add multiple lore lines
     *
     * @param lores Lores
     * @return ItemBuilder
     */
    public ItemBuilder withLores(List<String> lores) {
        for (String lore : lores) {
            this.lores.add(lore);
        }
        return this;
    }

    /**
     * Add a single lore line
     *
     * @param lore Lore
     * @return ItemBuilder
     */
    public ItemBuilder withLore(String lore) {
        lores.add(lore);
        return this;
    }

    /**
     * Add a enchantment
     *
     * @param enchantment Enchantment type
     * @param level       Level of enchantment
     * @return ItemBuilder
     */
    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        enchantments.add(new Enchant(this.material, enchantment, level));
        return this;
    }

    /**
     * Set the durability (little bar under item)
     *
     * @param durability Durability
     * @return ItemBuilder
     */
    public ItemBuilder withDurability(short durability) {
        this.durability = durability;
        return this;
    }

    /**
     * Set the MaterialData of the item
     *
     * @param data Data
     * @return ItemBuilder
     */
    public ItemBuilder withData(MaterialData data) {
        this.data = data;
        return this;
    }

    /**
     * Set the potion to be apply to the item.
     * The Item material must be a Potion so it applies.
     *
     * @param potion Potion
     * @return ItemStack
     */
    public ItemBuilder withPotion(Potion potion) {
        this.potion = potion;
        return this;
    }

    /**
     * Set the color of the item. Only works with Leather armor.
     *
     * @param color
     * @return
     */
    public ItemBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     * Create the ItemStack!
     *
     * @return ItemStack
     */
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);

        ItemMeta meta = item.getItemMeta();
        if (name != null && name != "") {
            meta.setDisplayName(name);
        }
        if (!lores.isEmpty()) {
            meta.setLore(lores);
        }
        for (Enchant enchant : this.enchantments) {
            enchant.toEnchant();
        }
        if (durability != -101) {
            item.setDurability(durability);
        }
        if (data != null) {
            item.setData(data);
        }
        if (potion != null && material.getType() == Material.POTION) {
            potion.apply(item);
        }
        if (color != null && meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(color);
        }

        item.setItemMeta(meta);
        return item;
    }
}
