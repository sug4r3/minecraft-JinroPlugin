package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonJinroCommand implements CommandExecutor {
    JinroPlugin plg;

    public SummonJinroCommand(JinroPlugin plg_){ plg = plg_; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("summon_jinro")) {
            if (args.length == 0) { //引数がない場合
                sender.sendMessage("/summon_jinro <item>");
            } else {
                if (args[0].equalsIgnoreCase("shonin")) {
                    if (sender instanceof Player) {
                        Location loc = ((Player) sender).getPlayer().getLocation();
                        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                                "summon villager " +loc.getX()+" "+loc.getY()+" "+loc.getZ()+" "+
                                        "{VillagerData:{profession:\"cleric\",level:5,type:\"plains\"}," +
                                        "CustomName:\"\\\"アイテム商人\\\"\",Invulnerable:1,Silent:1,NoAI:1,Tags:[\"shonin\"],Rotation:["+(loc.getYaw()+180)+"f, 0.0f]," +
                                        "Offers:{Recipes:[{buy:{id:\"emerald\",Count:1},sell:{id:\"cooked_beef\",Count:3},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:3},sell:{id:\"arrow\",Count:1},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:6},sell:{id:\"potion\",Count:1,tag:{display:{Name:\"\\\"透明化のポーション\\\"\",Lore:[\"\\\"30秒間姿を隠すことができる\\\"\"]},CustomPotionColor:8847100,HideFlags:32,CustomPotionEffects:[{Id:14,Amplifier:0,Duration:600}]}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:4},sell:{id:\"snowball\",Count:1,tag:{display:{Name:\"\\\"発光玉\\\"\",Lore:[\"\\\"全プレイヤーを60秒間発光させる\\\"\"]}}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:5},sell:{id:\"sugar\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"霊媒の粉\\\",\\\"color\\\":\\\"aqua\\\"}\",Lore:['[{\"text\":\"死体に向けて右クリックで使用\"}]','[{\"text\":\"対象が人狼かどうか調べることができる\"}]']}}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:7},sell:{id:\"stick\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"占い棒\\\",\\\"color\\\":\\\"aqua\\\"}\",Lore:['[{\"text\":\"プレイヤーに向けて右クリックで使用\"}]','[{\"text\":\"対象が人狼かどうか調べることができる\"}]']},Unbreakable:1,HideFlags:31}},maxUses:1000000}]}}");
                    }
                }else if (args[0].equalsIgnoreCase("zen_shonin")) {
                    if (sender instanceof Player) {
                        Location loc = ((Player) sender).getPlayer().getLocation();
                        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                                "summon villager " +loc.getX()+" "+loc.getY()+" "+loc.getZ()+" "+
                                        "{VillagerData:{profession:\"cleric\",level:5,type:\"plains\"},CustomName:\"\\\"アイテム商人\\\"\",Invulnerable:1,Silent:1,NoAI:1,Tags:[\"shonin\"]," +
                                        "Offers:{Recipes:[{buy:{id:\"emerald\",Count:1},sell:{id:\"cooked_beef\",Count:3},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:3},sell:{id:\"arrow\",Count:1},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:6},sell:{id:\"potion\",Count:1,tag:{display:{Name:\"\\\"透明化のポーション\\\"\",Lore:[\"\\\"30秒間姿を隠すことができる\\\"\"]},CustomPotionColor:8847100,HideFlags:32,CustomPotionEffects:[{Id:14,Amplifier:0,Duration:600}]}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:4},sell:{id:\"snowball\",Count:1,tag:{display:{Name:\"\\\"発光玉\\\"\",Lore:[\"\\\"全プレイヤーを60秒間発光させる\\\"\"]}}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:5},sell:{id:\"sugar\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"霊媒の粉\\\",\\\"color\\\":\\\"aqua\\\"}\",Lore:['[{\"text\":\"死体に向けて右クリックで使用\"}]','[{\"text\":\"対象が人狼かどうか調べることができる\"}]']}}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:7},sell:{id:\"stick\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"占い棒\\\",\\\"color\\\":\\\"aqua\\\"}\",Lore:['[{\"text\":\"プレイヤーに向けて右クリックで使用\"}]','[{\"text\":\"対象が人狼かどうか調べることができる\"}]']},Unbreakable:1,HideFlags:31}},maxUses:1000000},"+
                                        "{buy:{id:\"emerald\",Count:5},sell:{id:\"soul_lantern\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"盲目のランタン\\\",\\\"color\\\":\\\"dark_red\\\"}\",Lore:['[{\"text\":\"右クリックで使用\"}]','[{\"text\":\"最も近いプレイヤーを15秒間盲目にする\"}]','[{\"text\":\"狂人のみ購入可能\"}]']}}},maxUses:1000000}," +
                                        "{buy:{id:\"emerald\",Count:3},sell:{id:\"stone_axe\",Count:1,tag:{display:{Name:\"{\\\"text\\\":\\\"殺人斧\\\",\\\"color\\\":\\\"dark_red\\\"}\",Lore:['[{\"text\":\"一撃でプレイヤーを殺害できる\"}]','[{\"text\":\"人狼のみ購入可能\"}]']},Unbreakable:1,HideFlags:7}},maxUses:1000000}]}}"
                        );
                    }
                }
            }
            return true;
        }
        return false;
    }
}
