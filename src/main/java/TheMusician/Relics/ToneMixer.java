package TheMusician.Relics;

import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static TheMusician.MusicianMod.*;

public class ToneMixer extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("ToneMixer");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public ToneMixer() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(targetCard.type == AbstractCard.CardType.ATTACK){
            if(targetCard.rarity == AbstractCard.CardRarity.BASIC){
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("BasicAttack"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.COMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("CommonAttack"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.UNCOMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("UncommonAttack"), 0.1f));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("RareAttack"), 0.1f));
            }
        }
        else if (targetCard.type == AbstractCard.CardType.SKILL) {
            if(targetCard.rarity == AbstractCard.CardRarity.BASIC){
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("BasicSkill"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.COMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("CommonSkill"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.UNCOMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("UncommonSkill"), 0.1f));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("RareSkill"), 0.1f));
            }
        }
        else if(targetCard.type == AbstractCard.CardType.POWER){
            if(targetCard.rarity == AbstractCard.CardRarity.BASIC){
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("BasicPower"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.COMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("CommonPower"), 0.1f));
            }
            else if(targetCard.rarity == AbstractCard.CardRarity.UNCOMMON) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("UncommonPower"), 0.1f));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("RarePower"), 0.1f));
            }
        }
        else if(targetCard.type == AbstractCard.CardType.CURSE) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("Curses"), 0.1f));
        }
        else if(targetCard.type == AbstractCard.CardType.STATUS) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("Status"), 0.1f));
        }
    }

    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
