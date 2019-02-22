package TheMusician.Relics;

import TheMusician.Powers.BassPower;
import TheMusician.Powers.PercussionPower;
import TheMusician.Powers.StringsPower;
import TheMusician.Powers.WoodWindPower;
import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static TheMusician.MusicianMod.*;

public class MusicalAward extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalAward");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//
    private AbstractPlayer player = AbstractDungeon.player;
    private static final String RELIC_ID = "TheMusician:MusicalInstrument";
    private static final int POWER_AMT = 1;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalAward() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new BassPower(player, player, POWER_AMT), POWER_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new StringsPower(player, player, POWER_AMT), POWER_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new WoodWindPower(player, player, POWER_AMT), POWER_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new PercussionPower(player, player, POWER_AMT), POWER_AMT));
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(RELIC_ID);
    }

    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
