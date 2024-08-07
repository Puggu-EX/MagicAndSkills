package org.puggu.magicandskills.item.tagger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.item.wand.WandEnchant;
import org.puggu.magicandskills.item.wand.WandType;

import java.util.ArrayList;
import java.util.List;

/**
 * Taken from 7smile7 on spigotmc.org
 */
public class WandTagger {
    private NamespacedKey wandTypeKey;

    public WandTagger(MagicAndSkills plugin) {
        wandTypeKey = new NamespacedKey(plugin, "wand-type");
    }
    
    public void setTypeOfWand(ItemStack item, WandType type){
        ItemMeta meta = item.getItemMeta();
        if (meta == null){
            return;
        }

        List<String> lore = new ArrayList<>();
        switch (type) {
            case FIRE:
                meta.setDisplayName(ChatColor.RED + "Fire Wand");
                lore.add(ChatColor.RED + "Fire Wand");
                lore.add("This wand gives you the power");
                lore.add("to cast fire spells");
                break;
            case LIGHTNING:
                meta.setDisplayName(ChatColor.WHITE + "LIGHTNING Wand");
                lore.add(ChatColor.WHITE + "LIGHTNING Wand");
                lore.add("This wand gives you the power");
                lore.add("to cast lightning spells");
                break;
            case WATER:
                meta.setDisplayName(ChatColor.BLUE + "WATER Wand");
                lore.add(ChatColor.BLUE + "WATER Wand");
                lore.add("This wand gives you the power");
                lore.add("to cast water spells");
                break;
            case GENERIC:
                meta.setDisplayName("Magic Wand");
//                lore.add(ChatColor.GRAY + "Magic Fireball I");
//                lore.add(ChatColor.GRAY + "Substitution I");
                break;
        }

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(this.wandTypeKey, PersistentDataType.STRING, type.toString());

        item.setItemMeta(meta);
    }

    public WandType getTypeOfWand(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null){
            return null;
        }

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String typeValue = container.get(this.wandTypeKey, PersistentDataType.STRING);
        if (typeValue == null){
            return null;
        }
        return WandType.valueOf(typeValue);
    }

    public void enchantWand(ItemStack wand, WandEnchant enchant){
        List<String> lore;
        if (wand.hasItemMeta() && wand.getItemMeta().hasLore()){
            lore = wand.getItemMeta().getLore();
        }else{
            lore = new ArrayList<>();
        }

        if (lore == null){
            return;
        }

        lore.add(enchant.asString());
        wand.getItemMeta().setLore(lore);
    }

    public ItemStack getWand(){
        ItemStack wand = new ItemStack(Material.STICK);

        return wand;
    }
}
