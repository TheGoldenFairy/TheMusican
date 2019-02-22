package TheMusician.Cards;

import TheMusician.Patches.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//Class Made and Taken From the Default. Creator Grem.
public abstract class AbstractMusicianCard extends CustomCard {

    public int MusicianSecondMagicNumber;
    public int MusicianBaseSecondMagicNumber;
    public boolean upgradedMusicianSecondMagicNumber;
    public boolean isMusicianSecondMagicNumberModified;
    private static final String BRASS_POWER = "TheMusician:BrassPower";
    private static final String STRING_POWER = "TheMusician:StringPower";
    private static final String WOODWIND_POWER = "TheMusician:WoodWindPower";
    private static final String PERCUSSION_POWER = "TheMusician:PercussionPower";


    public AbstractMusicianCard(final String id, final String name, final String img, final int cost, final String rawDescription,
                               final CardType type, final CardColor color,
                               final CardRarity rarity, final CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);


        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isMusicianSecondMagicNumberModified = false;
    }

    public void displayUpgrades() {

        if (upgradedMusicianSecondMagicNumber) {
            MusicianSecondMagicNumber = MusicianBaseSecondMagicNumber;
            isMusicianSecondMagicNumberModified = true;
        }

    }

    public void upgradeMusicianSecondMagicNumber(int amount) {
        MusicianBaseSecondMagicNumber += amount;
        MusicianSecondMagicNumber = MusicianBaseSecondMagicNumber;
        upgradedMusicianSecondMagicNumber = true;
    }

    @Override
    public void applyPowers() {
        int tmp1 = baseDamage;
        int tmp2 = baseBlock;
        int tmp3 = baseMagicNumber;
        if(this.hasTag(CustomTags.BRASS) &&  AbstractDungeon.player.hasPower(BRASS_POWER)) {
            baseDamage += AbstractDungeon.player.getPower(BRASS_POWER).amount;
        }
        if(this.hasTag(CustomTags.STRING) &&  AbstractDungeon.player.hasPower(STRING_POWER)) {
            baseBlock += AbstractDungeon.player.getPower(STRING_POWER).amount;
        }
        if(this.hasTag(CustomTags.PERCUSSION) &&  AbstractDungeon.player.hasPower(PERCUSSION_POWER)) {
            baseMagicNumber += AbstractDungeon.player.getPower(PERCUSSION_POWER).amount;
        }
        if(this.hasTag(CustomTags.WOODWIND) &&  AbstractDungeon.player.hasPower(WOODWIND_POWER)) {
            baseMagicNumber += AbstractDungeon.player.getPower(WOODWIND_POWER).amount;
        }
        super.applyPowers();
        baseDamage = tmp1;
        baseBlock = tmp2;
        baseMagicNumber = tmp3;
        isDamageModified = baseDamage != damage;
        isBlockModified = baseBlock != block;
        isMagicNumberModified = baseMagicNumber != magicNumber;
        isMusicianSecondMagicNumberModified = MusicianBaseSecondMagicNumber != MusicianSecondMagicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp1 = baseDamage;
        if (this.hasTag(CustomTags.BRASS) && AbstractDungeon.player.hasPower(BRASS_POWER)) {
            baseDamage += AbstractDungeon.player.getPower(BRASS_POWER).amount;
        }
        super.calculateCardDamage(mo);
        baseDamage = tmp1;
        isDamageModified = baseDamage != damage;
    }
}
