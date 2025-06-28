package com.pla.plachatedit;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;

@Mod.EventBusSubscriber(modid = PlaChatEdit.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TooltipOverride {
    static boolean registerTooltip(ItemStack stack, List<Component> tooltip, String itemId, String translatedTooltip) {
        if (stack.getItem().getRegistryName().toString().equals(itemId)) {
            if (tooltip.size() > 1) {
                tooltip.subList(1, tooltip.size()).clear();
            }
            tooltip.add(new TextComponent(translatedTooltip));
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
//        This is template
//        if(registerTooltip(event.getItemStack(), event.getToolTip(), "item_id", "translated tooltip")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagers:bomb_spawn_item", "Sneak and right-click to place. This trap will trigger and plant a C4 bomb inside the house.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagers:enchant_bed_item", "Right-click to enchant the bed and save a respawn point. On death, you won't drop your items. Each use requires reapplying by right-clicking again. Conflicts with Totem of Undying.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagers:time_bomb", "Timed C4 explosive, sneak and right-click to place.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagers:drop_all", "Sneak and right-click to place. This trap will trigger and drop all items from your inventory.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuangrave", "Right-click to summon Grave")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:villagerhead", "Grave's unique villager headger, right-click to wear")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:villagerhead_4", "Right-click to wear")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:villagerhead_3", "Right-click to wear")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:villagerhead_2", "Right-click to wear")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuanhim_7fenshen", "Right-click to summon 7th Herobrine Clone")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:lan_cun_qi_fu_mo_jian", "§3A special weapon. After hitting the enemy, right-click in sprint mode to release skills.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:herobrine_eyes", "Right-click to wear")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:heisezhenzhu", "A faster variant of the ender pearl.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuanhimfenshen", "Right-click to summon Herobrine Clone.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:join_herobrine_item", "Right-click to become a follower of Herobrine.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zzuanshijiandao", "Right-click to clear all buffs on creatures within 4 grids.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:fumomoyingzhenzhu", "This is an ender pearl with the ignore Fall Damage enchantment.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:enchaned_diamond", "One of the best quality diamonds.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:cchunduzuanshi", "Compressed diamonds are of the best quality and hardest")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuanlueduozhewang", "Right-click to summon the Illager King.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuanhim_3haofenshen", "Right-click to summon Herobrine No.3 Clone.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:she_ren", "§5One of Herobrine's legendary weapons, right-click in sprint mode to release.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:ffengyuansuxiyangjian", "§5The power of wind elements fills the sword.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:join_grave_item", "Right-click on Grave to join him.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:faguangdezhenzhu", "A shorter cooldown variant of the ender pearl.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:fumomumen", "Very unique wooden door, novices all say it is good.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:hhuoyuansuhejinwandao", "§5The power of mutant elements fills the sword.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuanhimzuanjiafenshen", "Right-click to summon Diamond Armor Herobrine.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:fulubuhuoqi", "Throwable handcuffs.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:sifangsheren", "§5One of Herobrine's legendary weapons, release form.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zhaohuansteve", "Right-click to summon Steve.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:mumen", "Very unique wooden door, novices all say it is good.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagers:villager_head", "A tool for disguising villagers. Right-click to wear it. Crouch + right-click to switch between camouflage and attack mode")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:join_player_item", "Right-click on player team.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:anyingheiyaoshi", "Shadow obsidian fired by Shadow's clone")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:baozhaxiaodao", "Right-click can detonate TNT")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:bbianzhonghuoyuansuhejinwandao", "§5The power of mutant elements fills the sword.")) return;
        if(registerTooltip(event.getItemStack(), event.getToolTip(), "annoying_villagersbychentu:zuan_shi_ci_yin_jian", "A special diamon weapon, squat and right-click to release skills.")) return;
    }


}
