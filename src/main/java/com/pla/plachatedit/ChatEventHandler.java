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
    private static final Pattern JOIN_PATTERN = Pattern.compile("^(.+)加入了游戏$");
    private static final Pattern KILL_PATTERN_1 = Pattern.compile("^§?e?你击杀了(.+)$");
    private static final Pattern KILL_PATTERN_2 = Pattern.compile("^§?a?你击杀了(.+)$");
    private static final Pattern DEATH_PATTERN = Pattern.compile("^(.+)被(.+)杀死了$");
    private static final Pattern DIED_PATTERN = Pattern.compile("^(.+)死了$");
    private static final Pattern INSULT_PATTERN = Pattern.compile("^(.+)操你妈，敢不敢装备公平单挑？(.+)$");
    private static final Pattern LEFT_PATTERN = Pattern.compile("^(.+)退出了游戏$");
    private static final Pattern REVENGE_PATTERN = Pattern.compile("^(.+)下次再会会你(.+)$");
    private static final Pattern TOUGH_PATTERN = Pattern.compile("^(.+)难绷(.+)$");
    private static final Pattern SPEECHLESS_PATTERN = Pattern.compile("^(.+)真无语(.+)$");
    private static final Pattern SO_WEAK_PATTERN = Pattern.compile("^(.+)没实力$");
    private static final Pattern WEAKNESS_TAUNT_PATTERN = Pattern.compile("^(.+)没实力就别嚣张$");
    private static final Pattern REALLY_PATTERN = Pattern.compile("^(.+)好似$");
    private static final Pattern DEATH_THREAT_PATTERN = Pattern.compile("^(.+)挡在我路上的都得死！$");
    private static final Pattern GIVEUP_PATTERN = Pattern.compile("^(.+)你赢了$");
    private static final Pattern WEAK_PATTERN = Pattern.compile("^(.+)我们太弱了$");
    private static final Pattern DOOM_PATTERN = Pattern.compile("^(.+)你死定了！$");
    private static final Pattern DOOM_PATTERN_2 = Pattern.compile("^(.+)你死定了$");
    private static final Pattern REVENGE_WAIT_PATTERN = Pattern.compile("^(.+)号你等着(.+)$");
    private static final Pattern REVENGE_RETURN_PATTERN = Pattern.compile("^(.+)我会回去，并且变得更强！$");
    private static final Pattern DESTRUCTION_PATTERN  = Pattern.compile("^(.+)我要将这个地方夷为平地$");
    private static final Pattern REMEMBER_MY_WORDS_PATTERN = Pattern.compile("^(.+)记住我说的话！$");
    private static final Pattern ARRIVED_BOSS_PATTERN = Pattern.compile("^(.+)已降临$");
    private static final Pattern CLONE_DEAD_PATTERN = Pattern.compile("^(.+)分身已被摧毁，数据已传入终端$");
    private static final Pattern DESTROYED_PATTERN = Pattern.compile("^(.+)号分身已被摧毁$");
    private static final Pattern KILLED_HAPPY_PATTERN = Pattern.compile("^(.+)你把我就这样杀了，你高兴了是吧(.+)$");
    private static final Pattern KILLED_OP_WEAPON_PATTERN = Pattern.compile("^(.+)用你那轮椅武器，很好玩是吗？(.+)$");
    private static final Pattern DESPERATE_PATTERN = Pattern.compile("^(.+)玩不起，急了哈哈?$");
    private static final Pattern TRY_AGAIN_PATTERN = Pattern.compile("^(.+)还要再试一次吗？?$");
    private static final Pattern KIDDING_PATTERN = Pattern.compile("^(.+)玩不起，急了哈哈?$");
    private static final Pattern IS_THAT_ALL_PATTERN = Pattern.compile("^(.+)就这？$");
    private static final Pattern FCK_PATTERN = Pattern.compile("^(.+)我操你妈$");
    private static final Pattern TAKE_REVENGE_PATTERN = Pattern.compile("^(.+)马上报复你(.+)$");
    private static final Pattern ALL_THAT_GOT_PATTERN = Pattern.compile("^(.+)你就这点实力吗？$");
    private static final Pattern BREAKING_DOWN_PATTERN = Pattern.compile("^(.+)崩溃了(.+)$");
    private static final Pattern THIS_IS_BOSS_PATTERN = Pattern.compile("^(.+)这个入是桂$");
    private static final Pattern MAD_PATTERN = Pattern.compile("^(.+)哈哈，急了$");
    private static final Pattern WELCOME_PATTERN = Pattern.compile("^(.+)欢迎！$");
    private static final Pattern LOST_GEAR_PATTERN = Pattern.compile("^(.+)玩牛魔，装备全没了(.+)$");
    private static final Pattern ARE_U_HACKING_PATTERN = Pattern.compile("^(.+)兄弟，说实话，你是不是开了？(.+)$");
    private static final Pattern TAKEDOWN_PATTERN = Pattern.compile("^(.+)直接拿下(.+)，有什么好说的？$");
    private static final Pattern DOG_PATTERN = Pattern.compile("^(.+)你是真的(.+)$");
    private static final Pattern WHY_STOP_PATTERN = Pattern.compile("^(.+)继续啊，怎么不继续了？$");
    private static final Pattern NOT_SERIOUS_PATTERN = Pattern.compile("^(.+)我还没认真呢$");
    private static final Pattern DONT_LEAVE_PATTERN = Pattern.compile("^(.+)别走，我叫几个人(.+)$");
    private static final Pattern HAHA_PATTERN = Pattern.compile("^(.+)哈哈哈(.+)真好笑，一个(.+)躺在床上，嘴里有节奏地念着着盖伦～发发$");
    private static final Pattern GET_LATTER_PATTERN = Pattern.compile("^(.+)你等着(.+)$");
    private static final Pattern COMING_PATTERN = Pattern.compile("^(.+)来了嗷，哥们(.+)$");
    private static final Pattern OVER_PATTERN = Pattern.compile("^(.+)唐完了$");
    private static final Pattern MOM_PATTERN = Pattern.compile("^(.+)你妈死了，我操你妈的$");
    private static final Pattern DEAD_SURE_PATTERN = Pattern.compile("^(.+)你死定了$");
    private static final Pattern IDIOT_PATTERN = Pattern.compile("^(.+)傻逼操你妈(.+)$");
    private static final Pattern ENOUGH_PATTERN = Pattern.compile("^(.+)够了！$");
    private static final Pattern PIN_GROUND_PATTERN = Pattern.compile("^(.+)被我按在地上打哈哈$");
    private static final Pattern AMBUSHING_PATTERN = Pattern.compile("^(.+)你这样偷袭我一个普通玩家，这好吗？这不好(.+)$");
    private static final Pattern REMEMBER_PATTERN = Pattern.compile("^(.+)哥们我记住你了(.+)$");
    private static final Pattern KILL_LAUGH_PATTERN = Pattern.compile("^(.+)笑死我了纯(.+)一个$");
    private static final Pattern REALLY_GIVEUP_PATTERN = Pattern.compile("^(.+)我真服了(.+)$");
    private static final Pattern DEATH_POINT = Pattern.compile("^死亡坐标(.+)$");
    private static final Pattern TOUGH_QUESTION_PATTERN = Pattern.compile("^(.+)不是挺能装的吗老弟？$");
    private static final Pattern BOT_PATTERN = Pattern.compile("^(.+)人机操作$");
    private static final Pattern POET_PATTERN = Pattern.compile("^(.+)诗人握持$");
    private static final Pattern SPAM_PATTERN = Pattern.compile("^(.+)你也就只会左键摁死了(.+)$");
    private static final Pattern PRACTICE_PATTERN = Pattern.compile("^(.+)菜就多练，小老弟$");

    private static final Logger LOGGER = LogUtils.getLogger();

