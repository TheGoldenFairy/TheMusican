package TheMusician.Encounters;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

import static TheMusician.MusicianMod.*;

public class MusicalLover extends AbstractMonster {

    //~~~~~~~~~~~~~~~~~~ Monsters Info ~~~~~~~~~~~~~~~~~~//
    private static final String ID = makeID("MusicalLover");
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    private static final String[] MOVES;
    private static final String[] DIALOG;
    private static final String IMG = makeEnemyPath("Musicallover/skeleton.png");
    private static final int HP_MIN = 20;
    private static final int A_4_HP_MIN = 24;
    private static final int OPENEAR_BLOCK = 5;
    private static final int ENCOURAGEMENT_STRENGTH = 1;
    private static final byte OPENEAR = 1;
    private static final byte ENCOURAGEMENT = 2;
    private int blockAmt;
    private boolean firstMove;


    //~~~~~~~~~~~~~~~~~~ Initialize Monster ~~~~~~~~~~~~~~~~~~//
    public MusicalLover(final float x, final float y) {
        super(NAME, ID, HP_MIN, 0.0f, 0.0f, 175.0f, 250.0f, IMG, x, y);
        firstMove = true;
        dialogY = 60.0f * Settings.scale;
        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(HP_MIN + 2, A_4_HP_MIN + 2);
            blockAmt = OPENEAR_BLOCK + 1;
        }
        else {
            setHp(HP_MIN, A_4_HP_MIN);
            blockAmt = OPENEAR_BLOCK;
        }
    }


    //~~~~~~~~~~~~~~~~~~ Take Turn ~~~~~~~~~~~~~~~~~~//
    @Override
    public void takeTurn() {
        switch (nextMove) {
            case OPENEAR: {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, blockAmt));
                break;
            }
            case ENCOURAGEMENT: {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this, new StrengthPower(mo, ENCOURAGEMENT_STRENGTH), ENCOURAGEMENT_STRENGTH));
                }
                break;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }


    //~~~~~~~~~~~~~~~~~~ Get Move ~~~~~~~~~~~~~~~~~~//
    @Override
    protected void getMove(int num) {
        if (firstMove) {
            firstMove = false;
            setMove((byte)1, Intent.DEFEND);
            return;
        }
        if (num < 50) {
            if (lastMove((byte)1)) {
                if (AbstractDungeon.aiRng.randomBoolean(0.80f)) {
                    setMove(MusicalLover.MOVES[0], (byte)2, Intent.BUFF);
                }
                else {
                    setMove(MusicalLover.MOVES[1], (byte)1, Intent.DEFEND);
                }
            }
            else {
                setMove(MusicalLover.MOVES[1], (byte)1, Intent.DEFEND);
            }
        }
        else {
            if (lastMove((byte)2)) {
                if (AbstractDungeon.aiRng.randomBoolean(0.80f)) {
                    setMove(MusicalLover.MOVES[1], (byte)1, Intent.DEFEND);
                }
                else {
                    setMove(MusicalLover.MOVES[0], (byte)2, Intent.BUFF);
                }
            }
            else {
                this.setMove(MusicalLover.MOVES[0], (byte)2, Intent.BUFF);
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~ Die ~~~~~~~~~~~~~~~~~~//
    @Override
    public void die() {
        super.die();
    }

    @Override
    public void deathReact() {
        AbstractDungeon.effectList.add(new SpeechBubble(dialogX, dialogY, 3.0f, MusicalLover.DIALOG[3], false));
    }


    //~~~~~~~~~~~~~~~~~~ Static Stuff ~~~~~~~~~~~~~~~~~~//
    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TheMusician:MusicalLover");
        NAME = MusicalLover.monsterStrings.NAME;
        MOVES = MusicalLover.monsterStrings.MOVES;
        DIALOG = MusicalLover.monsterStrings.DIALOG;
    }
}
