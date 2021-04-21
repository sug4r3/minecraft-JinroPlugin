package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InitGamerule implements CommandExecutor {
    JinroPlugin plg;

    public InitGamerule(JinroPlugin plg_){
        plg=plg_;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("init_gamerule")) {
            sender.sendMessage("初期設定を行いました");
            Location loc = ((Player)sender).getLocation();
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "setworldspawn "+(int)loc.getX()+" "+(int)loc.getY()+" "+(int)loc.getZ());
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "difficulty normal");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule keepInventory true");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule commandBlockOutput false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule doDaylightCycle false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule doMobSpawning false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule doWeatherCycle false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule fallDamage false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "defaultgamemode adventure");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule announceAdvancements false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule mobGriefing false");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamerule sendCommandFeedback false");
            return true;
        }

        //一致しない
        return false;
    }
}
