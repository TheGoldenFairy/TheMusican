package TheMusician.Relics;

import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import static TheMusician.MusicianMod.*;

public class MusicalDestiny extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalDestiny");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Relic Variables ~~~~~~~~~~~~~~~~~~//
    private static final String tipMSG = "Card to be Purged.";
    private static final int NumOfCards = 1;
    private boolean purgeResult = false;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalDestiny() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
        purgeLogic();
    }


    //~~~~~~~~~~~~~~~~~~ Relic Use's ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).size() > 0) {
            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), NumOfCards, tipMSG, false, false, false, true);
            purgeResult = true;
        }
    }

    @Override
    public void update() {
        purgeLogic();
    }

    private void purgeLogic() {
        if ((purgeResult) && (!AbstractDungeon.isScreenUp) && (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if(card == c) {
                    AbstractDungeon.player.drawPile.removeCard(c);
                }
            }
            AbstractDungeon.player.masterDeck.removeCard(c);
            AbstractDungeon.effectList.add(new PurgeCardEffect(c));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            purgeResult = false;
        }
    }


    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
