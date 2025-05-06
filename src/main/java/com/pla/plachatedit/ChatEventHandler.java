package com.pla.plachatedit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.TextComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = PlaChatEdit.MOD_ID, value = Dist.CLIENT)
public class ChatEventHandler {
    private static final Pattern blueDemonPattern = Pattern.compile("^(.*)蓝恶魔(.*)$");
    private static final Pattern pillagerKingPattern = Pattern.compile("^(.*)灾厄之王(.*)$");
    private static final Pattern villagerScoutPattern = Pattern.compile("^(.*)村民侦查兵(.*)$");
    private static final Pattern villagerArcherPattern = Pattern.compile("^(.*)村民侦察兵(.*)$");
    private static final Pattern blueVillageGuardPattern = Pattern.compile("^(.*)村民蓝骑兵(.*)$");
    private static final Pattern purpleVillageGuardPattern = Pattern.compile("^(.*)村民紫骑兵(.*)$");
    private static final Pattern redVillageGuardPattern = Pattern.compile("^(.*)村民红骑兵(.*)$");
    private static final Pattern entity303Pattern = Pattern.compile("^(.*)§f实体§43 0 3(.*)$");
    private static final Pattern villagerSoldierPattern = Pattern.compile("^(.*)村民綠騎兵(.*)$");
    private static final Pattern villagerRiderPattern = Pattern.compile("^(.*)村民绿骑兵(.*)$");

    private static final Logger LOGGER = LogUtils.getLogger();

    static boolean checkExactMessage(ClientChatReceivedEvent event, String originalText, String message, String translatedMessage) {
        if (originalText.contains(message)) {
            event.setMessage(new TextComponent(translatedMessage));
            return true;
        }
        return false;
    }

