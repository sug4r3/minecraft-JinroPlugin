package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.scheduler.BukkitRunnable;

public class emeraldTimer extends BukkitRunnable{
    private JinroPlugin plg;
    int time;
    static int INIT_TIME=60;
    static boolean flag = false;

    /**
     * コンストラクタ
     * @param plg_ プラグインメインクラスのインスタンス
     * @param time_ エメラルド配布までの時間
     */
    public emeraldTimer(JinroPlugin plg_, int time_){
        plg=plg_;
        time=time_;
        setFlag(true);
    }

    /**
     * 非同期タイマー処理
     */
    @Override
    public void run(){
        if(flag) {
            if (time <= 0) { //停止条件
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player, tag=!dead] minecraft:emerald");
                new emeraldTimer(plg, INIT_TIME).runTaskLater(plg, 0);
                return;
            }

            //タイマー作動中の処理
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "bossbar set my:timer value "+time);

            time--;
            new emeraldTimer(plg, time).runTaskLater(plg, 20);
        }
    }

    public static void setFlag(boolean b){
        flag = b;
    }

    public static void setInitTime(int init_time){
        INIT_TIME = init_time;
    }
}
