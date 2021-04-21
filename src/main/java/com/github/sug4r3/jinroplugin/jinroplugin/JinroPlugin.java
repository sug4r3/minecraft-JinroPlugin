package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class JinroPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new EventListener(this);
        getCommand("jinro").setExecutor(new JinroCommand(this));
        getCommand("em_timer").setExecutor(new EmTimerCommand(this));
        getCommand("init_gamerule").setExecutor(new InitGamerule(this));
        getCommand("give_jinro").setExecutor(new GiveJinroCommand(this));
        getCommand("summon_jinro").setExecutor(new SummonJinroCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
