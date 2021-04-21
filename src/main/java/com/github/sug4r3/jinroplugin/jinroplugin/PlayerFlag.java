package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerFlag {
    Player player;
    JinroPlugin plg;

    public PlayerFlag(Player pl_, JinroPlugin plg_){
        player = pl_;
        plg = plg_;
    }

    public void set(){
        setMetadata(true);
        plg.getServer().getScheduler().runTaskLater(plg, new Runnable() {
            @Override
            public void run() {
                setMetadata(false);
            }
        },10);
    }

    private void setMetadata(boolean flag){
        player.setMetadata("PlayerInteractEvent", new FixedMetadataValue(plg,flag));
    }

    public boolean get(){
        try{
            return (boolean)player.getMetadata("PlayerInteractEvent").get(0).value();
        } catch(Exception e){
            return false;
        }
    }
}
