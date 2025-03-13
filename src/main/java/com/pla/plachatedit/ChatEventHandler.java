package com.pla.plachatedit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.TextComponent;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = PlaChatEdit.MOD_ID, value = Dist.CLIENT)
public class ChatEventHandler {
    private static final Pattern blueDemonPattern = Pattern.compile("^(.+)蓝恶魔(.+)$");
    private static final Pattern pillagerKingPattern = Pattern.compile("^(.+)灾厄之王(.+)$");
    private static final Pattern villageScoutPattern = Pattern.compile("^(.+)村民侦查兵(.+)$");
    private static final Pattern blueVillageGuardPattern = Pattern.compile("^(.+)村民蓝骑兵(.+)$");

    private static final Logger LOGGER = LogUtils.getLogger();
    static class BreakException extends RuntimeException {}

    static void registerExactMessage(ClientChatReceivedEvent event, String originalText, String message, String translatedMessage) {
        if (originalText.contains(message)) {
            event.setMessage(new TextComponent(translatedMessage));
            throw new BreakException();
        }
    }

    static void registerStartClauseMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            event.setMessage(new TextComponent(startClause + translatedMessage));
            throw new BreakException();
        }
    }

    static void registerEndClauseMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String endClause = checkEntityName(matcher.group(1));
            event.setMessage(new TextComponent(translatedMessage + endClause));
            throw new BreakException();
        }
    }

    static void registerStartEndMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessage) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String endClause = checkEntityName(matcher.group(2));
            event.setMessage(new TextComponent(startClause + translatedMessage + endClause));
            throw new BreakException();
        }
    }

    static void registerStartMidMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessageFirst, String translatedMessageSecond) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String midClause = checkEntityName(matcher.group(2));
            event.setMessage(new TextComponent(startClause + translatedMessageFirst + midClause + translatedMessageSecond));
            throw new BreakException();
        }
    }

    static void registerStartMidEndMessage(ClientChatReceivedEvent event, String originalText, Pattern PATTERN, String translatedMessageFirst, String translatedMessageSecond, String translatedMessageThird) {
        Matcher matcher = PATTERN.matcher(originalText);
        if (matcher.matches()) {
            String startClause = checkEntityName(matcher.group(1));
            String midClause = checkEntityName(matcher.group(2));
            String endClause = checkEntityName(matcher.group(3));
            event.setMessage(new TextComponent(startClause + translatedMessageFirst + midClause + translatedMessageSecond + endClause + translatedMessageThird));
            throw new BreakException();
        }
    }

    static String checkEntityName(String plainMessage) {
        Matcher blueDemonMatcher = blueDemonPattern.matcher(plainMessage);
        if (blueDemonMatcher.matches()) {
            String startClause = blueDemonMatcher.group(1);
            String endClause = blueDemonMatcher.group(2);
            return startClause + "Blue Demon" + endClause;
        }

        Matcher pillagerKingMatcher = pillagerKingPattern.matcher(plainMessage);
        if (pillagerKingMatcher.matches()) {
            String startClause = pillagerKingMatcher.group(1);
            String endClause = pillagerKingMatcher.group(2);
            return startClause + "Pillager King" + endClause;
        }

        Matcher villageScout = villageScoutPattern.matcher(plainMessage);
        if (villageScout.matches()) {
            String startClause = villageScout.group(1);
            String endClause = villageScout.group(2);
            return startClause + "Village Scout" + endClause;
        }

        Matcher blueVillageGuard = blueVillageGuardPattern.matcher(plainMessage);
        if (villageScout.matches()) {
            String startClause = blueVillageGuard.group(1);
            String endClause = blueVillageGuard.group(2);
            return startClause + "Blue Village Guard" + endClause;
        }
        return plainMessage;
    }

    @SubscribeEvent
    public static void onChatMessage(ClientChatReceivedEvent event) {
        String originalText = event.getMessage().getString();

        try {
            registerExactMessage(event, originalText, "作者:@Pugilist_Steve (拳史)", "Author: @Pugilist_Steve");
            registerExactMessage(event, originalText, "平台:哔哩哔哩 (B站)", "Platform: Bilibili");
            registerExactMessage(event, originalText, "如要发布关于此整合包的视频，务必标注原作者及平台!", "If you post a video about this mod pack, you must credit the original author and platform!");
            registerExactMessage(event, originalText, "§4你失败了", "§4You died");
            registerExactMessage(event, originalText, "§6你未设置重生点，已被随机传送", "§6You didn’t set a respawn point and have been randomly teleported");
            registerExactMessage(event, originalText, "Herobrine已诞生新的附身体", "Herobrine has possessed a new body");
            registerExactMessage(event, originalText, "Herobrine第3号分身已降临", "Herobrine's No.3 clone has arrived");
            registerExactMessage(event, originalText, "<Steve> 我叫Steve，我是来摧毁Herobrine的", "<Steve> My name is Steve, I am here to destroy Herobrine");
            registerExactMessage(event, originalText, "<Steve> 我不敢相信我会在这里使用它", "<Steve> I can't believe I have to use it here");
            registerExactMessage(event, originalText, "<Steve> 不！", "<Steve> No!");
            registerExactMessage(event, originalText, "<Steve> 为什么我们要继续战斗下去?", "<Steve> Why do we have to keep fighting?");
            registerExactMessage(event, originalText, "<Steve> 我会杀了你！", "<Steve> I will kill you!");
            registerExactMessage(event, originalText, "<Alex> 可恶！", "<Alex> Damn it！");
            registerExactMessage(event, originalText, "<Alex> 呵，手下败将", "<Alex> Hah, just another defeated foe");
            registerExactMessage(event, originalText, "<蓝恶魔> 你也太好预测了", "<Blue Demon> You're too predictable");
            registerExactMessage(event, originalText, "<蓝恶魔> 真有意思，不过我很想知道你的动机是什么", "<Blue Demon> Interesting, but I really want to know what your motive is");
            registerExactMessage(event, originalText, "<蓝恶魔> 不要自大", "<Blue Demon> Don’t be arrogant");
            registerExactMessage(event, originalText, "<蓝恶魔> 一味地小看我们这些亡灵生物只会突出你到底是有多么的无知", "<Blue Demon> Underestimating us undead creatures so blindly only proves how ignorant you truly are");
            registerExactMessage(event, originalText, "<蓝恶魔> 三叉戟狂欢节！！！", "<Blue Demon> Trident Carnival!!!");
            registerExactMessage(event, originalText, "<蓝恶魔> 小看别人只会突出你有多么无知", "<Blue Demon> Underestimating others only highlights how ignorant you are");
            registerExactMessage(event, originalText, "<蓝恶魔> 终究顶不住这暴乱世界的压力……", "<Blue Demon> In the end, you couldn't withstand the pressure of this chaotic world...");
            registerExactMessage(event, originalText, "<灾厄之王> 作为王，可不会死的这么容易", "<Pillager King> As a king, I won’t die so easily");
            registerExactMessage(event, originalText, "<灾厄之王> 看来，王终究还是大意了……", "<Pillager King> It seems that the king was careless after all…");
            registerExactMessage(event, originalText, "<蓝恶魔> 你急了", "<Blue Demon> You're getting mad");
            registerExactMessage(event, originalText, "<Chris> 史蒂夫，对不起了", "<Chris> Steve, I'm sorry");
            registerExactMessage(event, originalText, "<Herobrine> 该我出手了……", "<Herobrine> It's my turn to strike...");
            registerExactMessage(event, originalText, "<X_Grave_X> 我们为生存而战，为保护自己而战！！", "<X_Grave_X> We fight for survival, we fight to protect ourselves!!");
            registerExactMessage(event, originalText, "<X_Grave_X> 走啊，走啊！", "<X_Grave_X> Go on, go on!");
            registerExactMessage(event, originalText, "<X_Grave_X> 绝望了吧?", "<X_Grave_X> Feeling desperate, huh?");
            registerExactMessage(event, originalText, "<X_Grave_X> 还能坚持多少个回合？", "<X_Grave_X> How many more rounds can you last?");
            registerExactMessage(event, originalText, "<村民蓝骑兵> 这是协议的一部分......", "<Blue Village Guard> This is part of the agreement...");
            registerExactMessage(event, originalText, "<村民侦查兵> Fire！", "<Village Scout> Fire!");
            registerExactMessage(event, originalText, "<村民侦察兵> 援军到了！", "<Village Scout> Reinforcements have arrived!");

            registerEndClauseMessage(event, originalText, Pattern.compile("^§?e?你击杀了(.+)$"), "§eYou killed ");
            registerEndClauseMessage(event, originalText, Pattern.compile("^§?a?你击杀了(.+)$"), "§aYou killed ");
            registerEndClauseMessage(event, originalText, Pattern.compile("^死亡坐标(.+)$"), "Death coordinates: ");

            registerStartMidMessage(event, originalText, Pattern.compile("^(.+)直接拿下(.+)，有什么好说的？$")," Just take down ", ", what's there to talk about?");

            registerStartMidEndMessage(event, originalText, Pattern.compile("^(.+)哈哈哈(.+)真好笑，一个(.+)躺在床上，嘴里有节奏地念着着盖伦～发发$")," Hahaha ", " so funny, ", " is lying on the bed, rhythmically chanting Garen~ Fa Fa.");

            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)加入了游戏$"), " has joined the game");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)死了$"), " died");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)退出了游戏$"), " has left the game");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)没实力$"), " So weak!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)好似$"), " Oh really?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)挡在我路上的都得死！$"), " Anyone in my way must die");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)你赢了$"), " You won!!!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)我们太弱了$"), " You are too weak");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)我会回去，并且变得更强！$"), " I will return and become stronger!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)我要将这个地方夷为平地$"), " I will flatten this place");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)记住我说的话！$"), " Remember my words!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)已降临$"), " has arrived");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)分身已被摧毁，数据已传入终端$"), " The clone has been destroyed, data has been transferred to the terminal.");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)号分身已被摧毁$"), " has been destroyed");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)玩不起，急了哈哈?$"), " Desperate now?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)还要再试一次吗？?$"), " Want to try again?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)玩不起，急了哈哈?$"), " Can't handle it? Getting mad? Haha.");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)就这？$"), " Is that all?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)我操你妈$"), ", f** you");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)你就这点实力吗？$"), ", is that all you've got?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)这个入是桂$"), " This guy is a boss");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)哈哈，急了$"), " Haha, you're getting mad");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)欢迎！$"), " Welcome!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)继续啊，怎么不继续了？$"), ", keep going, why did you stop?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)我还没认真呢$"), " I haven’t even gotten serious yet");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)唐完了$"), " It's over");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)你妈死了，我操你妈的$"), " , your mom is dead. F** your mom");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)够了！$"), " Enough!");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)被我按在地上打哈哈$"), " I pinned you to the ground and beat you up, haha");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)不是挺能装的吗老弟？$"), ", weren’t you acting all tough, bro?");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)人机操作$"), " plays like a bot");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)诗人握持$"), " Poet's grip");
            registerStartClauseMessage(event, originalText, Pattern.compile("^(.+)菜就多练，小老弟$"), ", if you're bad, just practice more, little bro");

            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)被(.+)杀死了$"), " was killed by ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)操你妈，敢不敢装备公平单挑？(.+)$"), " f*** your mom! Dare to fight fair with proper gear?");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)下次再会会你(.+)$"), ", I'll get you next time");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)难绷(.+)$"), " That was tough");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)真无语(.+)$"), " So speechless");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)没实力就别嚣张$"), ", Don't act tough if you're weak");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你死定了(.+)$"), ", you're dead for sure");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)号你等着(.+)$"), " You must wait!");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你把我就这样杀了，你高兴了是吧(.+)$"), ", you just killed me like that. Are you happy now?");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)用你那轮椅武器，很好玩是吗？(.+)$"), ", using your OP weapon—is that fun for you?");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)马上报复你(.+)$"), ", I will take revenge on you soon");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)崩溃了(.+)$"), " I'm breaking down");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)玩牛魔，装备全没了(.+)$"), " Played as Bull Demon, lost all my gear");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)兄弟，说实话，你是不是开了？(.+)$"), " bro, be honest, are you hacking? ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你是真的(.+)$"), ", you are really a ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)别走，我叫几个人(.+)$"), " Don’t leave, I’m calling some people ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)来了嗷，哥们(.+)$"), " I'm coming, bro ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你等着(.+)$"), " I'll get you later ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)傻逼操你妈(.+)$"), " Idiot, f** your mom");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你这样偷袭我一个普通玩家，这好吗？这不好(.+)$"), " , ambushing an ordinary player like me—is this okay? No, it's not ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)哥们我记住你了(.+)$"), ", bro, I’ll remember you ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)笑死我了纯(.+)一个$"), ", you’re killing me with laughter—such a pure ");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)我真服了(.+)$"), " I really give up");
            registerStartEndMessage(event, originalText, Pattern.compile("^(.+)你也就只会左键摁死了(.+)$"), " All you can do is spam left-click to death");
        } catch (BreakException ignored) {
        }

        LOGGER.info("PlaChatEdit: unidentified message: {}", new String(originalText.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
    }
}
