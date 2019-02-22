package TheMusician.Relics;

import TheMusician.Powers.BassPower;
import TheMusician.Powers.PercussionPower;
import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static TheMusician.MusicianMod.*;

public class MusicalInstrument extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalInstrument");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//
    private AbstractPlayer player = AbstractDungeon.player;
    private static final int POWER_AMT = 1;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalInstrument() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new BassPower(player, player, POWER_AMT), POWER_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new PercussionPower(player, player, POWER_AMT), POWER_AMT));
    }

    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
