package com.github.sug4r3.jinroplugin.jinroplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;


public class EventListener implements Listener {
    JinroPlugin plg;
    private static boolean isSession;



    public EventListener(JinroPlugin plg_){
        plg=plg_;
        plg.getServer().getPluginManager().registerEvents(this,plg);
        isSession = false;
    }

    /**
     * 攻撃アイテムの動作
     */
    //ok
    @EventHandler
    public void onPlayerItemClick(PlayerInteractEvent e){
        if(isSession){
            Player player = e.getPlayer();
            PlayerFlag flag = new PlayerFlag(player, plg);
            if(flag.get()) return;
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                flag.set();
                ItemStack item = player.getInventory().getItemInMainHand();
                //発光玉
                if(item.getType().equals(Material.SNOWBALL) && item.getItemMeta().getDisplayName().equalsIgnoreCase("発光玉")){
                    player.getInventory().setItemInMainHand(null);
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "effect give @a[tag=player, tag=!dead] minecraft:glowing 60 0 true"
                    );
                    e.setCancelled(true);
                }
                //盲目のランタン
                else if(item.getType().equals(Material.SOUL_LANTERN) && item.getItemMeta().getDisplayName().equalsIgnoreCase("§4盲目のランタン")){
                    player.getInventory().setItemInMainHand(null);
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "execute at "+player.getName()+" run effect give @p[tag=player, tag=!dead, name=!"+player.getName()+"] minecraft:blindness 15 2 true"
                    );
                    e.setCancelled(true);
                }
            }
        }
    }

    //ok
    @EventHandler
    public void onArmorStandAtRightCrick(PlayerInteractAtEntityEvent e){
        if(isSession){
            Player player = e.getPlayer();
            PlayerFlag flag = new PlayerFlag(player, plg);
            if(flag.get()) return;
            if(e.getRightClicked() instanceof ArmorStand) {
                flag.set();
                ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
                //霊媒の粉
                if (item.getType().equals(Material.SUGAR) && item.getItemMeta().getDisplayName().equalsIgnoreCase("§b霊媒の粉")) {
                    ArmorStand armorStand = (ArmorStand) e.getRightClicked();
                    ItemStack helmet = armorStand.getEquipment().getHelmet();
                    if (helmet.getType().equals(Material.PLAYER_HEAD)) {
                        player.getInventory().setItemInMainHand(null);

                        Player deadPlayer = (Player) ((SkullMeta) helmet.getItemMeta()).getOwningPlayer();
                        String deadPlayerName = deadPlayer.getName();
                        if (JobAnnounce.getJinroList().contains(deadPlayerName)) {
                            player.sendMessage(deadPlayerName + "は" + "§4人狼" + "§fです");
                        } else {
                            player.sendMessage(deadPlayerName + "は" + "村人" + "です");
                        }
                    }

                    e.setCancelled(true);
                }
            }
        }
    }

    /**
     * 村人クリック時の取引内容変更
     * 対プレイヤー時に占い発動
     */
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEntityEvent e){
        if(isSession){
            //ok
            Player player = e.getPlayer();
            if(e.getRightClicked() instanceof Villager){
                Villager villager = (Villager) e.getRightClicked();
                if(villager.getName().equalsIgnoreCase("アイテム商人")) {
                    if (JobAnnounce.getJinroList().contains(player.getName())) {
                        villager.setRecipe(5, new ItemTrade(ItemTrade.Trade.AXE).getRecipe());
                    } else if (JobAnnounce.getKyojinList().contains(player.getName())) {
                        villager.setRecipe(5, new ItemTrade(ItemTrade.Trade.LANTERN).getRecipe());
                    } else if (JobAnnounce.getUranaiList().contains(player.getName())) {
                        villager.setRecipe(5, new ItemTrade(ItemTrade.Trade.STICK).getRecipe());
                    } else if (JobAnnounce.getMurabitoList().contains(player.getName())) {
                        villager.setRecipe(5, new ItemTrade(ItemTrade.Trade.STICK).getRecipe());
                    }
                }
            }

            //ok
            else if(e.getRightClicked() instanceof Player){
                Player target = (Player) e.getRightClicked();
                String targetName = target.getName();
                if(player.getInventory().getItemInMainHand().getType().equals(Material.STICK) &&
                        (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b占い棒"))||(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§3占い棒"))) {
                    player.getInventory().setItemInMainHand(null);
                    if (JobAnnounce.getJinroList().contains(targetName)) {
                        player.sendMessage(targetName + "は" + "§4人狼" + "§fです");
                    } else {
                        player.sendMessage(targetName + "は" + "村人" + "です");
                    }
                }
            }
        }
    }

    //ok
    @EventHandler
    public void onGetExperience(PlayerExpChangeEvent e){
        if(isSession){
            e.setAmount(0);
        }
    }

    //ok
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        if(isSession){
            e.setExpToDrop(0);
            Block block = e.getBlock();
            if(block.getType().equals(Material.COAL_ORE)) {
                plg.getServer().getScheduler().runTaskLater(plg, new Runnable() {
                    @Override
                    public void run() {
                        block.setType(Material.COAL_ORE);
                    }
                }, 1200L);
            }
        }
    }

    //ok
    @EventHandler
    public void onPickupItem(EntityPickupItemEvent e){
        if(isSession){
            if(e.getEntity() instanceof Player){
                Player player = (Player)e.getEntity();
                Inventory inv = player.getInventory();
                ItemStack items = e.getItem().getItemStack();
                //4つめの石炭を入手したとき
                if(items.getType().equals(Material.COAL) && inv.contains(new ItemStack(Material.COAL,3))){
                    inv.addItem(new ItemStack(Material.EMERALD,1));
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "playsound minecraft:entity.experience_orb.pickup master "+player.getName()+" ~ ~ ~ 10 0.8");
                    plg.getServer().getScheduler().runTask(plg, new Runnable() {
                        @Override
                        public void run() {
                            inv.remove(Material.COAL);
                        }
                    });
                }
            }
        }
    }

    //ok
    /**
     * 矢があたった時のやつ
     */
    @EventHandler
    public void onArrowHit(ProjectileHitEvent e){
        if(isSession) {
            if (e.getHitEntity() != null && e.getHitEntity() instanceof Player) {
                Player target = (Player) e.getHitEntity();
                if(JinroCommand.getPlayerList().contains(target.getName())) {
                    target.setHealth(0);
                }
                e.getEntity().remove();
            }
        }
    }

    //ok
    /**
     * 敵に攻撃したときのやつ
     */
    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent e){
        if(isSession) {
            if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
                Player killer = (Player) e.getDamager();
                Player player = (Player) e.getEntity();
                if(JinroCommand.getPlayerList().contains(killer.getName()) && JinroCommand.getPlayerList().contains(player.getName())) {
                    ItemStack item = killer.getInventory().getItemInMainHand();
                    if (item.getType().equals(Material.STONE_AXE) && item.getItemMeta().getDisplayName().equalsIgnoreCase("§4殺人斧")) {
                        killer.getInventory().setItemInMainHand(null);
                        player.setHealth(0);
                    }
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    //ok
    public void onPlayerDeath(PlayerDeathEvent e){
        if(isSession) {
            Player player = e.getEntity();
            double[] pos = {player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()};
            float direction = player.getLocation().getYaw();

            //リストに追加
            JobAnnounce.addDeadList(player.getName());

            /**
             * 勝利判定行う
             */
            boolean jinroAllDeadFlag = true;
            boolean murabitoAllDeadFlag = true;
            for(String jinroMember: JobAnnounce.getJinroList()){
                jinroAllDeadFlag = jinroAllDeadFlag && JobAnnounce.getDeadList().contains(jinroMember);
            }
            for(String uranaiMember: JobAnnounce.getUranaiList()){
                murabitoAllDeadFlag = murabitoAllDeadFlag && JobAnnounce.getDeadList().contains(uranaiMember);
            }
            for(String murabitoMember: JobAnnounce.getMurabitoList()){
                murabitoAllDeadFlag = murabitoAllDeadFlag && JobAnnounce.getDeadList().contains(murabitoMember);
            }

            if(jinroAllDeadFlag){
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "title @a[tag=player] title \"村人陣営の勝利\"");
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "playsound minecraft:ui.toast.challenge_complete master @a[tag=player] ~ ~ ~ 10 1");
                plg.getServer().broadcastMessage("");
                plg.getServer().broadcastMessage("");
                plg.getServer().broadcastMessage("*********************");
                plg.getServer().broadcastMessage("   村人陣営の勝利");
                plg.getServer().getScheduler().runTask(plg, new Runnable() {
                    @Override
                    public void run() {
                        JinroCommand.finishSession();
                    }
                });
                return;

            }else if(murabitoAllDeadFlag){
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "title @a[tag=player] title \"§4人狼陣営の勝利\"");
                plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                        "playsound minecraft:ui.toast.challenge_complete master @a[tag=player] ~ ~ ~ 10 1");
                plg.getServer().broadcastMessage("");
                plg.getServer().broadcastMessage("");
                plg.getServer().broadcastMessage("*********************");
                plg.getServer().broadcastMessage("§4   人狼陣営の勝利");
                plg.getServer().getScheduler().runTask(plg, new Runnable() {
                    @Override
                    public void run() {
                        JinroCommand.finishSession();
                    }
                });
                return;
            }


            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "tag " + player.getName() + " add dead");
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "clear " + player.getName());
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "gamemode spectator " + player.getName());

            //死人用の防具立て設置
            plg.getServer().getScheduler().runTask(plg, new Runnable(){
                @Override
                public void run(){
                    plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                            "summon minecraft:armor_stand " + pos[0] + " " + pos[1] + " " + pos[2] +
                                    " {Marker:false, NoGravity:true, Tags:[\"deadBody\"], Rotation:["+direction+"f,0.0f], " +
                                    "ArmorItems:[ {},{},{},{ id:\"minecraft:player_head\",Count:1b,tag:{ SkullOwner:\"" + player.getName() + "\" } } ]}"
                    );
                }
            });


        }
    }

    @EventHandler
    //ok
    public void onRespawn(PlayerRespawnEvent e){
        if(isSession) {
            Player player = e.getPlayer();
            double[] ROOM = JinroCommand.getRoom();
            if(ROOM!=null) {
                e.setRespawnLocation(new Location(player.getWorld(), ROOM[0], ROOM[1], ROOM[2]));
            }
        }
        else{
            Player player = e.getPlayer();
            double[] HOME = JinroCommand.getHome();
            if(HOME!=null) {
                e.setRespawnLocation(new Location(player.getWorld(), HOME[0], HOME[1], HOME[2]));
            }
        }
    }

    @EventHandler
    //ok
    public void onJobCheck(EntityPotionEffectEvent e){
        if(isSession) {
            if (e.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) { //付与時にのみ動作
                Player player = (Player) e.getEntity();
                PotionEffectType effect = e.getModifiedType();

                //人狼の時（strength）
                if (effect.equals(PotionEffectType.INCREASE_DAMAGE)) {
                    plg.getServer().getScheduler().runTask(plg, new Runnable() {
                        @Override
                        public void run() {
                            JobAnnounce.addJinroList(player.getName());
                        }
                    });
                    new JobAnnounce(plg, player, "jinro").runTask(plg);
                }

                //狂人の時（haste）
                else if (effect.equals(PotionEffectType.FAST_DIGGING)) {
                    plg.getServer().getScheduler().runTask(plg, new Runnable() {
                        @Override
                        public void run() {
                            JobAnnounce.addKyojinList(player.getName());
                        }
                    });
                    new JobAnnounce(plg, player, "kyojin").runTask(plg);
                }

                //占い師の時(slow_digging)
                else if (effect.equals(PotionEffectType.SLOW_DIGGING)) {
                    plg.getServer().getScheduler().runTask(plg, new Runnable() {
                        @Override
                        public void run() {
                            JobAnnounce.addUranaiList(player.getName());
                        }
                    });
                    new JobAnnounce(plg, player, "uranai").runTask(plg);
                }

                //村人の時（water_breathing）
                else if (effect.equals(PotionEffectType.WATER_BREATHING)) {
                    plg.getServer().getScheduler().runTask(plg, new Runnable() {
                        @Override
                        public void run() {
                            JobAnnounce.addMurabitoList(player.getName());
                        }
                    });
                    new JobAnnounce(plg, player, "murabito").runTask(plg);
                }

            }
        }
    }

    @EventHandler
    //ok
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        /**
         * セッション中は各役職の人数を表示
         */
        if(isSession){
            Objective objective = board.registerNewObjective("sessionMember","dummy","役職内訳");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);


            int num1 = (JobAnnounce.getJinroList()==null)?0:JobAnnounce.getJinroList().size();
            Score score1 = objective.getScore("§4人狼   "+"§f["+num1+"人]");
            score1.setScore(-1);

            int num2 = (JobAnnounce.getKyojinList()==null)?0:JobAnnounce.getKyojinList().size();
            Score score2 = objective.getScore("§4狂人   "+"§f["+num2+"人]");
            score2.setScore(-2);

            int num3 = (JobAnnounce.getUranaiList()==null)?0:JobAnnounce.getUranaiList().size();
            Score score3 = objective.getScore("§3占い師 "+"§f["+num3+"人]");
            score3.setScore(-3);

            int num4 = (JobAnnounce.getMurabitoList()==null)?0:JobAnnounce.getMurabitoList().size();
            Score score4 = objective.getScore("§f村人   "+"§f["+num4+"人]");
            score4.setScore(-4);

            player.setScoreboard(board);
        }
        /**
         * 待機中は参加人数を表示
         */
        else{
            Objective objective = board.registerNewObjective("sankaMember","dummy","待機中");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int num = (JinroCommand.getPlayerList()==null)?0:JinroCommand.getPlayerList().size();
            Score score1 = objective.getScore("§a参加者   "+"§f["+num+"人]");
            score1.setScore(-1);

            Score score2 = objective.getScore("§f未参加者 "+"§f["+(plg.getServer().getOnlinePlayers().size()-num)+"人]");
            score2.setScore(-2);

            player.setScoreboard(board);

        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String name = e.getPlayer().getName();
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "effect give "+name+" minecraft:regeneration 1000000 255 true"
        );
        plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                "effect give "+name+" minecraft:saturation 1000000 255 true"
        );
        if(name.equalsIgnoreCase("sug4r3")){
            plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
                    "give_jinro sug4r3"
            );
        }
    }

    public static void setSession(boolean flag){ isSession = flag; }

}
