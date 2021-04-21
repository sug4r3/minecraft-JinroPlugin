package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;

public class JobAnnounce extends BukkitRunnable {
    private static ArrayList<String> jinroMember;
    private static ArrayList<String> kyojinMember;
    private static ArrayList<String> uranaiMember;
    private static ArrayList<String> murabitoMember;
    private static ArrayList<String> deadMember;
    private JinroPlugin plg;
    Player pl;
    String job;
    static String members;

    /**
     * コンストラクタ
     * @param plg_ プラグインメインクラスのインスタンス
     */
    public JobAnnounce(JinroPlugin plg_,Player pl_, String job_){
        plg = plg_;
        pl = pl_;
        job = job_;
        jinroMember = new ArrayList<>();
        kyojinMember = new ArrayList<>();
        uranaiMember = new ArrayList<>();
        murabitoMember = new ArrayList<>();
        deadMember = new ArrayList<>();
        members = "";
    }

    @Override
    public void run(){
        if(job.equalsIgnoreCase("jinro")){
            pl.sendMessage("§4*********************");
            pl.sendMessage("§4あなたは人狼です");
            pl.sendMessage(" ");
            pl.sendMessage("§4* 仲間 *");
            for(String name: jinroMember) members = members+name+" ";
            pl.sendMessage("§4"+members);
            pl.sendMessage("§4*********************");
        }
        else if(job.equalsIgnoreCase("kyojin")){
            pl.sendMessage("§4*********************");
            pl.sendMessage("§4あなたは狂人です");
            pl.sendMessage("§4*********************");
        }
        else if(job.equalsIgnoreCase("uranai")){
            pl.sendMessage("§3*********************");
            pl.sendMessage("§3あなたは占い師です");
            pl.sendMessage("§3*********************");
        }
        else if(job.equalsIgnoreCase("murabito")){
            pl.sendMessage("*********************");
            pl.sendMessage("あなたは村人です");
            pl.sendMessage("*********************");
        }
    }

    public static void showJobPlayer(JinroPlugin plg){
        plg.getServer().broadcastMessage("*********************");
        plg.getServer().broadcastMessage("   役職内訳");
        plg.getServer().broadcastMessage("*********************");
        plg.getServer().broadcastMessage("§4* 人狼 *");
        initMembers();
        if(jinroMember!=null) {
            for(String name: jinroMember) members = members+name+" ";
            plg.getServer().broadcastMessage("§4"+members);
            plg.getServer().broadcastMessage("");
        }

        plg.getServer().broadcastMessage("§4* 狂人 *");
        initMembers();
        if(kyojinMember!=null) {
            for(String name: kyojinMember) members = members+name+" ";
            plg.getServer().broadcastMessage("§4"+members);
            plg.getServer().broadcastMessage("");
        }

        plg.getServer().broadcastMessage("§3* 占い師 *");
        initMembers();
        if(uranaiMember!=null) {
            for(String name: uranaiMember) members = members+name+" ";
            plg.getServer().broadcastMessage("§3"+members);
            plg.getServer().broadcastMessage("");
        }

        plg.getServer().broadcastMessage("* 村人 *");
        initMembers();
        if(murabitoMember!=null) {
            for(String name: murabitoMember) members = members+name+" ";
            plg.getServer().broadcastMessage(members);
            plg.getServer().broadcastMessage("");
        }
        plg.getServer().broadcastMessage("*********************");
    }

    static void clearArrayList(){
        if(jinroMember!=null) jinroMember.clear();
        if(kyojinMember!=null) kyojinMember.clear();
        if(uranaiMember!=null) uranaiMember.clear();
        if(murabitoMember!=null) murabitoMember.clear();
        if(deadMember!=null) deadMember.clear();
        JinroCommand.clearPlayerList();
        initMembers();
    }
    public static void initMembers(){ members=""; }
    static void addJinroList(String name) { jinroMember.add(name); }
    static void addKyojinList(String name) { kyojinMember.add(name); }
    static void addUranaiList(String name) { uranaiMember.add(name); }
    static void addMurabitoList(String name) { murabitoMember.add(name); }
    static void addDeadList(String name) { deadMember.add(name); }

    static ArrayList<String> getJinroList(){ return jinroMember; }
    static ArrayList<String> getKyojinList(){ return kyojinMember; }
    static ArrayList<String> getUranaiList(){ return uranaiMember; }
    static ArrayList<String> getMurabitoList(){ return murabitoMember; }
    static ArrayList<String> getDeadList(){ return deadMember; }


}

