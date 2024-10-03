package org.lugi.upgrade.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.Arrays

class GiveOrderForm : CommandExecutor{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): Boolean {
        var p = sender as Player

        var stack : ItemStack = ItemStack(Material.TURTLE_SCUTE)
        var meta : ItemMeta = stack.itemMeta

        meta.setCustomModelData(1205071)
        meta.setDisplayName("§5§o마법 부여 주문서")
        meta.lore = Arrays.asList("§5대장간에서 사용해보세요!")

        stack.itemMeta = meta

        if (args?.size == 1){
            if (args.get(0).toInt() >= 0){
                if (args.get(0).toInt() <= 2147483647){
                    stack.amount = args.get(0).toInt()

                    p.sendMessage("§r" + p.name + "에게 [" + "§5§o마법 부여 주문서" + "§r]을(를) " + args[0] + "개 지급했습니다")

                    p.inventory.addItem(stack)

                    return true
                }else{
                    p.sendMessage(Component.text("아이템의 수는 2147483647를 넘으면 안됩니다")
                        .color(TextColor.color(255,0,0)))
                    return true
                }
            }else{
                p.sendMessage(Component.text("음수는 입력 할수없습니다.").color(TextColor.color(255,0,0)))
                p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1F, 0.5F)
                return true
            }
        }
        stack.amount = 1

        p.world.getEntitiesByClasses(Player::class.java).forEach { players ->
            players.sendMessage("§r" + p.name + "에게 [" + "§5§o마법 부여 주문서" + "§r]을(를) " + "1개 지급했습니다")
        }

        p.inventory.addItem(stack)

        return true
    }
}