//    void registerExactMessage(Pattern PATTERN, String originalText, String translatedMessage, ClientChatReceivedEvent event) {
//        if (originalText.contains("作者:@Pugilist_Steve (拳史)")) {
//            event.setMessage(new TextComponent("Author: @Pugilist_Steve"));
//            return;
//        }
//    }

    @SubscribeEvent
    public static void onChatMessage(ClientChatReceivedEvent event) {
        String originalText = event.getMessage().getString();

        if (originalText.contains("作者:@Pugilist_Steve (拳史)")) {
            event.setMessage(new TextComponent("Author: @Pugilist_Steve"));
            return;
        }

        if (originalText.contains("平台:哔哩哔哩 (B站)")) {;
            event.setMessage(new TextComponent("Platform: Bilibili"));
            return;
        }

        if (originalText.contains("如要发布关于此整合包的视频，务必标注原作者及平台!")) {
            event.setMessage(new TextComponent("If you post a video about this mod pack, you must credit the original author and platform!"));
            return;
        }

        if (originalText.contains("Herobrine已诞生新的附身体")) {
            event.setMessage(new TextComponent("Herobrine has possessed a new body."));
            return;
        }

        if (originalText.contains("Herobrine第3号分身已降临")) {
            event.setMessage(new TextComponent("Herobrine's No.3 clone has arrived."));
            return;
        }

        if (originalText.contains("<Steve> 我叫Steve，我是来摧毁Herobrine的")) {
            event.setMessage(new TextComponent("<Steve> My name is Steve, I am here to destroy Herobrine."));
            return;
        }

        if (originalText.contains("<Steve> 我不敢相信我会在这里使用它")) {
            event.setMessage(new TextComponent("<Steve> I can't believe I have to use it here."));
            return;
        }

        if (originalText.contains("<Alex> 可恶！")) {
            event.setMessage(new TextComponent("<Alex> Damn it！"));
            return;
        }

        if (originalText.contains("<Steve> 不！")) {
            event.setMessage(new TextComponent("<Steve> No!"));
            return;
        }

        if (originalText.contains("<蓝恶魔> 你也太好预测了")) {
            event.setMessage(new TextComponent("<Blue Demon> You're too predictable."));
            return;
        }

        if (originalText.contains("<蓝恶魔> 真有意思，不过我很想知道你的动机是什么")) {
            event.setMessage(new TextComponent("<Blue Demon> Interesting, but I really want to know what your motive is."));
            return;
        }

        if (originalText.contains("<蓝恶魔> 不要自大")) {
            event.setMessage(new TextComponent("<Blue Demon> Don’t be arrogant."));
            return;
        }

        if (originalText.contains("<蓝恶魔> 一味地小看我们这些亡灵生物只会突出你到底是有多么的无知")) {
            event.setMessage(new TextComponent("<Blue Demon> Underestimating us undead creatures so blindly only proves how ignorant you truly are."));
            return;
        }

        if (originalText.contains("<灾厄之王> 作为王，可不会死的这么容易")) {
            event.setMessage(new TextComponent("<Pillager King> As a king, I won’t die so easily."));
            return;
        }

        if (originalText.contains("<灾厄之王> 看来，王终究还是大意了……")) {
            event.setMessage(new TextComponent("<Pillager King> It seems that the king was careless after all…"));
            return;
        }

        if (originalText.contains("<Chris> 史蒂夫，对不起了")) {
            event.setMessage(new TextComponent("<Chris> Steve, I'm sorry."));
            return;
        }

        if (originalText.contains("分身已被摧毁，数据已传入终端")) {
            event.setMessage(new TextComponent("The clone has been destroyed, data has been transferred to the terminal."));
            return;
        }

        if (originalText.contains("<蓝恶魔> 三叉戟狂欢节！！！")) {
            event.setMessage(new TextComponent("<Blue Demon> Trident Carnival!!!"));
            return;
        }

        if (originalText.contains("<蓝恶魔> 小看别人只会突出你有多么无知")) {
            event.setMessage(new TextComponent("<Blue Demon> Underestimating others only highlights how ignorant you are."));
            return;
        }

        if (originalText.contains("<蓝恶魔> 终究顶不住这暴乱世界的压力……")) {
            event.setMessage(new TextComponent("<Blue Demon> In the end, you couldn't withstand the pressure of this chaotic world..."));
            return;
        }

        if (originalText.contains("<Herobrine> 该我出手了……")) {
            event.setMessage(new TextComponent("<Herobrine> It's my turn to strike..."));
            return;
        }

        if (originalText.contains("<X_Grave_X> 我们为生存而战，为保护自己而战！！")) {
            event.setMessage(new TextComponent("<X_Grave_X> We fight for survival, we fight to protect ourselves!!"));
            return;
        }

        if (originalText.contains("<X_Grave_X> 走啊，走啊！")) {
            event.setMessage(new TextComponent("<X_Grave_X> Go on, go on!"));
            return;
        }

        if (originalText.contains("<Steve> 为什么我们要继续战斗下去?")) {
            event.setMessage(new TextComponent("<Steve> Why do we have to keep fighting?"));
            return;
        }

        if (originalText.contains("<X_Grave_X> 绝望了吧?")) {
            event.setMessage(new TextComponent("<X_Grave_X> Feeling desperate, huh?"));
            return;
        }

        if (originalText.contains("<Steve> 我会杀了你！")) {
            event.setMessage(new TextComponent("<Steve> I will kill you!"));
            return;
        }

        if (originalText.contains("<X_Grave_X> 还能坚持多少个回合？")) {
            event.setMessage(new TextComponent("<X_Grave_X> How many more rounds can you last?"));
            return;
        }

        if (originalText.contains("<蓝恶魔> 你急了")) {
            event.setMessage(new TextComponent("<Blue Demon> You're getting mad."));
            return;
        }

        if (originalText.contains("<Alex> 呵，手下败将")) {
            event.setMessage(new TextComponent("<Alex> Hah, just another defeated foe."));
            return;
        }

        if (originalText.contains("§4你失败了")) {
            event.setMessage(new TextComponent("§4You died."));
            return;
        }

        if (originalText.contains("§6你未设置重生点，已被随机传送")) {
            event.setMessage(new TextComponent("§6You didn’t set a respawn point and have been randomly teleported."));
            return;
        }

        if (originalText.contains("<村民蓝骑兵> 这是协议的一部分......")) {
            event.setMessage(new TextComponent("<Blue Villager Rider> This is part of the agreement..."));
            return;
        }

        if (originalText.contains("<村民蓝骑兵> 这是协议的一部分......")) {
            event.setMessage(new TextComponent("<Blue Villager Guard> This is part of the agreement..."));
            return;
        }

        if (originalText.contains("<村民侦查兵> Fire！")) {
            event.setMessage(new TextComponent("<Villager Scout> Fire!"));
            return;
        }

        if (originalText.contains("<村民侦察兵> 援军到了！")) {
            event.setMessage(new TextComponent("<Villager Scout> Reinforcements have arrived!"));
            return;
        }

        Matcher matcherJoin = JOIN_PATTERN.matcher(originalText);
        if (matcherJoin.matches()) {
            String playerName = matcherJoin.group(1);
            event.setMessage(new TextComponent(playerName + " has joined the game"));
            return;
        }

        Matcher matcherKill_1 = KILL_PATTERN_1.matcher(originalText);
        if (matcherKill_1.matches()) {
            String entityName = matcherKill_1.group(1);
            event.setMessage(new TextComponent("§eYou killed " + entityName));
            return;
        }

        Matcher matcherKill_2 = KILL_PATTERN_2.matcher(originalText);
        if (matcherKill_2.matches()) {
            String entityName = matcherKill_2.group(1);
            event.setMessage(new TextComponent("§aYou killed " + entityName));
            return;
        }

        Matcher matcherDeath = DEATH_PATTERN.matcher(originalText);
        if (matcherDeath.matches()) {
            String playerName = matcherDeath.group(1);
            String entityName = matcherDeath.group(2);
            event.setMessage(new TextComponent(playerName + " was killed by " + entityName));
            return;
        }

        Matcher matcherDied = DIED_PATTERN.matcher(originalText);
        if (matcherDied.matches()) {
            String playerName = matcherDied.group(1);
            event.setMessage(new TextComponent(playerName + " died"));
            return;
        }

        Matcher matcherInsult = INSULT_PATTERN.matcher(originalText);
        if (matcherInsult.matches()) {
            String entityName = matcherInsult.group(1);
            String icon = matcherInsult.group(2);
            event.setMessage(new TextComponent(entityName + " f*** your mom! Dare to fight fair with proper gear?" + icon));
            return;
        }

        Matcher matcherLeft = LEFT_PATTERN.matcher(originalText);
        if (matcherLeft.matches()) {
            String playerName = matcherLeft.group(1);
            event.setMessage(new TextComponent(playerName + " has left the game"));
            return;
        }

        Matcher matcherRevenge = REVENGE_PATTERN.matcher(originalText);
        if (matcherRevenge.matches()) {
            String entityName = matcherRevenge.group(1);
            String icon = matcherRevenge.group(2);
            event.setMessage(new TextComponent(entityName + ", I'll get you next time"+ icon));
            return;
        }

        Matcher matcherTough = TOUGH_PATTERN.matcher(originalText);
        if (matcherTough.matches()) {
            String firstClause = matcherTough.group(1);
            String icon = matcherTough.group(2);
            event.setMessage(new TextComponent(firstClause + " That was tough" + icon));
            return;
        }

        Matcher matcherSpeechless = SPEECHLESS_PATTERN.matcher(originalText);
        if (matcherSpeechless.matches()) {
            String firstClause = matcherSpeechless.group(1);
            String icon = matcherSpeechless.group(2);
            event.setMessage(new TextComponent(firstClause + " So speechless" + icon));
            return;
        }

        Matcher matcherWeakness = WEAKNESS_TAUNT_PATTERN.matcher(originalText);
        if (matcherWeakness.matches()) {
            String firstClause = matcherWeakness.group(1);
            event.setMessage(new TextComponent(firstClause + ", Don't act tough if you're weak"));
            return;
        }

        Matcher matcherSoWeak = SO_WEAK_PATTERN.matcher(originalText);
        if (matcherSoWeak.matches()) {
            String firstClause = matcherSoWeak.group(1);
            event.setMessage(new TextComponent(firstClause + " So weak!"));
            return;
        }

        Matcher matcherReally = REALLY_PATTERN.matcher(originalText);
        if (matcherReally.matches()) {
            String entityName = matcherReally.group(1);
            event.setMessage(new TextComponent(entityName + " Oh really?"));
            return;
        }

        Matcher matcherDeadThreat = DEATH_THREAT_PATTERN.matcher(originalText);
        if (matcherDeadThreat.matches()) {
            String firstClause = matcherDeadThreat.group(1);
            event.setMessage(new TextComponent(firstClause + " Anyone in my way must die"));
            return;
        }

        Matcher matcherGiveUp = GIVEUP_PATTERN.matcher(originalText);
        if (matcherGiveUp.matches()) {
            String firstClause = matcherGiveUp.group(1);
            event.setMessage(new TextComponent(firstClause + " You won"));
            return;
        }

        Matcher matcherWeak = WEAK_PATTERN.matcher(originalText);
        if (matcherWeak.matches()) {
            String firstClause = matcherWeak.group(1);
            event.setMessage(new TextComponent(firstClause + " You are too weak"));
            return;
        }

        Matcher matcherDoom = DOOM_PATTERN.matcher(originalText);
        if (matcherDoom.matches()) {
            String firstClause = matcherDoom.group(1);
            event.setMessage(new TextComponent(firstClause + " You're doomed!"));
            return;
        }

        Matcher matcherDoom_2 = DOOM_PATTERN_2.matcher(originalText);
        if (matcherDoom.matches()) {
            String firstClause = matcherDoom_2.group(1);
            event.setMessage(new TextComponent(firstClause + " You're doomed!"));
            return;
        }

        Matcher matcherRevengeWait = REVENGE_WAIT_PATTERN.matcher(originalText);
        if (matcherRevengeWait.matches()) {
            String firstClause = matcherRevengeWait.group(1);
            String icon = matcherRevengeWait.group(2);
            event.setMessage(new TextComponent(firstClause + " You must wait!" + icon));
            return;
        }

        Matcher matcherRevengeReturn = REVENGE_RETURN_PATTERN.matcher(originalText);
        if (matcherRevengeReturn.matches()) {
            String firstClause = matcherRevengeReturn.group(1);
            event.setMessage(new TextComponent(firstClause + " I will return and become stronger!"));
            return;
        }

        Matcher matcherDestruction = DESTRUCTION_PATTERN.matcher(originalText);
        if (matcherDestruction.matches()) {
            String firstClause = matcherDestruction.group(1);
            event.setMessage(new TextComponent(firstClause + " I will flatten this place"));
            return;
        }

        Matcher matcherRememberMyWords = REMEMBER_MY_WORDS_PATTERN.matcher(originalText);
        if (matcherRememberMyWords.matches()) {
            String firstClause = matcherRememberMyWords.group(1);
            event.setMessage(new TextComponent(firstClause + " Remember my words!"));
            return;
        }

        Matcher matcherArrivedBoss = ARRIVED_BOSS_PATTERN.matcher(originalText);
        if (matcherArrivedBoss.matches()) {
            String firstClause = matcherArrivedBoss.group(1);
            event.setMessage(new TextComponent(firstClause + " has arrived"));
            return;
        }

        Matcher matcherCloneDead = CLONE_DEAD_PATTERN.matcher(originalText);
        if (matcherCloneDead.matches()) {
            String firstClause = matcherCloneDead.group(1);
            event.setMessage(new TextComponent(firstClause + " The clone has been destroyed, data has been transferred to the terminal."));
            return;
        }

        Matcher matcherDetroyed = DESTROYED_PATTERN.matcher(originalText);
        if (matcherDetroyed.matches()) {
            String firstClause = matcherDetroyed.group(1);
            event.setMessage(new TextComponent(firstClause + " has been destroyed"));
            return;
        }

        Matcher matcherKilledHappy = KILLED_HAPPY_PATTERN.matcher(originalText);
        if (matcherKilledHappy.matches()) {
            String firstClause = matcherKilledHappy.group(1);
            String icon = matcherKilledHappy.group(2);
            event.setMessage(new TextComponent(firstClause + ", you just killed me like that. Are you happy now?" + icon));
            return;
        }

        Matcher matcherKilledOPWeapon = KILLED_OP_WEAPON_PATTERN.matcher(originalText);
        if (matcherKilledOPWeapon.matches()) {
            String firstClause = matcherKilledOPWeapon.group(1);
            String icon = matcherKilledOPWeapon.group(2);
            event.setMessage(new TextComponent(firstClause + ", using your OP weapon—is that fun for you?" + icon));
            return;
        }

        Matcher matcherDesperate = DESPERATE_PATTERN.matcher(originalText);
        if (matcherDesperate.matches()) {
            String firstClause = matcherDesperate.group(1);
            event.setMessage(new TextComponent(firstClause + " Desperate now?"));
            return;
        }

        Matcher matcherTryAgain = TRY_AGAIN_PATTERN.matcher(originalText);
        if (matcherTryAgain.matches()) {
            String firstClause = matcherTryAgain.group(1);
            event.setMessage(new TextComponent(firstClause + " Want to try again?"));
            return;
        }

        Matcher matcherKidding = KIDDING_PATTERN.matcher(originalText);
        if (matcherKidding.matches()) {
            String firstClause = matcherKidding.group(1);
            event.setMessage(new TextComponent(firstClause + " Can't handle it? Getting mad? Haha."));
            return;
        }

        Matcher matcherIsThatAll = IS_THAT_ALL_PATTERN.matcher(originalText);
        if (matcherIsThatAll.matches()) {
            String firstClause = matcherIsThatAll.group(1);
            event.setMessage(new TextComponent(firstClause + " Is that all?"));
            return;
        }

        Matcher matcherFCK = FCK_PATTERN.matcher(originalText);
        if (matcherFCK.matches()) {
            String firstClause = matcherFCK.group(1);
            event.setMessage(new TextComponent(firstClause + ", f** you"));
            return;
        }

        Matcher matcherTakeRevenge = TAKE_REVENGE_PATTERN.matcher(originalText);
        if (matcherTakeRevenge.matches()) {
            String firstClause = matcherTakeRevenge.group(1);
            String icon = matcherTakeRevenge.group(2);
            event.setMessage(new TextComponent(firstClause + ", I will take revenge on you soon" + icon));
            return;
        }

        Matcher matcherAllThatGot = ALL_THAT_GOT_PATTERN.matcher(originalText);
        if (matcherAllThatGot.matches()) {
            String firstClause = matcherAllThatGot.group(1);
            event.setMessage(new TextComponent(firstClause + ", is that all you've got?"));
            return;
        }

        Matcher matcherBreakingDown = BREAKING_DOWN_PATTERN.matcher(originalText);
        if (matcherBreakingDown.matches()) {
            String firstClause = matcherBreakingDown.group(1);
            String icon = matcherBreakingDown.group(2);
            event.setMessage(new TextComponent(firstClause + " I'm breaking down" + icon));
            return;
        }

        Matcher matcherThisIsBoss = THIS_IS_BOSS_PATTERN.matcher(originalText);
        if (matcherThisIsBoss.matches()) {
            String firstClause = matcherThisIsBoss.group(1);
            event.setMessage(new TextComponent(firstClause + " This guy is a boss"));
            return;
        }

        Matcher matcherMad = MAD_PATTERN.matcher(originalText);
        if (matcherMad.matches()) {
            String firstClause = matcherMad.group(1);
            event.setMessage(new TextComponent(firstClause + " Haha, you're getting mad"));
            return;
        }

        Matcher matcherWelcome = WELCOME_PATTERN.matcher(originalText);
        if (matcherWelcome.matches()) {
            String firstClause = matcherWelcome.group(1);
            event.setMessage(new TextComponent(firstClause + " Welcome!"));
            return;
        }

        Matcher matcherLostGear = LOST_GEAR_PATTERN.matcher(originalText);
        if (matcherLostGear.matches()) {
            String firstClause = matcherLostGear.group(1);
            String icon = matcherLostGear.group(2);
            event.setMessage(new TextComponent(firstClause + " Played as Bull Demon, lost all my gear." + icon));
            return;
        }

        Matcher matcherAreUHacking = ARE_U_HACKING_PATTERN.matcher(originalText);
        if (matcherAreUHacking.matches()) {
            String firstClause = matcherAreUHacking.group(1);
            String icon = matcherAreUHacking.group(2);
            event.setMessage(new TextComponent(firstClause + " bro, be honest, are you hacking? " + icon));
            return;
        }

        Matcher matcherTakeDown = TAKEDOWN_PATTERN.matcher(originalText);
        if (matcherTakeDown.matches()) {
            String firstClause = matcherTakeDown.group(1);
            String name = matcherTakeDown.group(2);
            event.setMessage(new TextComponent(firstClause + " Just take down " + name + ", what's there to talk about?"));
            return;
        }

        Matcher matcherDog = DOG_PATTERN.matcher(originalText);
        if (matcherDog.matches()) {
            String firstClause = matcherDog.group(1);
            String icon = matcherDog.group(2);
            event.setMessage(new TextComponent(firstClause + ", you are really a " + icon));
            return;
        }

        Matcher matcherWhyStop = WHY_STOP_PATTERN.matcher(originalText);
        if (matcherWhyStop.matches()) {
            String firstClause = matcherWhyStop.group(1);
            event.setMessage(new TextComponent(firstClause + ", keep going, why did you stop?"));
            return;
        }

        Matcher matcherNotSerious = NOT_SERIOUS_PATTERN.matcher(originalText);
        if (matcherNotSerious.matches()) {
            String firstClause = matcherNotSerious.group(1);
            event.setMessage(new TextComponent(firstClause + " I haven’t even gotten serious yet."));
            return;
        }

        Matcher matcherDontLeave = DONT_LEAVE_PATTERN.matcher(originalText);
        if (matcherDontLeave.matches()) {
            String firstClause = matcherDontLeave.group(1);
            String icon = matcherDontLeave.group(2);
            event.setMessage(new TextComponent(firstClause + " Don’t leave, I’m calling some people " + icon));
            return;
        }

        Matcher matcherHaha = HAHA_PATTERN.matcher(originalText);
        if (matcherHaha.matches()) {
            String firstClause = matcherHaha.group(1);
            String icon = matcherHaha.group(2);
            String name = matcherHaha.group(3);
            event.setMessage(new TextComponent(firstClause + " Hahaha " + icon + " so funny, " + name + " is lying on the bed, rhythmically chanting Garen~ Fa Fa."));
            return;
        }

        Matcher matcherComing = COMING_PATTERN.matcher(originalText);
        if (matcherComing.matches()) {
            String firstClause = matcherComing.group(1);
            String icon = matcherComing.group(2);
            event.setMessage(new TextComponent(firstClause + " I'm coming, bro " + icon));
            return;
        }

        Matcher matcherOver = OVER_PATTERN.matcher(originalText);
        if (matcherOver.matches()) {
            String firstClause = matcherOver.group(1);
            event.setMessage(new TextComponent(firstClause + " It's over"));
            return;
        }

        Matcher matcherLater = GET_LATTER_PATTERN.matcher(originalText);
        if (matcherLater.matches()) {
            String firstClause = matcherLater.group(1);
            String icon = matcherLater.group(2);
            event.setMessage(new TextComponent(firstClause + " I'll get you later " + icon));
            return;
        }

        Matcher matcherMom = MOM_PATTERN.matcher(originalText);
        if (matcherMom.matches()) {
            String firstClause = matcherMom.group(1);
            event.setMessage(new TextComponent(firstClause + " , your mom is dead. F** your mom"));
            return;
        }

        Matcher matcherSureDead = DEAD_SURE_PATTERN.matcher(originalText);
        if (matcherSureDead.matches()) {
            String firstClause = matcherSureDead.group(1);
            event.setMessage(new TextComponent(firstClause + ", you're dead for sure"));
            return;
        }

        Matcher matcherIdiot = IDIOT_PATTERN.matcher(originalText);
        if (matcherIdiot.matches()) {
            String firstClause = matcherIdiot.group(1);
            String icon = matcherIdiot.group(2);
            event.setMessage(new TextComponent(firstClause + " Idiot, f** your mom" + icon));
            return;
        }

        Matcher matcherEnough = ENOUGH_PATTERN.matcher(originalText);
        if (matcherEnough.matches()) {
            String firstClause = matcherEnough.group(1);
            event.setMessage(new TextComponent(firstClause + " Enough!"));
            return;
        }

        Matcher matcherPin = PIN_GROUND_PATTERN.matcher(originalText);
        if (matcherPin.matches()) {
            String firstClause = matcherPin.group(1);
            event.setMessage(new TextComponent(firstClause + " I pinned you to the ground and beat you up, haha."));
            return;
        }

        Matcher matcherAmbushing = AMBUSHING_PATTERN.matcher(originalText);
        if (matcherAmbushing.matches()) {
            String firstClause = matcherAmbushing.group(1);
            String icon = matcherAmbushing.group(2);
            event.setMessage(new TextComponent(firstClause + " , ambushing an ordinary player like me—is this okay? No, it's not " + icon));
            return;
        }

        Matcher matcherRemember = REMEMBER_PATTERN.matcher(originalText);
        if (matcherRemember.matches()) {
            String firstClause = matcherRemember.group(1);
            String icon = matcherRemember.group(2);
            event.setMessage(new TextComponent(firstClause + ", bro, I’ll remember you " + icon));
            return;
        }

        Matcher matcherKillLaugh = KILL_LAUGH_PATTERN.matcher(originalText);
        if (matcherKillLaugh.matches()) {
            String firstClause = matcherKillLaugh.group(1);
            String icon = matcherKillLaugh.group(2);
            event.setMessage(new TextComponent(firstClause + ", you’re killing me with laughter—such a pure " + icon));
            return;
        }

        Matcher matcherReallyGiveUp = REALLY_GIVEUP_PATTERN.matcher(originalText);
        if (matcherReallyGiveUp.matches()) {
            String firstClause = matcherReallyGiveUp.group(1);
            String icon = matcherReallyGiveUp.group(2);
            event.setMessage(new TextComponent(firstClause + " I really give up" + icon));
            return;
        }

        Matcher matcherDeathPoint = DEATH_POINT.matcher(originalText);
        if (matcherDeathPoint.matches()) {
            String endClause = matcherDeathPoint.group(1);
            event.setMessage(new TextComponent("Death coordinates: " + endClause));
            return;
        }

        Matcher matcherToughQuestion = TOUGH_QUESTION_PATTERN.matcher(originalText);
        if (matcherToughQuestion.matches()) {
            String firstClause = matcherToughQuestion.group(1);
            event.setMessage(new TextComponent(firstClause + ", weren’t you acting all tough, bro?"));
            return;
        }

        Matcher matcherBot = BOT_PATTERN.matcher(originalText);
        if (matcherBot.matches()) {
            String firstClause = matcherBot.group(1);
            event.setMessage(new TextComponent(firstClause + " plays like a bot"));
            return;
        }

        Matcher matcherPoet = POET_PATTERN.matcher(originalText);
        if (matcherPoet.matches()) {
            String firstClause = matcherPoet.group(1);
            event.setMessage(new TextComponent(firstClause + " Poet's grip"));
            return;
        }

        Matcher matcherSpam = SPAM_PATTERN.matcher(originalText);
        if (matcherSpam.matches()) {
            String firstClause = matcherSpam.group(1);
            String icon = matcherSpam.group(2);
            event.setMessage(new TextComponent(firstClause + " All you can do is spam left-click to death" + icon));
            return;
        }

        Matcher matcherPractice = PRACTICE_PATTERN.matcher(originalText);
        if (matcherPractice.matches()) {
            String firstClause = matcherPractice.group(1);
            event.setMessage(new TextComponent(firstClause + ", if you're bad, just practice more, little bro"));
            return;
        }

        LOGGER.info("PlaChatEdit: unidentified message: {}", new String(originalText.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
    }
}
