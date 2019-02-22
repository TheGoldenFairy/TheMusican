package TheMusician.Patches;

import TheMusician.Relics.MusicalDestiny;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import javassist.CtBehavior;

@SpirePatch(
        clz = CombatRewardScreen.class,
        method = "setupItemReward"
)
public class NewCombatRewards {
    @SpireInsertPatch(locator = Locator.class,
            localvars = {"cardReward"})

    public static void Insert(CombatRewardScreen _instance, RewardItem cardReward) {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom && AbstractDungeon.player.hasRelic(MusicalDestiny.ID) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            cardReward = new RewardItem();
            if (cardReward.cards.size() > 0) {
                _instance.rewards.add(cardReward);
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethod) throws Exception {
            Matcher matcher = new Matcher.NewExprMatcher(RewardItem.class);
            int[] found = LineFinder.findAllInOrder(ctMethod, matcher);
            return new int[] {found[2]};
        }
    }
}
