package main.java.com.github.KrazyTraynz;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created with IntelliJ IDEA.
 * User: CarterMilch
 * Date: 12/7/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkeletonListener implements Listener{

    EntitiesPlus ep;

    public SkeletonListener(EntitiesPlus ep){
        this.ep = ep;
    }

    List<Integer> blindness = new ArrayList<Integer>();
    List<Integer> damage = new ArrayList<Integer>();
    List<Integer> fire = new ArrayList<Integer>();
    List<Integer> heal = new ArrayList<Integer>();
    List<Integer> hunger = new ArrayList<Integer>();
    List<Integer> normal = new ArrayList<Integer>();
    List<Integer> poison = new ArrayList<Integer>();
    List<Integer> slowness = new ArrayList<Integer>();
    List<Integer> starve = new ArrayList<Integer>();
    List<Integer> teleport = new ArrayList<Integer>();

    @EventHandler
    public void nameArrow(EntityShootBowEvent e){
        if(e.getEntity() instanceof Skeleton){
            if(e.getProjectile() instanceof Arrow){
                Arrow a = (Arrow)e.getProjectile();
                Random r = new Random();
                int i = r.nextInt(12);
                switch(i){
                    case 0:
                        fire.add(a.getEntityId());
                        break;
                    case 1:
                        hunger.add(a.getEntityId());
                        break;
                    case 2:
                        damage.add(a.getEntityId());
                        break;
                    case 3:
                        normal.add(a.getEntityId());
                        break;
                    case 4:
                        normal.add(a.getEntityId());
                        break;
                    case 5:
                        normal.add(a.getEntityId());
                        break;
                    case 6:
                        poison.add(a.getEntityId());
                        break;
                    case 7:
                        slowness.add(a.getEntityId());
                        break;
                    case 8:
                        blindness.add(a.getEntityId());
                        break;
                    case 9:
                        starve.add(a.getEntityId());
                        break;
                    case 10:
                        heal.add(a.getEntityId());
                        break;
                    case 11:
                        teleport.add(a.getEntityId());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void checkTypeA(ProjectileHitEvent e){
        if(e.getEntity().getShooter() instanceof Skeleton){
            if(teleport.contains(e.getEntity().getEntityId())){
                e.getEntity().getShooter().teleport(e.getEntity().getLocation());
            }
        }
    }

    @EventHandler
    public void checkTypeB(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Arrow){
            Arrow a = (Arrow)e.getDamager();
            if(a.getShooter() instanceof Skeleton && e.getEntity() instanceof Player){
                int id = a.getEntityId();
                Player p = (Player)e.getEntity();
                if(normal.contains(id)){}
                else if(blindness.contains(id)){
                    ep.applyEffect(p, PotionEffectType.BLINDNESS, 200, 2);
                }else if(damage.contains(id)){
                    double damage = p.getHealth() - e.getDamage();
                    p.setHealth(damage);
                }else if(fire.contains(id)){
                    p.setFireTicks(100);
                }else if(heal.contains(id)){
                    Double health = a.getShooter().getHealth() + e.getDamage();
                    a.getShooter().setHealth(health);
                }else if(hunger.contains(id)){
                    ep.applyEffect(p, PotionEffectType.HUNGER, 200, 2);
                }else if(poison.contains(id)){
                    ep.applyEffect(p, PotionEffectType.POISON, 200, 2);
                }else if(slowness.contains(id)){
                    ep.applyEffect(p, PotionEffectType.SLOW, 200, 2);
                }else if(starve.contains(id)){
                    p.setFoodLevel(0);
                }
            }
        }
    }
}