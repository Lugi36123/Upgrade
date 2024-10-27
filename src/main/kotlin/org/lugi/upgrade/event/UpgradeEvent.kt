package org.lugi.upgrade.event

import io.papermc.paper.registry.keys.EnchantmentKeys
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Registry
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.Arrays
import kotlin.random.Random
import kotlin.random.nextInt

class UpgradeEvent : Listener{
    private val notEnable = arrayOf(Enchantment.SHARPNESS
        ,Enchantment.EFFICIENCY
        , Enchantment.FORTUNE
        , Enchantment.UNBREAKING
        , Enchantment.PROTECTION
        , Enchantment.THORNS
        , Enchantment.FIRE_ASPECT
    )

    @EventHandler
    fun InventoryCloseEvent(e: InventoryCloseEvent){
        var p = e.player as Player
        if (e.view.title == "인첸트" && e.inventory.size == 9){
            if (e.inventory.getItem(4) != null) {
                var i = e.inventory.getItem(4) as ItemStack
                p.inventory.addItem(i)
            }else{
                return
            }
        }
    }

    @EventHandler
    fun InventoryClickEvent(e: InventoryClickEvent){
        var p = e.whoClicked as Player
        var i = e.inventory
        var raw = e.rawSlot

        if(e.view.title == "인첸트" && e.inventory.size == 9){
            if (raw in 0..8){
                if (raw == 4){
                    if (e.inventory.getItem(4) != null
                        && e.inventory.getItem(4)?.type != Material.AIR
                        && ( e.inventory.getItem(4)?.type.toString().endsWith("_SWORD")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_AXE")
                        || e.inventory.getItem(4)?.type.toString().endsWith("TRIDENT")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_PICKAXE")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_SHOVEL")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_HOE")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_CHESTPLATE")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_HELMET")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_LEGGINGS")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_TUNIC")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_CAP")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_PANTS")
                        || e.inventory.getItem(4)?.type.toString().endsWith("_BOOTS")
                        || e.inventory.getItem(4)?.type.toString().endsWith("MACE")
                        || e.inventory.getItem(4)?.type.toString().endsWith("BOW")
                        || e.inventory.getItem(4)?.type.toString().endsWith("CROSSBOW")
                        || e.inventory.getItem(4)?.type.toString().endsWith("SHIELD")
                                )

                    ) {
                        e.isCancelled = true
                        var jumun : ItemStack = ItemStack(Material.TURTLE_SCUTE)

                        for (item in p.inventory.contents) {
                            // 아이템이 null이 아니고, 종이(Material.PAPER)일 때
                            if (item != null
                                && item.itemMeta.hasCustomModelData() == true
                                && item.itemMeta.customModelData == 1205071
                                && item.type == Material.TURTLE_SCUTE
                            ){
                                val amount = item.amount

                                if (amount > 1) {
                                    // 종이 수를 1 줄임
                                    item.amount = amount - 1
                                } else {
                                    // 종이 수가 1이면 아이템을 제거
                                    p.inventory.remove(item)
                                }
                                // 첫 번째 종이를 찾으면 종료
                                p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F ,1F)

                                var stack : ItemStack = ItemStack(i.getItem(raw)?.type as Material)
                                var meta : ItemMeta = i.getItem(raw)?.itemMeta as ItemMeta
                                var num : Int
                                meta.removeEnchantments()
                                var ad = Random.nextInt(3)+1

                                for (isa: Int in 0..ad){
                                    var randa : Enchantment = Enchantment.values()[Random.nextInt(Enchantment.values().size)]
                                    if (notEnable.contains(randa) != true){
                                        if (randa.maxLevel > 1){
                                            num = Random.nextInt(4) + 1
                                        }else{
                                            num = 1
                                        }
                                        meta.addEnchant(randa , (num), true)
                                    }else{
                                        isa - 1
                                    }
                                }

                                stack.setItemMeta(meta)

                                i.setItem(4, stack)

                                return
                            }
                        }
                        p.sendMessage(Component.text("주문서가 부족합니다.")
                            .color(TextColor.color(255,0,0))
                            .decorate(TextDecoration.BOLD)
                            .decorate(TextDecoration.ITALIC))
                        p.stopSound(Sound.BLOCK_ANVIL_DESTROY)
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY,1F,3F)
                    }else if (e.inventory.getItem(4) != null){
                        e.isCancelled = true
                        p.sendMessage(Component.text("검, 갑옷, 도구에만 인첸트를 할수있습니다").color(TextColor.color(255,0,0)).hoverEvent(
                            HoverEvent.showText(Component.text("검, 갑옷, 도구"))))
                        p.stopSound(Sound.BLOCK_ANVIL_DESTROY)
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY,1F,3F)
                        var a = e.inventory.getItem(4) as ItemStack
                        p.inventory.addItem(a)
                        i.setItem(4, ItemStack(Material.AIR))
                    }else{
                        return
                    }
                }else{
                    e.isCancelled = true
                }
            }
        }
    }
    @EventHandler
    fun asdasd(e: InventoryOpenEvent){
        if (e.inventory.type.equals(InventoryType.ENCHANTING)){
            e.isCancelled = true
            var p = e.player as Player
            var inv : Inventory = Bukkit.createInventory(p, 9, "인첸트")

            for (i: Int in 0..3){
                var stack : ItemStack = ItemStack(Material.ARMADILLO_SCUTE)
                var meta : ItemMeta = stack.itemMeta

                meta.isHideTooltip = true
                meta.setCustomModelData(2)

                stack.itemMeta = meta

                stack.amount = 1

                inv.setItem(i, stack)
            }
            for (i: Int in 5..8){
                var stack : ItemStack = ItemStack(Material.ARMADILLO_SCUTE)
                var meta : ItemMeta = stack.itemMeta

                meta.isHideTooltip = true
                meta.setCustomModelData(2)

                stack.itemMeta = meta

                stack.amount = 1

                inv.setItem(i, stack)
            }

            p.openInventory(inv)
        }
    }
}