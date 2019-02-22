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

public class MusicalHarmony extends CustomRelic {

    //~~~~~~~~~~~~~~~~~~ Relic Information ~~~~~~~~~~~~~~~~~~//
    public static final String ID = makeID("MusicalHarmony");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Beta.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Beta.png"));


    //~~~~~~~~~~~~~~~~~~ Variables  to be used ~~~~~~~~~~~~~~~~~~//
    private int NUMBEROFENEMIES = 0;


    //~~~~~~~~~~~~~~~~~~ Initialize Relic ~~~~~~~~~~~~~~~~~~//
    public MusicalHarmony() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.SOLID);
    }


    //~~~~~~~~~~~~~~~~~~ Relic Uses ~~~~~~~~~~~~~~~~~~//
    @Override
    public void atBattleStart() {
        if(!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
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
