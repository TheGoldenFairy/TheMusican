package TheMusician.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static TheMusician.Patches.CustomTags.BRASS;
import static TheMusician.Patches.CustomTags.STRING;

public class CornetAction extends AbstractGameAction {

    //~~~~~~~~~~~~~~~~~~ Action Variables ~~~~~~~~~~~~~~~~~~//
    private static final int ENERGY_GAIN = 1;
    private static final int BRASS_DAMAGE_DEAL = 3;
    private AbstractCreature p;


    //~~~~~~~~~~~~~~~~~~ Constructor ~~~~~~~~~~~~~~~~~~//
    public CornetAction(AbstractCreature p) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = p;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            isDone = true;
        }
        else {
            AbstractCard TopCard = AbstractDungeon.player.drawPile.getTopCard();
            if (TopCard.hasTag(BRASS)) {
                AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(p, BRASS_DAMAGE_DEAL), AttackEffect.SLASH_HORIZONTAL));
            }
            if (TopCard.hasTag(STRING)) {
                TopCard.costForTurn = TopCard.cost + ENERGY_GAIN;
            }
            isDone = true;
        }
    }
}
