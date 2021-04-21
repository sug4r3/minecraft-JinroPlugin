package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * エメラルド配布タイマー
 * +仕様
 *  一定時間経過ごとに対象者にエメラルドを1つ配布する
 *  対象者は「Jinro」タグのついているプレイヤーとチェスト
 *
 * +コマンド
 *  /em_timer set <正の数値>  --  タイマーを<正の数値>秒にセット
 *  /em_timer start  --  タイマーを作動。対象者の上部bossbarの位置に表示
 *  /em_timer stop  --  起動中のタイマーを停止する
 *  /em_timer get  --  現在のタイマーの設定秒数を表示する
 */
public class EmTimerCommand implements CommandExecutor{
    JinroPlugin plg;
    final int INIT_SEC = 60;
    int time;

    public EmTimerCommand(JinroPlugin plg_){
        plg=plg_;
        time = INIT_SEC;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("em_timer")) {    //エメラルドタイマー
            if(args.length==0){ //引数がない場合
                sender.sendMessage("/em_timer [set,start,stop]");
                return true;
            }
            else{
                if(args[0].equalsIgnoreCase("set")){    //set処理
                    if(args.length!=2){
                        sender.sendMessage("コマンドに誤りがあります。 /em_timer set <time>");
                        return true;
                    }
                    int newtime = isNumber(args[1]);
                    if(newtime>0){
                        time = newtime;
                        sender.sendMessage("タイマーを"+time+"秒にセットしました");
                    }
                    else{
                        sender.sendMessage("時間には1以上の整数を入力してください");
                    }
                    return true;
                }
                else if(args[0].equalsIgnoreCase("start")){    //start処理
                    //bossbar作成
                    makeBossbar();
                    //タイマー実行
                    emeraldTimer.setInitTime(time);
                    emeraldTimer em_timer = new emeraldTimer(plg, time);
                    em_timer.runTaskLater(plg, 0);

                    return true;
                }
                else if(args[0].equalsIgnoreCase("stop")) {    //stop処理
                    //bossbar消去
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "bossbar remove my:timer");

                    //タイマー停止
                    emeraldTimer.setFlag(false);
                    sender.sendMessage("タイマーを停止しました");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("get")) {    //get処理
                    sender.sendMessage("現在のタイマーの時間は"+time+"秒です");
                    return true;
                }
                else {  //set,start,stop,get 以外のコマンドが入力されたとき
                    sender.sendMessage("コマンドに誤りがあります。/em_timer [set,start,stop]");
                    return true;
                }
            }
        }

        //該当コマンドがない場合
        return false;
    }

    private void makeBossbar() {
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "bossbar add my:timer \"エメラルド配布まで\"");
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "bossbar set my:timer color green");
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "bossbar set my:timer max "+time);
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "bossbar set my:timer players @a[tag=player]");
    }

    private int isNumber(String num){
        try {
            return Integer.parseInt(num);
        }catch(NumberFormatException e){
            return -1;
        }
    }
}

