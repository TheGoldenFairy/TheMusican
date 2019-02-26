package TheMusician.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

public class MusicalDestinyAction extends AbstractGameAction {

    private static final String tipMSG = "Card to be Purged.";
    private static final int NumOfCards = 1;

    //~~~~~~~~~~~~~~~~~~ Worthless Fucking Constructor ~~~~~~~~~~~~~~~~~~//
    public MusicalDestinyAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    //~~~~~~~~~~~~~~~~~~ Uses of the Action ~~~~~~~~~~~~~~~~~~//
    @Override
    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), NumOfCards, tipMSG, false, false, false, true);
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
            tickDuration();
        }
        else {
            if ((!AbstractDungeon.isScreenUp) && (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                    if (card == c) {
                        AbstractDungeon.player.drawPile.removeCard(c);
                    }
                }
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card == c) {
                        AbstractDungeon.player.hand.removeCard(c);
                    }
                }
                AbstractDungeon.player.masterDeck.removeCard(c);
                AbstractDungeon.effectList.add(new PurgeCardEffect(c));
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            tickDuration();
        }
    }
}
