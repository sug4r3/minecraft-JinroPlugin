package com.github.sug4r3.jinroplugin.jinroplugin;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemTrade {
    enum Trade {
        BEEF,
        ARROW,
        POTION,
        BALL,
        SUGAR,
        LANTERN,
        AXE,
        STICK
    }

    private MerchantRecipe recipe;

    public ItemTrade(Trade item){
        if(item == Trade.BEEF){
            recipe = new MerchantRecipe(new ItemStack(Material.COOKED_BEEF,3),1000000);
            recipe.addIngredient(new ItemStack(Material.EMERALD,1));
        }
        else if(item == Trade.AXE){
            ItemStack jinroAxe = new ItemStack(Material.STONE_AXE,1);
            ItemMeta metaData = jinroAxe.getItemMeta();
            metaData.setDisplayName("§4§o殺人斧");
            List<String> lore = new ArrayList<>();
            lore.add("一撃でプレイヤーを殺害できる");
            lore.add("人狼のみ購入可能");
            metaData.setLore(lore);
            metaData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_UNBREAKABLE);
            jinroAxe.setItemMeta(metaData);
            recipe = new MerchantRecipe(jinroAxe,1000000);
            recipe.addIngredient(new ItemStack(Material.EMERALD,3));
        }
        else if(item == Trade.LANTERN){
            ItemStack kyojinLantern = new ItemStack(Material.SOUL_LANTERN,1);
            ItemMeta metaData = kyojinLantern.getItemMeta();
            metaData.setDisplayName("§4§o盲目のランタン");
            List<String> lore = new ArrayList<>();
            lore.add("右クリックで使用");
            lore.add("最も近いプレイヤーを15秒間盲目にする");
            lore.add("狂人のみ購入可能");
            metaData.setLore(lore);
            metaData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_UNBREAKABLE);
            kyojinLantern.setItemMeta(metaData);
            recipe = new MerchantRecipe(kyojinLantern,1000000);
            recipe.addIngredient(new ItemStack(Material.EMERALD,5));
        }
        else if(item == Trade.STICK){
            ItemStack uranaiStick = new ItemStack(Material.STICK,1);
            ItemMeta metaData = uranaiStick.getItemMeta();
            metaData.setDisplayName("§b§o占い棒");
            List<String> lore = new ArrayList<>();
            lore.add("プレイヤーに向けて右クリックで使用");
            lore.add("対象が人狼かどうか調べることができる");
            metaData.setLore(lore);
            metaData.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_UNBREAKABLE);
            uranaiStick.setItemMeta(metaData);
            recipe = new MerchantRecipe(uranaiStick,1000000);
            recipe.addIngredient(new ItemStack(Material.EMERALD,7));
        }
    }

    public MerchantRecipe getRecipe() {
        return recipe;
    }
}
