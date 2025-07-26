package com.pla.plachatedit;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = PlaChatEdit.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntitySpawnHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    static boolean registerEntity(Entity entity, String entityId, String translatedName) {
        ResourceLocation registryName = entity.getType().getRegistryName();
        if (registryName != null && registryName.toString().equals(entityId)) {
            entity.setCustomName(new TextComponent(translatedName));
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        Level level = event.getWorld();
        if (level.isClientSide) return;

        Entity entity = event.getEntity();
        ResourceLocation registryName = entity.getType().getRegistryName();
        if (registryName == null) return;

        String namespace = registryName.getNamespace();
        if (!namespace.equals("annoying_villagers") && !namespace.equals("annoying_villagersbychentu")) return;

        if (entity.getCustomName() == null) return;

        if (registerEntity(entity, "annoying_villagers:blue_demon", "Blue Demon")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:shiti_303fenshen", "Entity §43 0 3 §fclone")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:shi_ti_303", "Entity §43 0 3")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:hb_gao_ji_fen_shen", "Diamond Armor §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:cczdz", "Village Scout Captain")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:hongyizombie", "Red-Clothed zombie")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:zai_e_zhi_wang", "Illager King")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:zaiezhiwang_2", "Illager King phase II")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:bei_gan_ran_jian_bing_guo_zi", "Infected Pancake Fruit")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:cun_min_zhen_cha_bing", "Villager Scout")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:herobrine_2", "§5Herobrine§f Clone 2")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:herobrine_3", "§5Herobrine§f")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:anyinghimfenshen", "§5Herobrine§f Shadow Clone")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:dark_herobrine", "Dark §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:gaojiherobrine", "Advanced §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:lianyingfensheng", "Scythe Shadow §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:sherenherobrine", "Snake Blade §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:ying_chui_herobrine", "Shadow Hammer §5Herobrine")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:lan_cun_qi", "Blue Village Generals")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:lu_cun_qi", "Green Village Generals")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:hong_cun_qi", "Red Village Generals")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:zi_cun_qi", "Purple Village Generals")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:cun_zhen_fu_lu", "Village Scout Prisoner")) return;
        if (registerEntity(entity, "annoying_villagers:blue_demon_2", "Blue Demon")) return;
        if (registerEntity(entity, "annoying_villagers:blue_demon_r", "Blue Demon")) return;
        if (registerEntity(entity, "annoying_villagers:blue_demon_end", "Blue Demon")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:heiyaoshitiekuilei", "Obsidian golem")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:hong_cun_qi_fu_lu", "Red Village Generals Prisoner")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:jianbingguozi", "Pancake Fruit")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:lan_cun_qi_fu_lu", "Blue Village Generals Prisoner")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:lvcunqifulu", "Green Village Generals Prisoner")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:zi_cun_qi_fu_lu", "Purple Village Generals Prisoner")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:di_jiherobrine", "Herobrine Low-level Clone")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:dijiyinfenshen", "Herobrine Low-level Shadow Clone")) return;
        if (registerEntity(entity, "annoying_villagersbychentu:herobrinefenshen", "Herobrine Clone")) return;

//        String originalName = entity.getCustomName() != null
//                ? entity.getCustomName().getString()
//                : "null";
//
//        LOGGER.info("PlaChatEdit: Found default entit's name from AV mod: {}, name: {}", registryName, originalName);
    }
}
