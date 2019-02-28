package TheMusician.Relics;

import TheMusician.Encounters.MusicalLover;
import TheMusician.Util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;


import static TheMusician.MusicianMod.*;

public class MusicalCrowd extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalCrowd");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//
    private float previousMhb_x = 99999;
    private float offsetX;
    private int NUMOFMONSTERS;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalCrowd() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        if(!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            NUMOFMONSTERS = 0;
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if(m.hb_x < previousMhb_x) {
                    previousMhb_x = m.hb_x;
                }
                NUMOFMONSTERS++;
            }
            if(NUMOFMONSTERS < 4) {
                offsetX = previousMhb_x - 200;
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new MusicalLover(offsetX - 100F, 15.0f), false));
            }
        }
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }


    //~~~~~~~~~~~~~~~~~~ Description ~~~~~~~~~~~~~~~~~~//
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
