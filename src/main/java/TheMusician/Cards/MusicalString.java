package TheMusician.Cards;

import TheMusician.Characters.TheMusician;
import TheMusician.MusicianMod;
import TheMusician.Patches.CustomTags;
import TheMusician.Powers.BrassPower;
import TheMusician.Powers.StringsPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MusicalString extends CustomCard {


    //~~~~~~~~~~~~~~~~~~ Getting All the Card's Information ~~~~~~~~~~~~~~~~~~//
    public static final String CARD_ID = MusicianMod.makeID("MusicalString");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CARD_ID);
    public static final String IMG = MusicianMod.makeCardPath("skills/Beta.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //~~~~~~~~~~~~~~~~~~ Getting The Card Aspects ~~~~~~~~~~~~~~~~~~//
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheMusician.Enums.COLOR_GOLD;


    //~~~~~~~~~~~~~~~~~~ Getting the Cards Numbers ~~~~~~~~~~~~~~~~~~//
    private static final int COST = 0;
    private static final int STRING_AMT = 1;
    private static final int UPGRADE_PLUS_STRING_AMT = 1;
    private static final int BASS_AMT = -1;


    //~~~~~~~~~~~~~~~~~~ Initializing the Card ~~~~~~~~~~~~~~~~~~//
    public MusicalString() {
        super(CARD_ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = STRING_AMT;
        exhaust = true;
        this.tags.add(CustomTags.MUSICAL);
    }

    //~~~~~~~~~~~~~~~~~~ Uses of the Card ~~~~~~~~~~~~~~~~~~//
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StringsPower(p, STRING_AMT), STRING_AMT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BrassPower(p, BASS_AMT), BASS_AMT));
    }

    //~~~~~~~~~~~~~~~~~~ Upgraded Card ~~~~~~~~~~~~~~~~~~
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STRING_AMT);
            initializeDescription();
        }
    }
}
