package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GiveJinroCommand implements CommandExecutor {
    JinroPlugin plg;

    public GiveJinroCommand(JinroPlugin plg_){
        plg=plg_;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("give_jinro")) {
            if (args.length == 0) { //引数がない場合
                giveSettingBook(sender);
            } else {
                if (args[0].equalsIgnoreCase("sankaKanban")) {
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "give "+sender.getName()+" minecraft:oak_sign{BlockEntityTag:{Text1:'[{\"text\":\"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"jinro join\"}},{\"text\":\"Minecraft \"},{\"text\":\"\\\\u4eba\\\\u72fc\",\"bold\":true,\"color\":\"dark_red\"}]',Text2:'{\"text\":\"セッションに参加\",\"bold\":true,\"underlined\":true,\"color\":\"green\"}'},display:{Name:'{\"text\":\"\\\\u53c2\\\\u52a0\\\\u770b\\\\u677f\"}'}}"
                    );
                }
                else if (args[0].equalsIgnoreCase("cancelKanban")) {
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "give "+sender.getName()+" minecraft:oak_sign{BlockEntityTag:{Text1:'[{\"text\":\"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"jinro leave\"}},{\"text\":\"Minecraft \"},{\"text\":\"\\\\u4eba\\\\u72fc\",\"bold\":true,\"color\":\"dark_red\"}]',Text2:'{\"text\":\"参加をキャンセル\",\"bold\":true,\"underlined\":true,\"color\":\"yellow\"}'},display:{Name:'{\"text\":\"キャンセル看板\"}'}}"
                    );
                }
                else if (args[0].equalsIgnoreCase("kaisiKanban")) {
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "give "+sender.getName()+" minecraft:oak_sign{BlockEntityTag:{Text1:'[{\"text\":\"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"jinro start\"}},{\"text\":\"Mincraft \"},{\"text\":\"\\\\u4eba\\\\u72fc\",\"bold\":true,\"color\":\"dark_red\"}]',Text2:'{\"text\":\"\\\\u30b2\\\\u30fc\\\\u30e0\\\\u30b9\\\\u30bf\\\\u30fc\\\\u30c8\\\\uff01\\\\uff01\",\"bold\":true,\"underlined\":true,\"color\":\"green\"}'},display:{Name:'{\"text\":\"\\\\u958b\\\\u59cb\\\\u770b\\\\u677f\"}'}}"
                    );
                }
                else if (args[0].equalsIgnoreCase("settingBook")) {
                    giveSettingBook(sender);
                }
                else if (args[0].equalsIgnoreCase("sug4r3")) {
                    giveSettingBook("sug4r3");
                }
                else {
                    sender.sendMessage("/give_jinro <item>");
                }
            }
            return true;
        }
        return false;
    }

    private void giveSettingBook(CommandSender sender) {
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "give "+ sender.getName()+ " " +
                        "written_book{" +
                        "pages:['[\"\",{\"text\":\"***\",\"bold\":true},{\"text\":\" Mincraft \",\"color\":\"reset\"},{\"text\":\"人狼 \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"***\",\"color\":\"reset\",\"bold\":true}," +
                        "{\"text\":\"\\\\n+役職人数設定\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"人狼  \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"0\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 2\"}},{\"text\":\" \"},{\"text\":\"3\\\\n\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 3\"}}" +
                        ",{\"text\":\"狂人  \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"0\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 2\"}},{\"text\":\"\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"占い師  \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"0\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 2\"}}," +
                        "{\"text\":\"\\\\n\\\\n+商人スポーン\\\\n  \",\"color\":\"reset\"},{\"text\":\"足元にスポーンします\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/summon_jinro shonin\"}}," +
                        "{\"text\":\"\\\\n\\\\n☆マップ上に石炭鉱石を\\\\n  いくつか配置すること！\\\\n\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"GAME START\",\"bold\":true,\"underlined\":true,\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro start\"}}]" +
                        "','" +
                        "[\"\",{\"text\":\"***\",\"bold\":true},{\"text\":\" Mincraft \",\"color\":\"reset\"},{\"text\":\"人狼 \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"***\",\"color\":\"reset\",\"bold\":true}," +
                        "{\"text\":\"\\\\n\\\\n+初期設定\\\\n  \",\"color\":\"reset\"}," +
                        "{\"text\":\"とりあえず押すコマンド\",\"bold\":true,\"underlined\":true,\"color\":\"red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/init_gamerule\"}}," +
                        "{\"text\":\"\\\\n\\\\n+リス位置\\\\n  待機部屋    \",\"color\":\"reset\"},{\"text\":\"set\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro setHomeRespawn\"}}," +
                        "{\"text\":\"\\\\n  ステージ    \",\"color\":\"reset\"},{\"text\":\"set\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro setRoomRespawn\"}}," +
                        "{\"text\":\"\\\\n\\\\n+各種看板\\\\n  参加看板         \",\"color\":\"reset\"},{\"text\":\"get\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/give_jinro sankaKanban\"}}," +
                        "{\"text\":\"\\\\n  参加取り消し看板   \",\"color\":\"reset\"},{\"text\":\"get\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/give_jinro cancelKanban\"}}]'" +
                        "],title:\"人狼ゲーム設定いじる本\",author:sug4r3}"
        );
    }

    private void giveSettingBook(String sug4r3) {
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "give "+ "sug4r3"+ " " +
                        "written_book{" +
                        "pages:['[\"\",{\"text\":\"***\",\"bold\":true},{\"text\":\" Mincraft \",\"color\":\"reset\"},{\"text\":\"人狼 \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"***\",\"color\":\"reset\",\"bold\":true}," +
                        "{\"text\":\"\\\\n+役職人数設定\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"人狼  \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"0\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 2\"}},{\"text\":\" \"},{\"text\":\"3\\\\n\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set jinro 3\"}}" +
                        ",{\"text\":\"狂人  \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"0\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set kyojin 2\"}},{\"text\":\"\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"占い師  \",\"bold\":true,\"color\":\"blue\"},{\"text\":\"0\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 0\"}},{\"text\":\" \"},{\"text\":\"1\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 1\"}},{\"text\":\" \"},{\"text\":\"2\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro set uranai 2\"}}," +
                        "{\"text\":\"\\\\n\\\\n+商人スポーン\\\\n  \",\"color\":\"reset\"},{\"text\":\"足元にスポーンします\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/summon_jinro shonin\"}}," +
                        "{\"text\":\"\\\\n\\\\n☆マップ上に石炭鉱石を\\\\n  いくつか配置すること！\\\\n\\\\n\",\"color\":\"reset\"}," +
                        "{\"text\":\"GAME START\",\"bold\":true,\"underlined\":true,\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro start\"}}]" +
                        "','" +
                        "[\"\",{\"text\":\"***\",\"bold\":true},{\"text\":\" Mincraft \",\"color\":\"reset\"},{\"text\":\"人狼 \",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\"***\",\"color\":\"reset\",\"bold\":true}," +
                        "{\"text\":\"\\\\n\\\\n+初期設定\\\\n  \",\"color\":\"reset\"}," +
                        "{\"text\":\"とりあえず押すコマンド\",\"bold\":true,\"underlined\":true,\"color\":\"red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/init_gamerule\"}}," +
                        "{\"text\":\"\\\\n\\\\n+リス位置\\\\n  待機部屋    \",\"color\":\"reset\"},{\"text\":\"set\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro setHomeRespawn\"}}," +
                        "{\"text\":\"\\\\n  ステージ    \",\"color\":\"reset\"},{\"text\":\"set\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/jinro setRoomRespawn\"}}," +
                        "{\"text\":\"\\\\n\\\\n+各種看板\\\\n  参加看板         \",\"color\":\"reset\"},{\"text\":\"get\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/give_jinro sankaKanban\"}}," +
                        "{\"text\":\"\\\\n  参加取り消し看板   \",\"color\":\"reset\"},{\"text\":\"get\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/give_jinro cancelKanban\"}}]'" +
                        "],title:\"人狼ゲーム設定いじる本\",author:sug4r3}"
        );
    }
}
