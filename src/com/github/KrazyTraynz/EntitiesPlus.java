package com.github.KrazyTraynz;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created with IntelliJ IDEA.
 * User: CarterMilch
 * Date: 12/7/13
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntitiesPlus extends JavaPlugin{

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new SkeletonListener(this), this);
        getServer().getPluginManager().registerEvents(new ZombieListener(this), this);
        getServer().getPluginManager().registerEvents(new CreeperListener(this), this);
        getLogger().info("EntitiesPlus has been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().warning("EntitiesPlus has been disabled!");
    }

    public void applyEffect(Player p, PotionEffectType effectType, int duration, int strength){
        p.addPotionEffect(new PotionEffect(effectType, duration, strength));
    }
}
