package org.lugi.upgrade.command

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class OpenInventory : CommandExecutor{
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        var p = p0 as Player
        var inv : Inventory = Bukkit.createInventory(p, 9, "강화!")

        for (i: Int in 0..3){
            var stack : ItemStack = ItemStack(Material.PAPER)
            var meta : ItemMeta = stack.itemMeta

            meta.isHideTooltip = true
            meta.setCustomModelData(2)

            stack.itemMeta = meta

            stack.amount = 1

            inv.setItem(i, stack)
        }
        for (i: Int in 5..8){
            var stack : ItemStack = ItemStack(Material.PAPER)
            var meta : ItemMeta = stack.itemMeta

            meta.isHideTooltip = true
            meta.setCustomModelData(2)

            stack.itemMeta = meta

            stack.amount = 1

            inv.setItem(i, stack)
        }

        p.openInventory(inv)

        return true
    }
}
