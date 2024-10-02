package org.lugi.upgrade

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.lugi.upgrade.command.GiveOrderForm
import org.lugi.upgrade.command.OpenInventory
import org.lugi.upgrade.event.UpgradeEvent

class Upgrade : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        getCommand("강화")?.setExecutor(OpenInventory())
        getCommand("마법부여주문서")?.setExecutor(GiveOrderForm())
        Bukkit.getServer().pluginManager.registerEvents(UpgradeEvent(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}