    static boolean checkStartClauseMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            event.setMessage(new TextComponent(startClause + translatedMessage));
            return true;
        }
        return false;
    }

    static boolean checkHerobrinePossessedMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String name = checkEntityName(matcher.group(1));
            event.setMessage(new TextComponent( "System Message: " + name + " has been possessed by Herobrine."));
            return true;
        }
        return false;
    }

    static boolean checkEndClauseMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String endClause = checkEntityName(matcher.group(1));
            event.setMessage(new TextComponent(translatedMessage + endClause));
            return true;
        }
        return false;
    }

    static boolean checkStartEndMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String endClause = checkEntityName(matcher.group(2));
            event.setMessage(new TextComponent(startClause + translatedMessage + endClause));
            return true;
        }
        return false;
    }

    static boolean checkStartMidMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessageFirst, String translatedMessageSecond) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String midClause = checkEntityName(matcher.group(2));
            event.setMessage(new TextComponent(startClause + translatedMessageFirst + midClause + translatedMessageSecond));
            return true;
        }
        return false;
    }

    static boolean checkStartMidEndMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessageFirst, String translatedMessageSecond, String translatedMessageThird) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String midClause = checkEntityName(matcher.group(2));
            String endClause = checkEntityName(matcher.group(3));
            event.setMessage(new TextComponent(startClause + translatedMessageFirst + midClause + translatedMessageSecond + endClause + translatedMessageThird));
            return true;
        }
        return false;
    }

    static String checkEntityName(String plainMessage) {
        Matcher blueDemon = blueDemonPattern.matcher(plainMessage);
        if (blueDemon.matches()) {
            String startClause = blueDemon.group(1);
            String endClause = blueDemon.group(2);
            return startClause + "Blue Demon" + endClause;
        }

        Matcher pillagerKing = pillagerKingPattern.matcher(plainMessage);
        if (pillagerKing.matches()) {
            String startClause = pillagerKing.group(1);
            String endClause = pillagerKing.group(2);
            return startClause + "Illager King" + endClause;
        }

        Matcher villagerScout = villagerScoutPattern.matcher(plainMessage);
        if (villagerScout.matches()) {
            String startClause = villagerScout.group(1);
            String endClause = villagerScout.group(2);
            return startClause + "Villager Archer" + endClause;
        }

        Matcher villagerArcher = villagerArcherPattern.matcher(plainMessage);
        if (villagerArcher.matches()) {
            String startClause = villagerArcher.group(1);
            String endClause = villagerArcher.group(2);
            return startClause + "Villager Archer" + endClause;
        }

        Matcher villagerRider = villagerRiderPattern.matcher(plainMessage);
        if (villagerRider.matches()) {
            String startClause = villagerRider.group(1);
            String endClause = villagerRider.group(2);
            return startClause + "Villager Rider" + endClause;
        }

        Matcher blueVillageGuard = blueVillageGuardPattern.matcher(plainMessage);
        if (blueVillageGuard.matches()) {
            String startClause = blueVillageGuard.group(1);
            String endClause = blueVillageGuard.group(2);
            return startClause + "Blue Villager Knight" + endClause;
        }

        Matcher purpleVillageGuard = purpleVillageGuardPattern.matcher(plainMessage);
        if (purpleVillageGuard.matches()) {
            String startClause = purpleVillageGuard.group(1);
            String endClause = purpleVillageGuard.group(2);
            return startClause + "Purple Villager Knight" + endClause;
        }

        Matcher redVillageGuard = redVillageGuardPattern.matcher(plainMessage);
        if (redVillageGuard.matches()) {
            String startClause = redVillageGuard.group(1);
            String endClause = redVillageGuard.group(2);
            return startClause + "Red Villager Knight" + endClause;
        }

        Matcher entity303 = entity303Pattern.matcher(plainMessage);
        if (entity303.matches()) {
            String startClause = entity303.group(1);
            String endClause = entity303.group(2);
            return startClause + "§fEntity §43 0 3" + endClause;
        }

        Matcher villagerSoldier = villagerSoldierPattern.matcher(plainMessage);
        if (villagerSoldier.matches()) {
            String startClause = villagerSoldier.group(1);
            String endClause = villagerSoldier.group(2);
            return startClause + "Villager Soldier" + endClause;
        }
        return plainMessage;
    }

    @SubscribeEvent
    public static void onChatMessage(ClientChatReceivedEvent event) {
        String originalText = event.getMessage().getString();

        if(checkExactMessage(event, originalText, "灾厄之王已诞生", "The Illager King has been born")) return;
        if(checkExactMessage(event, originalText, "作者:@Pugilist_Steve (拳史)", "Author: @Pugilist_Steve")) return;
        if(checkExactMessage(event, originalText, "平台:哔哩哔哩 (B站)", "Platform: Bilibili")) return;
        if(checkExactMessage(event, originalText, "如要发布关于此整合包的视频，务必标注原作者及平台!", "If you post a video about this mod pack, you must credit the original author and platform!")) return;
        if(checkExactMessage(event, originalText, "§4你失败了", "§4You died")) return;
        if(checkExactMessage(event, originalText, "§c你失败了！", "§eYou have saved your respawn point")) return;
        if(checkExactMessage(event, originalText, "你被通缉了！", "You are now wanted!")) return;
        if(checkExactMessage(event, originalText, "§e你保存了重生点，如果你死了，请再次右键床来保存重生点，否则将随机传送", "§4Saving checkpoint...")) return;
        if(checkExactMessage(event, originalText, "§6你未设置重生点，已被随机传送", "§6You are too weak !!!")) return;
        if(checkExactMessage(event, originalText, "Herobrine已诞生新的附身体", "Herobrine has possessed a new body")) return;
        if(checkExactMessage(event, originalText, "Herobrine第3号分身已降临", "Herobrine's No.3 clone has arrived")) return;
        if(checkExactMessage(event, originalText, "<Steve> 我叫Steve，我是来摧毁Herobrine的", "<Steve> My name is Steve, I am here to destroy Herobrine")) return;
        if(checkExactMessage(event, originalText, "<Steve> 我不敢相信我会在这里使用它", "<Steve> I can't believe I have to use it here")) return;
        if(checkExactMessage(event, originalText, "<Steve> 不！", "<Steve> No!")) return;
        if(checkExactMessage(event, originalText, "<Steve> 为什么我们要继续战斗下去?", "<Steve> Why do we have to keep fighting?")) return;
        if(checkExactMessage(event, originalText, "<Steve> 我会杀了你！", "<Steve> I will kill you!")) return;
        if(checkExactMessage(event, originalText, "<Alex> 可恶！", "<Alex> Damn it！")) return;
        if(checkExactMessage(event, originalText, "<Alex> 呵，手下败将", "<Alex> Hah, just another defeated foe")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 你也太好预测了", "<Blue Demon> You're too predictable")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 真有意思，不过我很想知道你的动机是什么", "<Blue Demon> Interesting, but I really want to know what your motive is")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 不要自大", "<Blue Demon> Don’t be arrogant")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 一味地小看我们这些亡灵生物只会突出你到底是有多么的无知", "<Blue Demon> Underestimating us undead creatures so blindly only proves how ignorant you truly are")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 三叉戟狂欢节！！！", "<Blue Demon> Trident Carnival!!!")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 小看别人只会突出你有多么无知", "<Blue Demon> Underestimating others only highlights how ignorant you are")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 终究顶不住这暴乱世界的压力……", "<Blue Demon> In the end, you couldn't withstand the pressure of this chaotic world...")) return;
        if(checkExactMessage(event, originalText, "<灾厄之王> 作为王，可不会死的这么容易", "<Pillager King> As a king, I won’t die so easily")) return;
        if(checkExactMessage(event, originalText, "<灾厄之王> 看来，王终究还是大意了……", "<Pillager King> It seems that the king was careless after all…")) return;
        if(checkExactMessage(event, originalText, "<蓝恶魔> 你急了", "<Blue Demon> You're getting mad")) return;
        if(checkExactMessage(event, originalText, "<Chris> 史蒂夫，对不起了", "<Chris> Steve, I'm sorry")) return;
        if(checkExactMessage(event, originalText, "<Herobrine> 该我出手了……", "<Herobrine> It's my turn to strike...")) return;
        if(checkExactMessage(event, originalText, "<X_Grave_X> 我们为生存而战，为保护自己而战！！", "<X_Grave_X> We fight for survival, we fight to protect ourselves!!")) return;
        if(checkExactMessage(event, originalText, "<X_Grave_X> 走啊，走啊！", "<X_Grave_X> Go on, go on!")) return;
        if(checkExactMessage(event, originalText, "<X_Grave_X> 绝望了吧?", "<X_Grave_X> Feeling desperate, huh?")) return;
        if(checkExactMessage(event, originalText, "<X_Grave_X> 还能坚持多少个回合？", "<X_Grave_X> How many more rounds can you last?")) return;
        if(checkExactMessage(event, originalText, "<村民蓝骑兵> 这是协议的一部分......", "<Blue Village Knight> This is part of the agreement...")) return;
        if(checkExactMessage(event, originalText, "<村民蓝骑兵> 请求支援！", "<Blue Village Knight> Requesting support!")) return;
        if(checkExactMessage(event, originalText, "<村民紫骑兵> 我们遇到了极强的玩家，请求支援！", "<Purple Village Knight> We have encountered an extremely strong player, requesting support!")) return;
        if(checkExactMessage(event, originalText, "<村民红骑兵> 誓死效力于村民国王...... ", "<Red Village Knight> Sworn to serve the Village King until death...")) return;
        if(checkExactMessage(event, originalText, "<村民侦查兵> Fire！", "<Village Archer> Fire!")) return;
        if(checkExactMessage(event, originalText, "<村民侦查兵> 请求支援！", "<Village Archer> Requesting reinforcements!")) return;
        if(checkExactMessage(event, originalText, "<村民侦察兵> 援军到了！", "<Village Archer> Reinforcements have arrived!")) return;
        if(checkExactMessage(event, originalText, "<村民侦察兵> What the matter?", "<Village Archer> What the matter?")) return;
        if(checkExactMessage(event, originalText, "<村民> Help !", "<Villager> Help!")) return;
        if(checkExactMessage(event, originalText, "<村民> Help me!", "<Villager> Help me!")) return;
        if(checkExactMessage(event, originalText, "在聊天栏里输入/leave @s可解脱", "Type /leave @s in the chat to break free.")) return;
        if(checkExactMessage(event, originalText, "附身失败，你因实体错误而被游戏删除了", "Possession failed. You were removed from the game due to an entity error.")) return;

        if(checkHerobrinePossessedMessage(event, originalText, Pattern.compile("^系统提示：(.+)已被Herobrine附身$"), "")) return;

        if(checkEndClauseMessage(event, originalText, Pattern.compile("^§?e?你击杀了(.+)$"), "§eYou killed ")) return;
        if(checkEndClauseMessage(event, originalText, Pattern.compile("^§?a?你击杀了(.+)$"), "§aYou killed ")) return;
        if(checkEndClauseMessage(event, originalText, Pattern.compile("^死亡坐标(.+)$"), "Death coordinates: ")) return;
        if(checkEndClauseMessage(event, originalText, Pattern.compile("^已将你的重生点设置到(.+)$"), "Your respawn point has been set to: ")) return;
        if(checkEndClauseMessage(event, originalText, Pattern.compile("^已将你传送至重生点(.+)$"), "You have been teleported to the respawn point: ")) return;

        if(checkStartMidMessage(event, originalText, Pattern.compile("^(.+)直接拿下(.+)，有什么好说的？$")," Just take down ", ", what's there to talk about?")) return;

        if(checkStartMidEndMessage(event, originalText, Pattern.compile("^(.+)哈哈哈(.+)真好笑，一个(.+)躺在床上，嘴里有节奏地念着着盖伦～发发$")," Hahaha ", " so funny, ", " is lying on the bed, rhythmically chanting Garen~ Fa Fa.")) return;

        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)加入了游戏$"), " has joined the game")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)退出了游戏$"), " has left the game")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)没实力$"), " So weak!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)好似$"), " Oh really?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)挡在我路上的都得死！$"), " Anyone in my way must die")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)你赢了$"), " You won!!!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)我们太弱了$"), " You are too weak")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)我会回去，并且变得更强！$"), " I will return and become stronger!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)我要将这个地方夷为平地$"), " I will flatten this place")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)记住我说的话！$"), " Remember my words!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)已降临$"), " has arrived")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)分身已被摧毁，数据已传入终端$"), " The clone has been destroyed, data has been transferred to the terminal.")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)号分身已被摧毁$"), " has been destroyed")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)玩不起，急了哈哈?$"), " Desperate now?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)还要再试一次吗？?$"), " Want to try again?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)玩不起，急了哈哈?$"), " Can't handle it? Getting mad? Haha.")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)就这？$"), " Is that all?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)我操你妈$"), ", f** you")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)你就这点实力吗？$"), ", is that all you've got?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)这个入是桂$"), " This guy is a boss")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)哈哈，急了$"), " Haha, you're getting mad")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)欢迎！$"), " Welcome!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)继续啊，怎么不继续了？$"), ", keep going, why did you stop?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)我还没认真呢$"), " I haven’t even gotten serious yet")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)唐完了$"), " It's over")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)你妈死了，我操你妈的$"), " , your mom is dead. F** your mom")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)够了！$"), " Enough!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)被我按在地上打哈哈$"), " I pinned you to the ground and beat you up, haha")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)不是挺能装的吗老弟？$"), ", weren’t you acting all tough, bro?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)人机操作$"), " plays like a bot")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)诗人握持$"), " Poet's grip")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)菜就多练，小老弟$"), ", if you're bad, just practice more, little bro")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)哎你怎么似了？$"), " Hey, what's wrong with you?")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)多么愚蠢！$"), " How foolish!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)你打的是牛魔$"), ", you are fighting the Bull Demon!")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)What the matter?$"), "What the matter?")) return;

        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)被(.+)杀死了$"), " was killed by ")) return;
        if(checkStartClauseMessage(event, originalText, Pattern.compile("^(.+)死了$"), " died")) return;

        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你也是个神人了$"), ", you're a genius too!")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)操你妈，敢不敢装备公平单挑？(.+)$"), " f*** your mom! Dare to fight fair with proper gear?")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)下次再会会你(.+)$"), ", I'll get you next time")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)难绷(.+)$"), " That was tough")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)真无语(.+)$"), " So speechless")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)没实力就别嚣张$"), ", Don't act tough if you're weak")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你死定了(.*)$"), ", you're dead for sure")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)号你等着(.+)$"), " You must wait!")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你把我就这样杀了，你高兴了是吧(.+)$"), ", you just killed me like that. Are you happy now?")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)用你那轮椅武器，很好玩是吗？(.+)$"), ", using your OP weapon—is that fun for you?")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)马上报复你(.+)$"), ", I will take revenge on you soon")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)崩溃了(.+)$"), " I'm breaking down")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)玩牛魔，装备全没了(.+)$"), " Played as Bull Demon, lost all my gear")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)兄弟，说实话，你是不是开了？(.+)$"), " bro, be honest, are you hacking? ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你是真的(.+)$"), ", you are really a ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)别走，我叫几个人(.+)$"), " Don’t leave, I’m calling some people ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)来了嗷，哥们(.+)$"), " I'm coming, bro ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你等着(.+)$"), " I'll get you later ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)傻逼操你妈(.+)$"), " Idiot, f** your mom")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你这样偷袭我一个普通玩家，这好吗？这不好(.+)$"), " , ambushing an ordinary player like me—is this okay? No, it's not ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)哥们我记住你了(.+)$"), ", bro, I’ll remember you ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)笑死我了纯(.+)一个$"), ", you’re killing me with laughter—such a pure ")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)我真服了(.+)$"), " I really give up")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)你也就只会左键摁死了(.+)$"), " All you can do is spam left-click to death")) return;
        if(checkStartEndMessage(event, originalText, Pattern.compile("^(.+)这么菜？不会是原神玩多了导致的吧？(.+)$"), " So bad? Could it be that you've played too much Genshin Impact?")) return;

        LOGGER.info("PlaChatEdit: unidentified message: {}", originalText);
    }
}
