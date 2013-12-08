package main.java.com.github.KrazyTraynz;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: CarterMilch
 * Date: 12/7/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZombieListener implements Listener {

    EntitiesPlus ep;

    public ZombieListener(EntitiesPlus ep){
        this.ep = ep;
    }

    @EventHandler
    public void zombieDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() != null){
            if(e.getDamager() instanceof Zombie){
                if(e.getEntity() instanceof Player){
                    Random r = new Random();
                    int i = r.nextInt(10);
                    Player p = (Player)e.getEntity();
                    Zombie z = (Zombie)e.getDamager();
                    switch(i){
                        case 0:
                            ep.applyEffect(p, PotionEffectType.POISON, 200, 2);
                            break;
                        case 1:
                            ep.applyEffect(p, PotionEffectType.BLINDNESS, 200, 2);
                            break;
                        case 2:
                            ep.applyEffect(p, PotionEffectType.HUNGER, 200, 2);
                            break;
                        case 3:
                            ep.applyEffect(p, PotionEffectType.SLOW, 200, 2);
                            break;
                        case 4:
                            ep.applyEffect(p, PotionEffectType.CONFUSION, 200, 2);
                            break;
                        case 5:
                            ep.applyEffect(p, PotionEffectType.WEAKNESS, 200, 2);
                            break;
                        case 6:
                            p.setItemInHand(new ItemStack(Material.AIR));
                            break;
                        case 7:
                            double set = z.getHealth() + e.getDamage();
                            z.setHealth(set);
                            e.getEntity().getWorld().playEffect(e.getDamager().getLocation(), Effect.MOBSPAWNER_FLAMES, 3)
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
