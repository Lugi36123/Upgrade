package org.lugi.upgrade.command

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.Arrays

class GiveOrderForm : CommandExecutor, TabExecutor{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): Boolean {
        var p = sender as Player

        var stack : ItemStack = ItemStack(Material.PAPER)
        var meta : ItemMeta = stack.itemMeta

        meta.setCustomModelData(1)
        meta.setEnchantmentGlintOverride(true)
        meta.setDisplayName("§5§o마법 부여 주문서")
        meta.lore = Arrays.asList("§5엄청난 마법부여 주문서이다!")

        stack.itemMeta = meta

        if (args?.size == 1){
            if (args.contains("한세트")){
                stack.amount = 64
            }
        }

        p.inventory.addItem(stack)
        
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): List<String?>? {
        if (args?.size == 1){
            return Arrays.asList("한세트")
        }

        return ArrayList()
    }
}