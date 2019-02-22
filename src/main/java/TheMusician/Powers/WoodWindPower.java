package TheMusician.Powers;

import TheMusician.MusicianMod;
import TheMusician.Util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static TheMusician.MusicianMod.makePowerPath;

public class WoodWindPower extends AbstractPower {

    //~~~~~~~~~~~~~~~~~~ Power's Information ~~~~~~~~~~~~~~~~~~//
    private static final String POWER_ID = MusicianMod.makeID("WoodwindPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Beta84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Beta32.png"));


    //~~~~~~~~~~~~~~~~~~ Variables to be used ~~~~~~~~~~~~~~~~~~//
    private AbstractCreature source;


    //~~~~~~~~~~~~~~~~~~ Constructor ~~~~~~~~~~~~~~~~~~//
    public WoodWindPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.canGoNegative = true;

        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }

    //~~~~~~~~~~~~~~~~~~ Stack Amount ~~~~~~~~~~~~~~~~~~//
    public void stackPower(int stackAmount)  {
        fontScale = 8.0F;
        amount += stackAmount;
        if (amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (amount >= 999) {
            amount = 999;
        }
    }

    //~~~~~~~~~~~~~~~~~~ Update Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
