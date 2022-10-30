package com.itzzanty.ksrmcsmptp.events;

import com.itzzanty.ksrmcsmptp.Main;
import com.itzzanty.ksrmcsmptp.files.DataManager;
import com.onarandombox.MultiversePortals.event.MVPortalEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class world implements Listener {

    public DataManager data;
    public world(DataManager data) {
        this.data = data;
    }


    @EventHandler
    public void onPosChange(PlayerTeleportEvent e) {

        Player p = e.getPlayer();

        if (e.getFrom().getWorld().getName().startsWith("KSR-SMP")) {
            if (!e.getTo().getWorld().getName().startsWith(("KSR-SMP"))) {
                this.data.getConfig().set("players." + p.getUniqueId() + ".position.X", e.getFrom().getBlockX());
                this.data.getConfig().set("players." + p.getUniqueId() + ".position.Y", e.getFrom().getBlockY());
                this.data.getConfig().set("players." + p.getUniqueId() + ".position.Z", e.getFrom().getBlockZ());

                this.data.getConfig().set("players." + p.getUniqueId() + ".position.world", e.getFrom().getWorld().getName());
                this.data.getConfig().set("players." + p.getUniqueId() + ".position.yaw", e.getFrom().getYaw());
                this.data.getConfig().set("players." + p.getUniqueId() + ".position.pitch", e.getFrom().getPitch());


                this.data.saveConfig();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (p.getLocation().getWorld().getName().startsWith("KSR-SMP")) {
            this.data.getConfig().set("players." + p.getUniqueId() + ".position.X", p.getLocation().getBlockX());
            this.data.getConfig().set("players." + p.getUniqueId() + ".position.Y", p.getLocation().getBlockY());
            this.data.getConfig().set("players." + p.getUniqueId() + ".position.Z", p.getLocation().getBlockZ());

            this.data.getConfig().set("players." + p.getUniqueId() + ".position.world", p.getLocation().getWorld().getName());
            this.data.getConfig().set("players." + p.getUniqueId() + ".position.yaw", p.getLocation().getYaw());
            this.data.getConfig().set("players." + p.getUniqueId() + ".position.pitch", p.getLocation().getPitch());


            this.data.saveConfig();
        }
    }

    @EventHandler
    public void onPortalEvent(MVPortalEvent e) {

        Player p = e.getTeleportee();

        if (e.getDestination().getLocation(p).getWorld().getName().startsWith("KSR-SMP")) {
            if (!p.getLocation().getWorld().getName().startsWith("KSR-SMP")) {
                Location loc = new Location(
                        Bukkit.getServer().getWorld(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.world")),
                        Double.parseDouble(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.X")),
                        Double.parseDouble(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.Y")),
                        Double.parseDouble(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.Z")),
                        Float.parseFloat(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.yaw")),
                        Float.parseFloat(this.data.getConfig().getString("players." + p.getUniqueId() + ".position.pitch"))
                );
                p.teleport(loc);
                e.setCancelled(true);
            }
        }

    }

}
