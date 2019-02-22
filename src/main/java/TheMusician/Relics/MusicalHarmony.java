package TheMusician.Relics;

import TheMusician.Encounters.MusicalLover;
import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

import java.lang.reflect.Type;

import static TheMusician.MusicianMod.*;

public class MusicalHarmony extends CustomRelic implements ClickableRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalHarmony");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//
    private boolean HASBEENCLICKED = false;
    private int NUMBEROFENEMIES = 0;



    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalHarmony() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void onRightClick() {
        if (!isObtained) {
            return;
        }
        else {
            if(!HASBEENCLICKED) {
                flash();
                HASBEENCLICKED = true;
            }
        }
    }

    @Override
    public void atBattleStart() {
        if(HASBEENCLICKED && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            NUMBEROFENEMIES = 0;
            float offsetX = 0;
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                offsetX -= 250F;
                NUMBEROFENEMIES++;
            }
            if (NUMBEROFENEMIES <= 2) {
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new MusicalLover(offsetX - 100F, 15.0f), false));
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new MusicalLover(offsetX - 350F, 15.0f), false));
            }
            else {
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new MusicalLover(offsetX - 100F, 15.0f), false));
            }
        }
    }


    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

}
