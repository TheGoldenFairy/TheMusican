package TheMusician.Relics;

import TheMusician.Actions.MusicalDestinyAction;
import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static TheMusician.MusicianMod.*;

public class MusicalDestiny extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalDestiny");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables to be used ~~~~~~~~~~~~~~~~~~//
    private static final int NumOfCards = 1;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalDestiny() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Use's ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new MusicalDestinyAction(NumOfCards));
    }


    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
