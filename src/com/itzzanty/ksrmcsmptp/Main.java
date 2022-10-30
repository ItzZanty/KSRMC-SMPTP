package com.itzzanty.ksrmcsmptp;

import com.itzzanty.ksrmcsmptp.events.world;
import com.itzzanty.ksrmcsmptp.files.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public DataManager data;

    static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.data = new DataManager(this);

        getServer().getPluginManager().registerEvents(new world(this.data), this);
        getServer().getConsoleSender().sendMessage("[KSRMC-SMPTP] Plugin enabled");
    }
}
