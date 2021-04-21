package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTimer extends BukkitRunnable{
    private final JinroPlugin plg;
    private final Player pl;
    int time;
    static boolean flag = false;

    /**
     * コンストラクタ
     * @param plg_ プラグインメインクラスのインスタンス
     * @param pl_ 実行者
     * @param time_ エメラルド配布までの時間
     */
    public CountdownTimer(JinroPlugin plg_, Player pl_, int time_){
        plg=plg_;
        pl=pl_;
        time=time_;
        setFlag(true);
    }

    /**
     * 非同期タイマー処理
     */
    @Override
    public void run(){
        if(flag) {
            if (time <= 0) { //停止条件 スタート時のコマンド
                double[] ROOM = JinroCommand.getRoom();
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "clear @a[tag=player]"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "effect clear @a[tag=player]"
                );
                if(ROOM!=null) {
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "tp @a[tag=player] " + ROOM[0] + " " + ROOM[1] + " " + ROOM[2]
                    );
                }
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "em_timer start"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "title @a[tag=player] title \"START!!!\"");
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "playsound minecraft:entity.wolf.howl master @a[tag=player] ~ ~ ~ 10 0.8");

                //役職確認用
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "effect give @a[tag=jinro] minecraft:strength 1 0 true"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "effect give @a[tag=kyojin] minecraft:haste 1 0 true"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "effect give @a[tag=uranai] minecraft:mining_fatigue 1 0 true"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "effect give @a[tag=murabito] minecraft:water_breathing 1 0 true"
                );

                //初期アイテム配布用
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player] minecraft:wooden_pickaxe{display:{Name:\"{\\\"text\\\":\\\"練金ピッケル\\\"}\",Lore:['[{\"text\":\"石炭鉱石を破壊できる\"}]','[{\"text\":\"石炭を4つ集めるごとにエメラルド1つに換金される\"}]']},Unbreakable:1,CanDestroy:[\"minecraft:coal_ore\"],HideFlags:31} 1"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player] minecraft:bow{display:{Name:\"{\\\"text\\\":\\\"弓\\\"}\",Lore:['[{\"text\":\"プレイヤーを一撃で倒すことができる\"}]','[{\"text\":\"矢は別売り\"}]']},Unbreakable:1,HideFlags:31} 1"
                );

                //占いアイテムと占い騙りアイテム
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player, tag=!uranai] minecraft:stick{display:{Name:\"{\\\"text\\\":\\\"ただの棒\\\"}\",Lore:[\"\\\"特に意味はない。\\\"\"]},Unbreakable:1,HideFlags:31} 1"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=uranai] minecraft:stick{display:{Name:\"{\\\"text\\\":\\\"占い棒\\\",\\\"color\\\":\\\"aqua\\\"}\",Lore:['[{\"text\":\"プレイヤーに向けて右クリックで使用\"}]','[{\"text\":\"対象が人狼かどうか調べることができる\"}]']},Unbreakable:1,HideFlags:31} 1"
                );

                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player] minecraft:cooked_beef 3"
                );
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "give @a[tag=player] minecraft:arrow 1"
                );


                return;
            }

            //タイマー作動中の処理
            //タイトル表示
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "title @a[tag=player] title \""+time+"\"");
            //音声
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "playsound minecraft:block.anvil.place master @a[tag=player] ~ ~ ~ 10 0.8");

            time--;
            new CountdownTimer(plg, pl, time).runTaskLater(plg, 20);
        }
    }

    public static void setFlag(boolean b){
        flag = b;
    }

}

