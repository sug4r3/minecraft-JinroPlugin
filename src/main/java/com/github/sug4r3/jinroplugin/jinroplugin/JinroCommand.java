package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class JinroCommand implements CommandExecutor {
    static JinroPlugin plg;
    static double[] ROOM;   //対戦フィールド
    static double[] HOME; //ホーム

    private static ArrayList<String> playerMember;

    private static int jinroNum = 2;
    private static int kyojinNum = 0;
    private static int uranaiNum = 1;

    private static boolean isSession;

    public JinroCommand(JinroPlugin plg_){
        plg=plg_;
        isSession = false;
        playerMember = new ArrayList<>();
    }

    static void addPlayerList(String name) { playerMember.add(name); }
    static ArrayList<String> getPlayerList(){ return playerMember; }
    static void deleatPlayerList(int index){
        playerMember.remove(index);
    }
    static void clearPlayerList(){ if(playerMember!=null) playerMember.clear(); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("jinro")) {
            if (args.length == 0) { //引数がない場合
                sender.sendMessage("jinro [start/stop/set/join/leave/setRoomRespawn/setHomeRespawn]");
                return true;
            } else {
                if (args[0].equalsIgnoreCase("start")) {
                    startJinro(sender);
                } else if (args[0].equalsIgnoreCase("stop")) {
                    sender.sendMessage("人狼セッションを停止しました");

                    finishSession();
                } else if(args[0].equalsIgnoreCase("set")) {
                    int num;
                    if(args.length != 3 || (num=isNumber(args[2]))<0){
                        sender.sendMessage("jinro set [jinro/kyojin/uranai] <自然数>");
                        return true;
                    } else {
                        if(args[1].equalsIgnoreCase("jinro")){
                            setJinroNum(num);
                            sender.sendMessage("人狼の数を"+num+"人にしました");
                            return true;
                        }
                        else if(args[1].equalsIgnoreCase("kyojin")){
                            setKyojinNum(num);
                            sender.sendMessage("狂人の数を"+num+"人にしました");
                            return true;
                        }
                        else if(args[1].equalsIgnoreCase("uranai")){
                            setUranaiNum(num);
                            sender.sendMessage("占い師の数を"+num+"人にしました");
                            return true;
                        }
                    }
                }else if(args[0].equalsIgnoreCase("join")) {
                    if(!isSession){
                        if(getPlayerList()==null){

                        }
                        if(!getPlayerList().contains(sender.getName())) {
                            sender.sendMessage("参加しました");
                            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                                    "tag " + sender.getName() + " add player"
                            );
                            addPlayerList(sender.getName());
                        }else{
                            sender.sendMessage("すでに参加中です");
                        }
                    }else{
                        sender.sendMessage("セッション中のため参加できません");
                    }
                }else if(args[0].equalsIgnoreCase("leave")) {
                    if(!isSession){
                        int i = getPlayerList().indexOf(sender.getName());
                        if(i>=0){
                            sender.sendMessage("参加を取り消しました");
                            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                                    "tag " + sender.getName() + " remove player"
                            );
                            deleatPlayerList(i);
                        }else{
                            sender.sendMessage("セッションに参加していません");
                        }
                    }else{
                        sender.sendMessage("セッション中のため参加取り消しができません");
                    }
                }else if(args[0].equalsIgnoreCase("setHomeRespawn")) {
                    if (!isSession) {
                        Location loc = ((Player)sender).getPlayer().getLocation();
                        double[] pos = {loc.getX(),loc.getY(),loc.getZ()};
                        setHome(pos);
                        sender.sendMessage("待機場所のスポーン位置を"+(int)pos[0]+" "+(int)pos[1]+" "+(int)pos[2]+"に設定しました");
                    }
                }else if(args[0].equalsIgnoreCase("setRoomRespawn")) {
                    if (!isSession) {
                        Location loc = ((Player)sender).getPlayer().getLocation();
                        double[] pos = {loc.getX(),loc.getY(),loc.getZ()};
                        setRoom(pos);
                        sender.sendMessage("ステージの初期スポーン位置を"+(int)pos[0]+" "+(int)pos[1]+" "+(int)pos[2]+"に設定しました");
                    }
                }
            }
        }

        return false;
    }


    /**
     * 人狼スタートコマンド
     */
    private void startJinro(CommandSender sender){
        setSession(true);
        EventListener.setSession(true);
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "gamemode adventure @a[tag=player]"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "title @a reset"
        );

        selectJobs();

        CountdownTimer cd_timer = new CountdownTimer(plg, (Player) sender, 3);
        cd_timer.runTaskLater(plg, 0);
    }

    /**
     * 役職選出
     */
    private void selectJobs(){
        int i;
        for(i=0;i<jinroNum;i++) {
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "tag @r[tag=player, tag=!jinro] add jinro"
            );
        }
        for(i=0;i<kyojinNum;i++) {
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "tag @r[tag=player, tag=!jinro, tag=!kyojin] add kyojin"
            );
        }
        for(i=0;i<uranaiNum;i++) {
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "tag @r[tag=player, tag=!jinro, tag=!kyojin, tag=!uranai] add uranai"
            );
        }
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a[tag=player, tag=!jinro, tag=!kyojin, tag=!uranai, tag=!murabito] add murabito"
        );
    }

    /**
     * セッション終了コマンド
     */
    public static void finishSession() {
        setSession(false);
        EventListener.setSession(false);
        CountdownTimer.setFlag(false);
        emeraldTimer.setFlag(false);
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "bossbar remove my:timer");
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "kill @e[tag=deadBody]");
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "gamemode adventure @a[tag=player]"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "clear @a[tag=player]"
        );
        if(HOME!=null) {
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "tp @a[tag=player] " + HOME[0] + " " + HOME[1] + " " + HOME[2]
            );
        }
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "effect give @a[tag=player] minecraft:regeneration 1000000 255 true"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "effect give @a[tag=player] minecraft:saturation 1000000 255 true"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove jinro"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove kyojin"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove uranai"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove murabito"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove dead"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "tag @a remove player"
        );
        /**
         * 役職確認
         */
        JobAnnounce.showJobPlayer(plg);

        JobAnnounce.clearArrayList();

        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "give_jinro sug4r3"
        );

    }

    public static void setJinroNum(int num){ jinroNum = num; }

    public static void setKyojinNum(int num){ kyojinNum = num; }

    public static void setUranaiNum(int num){ uranaiNum = num; }

    private int isNumber(String num){
        try {
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            return -1;
        }
    }

    private static void setSession(boolean b){
        isSession = b;
    }

    public static void setRoom(double[] room){
        ROOM = room;
    }

    public static void setHome(double[] home){
        HOME = home;
    }

    public static double[] getRoom(){ return ROOM;}

    public static double[] getHome(){ return HOME;}
}
