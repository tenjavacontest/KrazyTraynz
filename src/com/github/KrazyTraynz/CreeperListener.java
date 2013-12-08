package com.github.KrazyTraynz;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: CarterMilch
 * Date: 12/7/13
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreeperListener implements Listener {

    EntitiesPlus ep;

    public CreeperListener(EntitiesPlus ep){
        this.ep = ep;
    }


    List<String> ids = new ArrayList<String>();

    @EventHandler
    public void explosion(EntityExplodeEvent e){
        if(e.getEntity() instanceof Creeper){
            Random r = new Random();
            int i = r.nextInt(20);
            Location l = e.getLocation();
            World w = l.getWorld();
            List<Block> blockList = e.blockList();
            switch(i){
                case 0:
                    for(Block b : blockList){
                        b.setType(Material.LAVA);
                    }
                    e.setCancelled(true);
                    break;
                case 1:
                    for(Block b : blockList){
                        b.setType(Material.WATER);
                    }
                    e.setCancelled(true);
                    break;
                case 2:
                    for(Block b : blockList){
                        b.setType(Material.BEDROCK);
                    }
                    e.setCancelled(true);
                    break;
                case 3:
                    e.setCancelled(true);
                    w.createExplosion(l, 20.0F);
                    break;
                case 4:
                    ((LivingEntity) e.getEntity()).setHealth(0);
                    w.spawnEntity(l, EntityType.CREEPER);
                    w.spawnEntity(l, EntityType.LIGHTNING);
                    break;
                case 5:
                    for (Block b : e.blockList()) {
                        Location loc = b.getLocation();
                        Location loc1 = loc.add(new Location(loc.getWorld(), 1, 0, 1));
                        b.getLocation().getWorld().spawnArrow(loc, new Vector(0, 2, 0), 1.0F, 3.0F);
                        b.getLocation().getWorld().spawnArrow(loc1, new Vector(0, 2, 0), 1.0F, 2.85F);
                    }
                    break;
                case 6:
                    w.spawnEntity(l, EntityType.PRIMED_TNT);
                    break;
                case 7:
                    for (int integer = 1; i <= l.getBlockY(); i++) {
                        Block b = w.getBlockAt(l.getBlockX(), integer, l.getBlockZ());
                        b.setType(Material.AIR);
                    }
                    break;
                case 8:
                    String[] idnumbers = { "29", "30", "33", "35", "41", "42",
                            "43", "45", "46", "47", "48", "49", "57", "61",
                            "79", "80", "81", "82", "86", "87", "88", "89",
                            "103", "110", "112", "121", "123", "125", "14", "15", "16", "56",
                            "74", "73", "21", "129", "41", "42", "57", "133", "22" };

                    for (String s : idnumbers) {
                        ids.add(s);
                    }

                    for (int id = 17; id < 25; id++) {
                        String s = Integer.toString(i);
                        ids.add(s);
                    }

                    Location loc = e.getEntity().getLocation();
                    Location loc1 = loc.add(0, 2, 0);

                    int rand = r.nextInt(ids.size());
                    int id = Integer.parseInt(ids.get(rand));

                    if (isNumberCubeId(id)) {
                        generateCube(loc1, 8, id);
                    } else {
                        generateCube(loc1, 8, 3);
                    }
                    break;
                case 9:


                default:
                    break;
            }
        }
    }

    public boolean isNumberCubeId(int i){
        String[] ids = { "29", "30", "33", "35", "41", "42", "43", "45", "46", "47", "48", "49", "57", "61", "79", "80", "81", "82", "86", "87", "88", "89", "103", "110", "112", "121", "123", "125" };
        for(int j = 1; j < 25; j++){
            if(i == j){
                return true;
            }
        }

        for(String s : ids){
            int k = Integer.parseInt(s);
            if(i == k){
                return true;
            }
        }
        return false;
    }

    public void generateCube(Location l, int length, int id) {
        World w = l.getWorld();

        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();

        int xl = x + length;
        int yl = y + length;
        int zl = z + length;

        for (int cubex = x; cubex <= xl; cubex++) {
            for (int cubey = y; cubey <= yl; cubey++) {
                for (int cubez = z; cubez <= zl; cubez++) {
                    Block blockToChange = w.getBlockAt(cubex, cubey, cubez);
                    blockToChange.setTypeId(id);
                }
            }
        }
    }
}
