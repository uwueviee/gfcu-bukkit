package gq.skyenet.gayfurclubutil.gayfurclubutil;

import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.List;

public final class GayFurClubUtil extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
        getLogger().info("Loaded the utilities for the Gay Fur Club SMP Server");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("killp")) {
            if(args.length < 1){
                if(sender instanceof  Player) {
                    Player target = (Player) sender;
                    sender.sendMessage("[GFCU] Killed " + target.getDisplayName());
                    target.getWorld().createExplosion(target.getLocation(), 10F);
                    target.setHealth(0.0D);
                    return true;
                } else {
                    sender.sendMessage("[GFCU] Please enter a valid user.");
                    return true;
                }
            } else {
                Player target = sender.getServer().getPlayer(args[0]);
                // Make sure the player is online.
                if (target == null) {
                    sender.sendMessage("[GFCU] " + args[0] + " is not currently online.");
                    return true;
                }
                target.getWorld().createExplosion(target.getLocation(), 10F);
                target.setHealth(0.0D);
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("safeboom")) {
            if(args.length < 1){
                if(sender instanceof  Player) {
                    Player target = (Player) sender;
                    sender.sendMessage("[GFCU] Safeboomed " + target.getDisplayName());
                    target.getWorld().createExplosion(target.getLocation(), 0F);
                    return true;
                } else {
                    sender.sendMessage("[GFCU] Please enter a valid user.");
                    return true;
                }
            } else {
                Player target = sender.getServer().getPlayer(args[0]);
                // Make sure the player is online.
                if (target == null) {
                    sender.sendMessage("[GFCU] " + args[0] + " is not currently online.");
                    return true;
                }
                target.getWorld().createExplosion(target.getLocation(), 0F);
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("nuke")) {
            if(args.length < 1){
                if(sender instanceof  Player) {
                    Player target = (Player) sender;
                    sender.sendMessage("[GFCU] Nuked " + target.getDisplayName());
                    target.getWorld().createExplosion(target.getLocation(), 250F);
                    return true;
                } else {
                    sender.sendMessage("[GFCU] Please enter a valid user.");
                    return true;
                }
            } else {
                Player target = sender.getServer().getPlayer(args[0]);
                // Make sure the player is online.
                if (target == null) {
                    sender.sendMessage("[GFCU] " + args[0] + " is not currently online.");
                    return true;
                }
                target.getWorld().createExplosion(target.getLocation(), 250F);
                return true;
            }
        } else if (cmd.getName().equalsIgnoreCase("clearlag")) {
            World world = getServer().getWorld("world");
            List<Entity> entList = ((World) world).getEntities();
            for(Entity current : entList){
                if(current instanceof Item){
                    current.remove();
                }
            }
            sender.sendMessage("[GFCU] Cleared all items.");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("tickrate")) {
            double tps = Lag.getTPS();
            double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
            sender.sendMessage("[GFCU] Current TPS: " + Lag.getTPS());
            sender.sendMessage("[GFCU] Lag percentage is %" + lag);
            return true;
        }
        return false;
    }
}
